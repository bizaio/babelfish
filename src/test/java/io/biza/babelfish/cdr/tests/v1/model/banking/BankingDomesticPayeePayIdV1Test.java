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
import io.biza.babelfish.cdr.enumerations.PayloadTypeBankingDomesticPayeePayId;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.domestic.BankingDomesticPayeePayIdV1;
import io.biza.babelfish.cdr.tests.v1.model.ModelConstants;

@DisplayName("BankingDomesticPayeePayId V1 Tests")
public class BankingDomesticPayeePayIdV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid BankingDomesticPayeePayId")
  void bankingDomesticPayeePayId() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_BANKING_DOMESTIC_PAYEE_PAYID).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_BANKING_DOMESTIC_PAYEE_PAYID).toString());
  }

  @Test
  @DisplayName("BankingDomesticPayeePayId Mandatory Fields for Email")
  void bankingDomesticPayeePayIdMandatoryFieldsEmail() {
    BankingDomesticPayeePayIdV1 data = new BankingDomesticPayeePayIdV1();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.type(PayloadTypeBankingDomesticPayeePayId.EMAIL);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.identifier("invalid email address");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.identifier("valid@email.com");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

  @Test
  @DisplayName("BankingDomesticPayeePayId Mandatory Fields for ABN")
  void bankingDomesticPayeePayIdMandatoryFieldsABN() {
    BankingDomesticPayeePayIdV1 data = new BankingDomesticPayeePayIdV1();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.type(PayloadTypeBankingDomesticPayeePayId.ABN);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // invalid text abn
    data.identifier("invalid abn");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // invalid length abn
    data.identifier("12341234");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // checksum failure abn
    data.identifier("12345678901");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // valid abn (Biza Pty Ltd) with spaces
    data.identifier("54 624 797 655");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // valid abn (Biza Pty Ltd) without spaces
    data.identifier("54624797655");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

  @Test
  @DisplayName("BankingDomesticPayeePayId Mandatory Fields for PhoneNumber")
  void bankingDomesticPayeePayIdMandatoryFieldsPhoneNumber() {
    BankingDomesticPayeePayIdV1 data = new BankingDomesticPayeePayIdV1();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.type(PayloadTypeBankingDomesticPayeePayId.TELEPHONE);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // invalid text phone
    data.identifier("invalid phone");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // valid australian phone number
    data.identifier("(02) 3307 1234");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // valid australian phone number in international format
    data.identifier("+61233071234");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // valid australian mobile number with area code (should be valid for FNN's too)
    data.identifier("0401123123");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

  @Test
  @DisplayName("BankingDomesticPayeePayId Mandatory Fields for OrgIdentifier")
  void bankingDomesticPayeePayIdMandatoryFieldsOrgIdentifier() {
    BankingDomesticPayeePayIdV1 data = new BankingDomesticPayeePayIdV1();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.type(PayloadTypeBankingDomesticPayeePayId.ORG_IDENTIFIER);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // random text
    // TODO: Find PayId Org Identifier
    data.identifier("org identifier");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

}
