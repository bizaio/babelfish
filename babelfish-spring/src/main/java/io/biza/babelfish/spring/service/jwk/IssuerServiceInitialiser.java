package io.biza.babelfish.spring.service.jwk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import io.biza.babelfish.common.exceptions.AlreadyInitialisedException;
import io.biza.babelfish.common.exceptions.NotInitialisedException;
import io.biza.babelfish.interfaces.IssuerService;
import io.biza.babelfish.oidc.enumerations.JWKKeyType;
import io.biza.babelfish.spring.service.config.properties.BabelfishProperties;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@ConditionalOnProperty(name = "babelfish.issuer.auto-init", havingValue = "true")
public class IssuerServiceInitialiser implements
ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	IssuerService issuerService;
	
	@Autowired
	BabelfishProperties properties;
	
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(properties.issuer().autoInitRealms() != null) {
			for(String realm : properties.issuer().autoInitRealms()) {
				try {
					issuerService.createIssuer(realm);
					issuerService.initSigningKey(realm, JWKKeyType.fromAlgorithm(properties.issuer().signingType()), properties.issuer().signingType());
					issuerService.initEncryptionKey(realm, JWKKeyType.fromAlgorithm(properties.issuer().encryptionType()), properties.issuer().encryptionType());
				} catch (NotInitialisedException e) {
					LOG.error("Encountered error while attempting automatic initialisation", e);
				} catch (AlreadyInitialisedException e) {
					LOG.debug("Realm {} is already initialised so skipping further progress", realm);
				}
			}
		}
	}

}
