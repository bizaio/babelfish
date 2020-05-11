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

import io.biza.babelfish.common.exceptions.AlreadyInitialisedException;
import io.biza.babelfish.common.exceptions.EncryptionOperationException;
import io.biza.babelfish.common.exceptions.KeyRetrievalException;
import io.biza.babelfish.common.exceptions.NotInitialisedException;
import io.biza.babelfish.common.exceptions.SigningOperationException;
import io.biza.babelfish.interfaces.IssuerService;
import io.biza.babelfish.oidc.enumerations.JWEEncryptionAlgorithmType;
import io.biza.babelfish.oidc.enumerations.JWEEncryptionEncodingType;
import io.biza.babelfish.oidc.enumerations.JWKKeyType;
import io.biza.babelfish.oidc.enumerations.JWKPublicKeyUse;
import io.biza.babelfish.oidc.enumerations.JWSSigningAlgorithmType;
import io.biza.babelfish.oidc.payloads.JWKS;
import io.biza.babelfish.oidc.payloads.JWTClaims;
import io.biza.babelfish.oidc.support.NimbusUtil;
import io.biza.babelfish.spring.Messages;
import io.biza.babelfish.spring.persistence.model.issuer.IssuerData;
import io.biza.babelfish.spring.persistence.model.issuer.IssuerKeyData;
import io.biza.babelfish.spring.persistence.repository.issuer.IssuerKeyRepository;
import io.biza.babelfish.spring.persistence.repository.issuer.IssuerRepository;
import io.biza.babelfish.spring.persistence.specifications.IssuerKeySpecifications;
import io.biza.babelfish.util.MessageUtil;
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

	@Value("${babelfish.jwk.key-size:2048}")
	Integer KEY_SIZE;

	@Autowired
	ObjectMapper mapper;

	@Override
	public JWKS jwks(String... names) {
		JWKS jwks = new JWKS();
		for (String name : names) {
			try {
				jwks.keys().addAll(mapper
						.readValue(getSet(name).toPublicJWKSet().toJSONObject().toJSONString(), JWKS.class).keys());
			} catch (JsonProcessingException e) {
				LOG.error(Messages.ENCOUNTERED_X_EXCEPTION_WHILE_PERFORMING_Y, e.getClass().getName(),
						"processing from smart-json to jackson", e);
			} catch (NotInitialisedException e) {
				LOG.warn(Messages.ENCOUNTERED_X_EXCEPTION_WHILE_PERFORMING_Y, e.getClass().getName(),
						"while processing issuer with name of " + name);
			}
		}
		return jwks;
	}

	@Override
	public String sign(String name, JWKKeyType keyType, JWTClaims claims, JWSSigningAlgorithmType algorithm)
			throws SigningOperationException, NotInitialisedException {
		JWK jwk = getKey(name, keyType, algorithm, JWKPublicKeyUse.SIGN);
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
	public String encrypt(String payload, JWKKeyType keyType, URI jwksUri,
			JWEEncryptionAlgorithmType encryptionAlgorithm, JWEEncryptionEncodingType method)
			throws KeyRetrievalException, EncryptionOperationException {

		JWEObject jweObject = new JWEObject(
				new JWEHeader.Builder(encryptionAlgorithm.toNimbus(), method.toNimbus()).contentType("JWT").build(),
				new Payload(payload));

		try {
			if (keyType.equals(JWKKeyType.RSA)) {
				jweObject.encrypt(new RSAEncrypter(
						(RSAKey) NimbusUtil.getRemoteKey(jwksUri, JWKMatcher.forJWEHeader(jweObject.getHeader()))));
				return jweObject.serialize();
			} else {
				throw EncryptionOperationException.builder()
						.message(MessageUtil.format("Key Type of {} is not supported", keyType)).build();
			}
		} catch (JOSEException e) {
			LOG.warn(Messages.ENCOUNTERED_X_EXCEPTION_WHILE_PERFORMING_Y, e.getClass().getSimpleName(), "ENCRYPT", e);
			throw EncryptionOperationException.builder().message(e.getMessage()).build();
		}

	}

	private JWKSet getSet(String issuer) throws NotInitialisedException {

		/**
		 * Retrieve existing issuer or throw not initialised
		 * 
		 */
		IssuerData issuerData = issuerRepository.findByIssuer(issuer)
				.orElseThrow(() -> NotInitialisedException.builder().build());

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

	private JWK getKey(String issuer, JWKKeyType keyType, JWSSigningAlgorithmType algorithm, JWKPublicKeyUse use)
			throws NotInitialisedException, SigningOperationException {

		/**
		 * If it doesn't exist, try and retrieve it which will either throw an exception
		 * or auto init
		 */
		if (!issuerRepository.existsByIssuer(issuer)) {
			throw NotInitialisedException.builder()
					.message(MessageUtil.format("Issuer of {} is not initialised", issuer)).build();
		}

		List<IssuerKeyData> keyData = issuerKeyRepository
				.findAll(
						IssuerKeySpecifications.issuer(issuer).and(IssuerKeySpecifications.keyType(keyType))
								.and(IssuerKeySpecifications.keyUse(use)
										.and(IssuerKeySpecifications.signingAlgorithm(algorithm)))
								.and(IssuerKeySpecifications.enabled(true)),
						Sort.by(List.of(Order.desc("creationTime"))));

		try {
			if (keyData != null && keyData.size() > 0) {
				return JWK.parse(keyData.get(0).keyContent());
			} else {
				// No key found for the specified use and algorithm
				throw NotInitialisedException.builder().build();
			}
		} catch (ParseException e) {
			throw SigningOperationException.builder()
					.message(MessageUtil.format(Messages.ENCOUNTERED_PARSE_EXCEPTION_WITH_ALG_USE, algorithm, use, e))
					.build();
		}
	}

	@Override
	public io.biza.babelfish.oidc.payloads.JWK initKey(String issuer, JWKKeyType type, JWKPublicKeyUse use,
			JWSSigningAlgorithmType signingAlgorithm, JWEEncryptionAlgorithmType encryptionAlgorithm)
			throws NotInitialisedException {

		IssuerData issuerData = issuerRepository.findByIssuer(issuer)
				.orElseThrow(() -> NotInitialisedException.builder().build());

		if (type.equals(JWKKeyType.RSA)) {
			RSAKey key;
			if (use.equals(JWKPublicKeyUse.SIGN)) {
				try {
					key = new RSAKeyGenerator(KEY_SIZE).keyUse(use.toNimbus()).keyIDFromThumbprint(true)
							.algorithm(signingAlgorithm.toNimbus()).generate();
				} catch (JOSEException e) {
					LOG.error("Encountered JOSE Exception while performing initialisation", e);
					throw NotInitialisedException.builder().build();
				}

			} else if (use.equals(JWKPublicKeyUse.ENCRYPT)) {
				try {
					key = new RSAKeyGenerator(KEY_SIZE).keyUse(use.toNimbus()).keyIDFromThumbprint(true)
							.algorithm(encryptionAlgorithm.toNimbus()).generate();
				} catch (JOSEException e) {
					LOG.error("Encountered JOSE Exception while performing initialisation", e);
					throw NotInitialisedException.builder().build();
				}
			} else {
				LOG.error("Requested initialisation of key with unsupported use of: {}", use);
				throw NotInitialisedException.builder().build();
			}

			IssuerKeyData keyData = issuerKeyRepository
					.save(IssuerKeyData.builder().creationTime(OffsetDateTime.now()).enabled(true).issuer(issuerData)
							.encryptionAlgorithm(encryptionAlgorithm).keyContent(key.toJSONString())
							.signingAlgorithm(signingAlgorithm).keyType(JWKKeyType.RSA).keyUse(use).build());

			try {
				return mapper.readValue(keyData.keyContent(), io.biza.babelfish.oidc.payloads.JWK.class);
			} catch (JsonProcessingException e) {
				LOG.error("Attempt to deserialise newly created JWK failed", use);
				throw NotInitialisedException.builder().build();
			}

		} else {
			LOG.error("Request initialisation of key with unsupported type of: {}", type);
			throw NotInitialisedException.builder().build();
		}
	}

	@Override
	public JWKS createIssuer(String issuer) throws NotInitialisedException, AlreadyInitialisedException {
		// If it already exists, we throw already initialised
		if (existsIssuer(issuer)) {
			LOG.warn("Issuer of {} already exists", issuer);
			throw AlreadyInitialisedException.builder().build();
		}
		issuerRepository.save(IssuerData.builder().issuer(issuer).build());
		return jwks(issuer);
	}

	@Override
	public void deleteIssuer(String issuer) {
		if (!issuerRepository.existsByIssuer(issuer)) {
			LOG.warn("Issuer {} does not exist", issuer);
			return;
		}
		issuerRepository.deleteByIssuer(issuer);
	}

	@Override
	public Boolean existsIssuer(String issuer) {
		return issuerRepository.existsByIssuer(issuer);
	}

}
