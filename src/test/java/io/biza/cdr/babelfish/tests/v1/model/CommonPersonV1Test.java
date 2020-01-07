package io.biza.cdr.babelfish.tests.v1.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.cdr.babelfish.tests.v1.ModelConstants;
import io.biza.cdr.babelfish.v1.model.common.CommonPerson;

@DisplayName("CommonPerson V1 Tests")
public class CommonPersonV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid CommonPerson")
  void commonPerson() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_COMMON_PERSON).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_COMMON_PERSON).toString());
  }

  @Test
  @DisplayName("CommonPerson Mandatory Fields")
  void commonPersonMandatoryFields() {
    CommonPerson data = new CommonPerson();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.lastName("Last");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }
}
