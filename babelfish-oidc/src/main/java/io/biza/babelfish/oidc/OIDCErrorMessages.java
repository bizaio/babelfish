package io.biza.babelfish.oidc;

import java.net.URI;

public class OIDCErrorMessages {

	/**
	 * OAuth2 Error Messages
	 */
	public static final String OAUTH2_INVALID_REQUEST = "The request is missing a required parameter, includes an unsupported parameter value (other than grant type), repeats a parameter, includes multiple credentials, utilizes more than one mechanism for authenticating the client, or is otherwise malformed.";
	public static final String OAUTH2_INVALID_CLIENT = "Client authentication failed (e.g., unknown client, no client authentication included, or unsupported authentication method).";
	public static final String OAUTH2_INVALID_GRANT = "The provided authorization grant (e.g., authorization code, resource owner credentials) or refresh token is invalid, expired, revoked, does not match the redirection URI used in the authorization request, or was issued to another client.";
	public static final String OAUTH2_UNAUTHORIZED_CLIENT = "The authenticated client is not authorized to use this authorization grant type.";
	public static final String OAUTH2_ACCESS_DENIED = "The resource owner or authorization server denied the request";
	public static final String OAUTH2_SERVER_ERROR = "Encountered an unknown server error";
	public static final String OAUTH2_UNSUPPORTED_GRANT_TYPE = "The authorization grant type is not supported by the authorization server.";
	public static final String OAUTH2_UNSUPPORTED_RESPONSE_TYPE = "The authorization server does not support the specified response type(s)";
	public static final String OAUTH2_INVALID_SCOPE = "The requested scope is invalid, unknown, malformed, or exceeds the scope granted by the resource owner.";
	public static final URI OAUTH2_ERROR_RESPONSE_URI = URI.create("https://tools.ietf.org/html/rfc6749#section-5.2");

	/**
	 * Supplemental OAuth2 Error Messages
	 */
	public static final String OAUTH2_INVALID_REDIRECT_URI = "The request contains an invalid or unregistered redirect_uri";
	
	/**
	 * OAuth 2.0 Dynamic Registration Error Messages
	 */
	public static final String OAUTH2_REGISTRATION_INVALID_REDIRECT_URI = "The value of one or more redirect_uris is invalid.";
	public static final String OAUTH2_REGISTRATION_INVALID_CLIENT_METADATA = "The value of one of the Client Metadata fields is invalid and the server has rejected this request.";
	public static final String OAUTH2_REGISTRATION_INVALID_SOFTWARE_STATEMENT = "The software statement presented is invalid.";
	public static final String OAUTH2_REGISTRATION_UNAPPROVED_SOFTWARE_STATEMENT = "The software statement presented is not approved for use by this authorization server.";


	/**
	 * OpenID Connect Core Error Messages
	 */
	/**
	public static final String OIDC_CORE_INTERACTION_REQUIRED = "The Authorization Server requires End-User interaction of some form to proceed.";
	public static final String OIDC_CORE_LOGIN_REQUIRED = "The Authorization Server requires End-User authentication.";
	public static final String OIDC_CORE_ACCOUNT_SELECTION_REQUIRED = "The End-User is REQUIRED to select a session at the Authorization Server.";
	public static final String OIDC_CORE_CONSENT_REQUIRED = "The Authorization Server requires End-User consent";
	public static final String OIDC_CORE_INVALID_REQUEST_URI = "The request_uri in the Authorization Request returns an error or contains invalid data";
	public static final String OIDC_CORE_INVALID_REQUEST_OBJECT = "The request parameter contains an invalid Request Object.";
	public static final String OIDC_CORE_REQUEST_NOT_SUPPORTED = "The OP does not support use of the request parameter defined in OpenID Connect Core Section 6";
	public static final String OIDC_CORE_REQUEST_URI_NOT_SUPPORTED = "The OP does not support use of the request_uri parameter defined in OpenID Connect Core Section 6";
	public static final String OIDC_CORE_REGISTRATION_NOT_SUPPORTED = "The OP does not support use of the registration parameter defined in OpenID Connect Core Section 7.2.1";

*/

}
