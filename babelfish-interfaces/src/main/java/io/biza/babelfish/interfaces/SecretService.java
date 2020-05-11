package io.biza.babelfish.interfaces;

import java.util.UUID;

import io.biza.babelfish.common.exceptions.AlreadyInitialisedException;
import io.biza.babelfish.common.exceptions.NotFoundException;
import io.biza.babelfish.common.exceptions.SigningVerificationException;
import io.biza.babelfish.oidc.payloads.JWTClaims;

public interface SecretService {

	public String generateSecret(UUID identifier, boolean regenerate) throws AlreadyInitialisedException;
	
	default String generateSecret(UUID identifier) throws AlreadyInitialisedException {
		return generateSecret(identifier, false);
	}
	
	public Boolean checkSecret(UUID identifier, String secret) throws NotFoundException;
	
	public void deleteSecret(UUID identifier);
	
	public Boolean hasSecret(UUID identifier);
	
	public JWTClaims checkHmacJwt(UUID identifier, String compactSerialisation, JWTClaims claims) throws NotFoundException, SigningVerificationException;
	

}
