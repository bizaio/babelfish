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
import io.biza.babelfish.util.NimbusUtil;

public interface IssuerService {

	/**
	 * Generate a JWKS object in Babelfish JWKS Format
	 * 
	 * @param realms containing one or more realm names to produce a key set for
	 * @return JWKS object
	 * @throws NotInitialisedException if the JWKS isn't (and couldn't be)
	 *                                 initialised initialised
	 */
	public JWKS jwks(String... realms) throws NotInitialisedException;

	/**
	 * Generate a JWKS Object using a supplied URI as the issuer
	 * 
	 * @param uri containing the unique name of the key set to use
	 * @return JWKS object
	 * @throws NotInitialisedException if the JWKS isn't (and couldn't be)
	 *                                 initialised
	 */
	default JWKS jwks(URI uri) throws NotInitialisedException {
		return jwks(uri.toASCIIString());
	}

	/**
	 * Generate a JWKS Object using a list of supplied URI issuers
	 * 
	 * @param uris containing the unique name of the key set to use
	 * @return JWKS object
	 * @throws NotInitialisedException if the JWKS isn't (and couldn't be)
	 *                                 initialised
	 */
	default JWKS jwks(List<URI> uris) throws NotInitialisedException {
		return jwks(uris.stream().map(uri -> uri.toASCIIString()).collect(Collectors.toList())
				.toArray(new String[uris.size()]));
	}

	/**
	 * Given a set of JWTClaims, sign the data and then supply back the compact
	 * serialisation
	 * 
	 * @param name      containing the unique key set name to use for signing
	 * @param claims    to be signed
	 * @param algorithm specifying the requested signing algorithm
	 * @return a compact serialisation of the signed content
	 * @throws SigningOperationException
	 * @throws NotInitialisedException
	 */
	public String sign(String name, JWKKeyType keyType, JWTClaims claims, JWSSigningAlgorithmType algorithm)
			throws SigningOperationException, NotInitialisedException;

	default String sign(URI name, JWKKeyType keyType, JWTClaims claims, JWSSigningAlgorithmType algorithm)
			throws SigningOperationException, NotInitialisedException {
		return sign(name.toASCIIString(), keyType, claims, algorithm);
	}
	
	/**
	 * Given a realm name and a compact serialisation verify that it came from the realm and is still valid
	 * 
	 * @param name containing the unique key set name expected to have been used
	 * @param compactSerialisation containing a compact serialisation of the jwt
	 * @return Boolean if validated as accurate for this realm
	 */
	default Boolean validate(String name, String compactSerialisation, JWTClaims jwtClaims) {
		try {
			return NimbusUtil.validate(compactSerialisation, jwks(name), jwtClaims);
		} catch (NotInitialisedException e) {
			return false;
		}
	}

	/**
	 * Given a (potentially signed) payload encrypt to the JWKS retrievable from a
	 * URI using a specified algorithm and encoding
	 * 
	 * @param payload    to peform encryption on
	 * @param remoteJwks to retrieve JWKS recipient from
	 * @param algorithm  to use for encryption
	 * @param encoding   for the encoding type to use
	 * @return Encrypted result of supplied payload
	 * @throws KeyRetrievalException
	 * @throws EncryptionOperationException
	 */
	public String encrypt(String payload, JWKKeyType keyType, URI remoteJwks, JWEEncryptionAlgorithmType algorithm,
			JWEEncryptionEncodingType encoding) throws KeyRetrievalException, EncryptionOperationException;

