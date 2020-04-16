package io.biza.babelfish.spring.interfaces;

import java.net.URI;
import java.text.ParseException;

import com.nimbusds.jwt.SignedJWT;

import io.biza.babelfish.oidc.enumerations.JWEEncryptionAlgorithmType;
import io.biza.babelfish.oidc.enumerations.JWEEncryptionEncodingType;
import io.biza.babelfish.oidc.enumerations.JWSSigningAlgorithmType;
import io.biza.babelfish.oidc.payloads.JWKS;
import io.biza.babelfish.oidc.payloads.JWTClaims;
import io.biza.babelfish.spring.exceptions.SigningVerificationException;
import io.biza.babelfish.spring.enumerations.JWTPeekAttribute;
import io.biza.babelfish.spring.exceptions.EncryptionOperationException;
import io.biza.babelfish.spring.exceptions.KeyRetrievalException;
import io.biza.babelfish.spring.exceptions.NotInitialisedException;
import io.biza.babelfish.spring.exceptions.SigningOperationException;

public interface JWKService {

	/**
	 * Generate a JWKS object in Babelfish JWKS Format
	 * 
	 * @param containing the unique name of the key set to use
	 * @return JWKS object
	 * @throws NotInitialisedException if the JWKS isn't (and couldn't be)
	 *                                 initialised
	 */
	public JWKS jwks(String name) throws NotInitialisedException;

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
	public JWTClaims verify(String compactSerialisation, URI jwksUri, JWTClaims claims)
			throws SigningVerificationException, KeyRetrievalException;

	/**
	 * Given a set of JWTClaims, sign the data and then supply back the compact
	 * serialisation
	 * 
	 * @param containing the unique key set name to use for signing
	 * @param claims     to be signed
	 * @param algorithm  specifying the requested signing algorithm
	 * @return a compact serialisation of the signed content
	 * @throws SigningOperationException
	 * @throws NotInitialisedException 
	 */
	public String sign(String name, JWTClaims claims, JWSSigningAlgorithmType algorithm)
			throws SigningOperationException, NotInitialisedException;

	/**
	 * Given a set of JWTClaims and a recipient JWKS uri sign and encrypt the data
	 * and then supply back the compact serialisation
	 * 
	 * @param containing          the unique key set name to use for signing
	 * @param claims              to sign
	 * @param jwksUri             of counterparty to sign+encrypt content to
	 * @param signingAlgorithm    to sign the JWT with
	 * @param encryptionAlgorithm to encrypt the signed JWT with
	 * @param encryptionMethod    to use for encryption
	 * @return String containing a compact serialisation of the signed data
	 * @throws SigningOperationException if signing operation failed
	 * @throws KeyRetrievalException
	 * @throws NotInitialisedException 
	 */
	public String encryptAndSign(String name, JWTClaims claims, URI jwksUri, JWSSigningAlgorithmType signingAlgorithm,
			JWEEncryptionAlgorithmType encryptionAlgorithm, JWEEncryptionEncodingType encryptionMethod)
			throws SigningOperationException, EncryptionOperationException, KeyRetrievalException, NotInitialisedException;

	/**
	 * Given a compact serialisation, take a peek at the specified attribute
	 * 
	 * @throws ParseException
	 */
	default String peekAt(String compactSerialisation, JWTPeekAttribute attribute) throws ParseException {
		SignedJWT inputJwt = SignedJWT.parse(compactSerialisation);
		if (attribute.equals(JWTPeekAttribute.ISSUER)) {
			return inputJwt.getJWTClaimsSet().getIssuer();
		} else {
			return inputJwt.getJWTClaimsSet().getStringClaim(attribute.value());
		}
	}

	/**
	 * Given a compact serialisation, make sure it is parsable NOTE: DOES NOT VERIFY
	 * THE SIGNING
	 */
	default Boolean canJwtParse(String compactSerialisation) {
		try {
			SignedJWT.parse(compactSerialisation);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

}
