package io.biza.babelfish.oidc.payloads.cdr;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.biza.babelfish.common.jackson.EpochToOffsetDateTimeConverter;
import io.biza.babelfish.common.jackson.ListStringToSpaceListConverter;
import io.biza.babelfish.common.jackson.OffsetDateTimeToEpochConverter;
import io.biza.babelfish.common.jackson.SpaceListToListStringConverter;
import io.biza.babelfish.common.jackson.UriStringToUriConverter;
import io.biza.babelfish.common.jackson.UriToUriStringConverter;
import io.biza.babelfish.oidc.enumerations.JWEEncryptionAlgorithmType;
import io.biza.babelfish.oidc.enumerations.JWEEncryptionEncodingType;
import io.biza.babelfish.oidc.enumerations.JWSSigningAlgorithmType;
import io.biza.babelfish.oidc.enumerations.OIDCApplicationType;
import io.biza.babelfish.oidc.enumerations.OIDCAuthMethod;
import io.biza.babelfish.oidc.enumerations.OIDCGrantType;
import io.biza.babelfish.oidc.enumerations.OIDCResponseType;
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
@Schema(description = "An individual software product with status")
public class RegistrationPropertiesV1 {

  @JsonProperty("client_id")
  @NotEmpty
  @Schema(description = "Data Holder issued client identifier string")
  String clientId;

  @JsonProperty("client_id_issued_at")
  @JsonSerialize(converter = OffsetDateTimeToEpochConverter.class)
  @JsonDeserialize(converter = EpochToOffsetDateTimeConverter.class)
  @Schema(
      description = "Time at which the client identifier was issued expressed as seconds since 1970-01-01T00:00:00Z as measured in UTC")
  @Builder.Default
  OffsetDateTime issuedAt = OffsetDateTime.now();

  @JsonProperty("client_name")
  @NotEmpty
  @Schema(
      description = "Human-readable string name of the software product to be presented to the end-user during authorization")
  String clientName;

  @JsonProperty("client_description")
  @NotEmpty
  @Schema(
      description = "Human-readable string name of the software product description to be presented to the end user during authorization")
  String clientDescription;

  @JsonProperty("client_uri")
  @NotNull
  @Schema(description = "URL string of a web page providing information about the client")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  @JsonDeserialize(converter = UriStringToUriConverter.class)
  URI clientUri;

  @JsonProperty("org_id")
  @NotEmpty
  @Schema(
      description = "A unique identifier string assigned by the CDR Register that identifies the Accredited Data Recipient Brand")
  String organisationId;

  @JsonProperty("org_name")
  @NotEmpty
  @Schema(
      description = "Human-readable string name of the Accredited Data Recipient to be presented to the end user during authorization")
  String organisationName;

  @JsonProperty("redirect_uris")
  @NotNull
  @Schema(description = "Array of redirection URI strings for use in redirect-based flows")
  List<URI> redirectUris;

  @JsonProperty("logo_uri")
  @NotNull
  @Schema(
      description = "URL string that references a logo for the client. If present, the server SHOULD display this image to the end-user during approval")
  URI logoUri;

  @JsonProperty("tos_uri")
  @NotNull
  @Schema(
      description = "URL string that points to a human-readable terms of service document for the Software Product")
  URI tosUri;

  @JsonProperty("policy_uri")
  @NotNull
  @Schema(
      description = "URL string that points to a human-readable policy document for the Software Product")
  URI policyUri;

  @JsonProperty("jwks_uri")
  @NotNull
  @Schema(
      description = "URL string referencing the client JSON Web Key (JWK) Set [RFC7517] document, which contains the client public keys")
  URI jwksUri;

  @JsonProperty("revocation_uri")
  @NotNull
  @Schema(
      description = "URI string that references the location of the Software Product consent revocation endpoint")
  URI revocationUri;

  @JsonProperty("token_endpoint_auth_method")
  @NotNull
  @Schema(description = "The requested authentication method for the token endpoint")
  OIDCAuthMethod tokenEndpointAuthMethod;

  @JsonProperty("token_endpoint_auth_signing_alg")
  @NotNull
  @Schema(description = "The algorithm used for signing the JWT")
  JWSSigningAlgorithmType tokenEndpointAuthSigningAlgorithm;

  @JsonProperty("grant_types")
  @NotNull
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
  OIDCApplicationType applicationType;

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
  JWEEncryptionEncodingType idTokenEncryptionMethod;

  @JsonProperty("request_object_signing_alg")
  @Schema(description = "Request Object Signing Algorithms")
  // TODO: https://github.com/cdr-register/register/issues/79
  @NotNull
  JWSSigningAlgorithmType requestObjectSigningAlgorithm;

  @JsonProperty("software_statement")
  @Schema(description = "The Software Statement Assertion")
  String ssa;

  @JsonProperty("software_id")
  @Schema(
      description = "String representing a unique identifier assigned by the ACCC Register and used by registration endpoints to identify the software product to be dynamically registered.")
  String softwareId;

  @JsonProperty("scope")
  @NotNull
  @Schema(
      description = "String containing a space-separated list of scope values that the client can use when requesting access tokens.")
  @JsonDeserialize(converter = SpaceListToListStringConverter.class)
  @JsonSerialize(converter = ListStringToSpaceListConverter.class)
  List<String> scope;

}
