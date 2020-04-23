package io.biza.babelfish.spring.service.jwk;

import java.net.URI;
import java.text.ParseException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
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
import io.biza.babelfish.spring.exceptions.AlreadyInitialisedException;
import io.biza.babelfish.spring.exceptions.EncryptionOperationException;
import io.biza.babelfish.spring.exceptions.NotInitialisedException;
import io.biza.babelfish.spring.exceptions.SigningOperationException;
import io.biza.babelfish.spring.interfaces.IssuerService;
import io.biza.babelfish.spring.persistence.model.issuer.IssuerData;
import io.biza.babelfish.spring.persistence.model.issuer.IssuerKeyData;
import io.biza.babelfish.spring.persistence.repository.issuer.IssuerKeyRepository;
import io.biza.babelfish.spring.persistence.repository.issuer.IssuerRepository;
import io.biza.babelfish.spring.persistence.specifications.IssuerKeySpecifications;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@ConditionalOnProperty(name = "babelfish.issuer.autoInit", havingValue = "true")
public class IssuerServiceInitialiser implements
ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	IssuerService issuerService;
	
	@Value("${babelfish.issuer.autoInitRealms}")
	List<String> realmList;
	
	@Value("${babelfish.issuer.autoInitKeyType:RSA}")
	JWKKeyType keyType = JWKKeyType.RSA;
	
	@Value("${babelfish.issuer.autoInitSigningAlgorithm:PS256}")
	JWSSigningAlgorithmType signingType = JWSSigningAlgorithmType.PS256;
	
	@Value("${babelfish.issuer.autoInitEncryptionAlgorithm:RSA-OAEP}")
	JWEEncryptionAlgorithmType encryptionType = JWEEncryptionAlgorithmType.RSA_OAEP;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(realmList != null) {
			for(String realm : realmList) {
				try {
					issuerService.createIssuer(realm);
					issuerService.initSigningKey(realm, keyType, signingType);
					issuerService.initEncryptionKey(realm, keyType, encryptionType);
				} catch (NotInitialisedException e) {
					LOG.error("Encountered error while attempting automatic initialisation", e);
				} catch (AlreadyInitialisedException e) {
					LOG.debug("Realm {} is already initialised so skipping further progress", realm);
				}
			}
		}
	}

}
