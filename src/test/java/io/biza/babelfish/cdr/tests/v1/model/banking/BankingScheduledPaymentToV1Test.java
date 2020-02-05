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
import java.util.UUID;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.babelfish.cdr.enumerations.PayloadTypeBankingScheduledPaymentTo;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.scheduled.BankingScheduledPaymentToV1;
import io.biza.babelfish.cdr.tests.v1.model.ModelConstants;

@DisplayName("BankingScheduledPaymentTo V1 Tests")
public class BankingScheduledPaymentToV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid BankingScheduledPaymentTo")
  void bankingScheduledPaymentTo() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_BANKING_SCHEDULED_PAYMENT_TO).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_BANKING_SCHEDULED_PAYMENT_TO).toString());
  }

  @Test
  @DisplayName("BankingScheduledPaymentTo for AccountId")
  void bankingScheduledPaymentToMandatoryFieldsAccountId() {
    BankingScheduledPaymentToV1 data = new BankingScheduledPaymentToV1();
    data.type(PayloadTypeBankingScheduledPaymentTo.ACCOUNT_ID);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    data.accountId(UUID.randomUUID().toString());
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

  @Test
  @DisplayName("BankingScheduledPaymentTo for PayeeId")
  void bankingScheduledPaymentToMandatoryFieldsPayeeId() {
    BankingScheduledPaymentToV1 data = new BankingScheduledPaymentToV1();
    data.type(PayloadTypeBankingScheduledPaymentTo.PAYEE_ID);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    data.payeeId(UUID.randomUUID().toString());
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("BankingScheduledPaymentTo for Domestic")
  void bankingScheduledPaymentToMandatoryFieldsDomestic() {
    BankingScheduledPaymentToV1 data = new BankingScheduledPaymentToV1();
    data.type(PayloadTypeBankingScheduledPaymentTo.DOMESTIC);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    data.domestic(ModelConstants.DEFAULT_BANKING_DOMESTIC_PAYEE.build());
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }


  @Test
  @DisplayName("BankingScheduledPaymentTo for Biller")
  void bankingScheduledPaymentToMandatoryFieldsBiller() {
    BankingScheduledPaymentToV1 data = new BankingScheduledPaymentToV1();
    data.type(PayloadTypeBankingScheduledPaymentTo.BILLER);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    data.biller(ModelConstants.DEFAULT_BANKING_BILLER_PAYEE.build());
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("BankingScheduledPaymentTo for International")
  void bankingScheduledPaymentToMandatoryFieldsInternational() {
    BankingScheduledPaymentToV1 data = new BankingScheduledPaymentToV1();
    data.type(PayloadTypeBankingScheduledPaymentTo.INTERNATIONAL);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    data.international(ModelConstants.DEFAULT_BANKING_INTERNATIONAL_PAYEE.build());
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }
}
