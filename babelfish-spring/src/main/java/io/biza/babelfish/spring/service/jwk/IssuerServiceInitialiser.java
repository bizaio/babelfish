package io.biza.babelfish.spring.service.jwk;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import io.biza.babelfish.cdr.exceptions.AlreadyInitialisedException;
import io.biza.babelfish.cdr.exceptions.NotInitialisedException;
import io.biza.babelfish.interfaces.IssuerService;
import io.biza.babelfish.oidc.enumerations.JWEEncryptionAlgorithmType;
import io.biza.babelfish.oidc.enumerations.JWKKeyType;
import io.biza.babelfish.oidc.enumerations.JWSSigningAlgorithmType;
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
