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
import io.biza.cdr.babelfish.v1.enumerations.CommonPhoneNumberPurpose;
import io.biza.cdr.babelfish.v1.model.common.CommonPhoneNumber;

@DisplayName("CommonPhoneNumber V1 Tests")
public class CommonPhoneNumberV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid CommonPhoneNumber")
  void commonPhoneNumber() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_COMMON_PHONE_NUMBER).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_COMMON_PHONE_NUMBER).toString());
  }

  @Test
  @DisplayName("CommonPhoneNumber Mandatory Fields (Home)")
  void commonPhoneNumberMandatoryFieldsHome() {
    CommonPhoneNumber data = new CommonPhoneNumber();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.purpose(CommonPhoneNumberPurpose.HOME);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.number("0733076000");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.fullNumber("tel:+61-073-307-6000");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.areaCode("7");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

  @Test
  @DisplayName("CommonPhoneNumber Mandatory Fields (Mobile)")
  void commonPhoneNumberMandatoryFieldsMobile() {
    CommonPhoneNumber data = new CommonPhoneNumber();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.purpose(CommonPhoneNumberPurpose.MOBILE);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.number("0404839839");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.fullNumber("tel:+61-040-483-9839");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }
}
