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
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.babelfish.cdr.enumerations.BankingTransactionStatus;
import io.biza.babelfish.cdr.enumerations.BankingTransactionType;
import io.biza.babelfish.cdr.models.payloads.banking.account.transaction.BankingTransactionV1;
import io.biza.babelfish.cdr.tests.v1.model.ModelConstants;

@DisplayName("BankingTransaction V1 Tests")
public class BankingTransactionV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid BankingTransaction")
  void bankingTransaction() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_BANKING_TRANSACTION).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_BANKING_TRANSACTION).toString());
  }

  @Test
  @DisplayName("BankingTransaction Mandatory Fields for POSTED Transactions")
  void bankingTransactionMandatoryFieldsPosted() {
    BankingTransactionV1 data = new BankingTransactionV1();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.accountId(UUID.randomUUID().toString());
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.transactionId(UUID.randomUUID().toString());
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.isDetailAvailable(false);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.type(BankingTransactionType.PAYMENT);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.status(BankingTransactionStatus.POSTED);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.description("Transaction Description");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.amount(new BigDecimal("10.00"));
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.reference("");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.postingDateTime(OffsetDateTime.now());
    // Should now be valid
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

  @Test
  @DisplayName("BankingTransaction Mandatory Fields for PENDING Transactions")
  void bankingTransactionMandatoryFieldsPending() {
    BankingTransactionV1 data = new BankingTransactionV1();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.accountId(UUID.randomUUID().toString());
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.transactionId(UUID.randomUUID().toString());
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.isDetailAvailable(false);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.type(BankingTransactionType.PAYMENT);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.status(BankingTransactionStatus.PENDING);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.description("Transaction Description");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.amount(new BigDecimal("10.00"));
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.reference("");

    // Should now be valid
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }
}
