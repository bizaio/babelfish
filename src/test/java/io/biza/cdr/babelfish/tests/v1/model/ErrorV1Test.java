package io.biza.cdr.babelfish.tests.v1.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.UUID;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.cdr.babelfish.tests.v1.ModelConstants;
import io.biza.cdr.babelfish.v1.model.common.Error;

@DisplayName("Error V1 Tests")
public class ErrorV1Test {
  private Validator validator;

  // TODO: Specific Error code verification

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid Error")
  void responseError() {
    Error data = ModelConstants.DEFAULT_ERROR;
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("Error Mandatory Fields")
  void responseErrorMandatoryFields() {
    Error data = new Error();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.code("0001 – Account not able to be found");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.title("Invalid account");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.detail(UUID.randomUUID().toString());
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }
}
