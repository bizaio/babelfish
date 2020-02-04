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
import io.biza.babelfish.cdr.enumerations.PayloadTypeBankingScheduledPaymentRecurrence;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.scheduled.BankingScheduledPaymentRecurrenceV1;
import io.biza.babelfish.cdr.tests.v1.model.ModelConstants;

@DisplayName("BankingScheduledPaymentRecurrence V1 Tests")
public class BankingScheduledPaymentRecurrenceV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid BankingScheduledPaymentRecurrence")
  void bankingScheduledPaymentRecurrence() {
    assertTrue(
        validator.validate(ModelConstants.DEFAULT_BANKING_SCHEDULED_PAYMENT_RECURRENCE).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_BANKING_SCHEDULED_PAYMENT_RECURRENCE).toString());
  }

  @Test
  @DisplayName("BankingScheduledPaymentRecurrence Mandatory Fields Once Off")
  void bankingScheduledPaymentRecurrenceMandatoryFieldsOnceOff() {
    BankingScheduledPaymentRecurrenceV1 data = new BankingScheduledPaymentRecurrenceV1();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.type(PayloadTypeBankingScheduledPaymentRecurrence.ONCE_OFF);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.onceOff(ModelConstants.DEFAULT_BANKING_SCHEDULED_PAYMENT_RECURRENCE_ONCE_OFF);
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }


  @Test
  @DisplayName("BankingScheduledPaymentRecurrence Mandatory Fields Interval Schedule")
  void bankingScheduledPaymentRecurrenceMandatoryFieldsIntervalSchedule() {
    BankingScheduledPaymentRecurrenceV1 data = new BankingScheduledPaymentRecurrenceV1();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.type(PayloadTypeBankingScheduledPaymentRecurrence.INTERVAL_SCHEDULE);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.intervalSchedule(
        ModelConstants.DEFAULT_BANKING_SCHEDULED_PAYMENT_RECURRENCE_INTERVAL_SCHEDULE);
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

  @Test
  @DisplayName("BankingScheduledPaymentRecurrence Mandatory Fields Last Week Day")
  void bankingScheduledPaymentRecurrenceMandatoryFieldsLastWeekDay() {
    BankingScheduledPaymentRecurrenceV1 data = new BankingScheduledPaymentRecurrenceV1();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.type(PayloadTypeBankingScheduledPaymentRecurrence.LAST_WEEKDAY);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.lastWeekDay(ModelConstants.DEFAULT_BANKING_SCHEDULED_PAYMENT_RECURRENCE_LAST_WEEKDAY);
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

  @Test
  @DisplayName("BankingScheduledPaymentRecurrence Mandatory Fields Event Based")
  void bankingScheduledPaymentRecurrenceMandatoryFieldsEventBased() {
    BankingScheduledPaymentRecurrenceV1 data = new BankingScheduledPaymentRecurrenceV1();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.type(PayloadTypeBankingScheduledPaymentRecurrence.EVENT_BASED);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.eventBased(ModelConstants.DEFAULT_BANKING_SCHEDULED_PAYMENT_RECURRENCE_EVENT_BASED);
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

}
