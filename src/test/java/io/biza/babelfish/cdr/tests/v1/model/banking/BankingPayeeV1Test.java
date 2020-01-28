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
import io.biza.babelfish.cdr.tests.v1.model.ModelConstants;
import io.biza.babelfish.cdr.v1.model.banking.BankingPayee;
import io.biza.babelfish.enumerations.cdr.BankingPayeeType;

@DisplayName("BankingPayee V1 Tests")
public class BankingPayeeV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid BankingPayee")
  void bankingPayee() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_BANKING_PAYEE).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_BANKING_PAYEE).toString());
  }

  @Test
  @DisplayName("BankingPayee Mandatory Fields")
  void bankingPayeeMandatoryFields() {
    BankingPayee data = new BankingPayee();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.payeeId(UUID.randomUUID().toString());
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.setNickname("Payee Nickname");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.payeeType(BankingPayeeType.DOMESTIC);

    // Should now be a valid payload
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

}
