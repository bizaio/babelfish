package io.biza.babelfish.oauth2.payloads;

import java.time.OffsetDateTime;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.common.jackson.EpochToOffsetDateTimeConverter;
import io.biza.babelfish.common.jackson.ListStringToSpaceListConverter;
import io.biza.babelfish.common.jackson.OffsetDateTimeToEpochConverter;
import io.biza.babelfish.common.jackson.SpaceListToListStringConverter;
import io.biza.babelfish.oidc.enumerations.OAuth2TokenType;
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
@Schema(description = "OAuth2 Introspection Response")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IntrospectionResponse {
  /**
   * As defined in RFC7662 Section 2.2: https://tools.ietf.org/html/rfc7662#section-2.2
   */
	
  @JsonProperty("active")
  @NotNull
  @Schema(description = "Token Active Status")
  Boolean active;
  
  /**
   * List of scopes the access token has access to
   */
  @JsonProperty("scope")
  @JsonDeserialize(converter = SpaceListToListStringConverter.class)
  @JsonSerialize(converter = ListStringToSpaceListConverter.class)
  List<String> scope;
  
  @JsonProperty("client_id")
  @Schema(description = "Client identifier of OAuth 2.0 client that requested this token")
  String clientId;

  @JsonProperty("username")
  @Schema(description = "Human-readable identifier for the resource owner who authorized this token.")
  String username;

  @JsonProperty("token_type")
  @Schema(description = "Type of token")
  @Builder.Default
  OAuth2TokenType tokenType = OAuth2TokenType.BEARER;
  
  @JsonProperty("exp")
  @JsonSerialize(converter = OffsetDateTimeToEpochConverter.class)
  @JsonDeserialize(converter = EpochToOffsetDateTimeConverter.class)
  @Schema(
      description = "The time at which the request expires expressed as seconds since 1970-01-01T00:00:00Z as measured in UTC")
  OffsetDateTime exp;  

  @JsonProperty("iat")
  @JsonSerialize(converter = OffsetDateTimeToEpochConverter.class)
  @JsonDeserialize(converter = EpochToOffsetDateTimeConverter.class)
  @Schema(
      description = "The time at which the request was issued by the TPP expressed as seconds since 1970-01-01T00:00:00Z as measured in UTC")
  OffsetDateTime iat;

  @JsonProperty("nbf")
  @JsonSerialize(converter = OffsetDateTimeToEpochConverter.class)
  @JsonDeserialize(converter = EpochToOffsetDateTimeConverter.class)
  @Schema(
      description = "Integer timestamp, measured in the number of seconds since January 1 1970 UTC, indicating when this token is not to be used befor")
  OffsetDateTime nbf;
  
  @JsonProperty("sub")
  @Schema(description = "Subject of the token")
  String sub;
  
  @JsonProperty("aud")
  @Schema(description = "Audience of the token")
  String aud;
  
  @JsonProperty("iss")
  @Schema(description = "Issuer of the token")
  String iss;

  @JsonProperty("jti")
  @NotEmpty
  @Schema(description = "Unique identifier for the JWT, used to prevent replay of the token")
  String jti;

}
