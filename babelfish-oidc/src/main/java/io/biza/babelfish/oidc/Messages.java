package io.biza.babelfish.oidc;

public class Messages {

	public static String FAILED_TO_RETRIEVE_JWKS = "Attempt to retrieve JWKS from {} failed";
	public static String UNABLE_TO_PARSE_JWKS = "Unable to parse supplied jwks";
	public static String UNABLE_TO_FIND_KEY_WITH_DETAIL = "Unable to match remote key within {} with details of {}";
	public static String UNSUPPORTED_KEY_TYPE_WITH_VALUE = "Unsupported key type of {} specified";
	public static String KEY_SELECT_WITH_USE_ALG_KEYID_JWKS = "Attempting to select {} key with alg: {}, keyId: {} within JWKS {}";
	public static String MATCHED_JWK = "Matched JWK is: {}";
}
