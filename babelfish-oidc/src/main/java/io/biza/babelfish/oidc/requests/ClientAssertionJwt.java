package io.biza.babelfish.oidc.requests;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.biza.babelfish.oidc.Constants;
import io.biza.babelfish.oidc.payloads.JWTClaims;
import io.biza.babelfish.oidc.payloads.JWTClaims.JWTClaimsBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
