package io.biza.babelfish.spring;

public class Constants {

  public static final String OPENID_CONNECT_URL =
      "https://localhost:8103/.well-known/openid-configuration";

  /**
   * Tag definitions
   */
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
