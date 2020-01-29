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
import java.util.List;
import java.util.UUID;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.babelfish.cdr.enumerations.BankingScheduledPaymentStatus;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.scheduled.BankingScheduledPayment;
import io.biza.babelfish.cdr.tests.v1.model.ModelConstants;

@DisplayName("BankingScheduledPayment V1 Tests")
public class BankingScheduledPaymentV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid BankingScheduledPayment")
  void bankingScheduledPayment() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_BANKING_SCHEDULED_PAYMENT).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_BANKING_SCHEDULED_PAYMENT).toString());
  }

  @Test
  @DisplayName("BankingScheduledPayment Mandatory Fields")
  void bankingScheduledPaymentMandatoryFields() {
    BankingScheduledPayment data = new BankingScheduledPayment();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.scheduledPaymentId(UUID.randomUUID().toString());
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.payerReference("Payer Reference");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.payeeReference("Payee Reference");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.status(BankingScheduledPaymentStatus.ACTIVE);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.from(ModelConstants.DEFAULT_BANKING_SCHEDULED_PAYMENT_FROM);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.paymentSet(List.of(ModelConstants.DEFAULT_BANKING_SCHEDULED_PAYMENT_SET));
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.recurrence(ModelConstants.DEFAULT_BANKING_SCHEDULED_PAYMENT_RECURRENCE);

    // Should now be a valid payload
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }


}
