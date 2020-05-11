package io.biza.babelfish.spring.service.secret;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import io.biza.babelfish.common.exceptions.AlreadyInitialisedException;
import io.biza.babelfish.common.exceptions.NotFoundException;
import io.biza.babelfish.common.exceptions.SigningVerificationException;
import io.biza.babelfish.interfaces.SecretService;
import io.biza.babelfish.oidc.payloads.JWTClaims;
import io.biza.babelfish.spring.persistence.model.secret.SecretData;
import io.biza.babelfish.spring.persistence.repository.secret.SecretRepository;
import io.biza.babelfish.util.NimbusUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@ConditionalOnProperty(name = "babelfish.service.SecretService", havingValue = "DatabaseSecretService", matchIfMissing = true)
@EntityScan(basePackageClasses = SecretData.class)
@EnableJpaRepositories(basePackageClasses = SecretRepository.class)
public class DatabaseSecretService implements SecretService {
	
	@Value("${babelfish.secret.length:32}")
	Integer secretLength;
	
	@Autowired
	SecretRepository secretRepository;
	
	@Override
	public String generateSecret(UUID identifier, boolean regenerate) throws AlreadyInitialisedException {
		return generateSecret(identifier, regenerate, true);
	}
	
	private String generateSecret(UUID identifier, boolean regenerate, boolean oneWay) throws AlreadyInitialisedException {
		SecretData secretData;
		
		try {
			secretData = secretRepository.findById(identifier).orElseThrow(() -> NotFoundException.builder().build());
			if(!regenerate) {
				throw AlreadyInitialisedException.builder().build();
			}
		} catch (NotFoundException e) {
			secretData = SecretData.builder().id(identifier).build();
		}
		
		String secret = RandomStringUtils.randomAlphanumeric(secretLength);
		
		if(oneWay) {
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			LOG.error("Unable to initialise SHA-256 MessageDigest, returning null values.");
			return null;
		}
		secretData.secret(digest.digest(secret.getBytes(StandardCharsets.UTF_8)));
		} else {
			secretData.secret(secret.getBytes());
		}
		secretRepository.save(secretData);		
		
		return secret;
	}

	@Override
	public Boolean checkSecret(UUID identifier, String secret) throws NotFoundException {
		SecretData secretData = secretRepository.findById(identifier).orElseThrow(() -> NotFoundException.builder().build());
		
		try {
			final MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] secretDigest = digest.digest(secret.getBytes(StandardCharsets.UTF_8));
			return secretData.secret().equals(secretDigest);
		} catch (NoSuchAlgorithmException e) {
			LOG.error("Unable to initialise SHA-256 MessageDigest, returning denied until rectified.");
			return false;
		}
	}

	@Override
	public void deleteSecret(UUID identifier) {
		secretRepository.deleteById(identifier);
	}

	@Override
	public Boolean hasSecret(UUID identifier) {
		return secretRepository.existsById(identifier);
	}

	@Override
	public JWTClaims checkHmacJwt(UUID identifier, String compactSerialisation, JWTClaims claims) throws NotFoundException, SigningVerificationException {
		SecretData secretData = secretRepository.findById(identifier).orElseThrow(() -> NotFoundException.builder().build());
		return NimbusUtil.verifyHmac(compactSerialisation, new String(secretData.secret()), claims);
	}
}
