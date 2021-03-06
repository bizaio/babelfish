package io.biza.babelfish.cdr.models.payloads.register;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.biza.babelfish.cdr.enumerations.register.RegisterSoftwareRole;
import io.biza.babelfish.common.jackson.EpochToOffsetDateTimeConverter;
import io.biza.babelfish.common.jackson.ListStringToSpaceListConverter;
import io.biza.babelfish.common.jackson.OffsetDateTimeToEpochConverter;
import io.biza.babelfish.common.jackson.SpaceListToListStringConverter;
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
@Schema(description = "A Software Statement issued by the Register")
public class SoftwareStatementAssertionV1 {
  
  @JsonProperty("iss")
  @NotEmpty
  @Schema(description = "The Register Issuer Identifier")
  String iss;

  @JsonProperty("iat")
  @JsonSerialize(converter = OffsetDateTimeToEpochConverter.class)
  @JsonDeserialize(converter = EpochToOffsetDateTimeConverter.class)
  @Builder.Default
  @Schema(
      description = "The time at which the request was issued by the Recipient expressed as seconds since 1970-01-01T00:00:00Z as measured in UTC")
  OffsetDateTime iat = OffsetDateTime.now();

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
  URI clientUri;

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
  
  
  @JsonProperty("software_id")
  @Schema(
      description = "String representing a unique identifier assigned by the ACCC Register and used by registration endpoints to identify the software product to be dynamically registered.")
  String softwareId;
  
  @JsonProperty("software_roles")
  @NotNull
  RegisterSoftwareRole softwareRole;

  @JsonProperty("scope")
  @NotNull
  @Schema(
      description = "String containing a space-separated list of scope values that the client can use when requesting access tokens.")
  @JsonDeserialize(converter = SpaceListToListStringConverter.class)
  @JsonSerialize(converter = ListStringToSpaceListConverter.class)
  List<String> scope;

}
