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

  @AssertTrue(message = "iss must be populated, see RFC7523 Section 3: https://tools.ietf.org/html/rfc7523#page-5")
  private Boolean isRfc7523IssPopulated() {
    return iss() != null && iss().length() > 0;
  }

  @AssertTrue(message = "aud must be populated, see RFC7523 Section 3: https://tools.ietf.org/html/rfc7523#page-5")
  private Boolean isRfc7523AudPopulated() {
    return aud() != null && aud().length() > 0;
  }
  
  @AssertTrue(message = "sub must be populated, see RFC7523 Section 3: https://tools.ietf.org/html/rfc7523#page-5")
  private Boolean isRfc7523SubPopulated() {
    return sub() != null && sub().length() > 0;
  }

  @AssertTrue(message = "exp must be populated, see RFC7523 Section 3: https://tools.ietf.org/html/rfc7523#page-5")
  private Boolean isRfc7523ExpPopulated() {
    return exp() != null;
  }
  
  @AssertTrue(message = "nbf must be populated, see RFC7523 Section 3: https://tools.ietf.org/html/rfc7523#page-5")
  private Boolean isRfc7523NbfPopulated() {
    return nbf() != null;
  }
}
