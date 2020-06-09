package io.biza.babelfish.oidc.requests;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.biza.babelfish.oidc.payloads.JWTClaims;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Client Assertion Jwt
 *
 */
@Valid
@Schema(description = "Client Assertion JWT")
@JsonIgnoreProperties(ignoreUnknown = true)
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
  
  @AssertTrue(message = "Subject and Issuer should be the same")
  private Boolean isSubjectAndIssuerTheSame() {
    return issuer() != null ? issuer().equals(subject()) : true;
  }
}
