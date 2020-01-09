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
import java.time.Period;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.cdr.babelfish.v1.enumerations.CommonWeekDay;
import io.biza.cdr.babelfish.v1.model.banking.BankingScheduledPaymentRecurrenceLastWeekday;
import io.biza.cdr.babelfish.v1.support.ModelConstants;

@DisplayName("BankingScheduledPaymentRecurrenceLastWeekday V1 Tests")
public class BankingScheduledPaymentRecurrenceLastWeekdayV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid BankingScheduledPaymentRecurrenceLastWeekday")
  void bankingScheduledPaymentRecurrenceLastWeekday() {
    assertTrue(
        validator.validate(ModelConstants.DEFAULT_BANKING_SCHEDULED_PAYMENT_RECURRENCE_LAST_WEEKDAY)
            .isEmpty(),
        validator.validate(ModelConstants.DEFAULT_BANKING_SCHEDULED_PAYMENT_RECURRENCE_LAST_WEEKDAY)
            .toString());
  }

  @Test
  @DisplayName("BankingScheduledPaymentRecurrenceLastWeekday Mandatory Fields")
  void bankingScheduledPaymentRecurrenceLastWeekdayMandatoryFields() {
    BankingScheduledPaymentRecurrenceLastWeekday data =
        new BankingScheduledPaymentRecurrenceLastWeekday();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.interval(Period.ofDays(30));
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.lastWeekDay(CommonWeekDay.MON);

    // Should now be a valid payload
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

}
