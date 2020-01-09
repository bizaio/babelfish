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
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.cdr.babelfish.v1.model.banking.BankingBillerPayee;
import io.biza.cdr.babelfish.v1.support.ModelConstants;

@DisplayName("BankingBillerPayee V1 Tests")
public class BankingBillerPayeeV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid BankingBillerPayee")
  void bankingBillerPayee() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_BANKING_BILLER_PAYEE).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_BANKING_BILLER_PAYEE).toString());
  }

  @Test
  @DisplayName("BankingBillerPayee Mandatory Fields")
  void bankingBillerPayeeMandatoryFields() {
    BankingBillerPayee data = new BankingBillerPayee();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Biller Name
    data.billerName("Energy Australia");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Valid Biller Code (Energy Australia)
    data.billerCode("3111");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Valid CRN for MOD09V02
    data.crn("81752861");

    // Initial pass with 3 mandatory fields
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("BankingBillerPayee CRN Validation")
  void bankingBillerPayeeCRN() {
    BankingBillerPayee data =
        new BankingBillerPayee().billerName("Energy Australia").billerCode("3111");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Valid CRN for MOD09V02
    data.crn("81752861");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Valid CRN for MOD10V01
    data.crn("89557136");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Valid CRN for MOD10V02
    data.crn("74751417");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Valid CRN for MOD10V05
    data.crn("87348538");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Valid Biller Code (Energy Australia)
    data.billerCode("3111");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Invalid CRN using text
    data.crn("text");

    // Invalid CRN incorrect length
    data.crn("8734853");

    // Invalid CRN invalid check digit
    data.crn("87348531");

  }

  @Test
  @DisplayName("BankingBillerPayee Biller Code Validation")
  void bankingBillerPayeeBillerCode() {
    BankingBillerPayee data =
        new BankingBillerPayee().billerName("Energy Australia").crn("81752861");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Valid Biller Code (Energy Australia)
    data.billerCode("3111");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Invalid Biller Code
    data.billerCode("randomtext");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Invalid Biller Code (too short)
    data.billerCode("14");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Invalid Biller Code (too long)
    data.billerCode("12345678901");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

}
