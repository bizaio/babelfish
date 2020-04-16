package io.biza.babelfish.spring.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.security.Security;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.PostConstruct;

import org.apache.commons.collections.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.RSAEncrypter;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.crypto.bc.BouncyCastleProviderSingleton;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKMatcher;
import com.nimbusds.jose.jwk.JWKMatcher.Builder;
import com.nimbusds.jose.jwk.JWKSelector;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import com.nimbusds.jwt.SignedJWT;
import io.biza.babelfish.oidc.enumerations.JWEEncryptionAlgorithmType;
import io.biza.babelfish.oidc.enumerations.JWEEncryptionEncodingType;
import io.biza.babelfish.oidc.enumerations.JWKPublicKeyUse;
import io.biza.babelfish.oidc.enumerations.JWSSigningAlgorithmType;
import io.biza.babelfish.oidc.payloads.JWKS;
import io.biza.babelfish.oidc.payloads.JWTClaims;
import io.biza.babelfish.spring.enumerations.JWTPeekAttribute;
import io.biza.babelfish.spring.exceptions.EncryptionOperationException;
import io.biza.babelfish.spring.exceptions.KeyRetrievalException;
import io.biza.babelfish.spring.exceptions.NotInitialisedException;
import io.biza.babelfish.spring.exceptions.SigningOperationException;
import io.biza.babelfish.spring.exceptions.SigningVerificationException;
import io.biza.babelfish.spring.interfaces.JWKService;
import io.biza.babelfish.spring.interfaces.OldJWKService;
import io.biza.babelfish.spring.util.KeyUtil;
import io.biza.babelfish.spring.util.MessageUtil;
import io.biza.babelfish.spring.util.NimbusUtil;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONStyle;

@Slf4j
@Service
@ConditionalOnProperty(name = "babelfish.service.JWKService", havingValue = "LocalJWKService", matchIfMissing = true)
public class LocalJWKService implements JWKService {

	Map<String, JWKSet> jwkSets;

	@Value("${babelfish.jwk.setPrefix:babelfish}")
	String KEYSET_PREFIX;

	@Value("${babelfish.jwk.autoInit:true}")
	Boolean AUTO_INIT;

	@Value("${babelfish.jwk.key-size:2048}")
	Integer KEY_SIZE;

	/**
	 * @Value("${babelfish.jwk.filePath:babelfish-jwks.json}") String KEYSET_PATH;
	 * 
	 * @Value("${babelfish.jwk.signing-key-size:2048}") Integer SIGNING_KEY_SIZE;
	 */

	@Autowired
	ObjectMapper mapper;

	@Override
	public JWKS jwks(String name) throws NotInitialisedException {
		try {
			if (!jwkSets.containsKey(name)) {
				throw NotInitialisedException.builder().build();
			}
			return mapper.readValue(getJWKSet(name).toPublicJWKSet().toJSONObject().toJSONString(), JWKS.class);
		} catch (JsonProcessingException e) {
			LOG.error("Encountered JSON Processing exception processing from smart-json to jackson?", e);
			throw NotInitialisedException.builder().build();
		}
	}

	@Override
	public JWTClaims verify(String compactSerialisation, URI jwksUri, JWTClaims claimChecks)
			throws SigningVerificationException, KeyRetrievalException {

		try {
			SignedJWT jwt = SignedJWT.parse(compactSerialisation);

			JWK remoteJwk = KeyUtil.getRemoteKey(jwksUri, KeyUtil.getJwkMatcher(jwt, KeyUse.SIGNATURE));
			JWSVerifier verifier = new RSASSAVerifier(remoteJwk.toRSAKey());

			if (jwt.verify(verifier)) {
				JWTClaims inputClaims = NimbusUtil.fromClaimsSet(jwt.getJWTClaimsSet());

				if (claimChecks != null) {
					NimbusUtil.checkClaims(inputClaims, claimChecks);
				}
				return inputClaims;
			} else {
				LOG.warn("Received signing verification error for kid of {} from {}", remoteJwk.getKeyID(), jwksUri);
				throw SigningVerificationException.builder().message("Unable to verify serialisation using kid "
						+ remoteJwk.getKeyID() + " retrieved from " + jwksUri).build();
			}
		} catch (ParseException e) {
			LOG.error("Unable to parse supplied JWT: {}", e.getMessage(), e);
			throw SigningVerificationException.builder().message(e.getMessage()).build();
		} catch (JOSEException e) {
			LOG.warn("Encountered a JOSEException while attempting to verify payload {} using jwks {}",
					compactSerialisation, jwksUri, e);
			throw SigningVerificationException.builder().message(e.getMessage()).build();
		}
	}

