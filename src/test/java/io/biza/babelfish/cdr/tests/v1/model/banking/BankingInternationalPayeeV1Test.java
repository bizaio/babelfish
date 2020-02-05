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
package io.biza.babelfish.cdr.tests.v1.model.banking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.international.BankingInternationalPayeeV1;
import io.biza.babelfish.cdr.tests.v1.model.ModelConstants;

@DisplayName("BankingInternationalPayee V1 Tests")
public class BankingInternationalPayeeV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid BankingInternationalPayee")
  void bankingInternationalPayee() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_BANKING_INTERNATIONAL_PAYEE).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_BANKING_INTERNATIONAL_PAYEE).toString());
  }

  @Test
  @DisplayName("BankingInternationalPayee Mandatory Fields")
  void bankingInternationalPayeeMandatoryFields() {
    BankingInternationalPayeeV1 data = new BankingInternationalPayeeV1();
    data.beneficiaryDetails(ModelConstants.DEFAULT_BANKING_INTERNATIONAL_PAYEE_BENEFICIARY_DETAILS.build());
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.bankDetails(ModelConstants.DEFAULT_BANKING_INTERNATIONAL_PAYEE_BANK_DETAILS.build());
    
    // Should be a valid payload now
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());


  }

}
