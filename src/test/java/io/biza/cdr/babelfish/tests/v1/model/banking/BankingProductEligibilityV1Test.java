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
import io.biza.cdr.babelfish.tests.v1.ModelConstants;
import io.biza.cdr.babelfish.v1.enumerations.BankingProductEligibilityType;
import io.biza.cdr.babelfish.v1.model.banking.BankingProductEligibility;

@DisplayName("BankingProductEligibility V1 Tests")
public class BankingProductEligibilityV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Valid BankingProductEligibility")
  void bankingProductEligibility() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_BANKING_PRODUCT_ELIGIBILITY).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_BANKING_PRODUCT_ELIGIBILITY).toString());
  }

  @Test
  @DisplayName("BankingProductEligibility for Business")
  void bankingProductEligibilityBusiness() {
    BankingProductEligibility data =
        new BankingProductEligibility().eligibilityType(BankingProductEligibilityType.BUSINESS);

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is invalid
    data.additionalValue("Invalid");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("BankingProductEligibility for Pension Recipient")
  void bankingProductEligibilityPensionRecipient() {
    BankingProductEligibility data = new BankingProductEligibility()
        .eligibilityType(BankingProductEligibilityType.PENSION_RECIPIENT);

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is invalid
    data.additionalValue("Invalid");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("BankingProductEligibility for Minimum Age")
  void bankingProductEligibilityMinAge() {
    BankingProductEligibility data =
        new BankingProductEligibility().eligibilityType(BankingProductEligibilityType.MIN_AGE);

    // Null Value is invalid
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Invalid number
    data.additionalValue("Not A Number");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // A decimal is invalid
    data.additionalValue("5.00");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // A single integer is valid
    data.additionalValue("18");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

  @Test
  @DisplayName("BankingProductEligibility for Maximum Age")
  void bankingProductEligibilityMaxAge() {
    BankingProductEligibility data =
        new BankingProductEligibility().eligibilityType(BankingProductEligibilityType.MAX_AGE);

    // Null Value is invalid
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Invalid number
    data.additionalValue("Not A Number");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // A decimal is invalid
    data.additionalValue("5.00");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // A single integer is valid
    data.additionalValue("25");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

  @Test
  @DisplayName("BankingProductEligibility for Minimum Income")
  void bankingProductEligibilityMinIncome() {
    BankingProductEligibility data =
        new BankingProductEligibility().eligibilityType(BankingProductEligibilityType.MIN_INCOME);

    // Null Value invalid
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Invalid number
    data.additionalValue("Not A Number");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // A number value which is not in AmountString format is invalid
    data.additionalValue("10");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // AmountString formatted value is valid
    data.additionalValue("10.00");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

  @Test
  @DisplayName("BankingProductEligibility for Minimum Turnover")
  void bankingProductEligibilityMinTurnover() {
    BankingProductEligibility data =
        new BankingProductEligibility().eligibilityType(BankingProductEligibilityType.MIN_TURNOVER);

    // Null Value invalid
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Invalid number
    data.additionalValue("Not A Number");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // A number value which is not in AmountString format is invalid
    data.additionalValue("10");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // AmountString formatted value is valid
    data.additionalValue("10.00");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

  @Test
  @DisplayName("BankingProductEligibility for Staff")
  void bankingProductEligibilityStaff() {
    BankingProductEligibility data =
        new BankingProductEligibility().eligibilityType(BankingProductEligibilityType.STAFF);

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is invalid
    data.additionalValue("Invalid");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("BankingProductEligibility for Student")
  void bankingProductEligibilityStudent() {
    BankingProductEligibility data =
        new BankingProductEligibility().eligibilityType(BankingProductEligibilityType.STUDENT);

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is invalid
    data.additionalValue("Invalid");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }


  @Test
  @DisplayName("BankingProductEligibility for Employment Status")
  void bankingProductEligibilityEmploymentStatus() {

    // Missing description
    BankingProductEligibility data = new BankingProductEligibility()
        .eligibilityType(BankingProductEligibilityType.EMPLOYMENT_STATUS);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Correct with name
    data.setAdditionalValue("Employment Status Description");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

  @Test
  @DisplayName("BankingProductEligibility for Residency Status")
  void bankingProductEligibilityResidencyStatus() {

    // Missing description
    BankingProductEligibility data = new BankingProductEligibility()
        .eligibilityType(BankingProductEligibilityType.RESIDENCY_STATUS);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Correct with name
    data.setAdditionalValue("Residency Status Description");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }


  @Test
  @DisplayName("BankingProductEligibility for Natural Person")
  void bankingProductEligibilityNaturalPerson() {
    BankingProductEligibility data = new BankingProductEligibility()
        .eligibilityType(BankingProductEligibilityType.NATURAL_PERSON);

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is invalid
    data.additionalValue("Invalid");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }



  @Test
  @DisplayName("BankingProductEligibility for Other")
  void bankingProductEligibilityOther() {
    BankingProductEligibility data =
        new BankingProductEligibility().eligibilityType(BankingProductEligibilityType.OTHER);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Correct with additional information defined
    data.setAdditionalInfo("Additional Information on Other feature");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

}
