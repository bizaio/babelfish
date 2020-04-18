package io.biza.babelfish.spring.service.jwk;

import java.net.URI;
import java.text.ParseException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.RSAEncrypter;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKMatcher;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import com.nimbusds.jwt.SignedJWT;

import io.biza.babelfish.cdr.util.MessageUtil;
import io.biza.babelfish.oidc.enumerations.JWEEncryptionAlgorithmType;
import io.biza.babelfish.oidc.enumerations.JWEEncryptionEncodingType;
import io.biza.babelfish.oidc.enumerations.JWKKeyType;
import io.biza.babelfish.oidc.enumerations.JWKPublicKeyUse;
import io.biza.babelfish.oidc.enumerations.JWSSigningAlgorithmType;
import io.biza.babelfish.oidc.exceptions.KeyRetrievalException;
import io.biza.babelfish.oidc.payloads.JWKS;
import io.biza.babelfish.oidc.payloads.JWTClaims;
import io.biza.babelfish.oidc.support.NimbusUtil;
import io.biza.babelfish.spring.Messages;
import io.biza.babelfish.spring.exceptions.EncryptionOperationException;
import io.biza.babelfish.spring.exceptions.NotInitialisedException;
import io.biza.babelfish.spring.exceptions.SigningOperationException;
import io.biza.babelfish.spring.interfaces.IssuerService;
import io.biza.babelfish.spring.persistence.model.IssuerData;
import io.biza.babelfish.spring.persistence.model.IssuerKeyData;
import io.biza.babelfish.spring.persistence.repository.IssuerKeyRepository;
import io.biza.babelfish.spring.persistence.repository.IssuerRepository;
import io.biza.babelfish.spring.persistence.specifications.IssuerKeySpecifications;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@ConditionalOnProperty(name = "babelfish.service.IssuerService", havingValue = "DatabaseIssuerService", matchIfMissing = true)
@EntityScan(basePackageClasses = IssuerData.class)
@EnableJpaRepositories(basePackageClasses = IssuerRepository.class)
public class DatabaseIssuerService implements IssuerService {

	@Autowired
	IssuerRepository issuerRepository;

	@Autowired
	IssuerKeyRepository issuerKeyRepository;

	/**
	 * @Value("${babelfish.jwk.setPrefix:babelfish}") String KEYSET_PREFIX;
	 */

	@Value("${babelfish.jwk.autoInit:true}")
	Boolean AUTO_INIT;

	@Value("${babelfish.jwk.key-size:2048}")
	Integer KEY_SIZE;

	@Autowired
	ObjectMapper mapper;

	@Override
	public JWKS jwks(String... names) throws NotInitialisedException {
		JWKS jwks = new JWKS();
		for (String name : names) {
			try {
				jwks.keys().addAll(mapper
						.readValue(getSet(name).toPublicJWKSet().toJSONObject().toJSONString(), JWKS.class).keys());
			} catch (JsonProcessingException e) {
				LOG.error(Messages.ENCOUNTERED_X_EXCEPTION_WHILE_PERFORMING_Y, e.getClass().getName(),
						"processing from smart-json to jackson", e);
				throw NotInitialisedException.builder().build();
			}
		}
		return jwks;
	}

	@Override
	public String sign(String name, JWTClaims claims, JWSSigningAlgorithmType algorithm)
			throws SigningOperationException, NotInitialisedException {
		JWK jwk = getKey(name, algorithm, JWKPublicKeyUse.SIGN);
		JWSSigner signer;
		try {
			signer = new RSASSASigner(jwk.toRSAKey().toPrivateKey());

		} catch (JOSEException e) {
			LOG.warn(Messages.ENCOUNTERED_X_EXCEPTION_WHILE_PERFORMING_Y, e.getClass().getSimpleName(),
					"setup of RSA signer", e);
			throw SigningOperationException.builder().message(e.getMessage()).build();
		}

		SignedJWT signedJwt = new SignedJWT(new JWSHeader.Builder(algorithm.toNimbus()).keyID(jwk.getKeyID()).build(),
				NimbusUtil.toClaimsSet(claims));
		try {
			signedJwt.sign(signer);
			return signedJwt.serialize();
		} catch (JOSEException e) {
			LOG.warn(Messages.ENCOUNTERED_X_EXCEPTION_WHILE_PERFORMING_Y, e.getClass().getSimpleName(), "SIGN", e);
			throw SigningOperationException.builder().message(e.getMessage()).build();
		}
	}

