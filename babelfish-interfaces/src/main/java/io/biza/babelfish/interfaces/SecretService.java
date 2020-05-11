package io.biza.babelfish.interfaces;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import io.biza.babelfish.common.exceptions.AlreadyInitialisedException;
import io.biza.babelfish.common.exceptions.EncryptionOperationException;
import io.biza.babelfish.common.exceptions.KeyRetrievalException;
import io.biza.babelfish.common.exceptions.NotInitialisedException;
import io.biza.babelfish.common.exceptions.SigningOperationException;
import io.biza.babelfish.oidc.enumerations.JWEEncryptionAlgorithmType;
import io.biza.babelfish.oidc.enumerations.JWEEncryptionEncodingType;
import io.biza.babelfish.oidc.enumerations.JWKKeyType;
import io.biza.babelfish.oidc.enumerations.JWKPublicKeyUse;
import io.biza.babelfish.oidc.enumerations.JWSSigningAlgorithmType;
import io.biza.babelfish.oidc.payloads.JWK;
import io.biza.babelfish.oidc.payloads.JWKS;
import io.biza.babelfish.oidc.payloads.JWTClaims;

public interface SecretService {

	public JWKS jwks(String... realms) throws NotInitialisedException;

}
