package io.biza.babelfish.spring.interfaces;

import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.KeyType;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTClaimsSet.Builder;
import io.biza.babelfish.oidc.enumerations.JWSSigningAlgorithmType;
import io.biza.babelfish.oidc.payloads.JWTClaims;
import io.biza.babelfish.oidc.payloads.JWTClaims.JWTClaimsBuilder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NimbusUtil {
  
  public static JWTClaims fromClaimsSet(JWTClaimsSet claimsSet) {
    JWTClaimsBuilder claims = JWTClaims.builder();
    claims.issuer(claimsSet.getIssuer());
    claims.subject(claimsSet.getSubject());
    claims.audience(claimsSet.getAudience());
    claims.jwtId(claimsSet.getJWTID());
    if(claimsSet.getExpirationTime() != null) {
      claims.expiry(claimsSet.getExpirationTime().toInstant().atOffset(ZoneOffset.UTC));
    }
    if(claimsSet.getNotBeforeTime() != null) {
      claims.notBefore(claimsSet.getNotBeforeTime().toInstant().atOffset(ZoneOffset.UTC));
    }
    if(claimsSet.getIssueTime() != null) {
      claims.issuedAt(claimsSet.getIssueTime().toInstant().atOffset(ZoneOffset.UTC));
    }
    
    Set<String> registeredClaims = JWTClaimsSet.getRegisteredNames();
    Map<String, Object> additionalClaims = new HashMap<String, Object>();
    claimsSet.getClaims().forEach((claimName, claimValue) -> {
       if(!registeredClaims.contains(claimName)) {
        additionalClaims.put(claimName, claimValue); 
       }
    });
    return claims.additionalClaims(additionalClaims).build();
    
  }

  public static JWTClaimsSet toClaimsSet(JWTClaims claims) {
    Builder claimSet = new JWTClaimsSet.Builder().issuer(claims.issuer())
        .subject(claims.subject()).audience(claims.audience())
        .expirationTime(Date.from(claims.expiry().toInstant()))
        .notBeforeTime(Date.from(claims.expiry().toInstant()))
        .issueTime(Date.from(claims.expiry().toInstant()))
        .jwtID(claims.jwtId());
    
    claims.additionalClaims().forEach((claimName, claimValue) -> {
      claimSet.claim(claimName, claimValue);
    });
    return claimSet.build();
  }
}