	@Override
	public String encrypt(String payload, URI jwksUri, JWEEncryptionAlgorithmType encryptionAlgorithm,
			JWEEncryptionEncodingType method) throws KeyRetrievalException, EncryptionOperationException {

		JWEObject jweObject = new JWEObject(
				new JWEHeader.Builder(encryptionAlgorithm.toNimbus(), method.toNimbus()).contentType("JWT").build(),
				new Payload(payload));

		try {
			jweObject.encrypt(new RSAEncrypter(
					(RSAKey) NimbusUtil.getRemoteKey(jwksUri, JWKMatcher.forJWEHeader(jweObject.getHeader()))));
			return jweObject.serialize();
		} catch (JOSEException e) {
			LOG.warn(Messages.ENCOUNTERED_X_EXCEPTION_WHILE_PERFORMING_Y, e.getClass().getSimpleName(), "ENCRYPT", e);
			throw EncryptionOperationException.builder().message(e.getMessage()).build();
		}

	}

	private JWKSet getSet(String issuer) throws NotInitialisedException {

		/**
		 * Retrieve existing issuer, create it if AUTO INIT is enabled or return null
		 * 
		 */
		IssuerData issuerData = issuerRepository.findByIssuer(issuer).orElseGet(() -> {
			if (AUTO_INIT) {
				IssuerData data = issuerRepository.save(IssuerData.builder().issuer(issuer).build());
				return data;
			} else {
				return null;
			}
		});

		/**
		 * If we get past this we have the issuer data object
		 */
		if (issuerData == null) {
			throw NotInitialisedException.builder().build();
		}

		/**
		 * Reconstruct the JWKSet from the issuer data
		 */
		List<JWK> jwks = new ArrayList<JWK>();
		issuerData.keys().forEach(key -> {
			try {
				jwks.add(JWK.parse(key.keyContent()));
			} catch (ParseException e) {
				LOG.warn(Messages.ENCOUNTERED_PARSE_EXCEPTION_WITH_ID, key.id());
			}
		});

		return new JWKSet(jwks);

	}

	private JWK getKey(String issuer, JWSSigningAlgorithmType algorithm, JWKPublicKeyUse use)
			throws NotInitialisedException, SigningOperationException {

		/**
		 * If it doesn't exist, try and retrieve it which will either throw an exception
		 * or auto init
		 */
		if (!issuerRepository.existsByIssuer(issuer)) {
			getSet(issuer);
		}

		List<IssuerKeyData> keyData = issuerKeyRepository
				.findAll(
						IssuerKeySpecifications.issuer(issuer).and(IssuerKeySpecifications.keyType(JWKKeyType.RSA))
								.and(IssuerKeySpecifications.keyUse(use)
										.and(IssuerKeySpecifications.signingAlgorithm(algorithm)))
								.and(IssuerKeySpecifications.enabled(true)),
						Sort.by(List.of(Order.desc("creationTime"))));

		/**
		 * If a key isn't found then maybe auto init or bail out
		 */
		if (keyData == null || !(keyData.size() > 0)) {
			if (AUTO_INIT) {
				try {
					RSAKey key = new RSAKeyGenerator(KEY_SIZE).keyUse(use.toNimbus()).keyIDFromThumbprint(true)
							.algorithm(algorithm.toNimbus()).generate();
					IssuerKeyData data = issuerKeyRepository
							.save(IssuerKeyData.builder().creationTime(OffsetDateTime.now()).enabled(true)
									.issuer(issuerRepository.findByIssuer(issuer).get()).keyContent(key.toJSONString())
									.keyType(JWKKeyType.RSA).keyUse(use).build());
					keyData = List.of(data);
				} catch (JOSEException e) {
					throw SigningOperationException.builder().message(
							MessageUtil.format(Messages.ENCOUNTERED_PARSE_EXCEPTION_WITH_ALG_USE, algorithm, use))
							.build();
				}
			} else {
				throw NotInitialisedException.builder().build();
			}
		}

		/**
		 * By this point we should have a key (or multiple but we only use the first)
		 * that's either been retrieved or just created
		 */
		try {
			return JWK.parse(keyData.get(0).keyContent());
		} catch (ParseException e) {
			throw SigningOperationException.builder()
					.message(MessageUtil.format(Messages.ENCOUNTERED_PARSE_EXCEPTION_WITH_ALG_USE, algorithm, use, e))
					.build();
		}
	}

}
