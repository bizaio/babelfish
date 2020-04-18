package io.biza.babelfish.spring.interfaces;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import io.biza.babelfish.oidc.enumerations.JWEEncryptionAlgorithmType;
import io.biza.babelfish.oidc.enumerations.JWEEncryptionEncodingType;
import io.biza.babelfish.oidc.enumerations.JWSSigningAlgorithmType;
import io.biza.babelfish.oidc.exceptions.KeyRetrievalException;
import io.biza.babelfish.oidc.payloads.JWKS;
import io.biza.babelfish.oidc.payloads.JWTClaims;
import io.biza.babelfish.spring.exceptions.EncryptionOperationException;
import io.biza.babelfish.spring.exceptions.NotInitialisedException;
import io.biza.babelfish.spring.exceptions.SigningOperationException;

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
	public String sign(String name, JWTClaims claims, JWSSigningAlgorithmType algorithm)
			throws SigningOperationException, NotInitialisedException;

	default String sign(URI name, JWTClaims claims, JWSSigningAlgorithmType algorithm)
			throws SigningOperationException, NotInitialisedException {
		return sign(name.toASCIIString(), claims, algorithm);
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
	public String encrypt(String payload, URI remoteJwks, JWEEncryptionAlgorithmType algorithm,
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
	default String signAndEncrypt(String name, JWTClaims claims, URI remoteJwks,
			JWSSigningAlgorithmType signingAlgorithm, JWEEncryptionAlgorithmType encryptionAlgorithm,
			JWEEncryptionEncodingType encryptionMethod) throws SigningOperationException, NotInitialisedException,
			KeyRetrievalException, EncryptionOperationException {
		return encrypt(sign(name, claims, signingAlgorithm), remoteJwks, encryptionAlgorithm, encryptionMethod);
	}

	default String signAndEncrypt(URI issuer, JWTClaims claims, URI remoteJwks,
			JWSSigningAlgorithmType signingAlgorithm, JWEEncryptionAlgorithmType encryptionAlgorithm,
			JWEEncryptionEncodingType encryptionMethod) throws SigningOperationException, NotInitialisedException,
			KeyRetrievalException, EncryptionOperationException {
		return encrypt(sign(issuer, claims, signingAlgorithm), remoteJwks, encryptionAlgorithm, encryptionMethod);
	}

}
