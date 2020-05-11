package io.biza.babelfish.oidc.payloads;

import java.time.OffsetDateTime;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.biza.babelfish.common.jackson.FutureSecondsToOffsetDateTimeConverter;
import io.biza.babelfish.common.jackson.ListStringToSpaceListConverter;
import io.biza.babelfish.common.jackson.OffsetDateTimeToFutureSecondsConverter;
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
@Schema(description = "OAuth 2.0 Token Response")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenResponse {


  /**
   * Access token retrieved from the authorisation server
   */
  @JsonProperty("access_token")
  @NotNull
  String accessToken;

  /**
   * Access Token Type
   */
  @JsonProperty("token_type")
  @NotNull
  @Builder.Default
  OAuth2TokenType tokenType = OAuth2TokenType.BEARER;

  /**
   * Access Token expiry time
   * 
   * NOTE: Conversion to Java OffsetDateTime internally for operations to be easier
   */
  @JsonProperty("expires_in")
  @JsonDeserialize(converter = FutureSecondsToOffsetDateTimeConverter.class)
  @JsonSerialize(converter = OffsetDateTimeToFutureSecondsConverter.class)
  OffsetDateTime expiresAt;

  /**
   * Refresh token to use (if available) to retrieve a new access token
   */
  @JsonProperty("refresh_token")
  String refreshToken;

  /**
   * Refresh token expiry time
   * 
   * NOTE: Conversion to Java OffsetDateTime internally for operations to be easier
   */
  @JsonProperty("refresh_expires_in")
  @JsonDeserialize(converter = FutureSecondsToOffsetDateTimeConverter.class)
  @JsonSerialize(converter = OffsetDateTimeToFutureSecondsConverter.class)
  OffsetDateTime refreshExpiresAt;
  
  /**
   * ID Token
   */
  @JsonProperty("id_token")
  String idToken;
  
  /**
   * List of scopes the access token has access to
   * 
   * NOTE: Within thumb we do a transform to/from a java List<String> so we can do simpler
   * interrogation
   */
  @JsonProperty("scope")
  @JsonDeserialize(converter = SpaceListToListStringConverter.class)
  @JsonSerialize(converter = ListStringToSpaceListConverter.class)
  List<String> scope;

}
