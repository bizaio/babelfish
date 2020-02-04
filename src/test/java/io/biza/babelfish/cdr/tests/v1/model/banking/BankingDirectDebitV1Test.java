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
import io.biza.babelfish.cdr.models.payloads.banking.account.directdebit.BankingDirectDebitV1;
import io.biza.babelfish.cdr.tests.v1.model.ModelConstants;

@DisplayName("BankingDirectDebit V1 Tests")
public class BankingDirectDebitV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid BankingDirectDebit")
  void bankingDirectDebit() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_BANKING_DIRECT_DEBIT).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_BANKING_DIRECT_DEBIT).toString());
  }

  @Test
  @DisplayName("BankingDirectDebit Mandatory Fields")
  void bankingDirectDebitMandatoryFields() {
    BankingDirectDebitV1 data = new BankingDirectDebitV1();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.accountId(UUID.randomUUID().toString());
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.authorisedEntity(ModelConstants.DEFAULT_BANKING_AUTHORISED_ENTITY);

    // Should now be a valid payload
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

  @Test
  @DisplayName("BankingDirectDebit Last Debit Attributes should be present")
  void bankingDirectDebitVerifyLastDebitAttributesPresent() {
    BankingDirectDebitV1 data = new BankingDirectDebitV1().accountId(UUID.randomUUID().toString())
        .authorisedEntity(ModelConstants.DEFAULT_BANKING_AUTHORISED_ENTITY);
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.lastDebitDateTime(OffsetDateTime.now());
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // If date time is set, amount should be set
    data.lastDebitAmount(new BigDecimal("10.00"));
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // If amount is set date time should be set
    data.lastDebitDateTime(null);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

}
