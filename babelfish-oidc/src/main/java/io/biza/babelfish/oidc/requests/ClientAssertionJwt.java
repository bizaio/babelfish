package io.biza.babelfish.oidc.requests;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import io.biza.babelfish.oidc.payloads.JWTClaims;
import lombok.Builder;
import lombok.Data;

/**
 * Client Assertion Jwt
 * 
 * A container for a jose4j JwtClaims object with a conformance validator for mandatory components
 * as specified in RFC7523
 * 
 * 
 *
 */
@Valid
public class ClientAssertionJwt extends JWTClaims {

  @AssertTrue(message = "issuer must be populated, see RFC7523 Section 3: https://tools.ietf.org/html/rfc7523#page-5")
  private Boolean isRfc7523issuerPopulated() {
    return issuer() != null && issuer().length() > 0;
  }

  @AssertTrue(message = "audience must be populated, see RFC7523 Section 3: https://tools.ietf.org/html/rfc7523#page-5")
  private Boolean isRfc7523audiencePopulated() {
    return audience() != null && audience().size() > 0;
  }
  
  @AssertTrue(message = "subject must be populated, see RFC7523 Section 3: https://tools.ietf.org/html/rfc7523#page-5")
  private Boolean isRfc7523subjectPopulated() {
    return subject() != null && subject().length() > 0;
  }

  @AssertTrue(message = "expiry must be populated, see RFC7523 Section 3: https://tools.ietf.org/html/rfc7523#page-5")
  private Boolean isRfc7523expiryPopulated() {
    return expiry() != null;
  }
  
  @AssertTrue(message = "notBefore must be populated, see RFC7523 Section 3: https://tools.ietf.org/html/rfc7523#page-5")
  private Boolean isRfc7523notBeforePopulated() {
    return notBefore() != null;
  }
  
  public static ClientAssertionJwt from(JWTClaims claims) {
    return (ClientAssertionJwt) claims;
  }
}
