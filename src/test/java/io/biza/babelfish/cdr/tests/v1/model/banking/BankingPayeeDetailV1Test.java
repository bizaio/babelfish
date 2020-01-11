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
import io.biza.babelfish.cdr.v1.enumerations.BankingPayeeType;
import io.biza.babelfish.cdr.v1.enumerations.PayloadTypeBankingPayee;
import io.biza.babelfish.cdr.v1.model.banking.BankingPayeeDetail;
import io.biza.babelfish.cdr.v1.support.ModelConstants;

@DisplayName("BankingPayeeDetail V1 Tests")
public class BankingPayeeDetailV1Test {
  private Validator validator;

  // TODO: Correlate BankingPayeeType with PayloadTypeBankingPayee

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid BankingPayeeDetail")
  void bankingPayeeDetail() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_BANKING_PAYEE_DETAIL).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_BANKING_PAYEE_DETAIL).toString());
  }

  @Test
  @DisplayName("BankingPayeeDetail Mandatory Fields for Domestic")
  void bankingPayeeDetailMandatoryFieldsDomestic() {
    BankingPayeeDetail data = new BankingPayeeDetail();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.payeeId(UUID.randomUUID().toString());
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.nickname("Payee Nickname");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.payeeType(BankingPayeeType.DOMESTIC);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.type(PayloadTypeBankingPayee.DOMESTIC);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.domestic(ModelConstants.DEFAULT_BANKING_DOMESTIC_PAYEE);

    // Should now be a valid payload
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

  @Test
  @DisplayName("BankingPayeeDetail Mandatory Fields for Biller")
  void bankingPayeeDetailMandatoryFieldsBiller() {
    BankingPayeeDetail data = new BankingPayeeDetail();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.payeeId(UUID.randomUUID().toString());
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.nickname("Payee Nickname");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.payeeType(BankingPayeeType.BILLER);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.type(PayloadTypeBankingPayee.BILLER);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.biller(ModelConstants.DEFAULT_BANKING_BILLER_PAYEE);

    // Should now be a valid payload
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

  @Test
  @DisplayName("BankingPayeeDetail Mandatory Fields for International")
  void bankingPayeeDetailMandatoryFieldsInternational() {
    BankingPayeeDetail data = new BankingPayeeDetail();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.payeeId(UUID.randomUUID().toString());
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.nickname("Payee Nickname");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.payeeType(BankingPayeeType.INTERNATIONAL);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.type(PayloadTypeBankingPayee.INTERNATIONAL);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.international(ModelConstants.DEFAULT_BANKING_INTERNATIONAL_PAYEE);

    // Should now be a valid payload
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

}
