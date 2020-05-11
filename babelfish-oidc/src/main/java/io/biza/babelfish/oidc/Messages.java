package io.biza.babelfish.oidc;

public class Messages {

	public static final String FAILED_TO_RETRIEVE_JWKS = "Attempt to retrieve JWKS from {} failed";
	public static final String UNABLE_TO_PARSE_JWKS = "Unable to parse supplied jwks";
	public static final String UNABLE_TO_FIND_KEY_WITH_DETAIL = "Unable to match remote key within {} with details of {}";
	public static final String UNSUPPORTED_KEY_TYPE_WITH_VALUE = "Unsupported key type of {} specified";
	public static final String KEY_SELECT_WITH_USE_ALG_KEYID_JWKS = "Attempting to select {} key with alg: {}, keyId: {} within JWKS {}";
	public static final String MATCHED_JWK = "Matched JWK is: {}";
	public static final String UNABLE_TO_IDENTIFY_MESSAGE_DIGESTER = "Unable to identify required message digester for state hash calculation";
	public static final String UNSUPPORTED_DIGEST_FOR_ALGORITHM = "Unsupported digest method for algorithm of {}";
	public static final String CLAIMS_SET_CONVERSION_PRODUCED_X = "Claims Set Conversion produced: {}";
	public static final String ATTEMPTING_TO_DISABLE_TLS_VERIFICATION = "Attempting to disable TLS verification";
	public static final String INVALID_RESPONSE_TYPE = "Unrecognised response_type of {} specified";

}
