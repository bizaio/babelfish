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
import io.biza.babelfish.cdr.enumerations.PayloadTypeBankingDomesticPayee;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.domestic.BankingDomesticPayeeV1;
import io.biza.babelfish.cdr.tests.v1.model.ModelConstants;

@DisplayName("BankingDomesticPayee V1 Tests")
public class BankingDomesticPayeeV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid BankingDomesticPayee")
  void bankingDomesticPayee() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_BANKING_DOMESTIC_PAYEE).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_BANKING_DOMESTIC_PAYEE).toString());
  }

  @Test
  @DisplayName("BankingDomesticPayee Mandatory Fields for Account")
  void bankingDomesticPayeeMandatoryFieldsAccount() {
    BankingDomesticPayeeV1 data = new BankingDomesticPayeeV1();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.payeeAccountType(PayloadTypeBankingDomesticPayee.ACCOUNT);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.account(ModelConstants.DEFAULT_BANKING_DOMESTIC_PAYEE_ACCOUNT);

    // Should now be a valid payload
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

  @Test
  @DisplayName("BankingDomesticPayee Mandatory Fields for Card")
  void bankingDomesticPayeeMandatoryFieldsCard() {
    BankingDomesticPayeeV1 data = new BankingDomesticPayeeV1();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.payeeAccountType(PayloadTypeBankingDomesticPayee.CARD);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.card(ModelConstants.DEFAULT_BANKING_DOMESTIC_PAYEE_CARD);

    // Should now be a valid payload
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

  @Test
  @DisplayName("BankingDomesticPayee Mandatory Fields for PayId")
  void bankingDomesticPayeeMandatoryFieldsPayId() {
    BankingDomesticPayeeV1 data = new BankingDomesticPayeeV1();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.payeeAccountType(PayloadTypeBankingDomesticPayee.PAY_ID);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.payId(ModelConstants.DEFAULT_BANKING_DOMESTIC_PAYEE_PAYID);

    // Should now be a valid payload
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

}
