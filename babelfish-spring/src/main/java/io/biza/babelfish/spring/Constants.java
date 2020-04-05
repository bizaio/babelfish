package io.biza.babelfish.spring;

import java.net.URI;
import java.util.List;
import io.biza.babelfish.oidc.enumerations.JWEEncryptionAlgorithmType;
import io.biza.babelfish.oidc.enumerations.JWEEncryptionMethodType;
import io.biza.babelfish.oidc.enumerations.JWSSigningAlgorithmType;
import io.biza.babelfish.oidc.enumerations.OAuth2ResponseType;
import io.biza.babelfish.oidc.enumerations.OIDCApplicationType;
import io.biza.babelfish.oidc.enumerations.OIDCAuthMethod;
import io.biza.babelfish.oidc.enumerations.OIDCGrantType;

public class Constants {

  public static final String OPENID_CONNECT_URL =
      "https://localhost:8103/.well-known/openid-configuration";

  /**
   * Tag definitions
   */
  public final static String TAG_DISCOVERY_NAME = "discovery";
  public final static String TAG_DISCOVERY_DESCRIPTION = "Discovery Endpoints";
  public final static String TAG_TOKEN_NAME = "token";
  public final static String TAG_TOKEN_DESCRIPTION = "Token Authorisation Endpoints";
  public final static String TAG_TYPE_NAME = "type";
  public final static String TAG_TYPE_DESCRIPTION = "Type Discovery API";
  
  /**
   * Certificate Authority defaults
   */
  public static final String CA_ALGORITHM = "RSA";
  public static final int CA_KEY_SIZE = 2048;
  public static final int CA_VALIDITY_YEARS = 10;
  public static final int CERT_VALIDITY_YEARS = 2;
  public static final String CA_DN = "CN=Babelfish Development Register CA";
  public static final String CA_SIGNING_ALGORITHM = "SHA256WithRSA";

  /**
   * Content Types
   */
  public final static String CONTENT_TYPE_APPLICATION_JWT = "application/jwt";

  /**
   * Security Scopes
   */
  public final static String SECURITY_SCHEME_NAME = "wikkitgate_auth";

  /**
   * SSA Errors
   */
  public final static String REGISTRATION_INVALID_SOFTWARE_STATEMENT =
      "The software statement presented is invalid";
  public final static String REGISTRATION_INVALID_REDIRECT_URI =
      "The value of one or more redirection URIs is invalid.";
  public final static String REGISTRATION_INVALID_CLIENT_METADATA =
      "The value of one of the client metadata fields is invalid and the server has rejected this request. This error value is also used when attempts at duplicate registrations for the same software_id are rejected ";

  /**
   * Signing and Encryption Support
   */
  public final static List<JWSSigningAlgorithmType> SUPPORTED_SIGNING_ALGORITHMS =
      List.of(JWSSigningAlgorithmType.PS256);
  public final static List<JWEEncryptionAlgorithmType> SUPPORTED_ENCRYPTION_ALGORITHMS =
      List.of(JWEEncryptionAlgorithmType.RSA_OAEP);
  public final static List<JWEEncryptionMethodType> SUPPORTED_ENCRYPTION_METHODS =
      List.of(JWEEncryptionMethodType.A256GCM);
  public final static List<OIDCApplicationType> SUPPORTED_OIDC_APPLICATION_TYPES =
      List.of(OIDCApplicationType.WEB);
  public final static List<OIDCAuthMethod> SUPPORTED_TOKEN_AUTH_METHODS =
      List.of(OIDCAuthMethod.PRIVATE_KEY_JWT);
  public final static List<OIDCGrantType> SUPPORTED_OIDC_GRANT_TYPES =
      List.of(OIDCGrantType.CLIENT_CREDENTIALS);
  public final static List<List<OAuth2ResponseType>> SUPPORTED_OAUTH2_RESPONSE_TYPES =
      List.of(List.of(OAuth2ResponseType.CODE, OAuth2ResponseType.ID_TOKEN));


  /**
   * OAuth2 Errors
   */
  public final static URI OAUTH2_ERROR_RESPONSE_URI =
      URI.create("https://tools.ietf.org/html/rfc6749#section-5.2");
  public final static URI OAUTH2_INVALID_JWT_RESPONSE_URI =
      URI.create("https://tools.ietf.org/html/rfc7523#page-7");
  public final static String OAUTH2_INVALID_REQUEST_MESSAGE =
      "The request is missing a required parameter, includes an unsupported parameter value (other than grant type), repeats a parameter, includes multiple credentials, utilizes more than one mechanism for authenticating the client, or is otherwise malformed.";
  public final static String OAUTH2_INVALID_CLIENT_MESSAGE =
      "Client authentication failed (e.g., unknown client, no client authentication included, or unsupported authentication method).";
  public final static String OAUTH2_SERVER_ERROR_MESSAGE =
      "Client authentication failed due to unknown server error, contact the authorisation servers administator";
  public final static String OAUTH2_INVALID_GRANT_MESSAGE =
      "The provided authorization grant (e.g., authorization code, resource owner credentials) or refresh token is invalid, expired, revoked, does not match the redirection URI used in the authorization request, or was issued to another client.";
  public final static String OAUTH2_UNAUTHORIZED_CLIENT_MESSAGE =
      "The authenticated client is not authorized to use this authorization grant type.";
  public final static String OAUTH2_UNSUPPORTED_GRANT_TYPE_MESSAGE =
      "The authorization grant type is not supported by the authorization server.";
  public final static String OAUTH2_INVALID_SCOPE_MESSAGE =
      "The requested scope is invalid, unknown, malformed, or exceeds the scope granted by the resource owner.";

  /**
   * Response codes as strings
   */
  public final static String RESPONSE_CODE_CREATED = "201";
  public final static String RESPONSE_CODE_OK = "200";
  public final static String RESPONSE_CODE_NOT_FOUND = "404";
  public final static String RESPONSE_CODE_NO_CONTENT = "204";
  public final static String RESPONSE_CODE_UNPROCESSABLE_ENTITY = "422";

  /**
   * Response descriptions
   */
  public final static String RESPONSE_SUCCESSFUL_LIST =
      "Successful Response containing list of requested objects";

  public final static String RESPONSE_SUCCESSFUL_READ = "Success";

  public final static String RESPONSE_SUCCESSFUL_CREATE =
  "Successfully created new object with content returned";

  public final static String RESPONSE_SUCCESSFUL_DELETE =
  "Successfully deleted object specified in request with no content returned";

  public final static String RESPONSE_SUCCESSFUL_UPDATE =
  "Successfully updated object specified with updated object returned";

  public final static String RESPONSE_OBJECT_NOT_FOUND = "Requested Object could not be found";

  public final static String RESPONSE_INPUT_VALIDATION_ERROR =
  "Provided request details contains validation errors, validation errors are included in the response";

}
