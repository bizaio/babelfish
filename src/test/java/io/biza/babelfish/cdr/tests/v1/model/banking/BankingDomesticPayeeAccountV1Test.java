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
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.domestic.BankingDomesticPayeeAccountV1;
import io.biza.babelfish.cdr.support.customtypes.ApcaNumberType;
import io.biza.babelfish.cdr.tests.v1.model.ModelConstants;

@DisplayName("BankingDomesticPayeeAccount V1 Tests")
public class BankingDomesticPayeeAccountV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid BankingDomesticPayeeAccount")
  void bankingDomesticPayeeAccount() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_BANKING_DOMESTIC_PAYEE_ACCOUNT).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_BANKING_DOMESTIC_PAYEE_ACCOUNT).toString());
  }

  @Test
  @DisplayName("BankingDomesticPayeeAccount Mandatory Fields for Account")
  void bankingDomesticPayeeAccountMandatoryFieldsAccount() {
    BankingDomesticPayeeAccountV1 data = new BankingDomesticPayeeAccountV1();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.bsb(ApcaNumberType.fromValue("012-055"));
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.accountNumber("12341234");

    // Should now be a valid payload
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

}
