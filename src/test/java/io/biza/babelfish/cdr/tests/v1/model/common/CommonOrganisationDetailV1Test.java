/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU Lesser General Public License as published by the Free Software Foundation, either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *******************************************************************************/
package io.biza.babelfish.cdr.tests.v1.model.common;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.babelfish.cdr.tests.v1.model.ModelConstants;
import io.biza.babelfish.cdr.v1.model.common.CommonOrganisationDetail;
import io.biza.babelfish.enumerations.cdr.CommonOrganisationType;

@DisplayName("CommonOrganisationDetail V1 Tests")
public class CommonOrganisationDetailV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid CommonOrganisationDetail")
  void commonOrganisationDetail() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_COMMON_ORGANISATION_DETAIL).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_COMMON_ORGANISATION_DETAIL).toString());
  }

  @Test
  @DisplayName("CommonOrganisationDetail Mandatory Fields Other Organisation")
  void commonOrganisationDetailMandatoryFieldsOther() {
    CommonOrganisationDetail data = new CommonOrganisationDetail();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.agentLastName("Last");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.businessName("Organisation Business Name");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.organisationType(CommonOrganisationType.OTHER);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.physicalAddresses(List.of(ModelConstants.DEFAULT_COMMON_PHYSICAL_ADDRESS_WITH_PURPOSE));
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

  @Test
  @DisplayName("CommonOrganisationDetail Mandatory Fields Company")
  void commonOrganisationDetailMandatoryFieldsCompany() {
    CommonOrganisationDetail data = new CommonOrganisationDetail();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.agentLastName("Last");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.businessName("Organisation Business Name");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.organisationType(CommonOrganisationType.COMPANY);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.acn("010 499 966");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.physicalAddresses(List.of(ModelConstants.DEFAULT_COMMON_PHYSICAL_ADDRESS_WITH_PURPOSE));
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

}
