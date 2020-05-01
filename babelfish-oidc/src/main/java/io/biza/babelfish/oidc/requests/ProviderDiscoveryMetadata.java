package io.biza.babelfish.oidc.requests;

import java.net.URI;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.biza.babelfish.oidc.enumerations.JWEEncryptionAlgorithmType;
import io.biza.babelfish.oidc.enumerations.JWEEncryptionEncodingType;
import io.biza.babelfish.oidc.enumerations.JWSSigningAlgorithmType;
import io.biza.babelfish.oidc.enumerations.OAuth2PKCEType;
import io.biza.babelfish.oidc.enumerations.OIDCAuthMethod;
import io.biza.babelfish.oidc.enumerations.OIDCClaimType;
import io.biza.babelfish.oidc.enumerations.OIDCDisplayType;
import io.biza.babelfish.oidc.enumerations.OIDCGrantType;
import io.biza.babelfish.oidc.enumerations.OIDCResponseMode;
import io.biza.babelfish.oidc.enumerations.OIDCSubjectType;
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
@Schema(description = "OpenID Provider Discovery Document")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProviderDiscoveryMetadata {
  @JsonProperty("issuer")
  @NotNull
  @Schema(
      description = "URL using the https scheme with no query or fragment component that the OP asserts as its Issuer Identifier. If Issuer discovery is supported (see Section 2), this value MUST be identical to the issuer value returned by WebFinger. This also MUST be identical to the iss Claim value in ID Tokens issued from this Issuer. ")
  URI issuer;

  @JsonProperty("authorization_endpoint")
  @NotNull
  @Schema(description = "URL of the OP's Oauth 2.0 Authorisation Endpoint")
  URI authorisationEndpoint;

  @JsonProperty("token_endpoint")
  @Schema(description = "URL of the OP's OAuth 2.0 Token Endpoint")
  URI tokenEndpoint;

  @JsonProperty("userinfo_endpoint")
  @Schema(description = "URL of the OP's UserInfo Endpoint")
  URI userInfoEndpoint;

  @JsonProperty("jwks_uri")
  @Schema(description = "URL of the OP's JSON Web Key Set document")
  URI jwksUri;

  @JsonProperty("registration_endpoint")
  @Schema(description = "URL of the OP's Dynamic Client Registration Endpoint")
  URI registrationEndpoint;
  
  @JsonProperty("introspection_endpoint")
  @Schema(description = "URL of the OP's Introspection Endpoint")
  URI introspectionEndpoint;
  
  @JsonProperty("revocation_endpoint")
  @Schema(description = "URL of the OP's Revocation Endpoint")
  // NOTE: This endpoint is not formally part of the OIDC spec but is widely accepted
  URI revocationEndpoint;

  @JsonProperty("scopes_supported")
  @Schema(
      description = "JSON array containing a list of OAuth 2.0 scope values that this server supports")
  @Builder.Default
  List<String> scopesSupported = List.of("openid", "profile");

  @JsonProperty("response_types_supported")
  @Schema(description = "OAuth2 Response Types supported")
  Set<String> responseTypesSupported;

  @JsonProperty("response_modes_supported")
  @Schema(description = "OAuth2 Response Modes supported")
  List<OIDCResponseMode> responseModesSupported;
  
  @JsonProperty("grant_types_supported")
  @Schema(description = "OAuth 2.0 Grant Type values supported")
  List<OIDCGrantType> grantTypesSupported;
  
  @JsonProperty("acr_values_supported")
  @Schema(description = "List of Authentication Context Class References supported")
  List<String> acrValuesSupported;
  
  @JsonProperty("subject_types_supported")
  @Schema(description = "Subject Identifier types supported")
  @NotEmpty
  @Builder.Default
  List<OIDCSubjectType> subjectTypesSupported = List.of(OIDCSubjectType.PAIRWISE);
  
  @JsonProperty("id_token_signing_alg_values_supported")
  @Schema(description = "ID Token JWS Signing Algorithms Supported")
  @Builder.Default
  @NotEmpty
  List<JWSSigningAlgorithmType> idTokenSigningAlgorithms = List.of(JWSSigningAlgorithmType.PS256);
  
  @JsonProperty("id_token_encryption_alg_values_supported")
  @Schema(description = "ID Token JWE Encryption Algorithms Supported")
  List<JWEEncryptionAlgorithmType> idTokenEncryptionAlgorithms;
  
  @JsonProperty("id_token_encryption_enc_values_supported")
  @Schema(description = "ID Token JWE Encryption Encoding Methods Supported")
  List<JWEEncryptionEncodingType> idTokenEncryptionEncodings;

  @JsonProperty("userinfo_signing_alg_values_supported")
  @Schema(description = "User Info JWS Signing Algorithms Supported")
  @Builder.Default
  @NotEmpty
  List<JWSSigningAlgorithmType> userInfoSigningAlgorithms = List.of(JWSSigningAlgorithmType.PS256);
  
  @JsonProperty("userinfo_encryption_alg_values_supported")
  @Schema(description = "User Info JWE Encryption Algorithms Supported")
  List<JWEEncryptionAlgorithmType> userInfoEncryptionAlgorithms;
  
  @JsonProperty("userinfo_encryption_enc_values_supported")
  @Schema(description = "User Info JWE Encryption Algorithms Supported")
  List<JWEEncryptionEncodingType> userInfoEncryptionMethods;
  
  @JsonProperty("request_object_signing_alg_values_supported")
  @Schema(description = "Request Object JWS Signing Algorithms Supported")
  @Builder.Default
  @NotEmpty
  List<JWSSigningAlgorithmType> requestObjectSigningAlgorithms = List.of(JWSSigningAlgorithmType.PS256);
  
  @JsonProperty("request_object_encryption_alg_values_supported")
  @Schema(description = "Request Object JWE Encryption Algorithms Supported")
  List<JWEEncryptionAlgorithmType> requestObjectEncryptionAlgorithms;
  
  @JsonProperty("request_object_encryption_enc_values_supported")
  @Schema(description = "Request Object JWE Encryption Algorithms Supported")
  List<JWEEncryptionEncodingType> requestObjectEncryptionMethods;
  
  @JsonProperty("token_endpoint_auth_methods_supported")
  @Schema(description = "Token Endpoint Client Authentication methods supported")
  @Builder.Default
  List<OIDCAuthMethod> tokenEndpointAuthMethods = List.of(OIDCAuthMethod.CLIENT_SECRET_BASIC);
  
  @JsonProperty("token_endpoint_auth_signing_alg_values_supported")
  @Schema(description = "Token Endpoint Signing Algorithms Supported")
  List<JWSSigningAlgorithmType> tokenEndpointSigningAlgorithms;
  
  @JsonProperty("display_values_supported")
  @Schema(description = "OIDC Display Parameters supported")
  List<OIDCDisplayType> displayValuesSupported;
  
  @JsonProperty("service_documentation")
  @Schema(description = "URL of a page containing human-readable information the developers might want or need to know when using this OpenID Provider")
  URI serviceDocumentation;
  
  @JsonProperty("claims_locales_supported")
  Locale claimsLocales;
  
  @JsonProperty("ui_locales_supported")
  Locale uiLocales;
  
  @JsonProperty("claims_parameter_supported")
  @Schema(description = "Specify whether OP supports use of claims parameter")
  @Builder.Default
  Boolean claimsSupport = false;
  
  @JsonProperty("claims_supported")
  @Schema(description = "Array containing a list of Client Authentication Methods supported by this Token Endpoint")
  List<String> claimsSupported;
  
  @JsonProperty("claim_types_supported")
  @Schema(description = "Array containing a list of Claim Types supported by the OIDC Provider")
  @Builder.Default
  List<OIDCClaimType> claimTypesSupported = List.of(OIDCClaimType.NORMAL);
  
  @JsonProperty("request_parameter_supported")
  @Schema(description = "Specify whether OP supports use of request parameter")
  @Builder.Default
  Boolean requestSupport = false;
  
  @JsonProperty("request_uri_parameter_supported")
  @Schema(description = "Specify whether OP supports use of request_uri parameter")
  @Builder.Default
  Boolean requestUriSupport = true;
  
  @JsonProperty("require_request_uri_registration")
  @Schema(description = "Specifies whether the OP requires request_uri to be preregistered using the request_uris registration parameter")
  @Builder.Default
  Boolean requestUriRegistrationRequired = false;
  
  @JsonProperty("op_policy_uri")
  @Schema(description = "URL for OpenID Providers Policy Documentation")
  URI policyUri;
  
  @JsonProperty("op_tos_uri")
  @Schema(description = "URL for OpenID Providers Terms of Service")
  URI tosUri;
  
  @JsonProperty("check_session_iframe")
  @Schema(description = "URL of an OP iframe that supports cross-origin communications for session state information with the RP Client, using the HTML5 postMessage API")
  URI checkSessionIframe;
  
  @JsonProperty("end_session_endpoint")
  @Schema(description = "URL at the OP to which an RP can perform a redirect to request that the End-User be logged out at the OP")
  URI endSessionEndpoint;
  
  @JsonProperty("code_challenge_methods_supported")
  @Schema(description = "JSON array containing a list of Proof Key for Code Exchange (PKCE) [RFC7636] code challenge methods supported by this authorization server.")
  List<OAuth2PKCEType> codeChallengeMethodsSupported;
  
  @JsonProperty("tls_client_certificate_bound_access_tokens")
  @Schema(description = "Boolean value indicating support for mutal TLS client certificate bound access tokens")
  @Builder.Default
  Boolean oauthMtlsSupported = false;
  
  
  
}
