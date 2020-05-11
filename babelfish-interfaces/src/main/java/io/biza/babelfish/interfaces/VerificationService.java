package io.biza.babelfish.interfaces;

import java.net.URI;
import java.text.ParseException;

import io.biza.babelfish.common.exceptions.KeyRetrievalException;
import io.biza.babelfish.common.exceptions.SigningOperationException;
import io.biza.babelfish.common.exceptions.SigningVerificationException;
import io.biza.babelfish.oidc.enumerations.JWTPeekAttribute;
import io.biza.babelfish.oidc.payloads.JWTClaims;
import io.biza.babelfish.util.NimbusUtil;

public interface VerificationService {

	/**
	 * Given a compact serialisation, verify the signature and the supplied claims
	 * then provide a new JWTClaims object with the serialisation content
	 * 
	 * @param compactSerialisation containing a signed JWT
	 * @param jwksUri              containing a URL to the JWKS endpoint
	 * @param claims               to verify
	 * @return JWTClaims containing all claims from the compact serialisation
	 * @throws KeyRetrievalException
	 * @throws SigningOperationException
	 */
	default JWTClaims verify(String compactSerialisation, URI jwksUri, JWTClaims claims)
			throws SigningVerificationException, KeyRetrievalException {
		return NimbusUtil.verify(compactSerialisation, jwksUri, claims);
	}

	/**
	 * Given a compact serialisation, take a peek at the specified attribute
	 * 
	 * @throws ParseException
	 */
	default String peekAt(String compactSerialisation, JWTPeekAttribute attribute) throws ParseException {
		return NimbusUtil.peekAt(compactSerialisation, attribute);
	}

	/**
	 * Given a compact serialisation, make sure it is parsable NOTE: DOES NOT VERIFY
	 * THE SIGNING
	 */
	default Boolean canJwtParse(String compactSerialisation) {
		return NimbusUtil.canParse(compactSerialisation);
	}
}
