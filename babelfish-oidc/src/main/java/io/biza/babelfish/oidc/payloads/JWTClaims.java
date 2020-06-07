package io.biza.babelfish.oidc.payloads;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.biza.babelfish.common.jackson.EpochToOffsetDateTimeConverter;
import io.biza.babelfish.common.jackson.ListStringToSpaceListConverter;
import io.biza.babelfish.common.jackson.OffsetDateTimeToEpochConverter;
import io.biza.babelfish.common.jackson.SpaceListToListStringConverter;
import io.biza.babelfish.oidc.Constants;
import io.biza.babelfish.oidc.payloads.oidc.OIDCClaimMap;
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
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class JWTClaims {

  /**
   * Registered Claim Names RFC7519 4.1 https://tools.ietf.org/html/rfc7519#page-9
   */
  @JsonProperty("iss")
  String issuer;

  @JsonProperty("sub")
  String subject;

  @JsonProperty("aud")
  @JsonFormat(with = { JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, JsonFormat.Feature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED })
  List<String> audience;

  @JsonProperty("exp")
  @JsonSerialize(converter = OffsetDateTimeToEpochConverter.class)
  @JsonDeserialize(converter = EpochToOffsetDateTimeConverter.class)
  @Builder.Default
  OffsetDateTime expiry = OffsetDateTime.now().plusMinutes(Constants.DEFAULT_MINUTES_EXPIRY);

  @JsonProperty("nbf")
  @JsonSerialize(converter = OffsetDateTimeToEpochConverter.class)
  @JsonDeserialize(converter = EpochToOffsetDateTimeConverter.class)
  @Builder.Default
  OffsetDateTime notBefore =
      OffsetDateTime.now().minusMinutes(Constants.DEFAULT_MINUTES_NOT_BEFORE);

  @JsonProperty("iat")
  @JsonSerialize(converter = OffsetDateTimeToEpochConverter.class)
  @JsonDeserialize(converter = EpochToOffsetDateTimeConverter.class)
  @Builder.Default
  OffsetDateTime issuedAt = OffsetDateTime.now();

  @JsonProperty("jti")
  String jwtId;
  
  @JsonProperty("scope")
  @JsonSerialize(converter = ListStringToSpaceListConverter.class)
  @JsonDeserialize(converter = SpaceListToListStringConverter.class)
  List<String> scope;
  
  @JsonProperty("claims")
  OIDCClaimMap claims;
  
  /**
   * A catch all Map for all other additional claims
   */
  @JsonAnySetter
  @Getter(onMethod_ = {@JsonAnyGetter})
  @Builder.Default
  Map<String, Object> additionalClaims = new HashMap<String, Object>();

  /**
   * Convenience functions
   */
  public static class JWTClaimsBuilder {
    public JWTClaimsBuilder issuerByUUID(UUID issuer) {
      if (issuer != null) {
        this.issuer = issuer.toString();
      }
      return this;
    }

    public JWTClaimsBuilder issuerByURI(URI issuer) {
      if (issuer != null) {
        this.issuer = issuer.toString();
      }
      return this;
    }

    public JWTClaimsBuilder subjectByUUID(UUID subject) {
      if (subject != null) {
        this.subject = subject.toString();
      }
      return this;
    }
    
    public JWTClaimsBuilder jwtIdByUUID(UUID jwtId) {
      if (jwtId != null) {
        this.jwtId = jwtId.toString();
      }
      return this;
    }


    public JWTClaimsBuilder subjectByURI(URI subject) {
      if (subject != null) {
        this.subject = subject.toString();
      }
      return this;
    }
    
    public JWTClaimsBuilder audience(String audience) {
      if (audience != null) {
        this.audience = List.of(audience);
      }
      return this;
    }
    
    public JWTClaimsBuilder audience(List<String> audience) {
      if (audience != null) {
        this.audience = audience;
      }
      return this;
    }
    
    public JWTClaimsBuilder audience(URI audience) {
      if (audience != null) {
        this.audience = List.of(audience.toString());
      }
      return this;
    }

  }
  
  /**
   * Convenience methods
   */
  @JsonIgnore
  public JWTClaims issuerByURI(URI issuer) {
    if (issuer != null) {
      issuer(issuer.toString());
    }
    return this;
  }

  /**
   * Verification Assertions
   */
  @AssertTrue(
      message = "When defined exp must be in the future (1 min skew allowance), see RFC7519: https://tools.ietf.org/html/rfc7519#page-9")
  private Boolean isExpiryTimeFutureDated() {
    return expiry() != null ? expiry().isAfter(OffsetDateTime.now().minusMinutes(1)) : true;
  }

  @AssertTrue(
      message = "When defined nbf must be in the past (1 min skew allowance), see RFC7519: https://tools.ietf.org/html/rfc7519#page-9")
  private Boolean isNotBeforePastDated() {
    return notBefore() != null ? notBefore().isBefore(OffsetDateTime.now().plusMinutes(1)) : true;
  }



}