	/**
	 * Given a set of JWTClaims and a recipient JWKS uri sign and encrypt the data
	 * and then supply back the compact serialisation
	 * 
	 * @param name                containing the unique key set name to use for
	 *                            signing
	 * @param claims              to sign
	 * @param remoteJwks          of counterparty to sign+encrypt content to
	 * @param signingAlgorithm    to sign the JWT with
	 * @param encryptionAlgorithm to encrypt the signed JWT with
	 * @param encryptionMethod    to use for encryption
	 * @return String containing a compact serialisation of the signed data
	 * @throws SigningOperationException    if signing operation failed
	 * @throws KeyRetrievalException
	 * @throws NotInitialisedException
	 * @throws EncryptionOperationException
	 */
	default String signAndEncrypt(String name, JWKKeyType keyType, JWTClaims claims, URI remoteJwks,
			JWSSigningAlgorithmType signingAlgorithm, JWEEncryptionAlgorithmType encryptionAlgorithm,
			JWEEncryptionEncodingType encryptionMethod) throws SigningOperationException, NotInitialisedException,
			KeyRetrievalException, EncryptionOperationException {
		return encrypt(sign(name, keyType, claims, signingAlgorithm), keyType, remoteJwks, encryptionAlgorithm, encryptionMethod);
	}

	default String signAndEncrypt(URI issuer, JWKKeyType keyType, JWTClaims claims, URI remoteJwks,
			JWSSigningAlgorithmType signingAlgorithm, JWEEncryptionAlgorithmType encryptionAlgorithm,
			JWEEncryptionEncodingType encryptionMethod) throws SigningOperationException, NotInitialisedException,
			KeyRetrievalException, EncryptionOperationException {
		return encrypt(sign(issuer, keyType, claims, signingAlgorithm), keyType, remoteJwks, encryptionAlgorithm, encryptionMethod);
	}

	/**
	 * Initialise Key for a given issuer
	 * 
	 * @param issuer              containing the issuer name
	 * @param type                type of key to produce
	 * @param use                 describing the intended key use
	 * @param signingAlgorithm    containing the Algorithm to use for Signing use
	 * @param encryptionAlgorithm containing the algorithm to use forEencryption use
	 * @return a Babelfish JWK Object with the new key
	 * @throws NotInitialisedException if you are unable to initialise
	 */
	public JWK initKey(String issuer, JWKKeyType type, JWKPublicKeyUse use, JWSSigningAlgorithmType signingAlgorithm,
			JWEEncryptionAlgorithmType encryptionAlgorithm) throws NotInitialisedException;

	default JWK initSigningKey(String issuer, JWKKeyType type, JWSSigningAlgorithmType signingAlgorithm)
			throws NotInitialisedException {
		return initKey(issuer, type, JWKPublicKeyUse.SIGN, signingAlgorithm, null);
	}
	
	default JWK initSigningKey(URI issuer, JWKKeyType type, JWSSigningAlgorithmType signingAlgorithm)
			throws NotInitialisedException {
		return initKey(issuer.toASCIIString(), type, JWKPublicKeyUse.SIGN, signingAlgorithm, null);
	}


	default JWK initEncryptionKey(String issuer, JWKKeyType type, JWEEncryptionAlgorithmType encryptionAlgorithm)
			throws NotInitialisedException {
		return initKey(issuer, type, JWKPublicKeyUse.ENCRYPT, null, encryptionAlgorithm);
	}

	/**
	 * Initialise a new issuer with an empty JWKS
	 * 
	 * @param issuer containing the name of the issuer to issue for
	 * @return JWKS containing an empty key set
	 * @throws NotInitialisedException if unable to initialise 
	 * @throws AlreadyInitialisedException if issuer is already intialised
	 */
	public JWKS createIssuer(String issuer) throws NotInitialisedException, AlreadyInitialisedException;
	
	/**
	 * Initialised a new issuer with a URI
	 * @param issuer containing the issuer base URI
	 * @return JWKS containing an empty key set
	 * @throws NotInitialisedException if unable to intialise
	 * @throws AlreadyInitialisedException if issuer already initialised
	 */
	default JWKS createIssuer(URI issuer) throws NotInitialisedException, AlreadyInitialisedException {
		return createIssuer(issuer.toASCIIString());
	}
	
	/**
	 * Check issuer exists
	 */
	public Boolean existsIssuer(String issuer);
	
	default Boolean existsIssuer(URI issuer) {
		return existsIssuer(issuer.toASCIIString());
	}

	/**
	 * Delete an issuer and all keys
	 * 
	 * @param issuer name to delete
	 */
	public void deleteIssuer(String issuer);

}
