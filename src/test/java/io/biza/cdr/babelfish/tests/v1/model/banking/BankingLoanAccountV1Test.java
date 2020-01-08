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
import java.time.Duration;
import java.time.LocalDate;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.cdr.babelfish.tests.v1.ModelConstants;
import io.biza.cdr.babelfish.v1.model.banking.BankingLoanAccount;

@DisplayName("BankingLoanAccount V1 Tests")
public class BankingLoanAccountV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid BankingLoanAccount")
  void bankingLoanAccount() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_BANKING_LOAN_ACCOUNT).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_BANKING_LOAN_ACCOUNT).toString());
  }

  @Test
  @DisplayName("BankingLoanAccount Mandatory Fields")
  void bankingLoanAccountMandatoryFields() {
    BankingLoanAccount data = new BankingLoanAccount();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.setLoanEndDate(LocalDate.now());
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.nextInstalmentDate(LocalDate.now());
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.repaymentFrequency(Duration.ofDays(10));

    // Should now be valid payload
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }
}
