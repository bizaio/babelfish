package io.biza.babelfish.interfaces;

import io.biza.babelfish.common.exceptions.NotInitialisedException;
import io.biza.babelfish.oidc.payloads.JWKS;

public interface SecretService {

	public JWKS jwks(String... realms) throws NotInitialisedException;

}
