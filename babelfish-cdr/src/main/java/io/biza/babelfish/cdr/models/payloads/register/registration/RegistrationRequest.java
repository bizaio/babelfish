package io.biza.babelfish.cdr.models.payloads.register.registration;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.cdr.converters.EpochToOffsetDateTimeConverter;
import io.biza.babelfish.cdr.converters.OffsetDateTimeToEpochConverter;
import io.biza.babelfish.cdr.converters.UriStringToUriConverter;
import io.biza.babelfish.cdr.converters.UriToUriStringConverter;
import io.biza.babelfish.oidc.enumerations.OIDCResponseType;
import io.biza.babelfish.oidc.enumerations.JWEEncryptionAlgorithmType;
import io.biza.babelfish.oidc.enumerations.JWEEncryptionMethodType;
import io.biza.babelfish.oidc.enumerations.JWSSigningAlgorithmType;
import io.biza.babelfish.oidc.enumerations.OAuth2ResponseType;
import io.biza.babelfish.oidc.enumerations.OIDCApplicationType;
import io.biza.babelfish.oidc.enumerations.OIDCAuthMethod;
import io.biza.babelfish.oidc.enumerations.OIDCGrantType;
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
@Schema(description = "A request for Recipient Registration with a Holder")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegistrationRequest {
  /**
   * As defined in ACCC Register Documentation: https://cdr-register.github.io/register/#registration-request-using-jwt
   */

  @JsonProperty("iss")
  @NotEmpty
  @Schema(description = "Unique identifier for the Data Holder issued by the CDR Register")
  String iss;

  @JsonProperty("iat")
  @JsonSerialize(converter = OffsetDateTimeToEpochConverter.class)
  @JsonDeserialize(converter = EpochToOffsetDateTimeConverter.class)
  @Schema(
      description = "The time at which the request was issued by the TPP expressed as seconds since 1970-01-01T00:00:00Z as measured in UTC")
  OffsetDateTime iat;

  @JsonProperty("exp")
  @JsonSerialize(converter = OffsetDateTimeToEpochConverter.class)
  @JsonDeserialize(converter = EpochToOffsetDateTimeConverter.class)
  @Schema(
      description = "The time at which the request expires expressed as seconds since 1970-01-01T00:00:00Z as measured in UTC")

  OffsetDateTime exp;

  @JsonProperty("jti")
  @NotEmpty
  @Schema(description = "Unique identifier for the JWT, used to prevent replay of the token")
  String jti;

  @JsonProperty("aud")
  @NotEmpty
  @Schema(
      description = "The audience for the request. This should be the Data Holder authorisation server URI")
  @JsonFormat(with = { JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, JsonFormat.Feature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED })
  List<String> audience;

  @JsonProperty("redirect_uris")
  @NotNull
  @NotEmpty
  @Schema(description = "Array of redirection URI strings for use in redirect-based flows, otherwise derived from SSA")
  List<URI> redirectUris;

  @JsonProperty("token_endpoint_auth_signing_alg")
  @NotNull
  @Schema(description = "The algorithm used for signing the JWT")
  JWSSigningAlgorithmType tokenEndpointAuthSigningAlgorithm;
  
  @JsonProperty("token_endpoint_auth_method")
  @NotNull
  @Schema(description = "The requested authentication method for the token endpoint")
  OIDCAuthMethod tokenEndpointAuthMethod;

  @JsonProperty("grant_types")
  @NotEmpty
  @Schema(
      description = "Array of OAuth 2.0 grant type strings that the client can use at the token endpoint")
  List<OIDCGrantType> grantTypes;

  @JsonProperty("response_types")
  @NotNull
  @Schema(
      description = "Array of the OAuth 2.0 response type strings that the client can use at the authorization endpoint.")
  List<OIDCResponseType> responseTypes;

  @JsonProperty("application_type")
  @NotNull
  @Schema(description = "Kind of the application. The only supported application type will be web")
  @Builder.Default
  OIDCApplicationType applicationType = OIDCApplicationType.WEB;

  @JsonProperty("id_token_signed_response_alg")
  @Schema(description = "ID Token JWS Signing Algorithms Supported")
  @NotNull
  JWSSigningAlgorithmType idTokenSigningAlgorithm;

  @JsonProperty("id_token_encrypted_response_alg")
  @Schema(description = "ID Token JWE Encryption Algorithms Supported")
  @NotNull
  JWEEncryptionAlgorithmType idTokenEncryptionAlgorithm;

  @JsonProperty("id_token_encrypted_response_enc")
  @Schema(description = "ID Token JWE Encryption Algorithms Supported")
  @NotNull
  JWEEncryptionMethodType idTokenEncryptionMethod;

  @JsonProperty("request_object_signing_alg")
  @Schema(description = "Request Object Signing Algorithms")
  // TODO: https://github.com/cdr-register/register/issues/79
  @NotNull
  JWSSigningAlgorithmType requestObjectSigningAlgorithm;

  @JsonProperty("software_statement")
  @Schema(description = "The Software Statement Assertion")
  @NotNull
  String ssa;

}
