package io.biza.cdr.babelfish.tests.v1.model.common;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.cdr.babelfish.tests.v1.ModelConstants;
import io.biza.cdr.babelfish.v1.enumerations.CommonOrganisationType;
import io.biza.cdr.babelfish.v1.model.common.CommonOrganisation;

@DisplayName("CommonOrganisation V1 Tests")
public class CommonOrganisationV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid CommonOrganisation")
  void commonOrganisation() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_COMMON_ORGANISATION).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_COMMON_ORGANISATION).toString());
  }

  @Test
  @DisplayName("CommonOrganisation Mandatory Fields Other Organisation")
  void commonOrganisationMandatoryFieldsOther() {
    CommonOrganisation data = new CommonOrganisation();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.agentLastName("Last");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.businessName("Organisation Business Name");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.organisationType(CommonOrganisationType.OTHER);
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

  @Test
  @DisplayName("CommonOrganisation Mandatory Fields Company")
  void commonOrganisationMandatoryFieldsCompany() {
    CommonOrganisation data = new CommonOrganisation();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.agentLastName("Last");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.businessName("Organisation Business Name");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.organisationType(CommonOrganisationType.COMPANY);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.acn("624123123");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

}
