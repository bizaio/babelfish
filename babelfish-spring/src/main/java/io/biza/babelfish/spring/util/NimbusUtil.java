package io.biza.babelfish.spring.util;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTClaimsSet.Builder;
import io.biza.babelfish.oidc.converters.ListStringToSpaceListConverter;
import io.biza.babelfish.oidc.converters.SpaceListToListStringConverter;
import io.biza.babelfish.oidc.payloads.JWTClaims;
import io.biza.babelfish.oidc.payloads.JWTClaims.JWTClaimsBuilder;
import io.biza.babelfish.spring.exceptions.SigningVerificationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NimbusUtil {

  public static ObjectMapper mapper = new ObjectMapper();

  public static JWTClaims fromClaimsSet(JWTClaimsSet inputClaims) {
    try {
      // Ask json-smart to serialise to a JSON String then let Jackson pick it up.
      LOG.trace("Claims data to serialise is: {}", inputClaims.toJSONObject().toJSONString());
      LOG.trace("And jackson is producing: {}", mapper.readValue(inputClaims.toJSONObject().toJSONString(), JWTClaims.class).toString());      
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
          LOG.warn("Scope claim was found but getting it failed, value is {}",
              inputClaims.getClaim("scope"));
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
    Builder claimSet = new JWTClaimsSet.Builder().issuer(claims.issuer()).subject(claims.subject())
        .audience(claims.audience()).expirationTime(Date.from(claims.expiry().toInstant()))
        .notBeforeTime(Date.from(claims.notBefore().toInstant()))
        .issueTime(Date.from(claims.issuedAt().toInstant())).jwtID(claims.jwtId());

    if (claims.scope() != null) {
      claimSet.claim("scope", new ListStringToSpaceListConverter().convert(claims.scope()));
    }

    claims.additionalClaims().forEach((claimName, claimValue) -> {
      claimSet.claim(claimName, claimValue);
    });
    return claimSet.build();
  }

  public static SSLSocketFactory tlsDisableSocketFactory() {

    try {
      SSLContext sslContext = SSLContext.getInstance("SSL");
      sslContext.init(null, new TrustManager[] {new X509TrustManager() {
        @Override
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
          return new java.security.cert.X509Certificate[] {};
        }

        @Override
        public void checkClientTrusted(java.security.cert.X509Certificate[] certs,
            String authType) {}

        @Override
        public void checkServerTrusted(java.security.cert.X509Certificate[] certs,
            String authType) {}
      }}, new java.security.SecureRandom());
      return sslContext.getSocketFactory();
    } catch (NoSuchAlgorithmException | KeyManagementException e) {
      LOG.error("Something went wrong when setting up Trust Manager to allow all certificates", e);
      return null;
    }

  }
  
  public static void checkClaims(JWTClaims inputClaims, JWTClaims claimChecks) throws SigningVerificationException {
			if(claimChecks.issuer() != null) NimbusUtil.checkEquals("issuer", claimChecks.issuer(), inputClaims.issuer());
			if(claimChecks.subject() != null) NimbusUtil.checkEquals("subject", claimChecks.subject(), inputClaims.subject());
			if(claimChecks.audience() != null) NimbusUtil.checkEquals("audience", claimChecks.audience(), inputClaims.audience());
			if(claimChecks.additionalClaims() != null) {
				for(String claim : claimChecks.additionalClaims().keySet()) {
					NimbusUtil.checkEquals(claim, claimChecks.additionalClaims().get(claim), inputClaims.additionalClaims().get(claim));
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
}
