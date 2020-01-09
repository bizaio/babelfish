/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *******************************************************************************/
package io.biza.cdr.babelfish.tests.v1.model.banking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.cdr.babelfish.v1.model.banking.BankingCreditCardAccount;
import io.biza.cdr.babelfish.v1.support.ModelConstants;

@DisplayName("BankingCreditCardAccount V1 Tests")
public class BankingCreditCardAccountV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid BankingCreditCardAccount")
  void bankingCreditCardAccount() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_BANKING_CREDIT_CARD_ACCOUNT).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_BANKING_CREDIT_CARD_ACCOUNT).toString());
  }

  @Test
  @DisplayName("BankingCreditCardAccount Mandatory Fields")
  void bankingCreditCardAccountMandatoryFields() {
    BankingCreditCardAccount data = new BankingCreditCardAccount();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.minPaymentAmount(new BigDecimal("10.00"));
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.paymentDueAmount(new BigDecimal("10.00"));
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.paymentDueDate(LocalDate.now());

    // Now a compliant payload
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }
}
