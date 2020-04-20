package io.biza.babelfish.spring.service.config;

import java.net.URI;
import java.text.MessageFormat;
import java.text.ParseException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

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
import com.fasterxml.jackson.core.type.TypeReference;
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
import io.biza.babelfish.spring.exceptions.NotFoundException;
import io.biza.babelfish.spring.exceptions.NotInitialisedException;
import io.biza.babelfish.spring.exceptions.SigningOperationException;
import io.biza.babelfish.spring.interfaces.ConfigService;
import io.biza.babelfish.spring.interfaces.IssuerService;
import io.biza.babelfish.spring.persistence.model.config.ConfigData;
import io.biza.babelfish.spring.persistence.model.issuer.IssuerData;
import io.biza.babelfish.spring.persistence.model.issuer.IssuerKeyData;
import io.biza.babelfish.spring.persistence.repository.config.ConfigRepository;
import io.biza.babelfish.spring.persistence.repository.issuer.IssuerKeyRepository;
import io.biza.babelfish.spring.persistence.repository.issuer.IssuerRepository;
import io.biza.babelfish.spring.persistence.specifications.IssuerKeySpecifications;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@ConditionalOnProperty(name = "babelfish.service.ConfigService", havingValue = "DatabaseConfigService", matchIfMissing = true)
@EntityScan(basePackageClasses = ConfigData.class)
@EnableJpaRepositories(basePackageClasses = ConfigRepository.class)
@Transactional
public class DatabaseConfigService implements ConfigService {

	@Autowired
	ObjectMapper mapper;

	@Autowired
	ConfigRepository configRepository;

	@Override
	public <T> T getConfig(String object, String key) throws NotFoundException {
		ConfigData data = configRepository.findByObjectAndKey(object, key).orElseThrow(() -> NotFoundException.builder()
				.message(MessageFormat.format(Messages.CONFIG_UNABLE_TO_LOCATE, object, key)).build());

		try {
			return mapper.readValue(data.value(), new TypeReference<T>() {
			});
		} catch (JsonProcessingException e) {
			throw NotFoundException.builder().message(MessageUtil.format(Messages.CONFIG_UNABLE_TO_PARSE, object, key))
					.build();
		}
	}

	@Override
	public <T> T setConfig(String object, String key, T value) throws NotInitialisedException {
		ConfigData data = configRepository.findByObjectAndKey(object, key)
				.orElse(ConfigData.builder().key(key).object(object).build());
		try {
			data.keyClass(value.getClass().getName());
			data.value(mapper.writeValueAsString(value));
			configRepository.save(data);
			return value;
		} catch (JsonProcessingException e) {
			throw NotInitialisedException.builder()
					.message(MessageFormat.format(Messages.CONFIG_UNABLE_TO_PROCESS, object, key)).build();
		}
	}

	@Override
	public void deleteObject(String object) {
		configRepository.deleteByObject(object);
	}

	@Override
	public Map<String, Object> listConfig(String object) {
		List<ConfigData> data = configRepository.findByObject(object);
		Map<String,Object> keyValue = new HashMap<String,Object>();
		for(ConfigData config : data) {
			try {
				keyValue.put(config.key(), mapper.readValue(config.value(), Class.forName(config.keyClass())));
			} catch (JsonProcessingException | ClassNotFoundException e) {
				LOG.error("Encountered deserialisation failure when converting value {} to class of {}", config.value(), config.keyClass());
			}
		}
		return keyValue;
	}

}