	@Override
	public String sign(String name, JWTClaims claims, JWSSigningAlgorithmType algorithm)
			throws SigningOperationException, NotInitialisedException {
		JWK jwk = getJWK(name, algorithm, JWKPublicKeyUse.SIGN);
		JWSSigner signer;
		try {
			signer = new RSASSASigner(jwk.toRSAKey().toPrivateKey());

		} catch (JOSEException e) {
			LOG.warn("Encountered a JOSEException while attempting to setup RSA signer", e);
			throw SigningOperationException.builder().message(e.getMessage()).build();
		}

		SignedJWT signedJwt = new SignedJWT(new JWSHeader.Builder(algorithm.toNimbus()).keyID(jwk.getKeyID()).build(),
				NimbusUtil.toClaimsSet(claims));
		try {
			signedJwt.sign(signer);
			return signedJwt.serialize();
		} catch (JOSEException e) {
			LOG.warn("Encountered a JOSEException while performing signature", e);
			throw SigningOperationException.builder().message(e.getMessage()).build();
		}
	}

	@Override
	public String encryptAndSign(String name, JWTClaims claims, URI jwksUri, JWSSigningAlgorithmType signingAlgorithm,
			JWEEncryptionAlgorithmType encryptionAlgorithm, JWEEncryptionEncodingType method)
			throws SigningOperationException, EncryptionOperationException, KeyRetrievalException,
			NotInitialisedException {

		JWEObject jweObject = new JWEObject(
				new JWEHeader.Builder(encryptionAlgorithm.toNimbus(), method.toNimbus()).contentType("JWT").build(),
				new Payload(sign(name, claims, signingAlgorithm)));

		try {
			jweObject.encrypt(new RSAEncrypter(
					(RSAKey) KeyUtil.getRemoteKey(jwksUri, JWKMatcher.forJWEHeader(jweObject.getHeader()))));
			return jweObject.serialize();
		} catch (JOSEException e) {
			LOG.error("Encountered JOSE Exception during encrypt operation", e);
			throw EncryptionOperationException.builder().message(e.getMessage()).build();
		}

	}

	private JWKSet getJWKSet(String name) throws NotInitialisedException {
		/**
		 * If it already exists return it
		 */
		String setFilename = String.format("{}-{}.json", KEYSET_PREFIX, name);
		if (jwkSets.containsKey(name)) {
			return jwkSets.get(name);
		} else if (new File(setFilename).exists()) {
			try {
				jwkSets.put(name, JWKSet.load(new File(setFilename)));
				return jwkSets.get(name);
			} catch (IOException e) {
				LOG.error("Encountered IOException when attempting to load file from: {}", setFilename);
				throw NotInitialisedException.builder().build();
			} catch (ParseException e) {
				LOG.error("Encountered Parsing Exception when attempting to load file from: {}", setFilename);
				throw NotInitialisedException.builder().build();
			}
		} else {
			if (AUTO_INIT) {
				try {
					FileWriter jwkFile = new FileWriter(setFilename);
					JWKSet localJwk = new JWKSet();
					jwkFile.write(localJwk.toJSONObject(false).toJSONString(JSONStyle.NO_COMPRESS));
					jwkFile.close();
					return localJwk;
				} catch (IOException e) {
					LOG.error("Attempt to initialise JWKS at {} failed with {}", setFilename, e.getMessage(), e);
					throw NotInitialisedException.builder().build();
				}

			} else {
				throw NotInitialisedException.builder().build();
			}

		}
	}

	private JWK getJWK(String name, JWSSigningAlgorithmType algorithm, JWKPublicKeyUse use)
			throws NotInitialisedException, SigningOperationException {
		String setFilename = String.format("{}-{}.json", KEYSET_PREFIX, name);
		JWKSet jwkSet = getJWKSet(name);

		List<JWK> matches = new JWKSelector(KeyUtil.getJwkMatcher(algorithm, use)).select(jwkSet);
		if (matches.size() > 0) {
			return matches.get(0);
		} else {
			if (AUTO_INIT) {

				try {
					RSAKey key = new RSAKeyGenerator(KEY_SIZE).keyUse(use.toNimbus()).keyIDFromThumbprint(true)
							.algorithm(algorithm.toNimbus()).generate();
					List<JWK> jwks = new ArrayList<JWK>(List.of(key));
					jwks.addAll(matches);
					JWKSet localJwk = new JWKSet(jwks);
					FileWriter jwkFile = new FileWriter(setFilename);
					jwkFile.write(localJwk.toJSONObject(false).toJSONString(JSONStyle.NO_COMPRESS));
					jwkFile.close();
					jwkSets.put(name, JWKSet.load(new File(setFilename)));
					return key;
				} catch (JOSEException e) {
					throw SigningOperationException.builder()
							.message(MessageUtil.format(
									"Unable to initialise a new key with algorithm of {} and use of {}", algorithm,
									use))
							.build();
				} catch (IOException | ParseException e) {
					throw SigningOperationException.builder().message(MessageUtil.format(
							"Encountered an I/O or Parsing Exception while attempting to initialise additional key with algorithm of {} and use of {}",
							algorithm, use)).build();
				}

			} else {
				throw NotInitialisedException.builder().build();
			}
		}
	}

}
