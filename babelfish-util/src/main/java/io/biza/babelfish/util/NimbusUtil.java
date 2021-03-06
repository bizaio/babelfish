package io.biza.babelfish.util;

import java.io.IOException;
import java.net.URI;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.text.ParseException;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.ECDSAVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKMatcher;
import com.nimbusds.jose.jwk.JWKSelector;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.KeyType;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import io.biza.babelfish.common.exceptions.KeyRetrievalException;
import io.biza.babelfish.common.exceptions.SigningVerificationException;
import io.biza.babelfish.common.jackson.ListStringToSpaceListConverter;
import io.biza.babelfish.common.jackson.SpaceListToListStringConverter;
import io.biza.babelfish.oidc.Messages;
import io.biza.babelfish.oidc.enumerations.JWKPublicKeyUse;
import io.biza.babelfish.oidc.enumerations.JWSSigningAlgorithmType;
import io.biza.babelfish.oidc.enumerations.JWTPeekAttribute;
import io.biza.babelfish.oidc.payloads.JWKS;
import io.biza.babelfish.oidc.payloads.JWTClaims;
import io.biza.babelfish.oidc.payloads.JWTClaims.JWTClaimsBuilder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NimbusUtil {

	public static ObjectMapper mapper = new ObjectMapper();

	public static JWTClaims fromClaimsSet(JWTClaimsSet inputClaims) {
		try {
			// Ask json-smart to serialise to a JSON String then let Jackson pick it up.
			LOG.debug("Claims data to serialise is: {}", inputClaims.toJSONObject().toJSONString());
			LOG.debug("And jackson is producing: {}",
					mapper.readValue(inputClaims.toJSONObject().toJSONString(), JWTClaims.class).toString());
			return mapper.readValue(inputClaims.toJSONObject().toJSONString(), JWTClaims.class);
		} catch (JsonProcessingException jacksonFailure) {
			LOG.error(
					"Encountered a conversion error between JWTClaimsSet and JWTClaims, this should never happen yet somehow it did!");
			LOG.warn("Using manual conversion, results may vary!");
			LOG.warn("JSON Processing exception is as follows", jacksonFailure);

			JWTClaimsBuilder outputClaims = JWTClaims.builder();
			outputClaims.issuer(inputClaims.getIssuer());
			outputClaims.subject(inputClaims.getSubject());
			outputClaims.audience(inputClaims.getAudience());
			outputClaims.jwtId(inputClaims.getJWTID());
			if (inputClaims.getExpirationTime() != null) {
				outputClaims.expiry(inputClaims.getExpirationTime().toInstant().atOffset(ZoneOffset.UTC));
			}
			if (inputClaims.getNotBeforeTime() != null) {
				outputClaims.notBefore(inputClaims.getNotBeforeTime().toInstant().atOffset(ZoneOffset.UTC));
			}
			if (inputClaims.getIssueTime() != null) {
				outputClaims.issuedAt(inputClaims.getIssueTime().toInstant().atOffset(ZoneOffset.UTC));
			}

			Set<String> registeredClaims = JWTClaimsSet.getRegisteredNames();
			Map<String, Object> additionalClaims = new HashMap<String, Object>();

			if (inputClaims.getClaims().containsKey("scope")) {
				try {
					String scopeString = inputClaims.getStringClaim("scope");
					outputClaims.scope(new SpaceListToListStringConverter().convert(scopeString));
				} catch (ParseException e) {
					LOG.warn("Scope claim was found but getting it failed, value is {}", inputClaims.getClaim("scope"));
				}
			}

			inputClaims.getClaims().forEach((claimName, claimValue) -> {
				if (!registeredClaims.contains(claimName)) {
					additionalClaims.put(claimName, claimValue);
				}
			});

			return outputClaims.additionalClaims(additionalClaims).build();
		}

	}

	public static JWTClaimsSet toClaimsSet(JWTClaims claims) {
		com.nimbusds.jwt.JWTClaimsSet.Builder claimSet = new JWTClaimsSet.Builder().issuer(claims.issuer())
				.subject(claims.subject()).audience(claims.audience())
				.expirationTime(Date.from(claims.expiry().toInstant()))
				.notBeforeTime(Date.from(claims.notBefore().toInstant()))
				.issueTime(Date.from(claims.issuedAt().toInstant())).jwtID(claims.jwtId());

		if (claims.scope() != null) {
			claimSet.claim("scope", new ListStringToSpaceListConverter().convert(claims.scope()));
		}

		claims.additionalClaims().forEach((claimName, claimValue) -> {
			claimSet.claim(claimName, claimValue);
		});

		JWTClaimsSet claimsSetResult = claimSet.build();
		LOG.debug(MessageUtil.format(Messages.CLAIMS_SET_CONVERSION_PRODUCED_X, claimsSetResult));
		return claimsSetResult;
	}

	public static SSLSocketFactory tlsDisableSocketFactory() {

		LOG.debug(Messages.ATTEMPTING_TO_DISABLE_TLS_VERIFICATION);
		try {
			SSLContext sslContext = SSLContext.getInstance("SSL");
			sslContext.init(null, new TrustManager[] { new X509TrustManager() {
				@Override
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return new java.security.cert.X509Certificate[] {};
				}

				@Override
				public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
				}

				@Override
				public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
				}
			} }, new java.security.SecureRandom());
			return sslContext.getSocketFactory();
		} catch (NoSuchAlgorithmException | KeyManagementException e) {
			LOG.error("Something went wrong when setting up Trust Manager to allow all certificates", e);
			return null;
		}

	}

	public static void checkClaims(JWTClaims inputClaims, JWTClaims claimChecks) throws SigningVerificationException {
		if (claimChecks.issuer() != null)
			NimbusUtil.checkEquals("issuer", claimChecks.issuer(), inputClaims.issuer());
		if (claimChecks.subject() != null)
			NimbusUtil.checkEquals("subject", claimChecks.subject(), inputClaims.subject());
		if (claimChecks.expiry() != null)
			if (claimChecks.expiry().isBefore(inputClaims.expiry())) {
				throw SigningVerificationException.builder().message("Verification of expiration time claim failed "
						+ inputClaims.expiry() + " is after " + claimChecks.expiry()).build();
			}
		if (claimChecks.audience() != null)
			NimbusUtil.checkEquals("audience", claimChecks.audience(), inputClaims.audience());
		if (claimChecks.additionalClaims() != null) {
			for (String claim : claimChecks.additionalClaims().keySet()) {
				NimbusUtil.checkEquals(claim, claimChecks.additionalClaims().get(claim),
						inputClaims.additionalClaims().get(claim));
			}
		}
	}

	public static <T> void checkEquals(String name, T classOne, T classTwo) throws SigningVerificationException {
		if (classOne == null)
			return;
		if (!classOne.equals(classTwo)) {
			throw SigningVerificationException.builder()
					.message("Verification of " + name + " required claim failed: " + classOne + " versus " + classTwo)
					.build();
		}
	}

	public static JWTClaims verifyHmac(String compactSerialisation, String hmacSecret, JWTClaims claimChecks)
			throws SigningVerificationException {
		try {
			SignedJWT jwt = SignedJWT.parse(compactSerialisation);
			JWSVerifier verifier = new MACVerifier(hmacSecret);

			if (jwt.verify(verifier)) {
				JWTClaims inputClaims = NimbusUtil.fromClaimsSet(jwt.getJWTClaimsSet());

				if (claimChecks != null) {
					NimbusUtil.checkClaims(inputClaims, claimChecks);
				}
				return inputClaims;
			} else {
				LOG.warn("Unable to verify supplied JWT using specified secret");
				throw SigningVerificationException.builder().message("Unable to verify HMAC Signed JWT").build();
			}
		} catch (ParseException e) {
			LOG.error("Unable to parse supplied JWT: {}", e.getMessage(), e);
			throw SigningVerificationException.builder().message(e.getMessage()).build();
		} catch (JOSEException e) {
			LOG.warn("Encountered a JOSEException while attempting to verify hmac signed JWT", e);
			throw SigningVerificationException.builder().message(e.getMessage()).build();
		}
	}

	public static JWTClaims verify(String compactSerialisation, URI jwksUri, JWTClaims claimChecks)
			throws SigningVerificationException, KeyRetrievalException {
		try {
			SignedJWT jwt = SignedJWT.parse(compactSerialisation);

			JWK remoteJwk = getRemoteKey(jwksUri, getJwkMatcher(jwt, KeyUse.SIGNATURE));
			return verify(jwt, remoteJwk, claimChecks);
		} catch (ParseException e) {
			LOG.error("Unable to parse supplied JWT: {}", e.getMessage(), e);
			throw SigningVerificationException.builder().message(e.getMessage()).build();
		}
	}

	public static Boolean validate(String compactSerialisation, JWKS jwks, JWTClaims claimChecks) {
		try {
			SignedJWT jwt = SignedJWT.parse(compactSerialisation);

			List<JWK> matches = new JWKSelector(getJwkMatcher(jwt, KeyUse.SIGNATURE))
					.select(JWKSet.parse(StaticObjectMapper.toJSON(jwks)));
			if (matches.size() > 0) {
				try {
					verify(jwt, matches.get(0), claimChecks);
					return true;
				} catch (SigningVerificationException | KeyRetrievalException e) {
					return false;
				}
			} else {
				return false;
			}
		} catch (ParseException e) {
			return false;
		}
	}
	
	public static JWTClaims verify(SignedJWT jwt, JWK jwk, JWTClaims claimChecks)
			throws SigningVerificationException, KeyRetrievalException {

		try {
			JWSVerifier verifier;
			if (jwk.getKeyType().equals(KeyType.RSA)) {
				verifier = new RSASSAVerifier(jwk.toRSAKey());
			} else if (jwk.getKeyType().equals(KeyType.EC)) {
				verifier = new ECDSAVerifier(jwk.toECKey());
			} else {
				throw SigningVerificationException.builder().message(
						MessageFormat.format(Messages.UNSUPPORTED_KEY_TYPE_WITH_VALUE, jwk.getKeyType().toString()))
						.build();
			}

			if (jwt.verify(verifier)) {
				JWTClaims inputClaims = NimbusUtil.fromClaimsSet(jwt.getJWTClaimsSet());

				if (claimChecks != null) {
					NimbusUtil.checkClaims(inputClaims, claimChecks);
				}
				return inputClaims;
			} else {
				LOG.warn("Received signing verification error for kid of {}", jwk.getKeyID());
				throw SigningVerificationException.builder()
						.message("Unable to verify serialisation using kid " + jwk.getKeyID()).build();
			}
		} catch (ParseException e) {
			LOG.error("Unable to parse supplied JWT: {}", e.getMessage(), e);
			throw SigningVerificationException.builder().message(e.getMessage()).build();

		} catch (JOSEException e) {
			LOG.warn("Encountered a JOSEException while attempting to verify payload {}", jwt.serialize(), e);
			throw SigningVerificationException.builder().message(e.getMessage()).build();
		}

	}

	/**
	 * Parse a compact serialisation and return an attribute within it from a list
	 * of known Peekable attributes
	 * 
	 * @param compactSerialisation containing a JWT serialisation
	 * @param JWTPeekAttribute     of value to return
	 * @return String of claim value
	 * @throws ParseException if parsing failed
	 */
	public static String peekAt(String compactSerialisation, JWTPeekAttribute attribute) throws ParseException {
		SignedJWT inputJwt = SignedJWT.parse(compactSerialisation);
		if (attribute.equals(JWTPeekAttribute.ISSUER)) {
			return inputJwt.getJWTClaimsSet().getIssuer();
		} else {
			return inputJwt.getJWTClaimsSet().getStringClaim(attribute.value());
		}
	}

	/**
	 * Given a compact serialisation make sure we can parse it
	 * 
	 * NOTE: Does NOT perform signing verification
	 * 
	 * @param compactSerialisation containing JWT
	 * @return boolean if it is parseable
	 */
	public static boolean canParse(String compactSerialisation) {
		try {
			SignedJWT.parse(compactSerialisation);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	public static String getSignature(String compactSerialisation) throws ParseException {
		SignedJWT jwt = SignedJWT.parse(compactSerialisation);
		return jwt.getSignature().toString();
	}

	/**
	 * Given a URI return a JWKSet
	 * 
	 * @param jwksUri containing the JWKS url
	 * @return a JWKSet
	 * @throws KeyRetrievalException if we are unable to retrieve the jwks
	 */
	public static JWKSet getRemoteJwks(URI jwksUri) throws KeyRetrievalException {
		try {
			return JWKSet.load(jwksUri.toURL());
		} catch (IOException | ParseException e) {
			LOG.error(Messages.FAILED_TO_RETRIEVE_JWKS, jwksUri, e);
			throw KeyRetrievalException.builder().message(e.getMessage()).build();
		}
	}

	public static JWK getRemoteKey(URI jwksUri, JWKMatcher matcher) throws KeyRetrievalException {
		List<JWK> matches = new JWKSelector(matcher).select(getRemoteJwks(jwksUri));
		if (matches.size() > 0) {
			return matches.get(0);
		} else {
			throw KeyRetrievalException.builder()
					.message(MessageUtil.format(Messages.UNABLE_TO_FIND_KEY_WITH_DETAIL, jwksUri, matcher.toString()))
					.build();
		}
	}

	public static JWKMatcher getJwkMatcher(SignedJWT jwt, KeyUse keyUse) {
		com.nimbusds.jose.jwk.JWKMatcher.Builder matcher = new JWKMatcher.Builder()
				.algorithm(jwt.getHeader().getAlgorithm()).keyUse(keyUse);
		/**
		 * Filter to Key ID if it's available
		 */
		if (jwt.getHeader().getKeyID() != null) {
			matcher.keyID(jwt.getHeader().getKeyID());
		}
		return matcher.build();
	}

	public static JWKMatcher getJwkMatcher(JWSSigningAlgorithmType algorithm, JWKPublicKeyUse keyUse) {
		com.nimbusds.jose.jwk.JWKMatcher.Builder matcher = new JWKMatcher.Builder().algorithm(algorithm.toNimbus())
				.keyUse(keyUse.toNimbus());
		return matcher.build();
	}

	public static JWK getRemoteKey(URI jwksUri, String keyId, KeyUse keyUse, JWSSigningAlgorithmType algorithm)
			throws KeyRetrievalException {

		JWKSet remoteJwks = getRemoteJwks(jwksUri);

		LOG.debug(Messages.KEY_SELECT_WITH_USE_ALG_KEYID_JWKS, keyUse.toString(), algorithm.toString(), keyId,
				remoteJwks.toPublicJWKSet().toJSONObject().toJSONString());

		List<JWK> matches = new JWKSelector(new JWKMatcher.Builder().keyUse(keyUse).keyID(keyId).build())
				.select(remoteJwks);

		LOG.debug(Messages.MATCHED_JWK, matches.toString());
		if (matches.size() > 0) {
			return matches.get(0).toPublicJWK();
		} else {
			throw KeyRetrievalException.builder()
					.message(MessageFormat.format(Messages.UNABLE_TO_FIND_KEY_WITH_DETAIL, algorithm, jwksUri)).build();
		}
	}
}
