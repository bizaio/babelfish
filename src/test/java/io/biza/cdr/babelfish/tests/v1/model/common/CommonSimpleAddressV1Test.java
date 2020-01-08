package io.biza.cdr.babelfish.tests.v1.model.common;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Locale;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.cdr.babelfish.Constants;
import io.biza.cdr.babelfish.tests.v1.ModelConstants;
import io.biza.cdr.babelfish.v1.model.common.CommonSimpleAddress;

@DisplayName("CommonSimpleAddress V1 Tests")
public class CommonSimpleAddressV1Test {
  private Validator validator;

  // TODO: Validate australian states

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid CommonSimpleAddress")
  void responseCommonSimpleAddress() {
    CommonSimpleAddress data = ModelConstants.DEFAULT_COMMON_SIMPLE_ADDRESS;
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("CommonSimpleAddress Mandatory Fields (Australia)")
  void responseCommonSimpleAddressMandatoryFieldsAustralia() {
    CommonSimpleAddress data = new CommonSimpleAddress();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.addressLine1("10 McGill Street");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.postcode("2550");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.city("Cobargo");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.state("NSW");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

  @Test
  @DisplayName("CommonSimpleAddress Mandatory Fields (International)")
  void responseCommonSimpleAddressMandatoryFieldsInternational() {
    CommonSimpleAddress data = new CommonSimpleAddress();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.addressLine1("10 McGill Street");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.city("Paris");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // https://github.com/ConsumerDataStandardsAustralia/standards-maintenance/issues/88
    data.state("");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.country(new Locale(Constants.DEFAULT_LOCALE, "FR"));
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }
}
