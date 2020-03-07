package io.biza.babelfish.oidc.payloads;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.oidc.converters.EpochToOffsetDateTimeConverter;
import io.biza.babelfish.oidc.converters.OffsetDateTimeToEpochConverter;
import io.biza.babelfish.oidc.payloads.JWKS.JWKSBuilder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Valid
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "JSON Web Token Claims")
@JsonIgnoreProperties(ignoreUnknown = true)
public class JWTClaims {

  /**
   * Registered Claim Names RFC7519 4.1
   * https://tools.ietf.org/html/rfc7519#page-9
   */
  @JsonProperty("iss")
  String iss;
  
  @JsonProperty("sub")
  String sub;
  
  @JsonProperty("aud")
  String aud;
  
  @JsonProperty("exp")
  @JsonSerialize(converter = OffsetDateTimeToEpochConverter.class)
  @JsonDeserialize(converter = EpochToOffsetDateTimeConverter.class)
  OffsetDateTime exp;

  @JsonProperty("nbf")
  @JsonSerialize(converter = OffsetDateTimeToEpochConverter.class)
  @JsonDeserialize(converter = EpochToOffsetDateTimeConverter.class)
  OffsetDateTime nbf;
 
  @JsonProperty("iat")
  @JsonSerialize(converter = OffsetDateTimeToEpochConverter.class)
  @JsonDeserialize(converter = EpochToOffsetDateTimeConverter.class)
  OffsetDateTime iat;
  
  @JsonProperty("jti")
  String jti;
  
  /**
   * A catch all Map for all other additional claims
   */
  @JsonAnySetter
  @Getter(onMethod_={@JsonAnyGetter})
  @Builder.Default
  Map<String,Object> additionalClaims = new HashMap<String,Object>();
  
  
  /**
   * Verification Assertions
   */
  @AssertTrue(message = "When defined exp must be in the future (1 min skew allowance), see RFC7519: https://tools.ietf.org/html/rfc7519#page-9")
  private Boolean isExpiryTimeFutureDated() {
      return exp() != null ? exp().isAfter(OffsetDateTime.now().minusMinutes(1)) : true;
  }
  
  @AssertTrue(message = "When defined nbf must be in the past (1 min skew allowance), see RFC7519: https://tools.ietf.org/html/rfc7519#page-9")
  private Boolean isNotBeforePastDated() {
      return nbf() != null ? nbf().isBefore(OffsetDateTime.now().plusMinutes(1)) : true;
  }

  
  
  
}
