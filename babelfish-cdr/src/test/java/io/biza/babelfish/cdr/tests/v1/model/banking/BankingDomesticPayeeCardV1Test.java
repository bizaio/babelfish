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
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.domestic.BankingDomesticPayeeCardV1;
import io.biza.babelfish.cdr.tests.v1.model.ModelConstants;

@DisplayName("BankingDomesticPayeeCard V1 Tests")
public class BankingDomesticPayeeCardV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid BankingDomesticPayeeCard")
  void bankingDomesticPayeeCard() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_BANKING_DOMESTIC_PAYEE_CARD).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_BANKING_DOMESTIC_PAYEE_CARD).toString());
  }

  @Test
  @DisplayName("BankingDomesticPayeeCard Mandatory Fields")
  void bankingDomesticPayeeCardMandatoryFieldsAccount() {
    BankingDomesticPayeeCardV1 data = new BankingDomesticPayeeCardV1();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Unmasked PAN First
    data.cardNumber("1234 1234 1234 1234");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Unmasked PAN First with hyphens
    data.cardNumber("1234-1234-1234-1234");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Masked PAN
    data.cardNumber("XXXX XXXX XXXX 1234");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Masked PAN with Hyphens
    data.cardNumber("XXXX-XXXX-XXXX-1234");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

}
