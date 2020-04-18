package io.biza.babelfish.spring;

public class Messages {
  public static final String ILLEGAL_STATE_EXCEPTION_MESSAGE =
      "Received Illegal State Exception: {}";
  public static final String INVALID_PARAMETERS_SEE_VALIDATION_ERRORS_MESSAGE =
      "Input has invalid parameters, see validationErrors for explanation";
  public static final String INVALID_PARAMETERS_WITH_EXPLANATION_MESSAGE =
      "Input has invalid parameters: {}";
  public static final String JSON_PARSE_ERROR_MESSAGE = "Supplied JSON was Unable to be Parsed";
  public static final String INVALID_PARAMETER_WITH_OPTIONS_MESSAGE =
      "The value ({}) supplied for {} is not one of the valid options: [ {} ]";
  public static final String DATABASE_ATTRIBUTE_FORMAT_ERROR_WITH_DESCRIPTION_MESSAGE =
      "While attempting to write data to database the server encountered a data format violation error: {}";
  public static final String PATH_PARAMETER_VALIDATION_ERROR_WITH_NAME_TARGET_MESSAGE = "A supplied path parameter was unable to be validated: {} should be a {}";
public static final String UNSUPPORTED_UNWRAP_WITH_DESCRIPTION_MESSAGE = "Attempted to unwrap an error which is not supported: {}";
public static final String RECEIVED_SPECIFIED_ERROR_WITH_DESCRIPTION_MESSAGE = "Received a {} with detail of: {}";
public static final String UNABLE_TO_VALIDATE_GENERIC_WITH_CONTENT =
"Unable to validate {} with input of: {}";
public static final String CREATED_NEW_GENERIC_WITH_CONTENT =
"Created a new {} with content of: {}";
public static final String LIST_ALL_GENERIC_AND_RECEIVED =
"Listing all {} and received content of: {}";
public static final String UPDATED_GENERIC_WITH_CONTENT = "Updated {} with content of: {}";
public static final String ENCOUNTERED_X_EXCEPTION_WHILE_PERFORMING_Y = "Encountered {} exception while performing {}";
public static final String ENCOUNTERED_UNSUPPORTED_KEY_TYPE_WITH_ID = "Encountered an unsupported key type of {} with id of {}";
public static final String ENCOUNTERED_PARSE_EXCEPTION_WITH_ALG_USE = "Encountered parse exception when retrieving content with algorithm of {} and use of {}";
public static final String ENCOUNTERED_PARSE_EXCEPTION_WITH_ID = "Encountered parsing error when processing key with id of {}";
}
