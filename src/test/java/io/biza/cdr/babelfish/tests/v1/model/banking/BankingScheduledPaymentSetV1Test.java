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
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.cdr.babelfish.v1.model.banking.BankingScheduledPaymentSet;
import io.biza.cdr.babelfish.v1.support.ModelConstants;

@DisplayName("BankingScheduledPaymentSet V1 Tests")
public class BankingScheduledPaymentSetV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid BankingScheduledPaymentSet")
  void bankingScheduledPayment() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_BANKING_SCHEDULED_PAYMENT_SET).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_BANKING_SCHEDULED_PAYMENT_SET).toString());
  }

  @Test
  @DisplayName("BankingScheduledPaymentSet Mandatory Fields")
  void bankingScheduledPaymentMandatoryFields() {
    BankingScheduledPaymentSet data = new BankingScheduledPaymentSet();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.to(ModelConstants.DEFAULT_BANKING_SCHEDULED_PAYMENT_TO);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.amount(new BigDecimal("10.00"));
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

  @Test
  @DisplayName("BankingScheduledPaymentSet Mandatory Fields with Amount Calculated")
  void bankingScheduledPaymentMandatoryFieldsAmountCalculated() {
    BankingScheduledPaymentSet data =
        new BankingScheduledPaymentSet().to(ModelConstants.DEFAULT_BANKING_SCHEDULED_PAYMENT_TO);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.isAmountCalculated(true);
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }


}
