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
import io.biza.babelfish.cdr.tests.v1.model.ModelConstants;
import io.biza.babelfish.enumerations.cdr.BankingProductEligibilityType;
import io.biza.babelfish.enumerations.cdr.BankingProductEligibilityType;
import io.biza.babelfish.interfaces.cdr.banking.product.BankingProductEligibilityV1;
import io.biza.babelfish.enumerations.cdr.BankingProductEligibilityType;

@DisplayName("BankingProductEligibilityV1 V1 Tests")
public class BankingProductEligibilityV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Valid BankingProductEligibilityV1")
  void bankingProductEligibility() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_BANKING_PRODUCT_ELIGIBILITY).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_BANKING_PRODUCT_ELIGIBILITY).toString());
  }

  @Test
  @DisplayName("BankingProductEligibilityV1 for Business")
  void bankingProductEligibilityBusiness() {
    BankingProductEligibilityV1 data =
        new BankingProductEligibilityV1.Builder().type(BankingProductEligibilityType.BUSINESS).buildPartial();

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is invalid
    assertFalse(
        validator.validate(BankingProductEligibilityV1.Builder.from(data)
            .additionalValue("Invalid").build()).isEmpty(),
        validator.validate(BankingProductEligibilityV1.Builder.from(data)
            .additionalValue("Invalid").build()).toString());
  }

  @Test
  @DisplayName("BankingProductEligibilityV1 for Pension Recipient")
  void bankingProductEligibilityPensionRecipient() {
    BankingProductEligibilityV1 data = new BankingProductEligibilityV1.Builder()
        .type(BankingProductEligibilityType.PENSION_RECIPIENT).buildPartial();

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is also correct
    assertTrue(
        validator.validate(BankingProductEligibilityV1.Builder.from(data)
            .additionalValue("Pension Recipient Explanation").build()).isEmpty(),
        validator.validate(BankingProductEligibilityV1.Builder.from(data)
            .additionalValue("Pension Recipient Explanation").build()).toString());
  }

  @Test
  @DisplayName("BankingProductEligibilityV1 for Minimum Age")
  void bankingProductEligibilityMinAge() {
    BankingProductEligibilityV1 data =
        new BankingProductEligibilityV1.Builder().type(BankingProductEligibilityType.MIN_AGE).buildPartial();

 // Null Value is invalid
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Invalid number
    assertFalse(
        validator.validate(BankingProductEligibilityV1.Builder.from(data)
            .additionalValue("Not a Number").build()).isEmpty(),
        validator.validate(BankingProductEligibilityV1.Builder.from(data)
            .additionalValue("Not a Number").build()).toString());

    // A decimal is invalid
    assertFalse(
        validator.validate(BankingProductEligibilityV1.Builder.from(data)
            .additionalValue("5.00").build()).isEmpty(),
        validator.validate(BankingProductEligibilityV1.Builder.from(data)
            .additionalValue("5.00").build()).toString());

    // A single integer is valid
    assertTrue(
        validator.validate(
            BankingProductEligibilityV1.Builder.from(data).additionalValue("18").build())
            .isEmpty(),
        validator.validate(
            BankingProductEligibilityV1.Builder.from(data).additionalValue("18").build())
            .toString());

  }

  @Test
  @DisplayName("BankingProductEligibilityV1 for Maximum Age")
  void bankingProductEligibilityMaxAge() {
    BankingProductEligibilityV1 data =
        new BankingProductEligibilityV1.Builder().type(BankingProductEligibilityType.MAX_AGE).buildPartial();

 // Null Value is invalid
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Invalid number
    assertFalse(
        validator.validate(BankingProductEligibilityV1.Builder.from(data)
            .additionalValue("Not a Number").build()).isEmpty(),
        validator.validate(BankingProductEligibilityV1.Builder.from(data)
            .additionalValue("Not a Number").build()).toString());

    // A decimal is invalid
    assertFalse(
        validator.validate(BankingProductEligibilityV1.Builder.from(data)
            .additionalValue("5.00").build()).isEmpty(),
        validator.validate(BankingProductEligibilityV1.Builder.from(data)
            .additionalValue("5.00").build()).toString());

    // A single integer is valid
    assertTrue(
        validator.validate(
            BankingProductEligibilityV1.Builder.from(data).additionalValue("18").build())
            .isEmpty(),
        validator.validate(
            BankingProductEligibilityV1.Builder.from(data).additionalValue("18").build())
            .toString());

  }

  @Test
  @DisplayName("BankingProductEligibilityV1 for Minimum Income")
  void bankingProductEligibilityMinIncome() {
    BankingProductEligibilityV1 data =
        new BankingProductEligibilityV1.Builder().type(BankingProductEligibilityType.MIN_INCOME).buildPartial();

    // Null Value invalid
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Invalid number
    assertFalse(
        validator.validate(BankingProductEligibilityV1.Builder.from(data)
            .additionalValue("Not a Number").build()).isEmpty(),
        validator.validate(BankingProductEligibilityV1.Builder.from(data)
            .additionalValue("Not a Number").build()).toString());

    // A number value which is not in AmountString format is invalid
    assertFalse(
        validator.validate(
            BankingProductEligibilityV1.Builder.from(data).additionalValue("18").build())
            .isEmpty(),
        validator.validate(
            BankingProductEligibilityV1.Builder.from(data).additionalValue("18").build())
            .toString());

    // AmountString formatted value is valid
    assertTrue(
        validator.validate(BankingProductEligibilityV1.Builder.from(data)
            .additionalValue("5.00").build()).isEmpty(),
        validator.validate(BankingProductEligibilityV1.Builder.from(data)
            .additionalValue("5.00").build()).toString());

  }

  @Test
  @DisplayName("BankingProductEligibilityV1 for Minimum Turnover")
  void bankingProductEligibilityMinTurnover() {
    BankingProductEligibilityV1 data =
        new BankingProductEligibilityV1.Builder().type(BankingProductEligibilityType.MIN_TURNOVER).buildPartial();

 // Null Value invalid
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

 // Invalid number
    assertFalse(
        validator.validate(BankingProductEligibilityV1.Builder.from(data)
            .additionalValue("Not a Number").build()).isEmpty(),
        validator.validate(BankingProductEligibilityV1.Builder.from(data)
            .additionalValue("Not a Number").build()).toString());

    // A number value which is not in AmountString format is invalid
    assertFalse(
        validator.validate(
            BankingProductEligibilityV1.Builder.from(data).additionalValue("18").build())
            .isEmpty(),
        validator.validate(
            BankingProductEligibilityV1.Builder.from(data).additionalValue("18").build())
            .toString());

    // AmountString formatted value is valid
    assertTrue(
        validator.validate(BankingProductEligibilityV1.Builder.from(data)
            .additionalValue("5.00").build()).isEmpty(),
        validator.validate(BankingProductEligibilityV1.Builder.from(data)
            .additionalValue("5.00").build()).toString());

  }

  @Test
  @DisplayName("BankingProductEligibilityV1 for Staff")
  void bankingProductEligibilityStaff() {
    BankingProductEligibilityV1 data =
        new BankingProductEligibilityV1.Builder().type(BankingProductEligibilityType.STAFF).buildPartial();

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is invalid
    assertFalse(
        validator.validate(BankingProductEligibilityV1.Builder.from(data)
            .additionalValue("Invalid").build()).isEmpty(),
        validator.validate(BankingProductEligibilityV1.Builder.from(data)
            .additionalValue("Invalid").build()).toString());
  }

  @Test
  @DisplayName("BankingProductEligibilityV1 for Student")
  void bankingProductEligibilityStudent() {
    BankingProductEligibilityV1 data =
        new BankingProductEligibilityV1.Builder().type(BankingProductEligibilityType.STUDENT).buildPartial();

 // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is ok for discount eligibility
    assertTrue(
        validator.validate(BankingProductEligibilityV1.Builder.from(data)
            .additionalValue("Explanation about the Student conditions for discount eligibility").build()).isEmpty(),
        validator.validate(BankingProductEligibilityV1.Builder.from(data)
            .additionalValue("Explanation about the Student conditions for discount eligibility").build()).toString());
  }


  @Test
  @DisplayName("BankingProductEligibilityV1 for Employment Status")
  void bankingProductEligibilityEmploymentStatus() {

 // Missing description
    BankingProductEligibilityV1 data =
        new BankingProductEligibilityV1.Builder()
            .type(BankingProductEligibilityType.EMPLOYMENT_STATUS).buildPartial();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Correct with name
    assertTrue(
        validator.validate(BankingProductEligibilityV1.Builder.from(data)
            .additionalValue("Employment Status Description").build()).isEmpty(),
        validator.validate(BankingProductEligibilityV1.Builder.from(data)
            .additionalValue("Employment Status Description").build()).toString());

  }

  @Test
  @DisplayName("BankingProductEligibilityV1 for Residency Status")
  void bankingProductEligibilityResidencyStatus() {

    // Missing description
    BankingProductEligibilityV1 data =
        new BankingProductEligibilityV1.Builder()
            .type(BankingProductEligibilityType.RESIDENCY_STATUS).buildPartial();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Correct with name
    assertTrue(
        validator.validate(BankingProductEligibilityV1.Builder.from(data)
            .additionalValue("Residency Status Description").build()).isEmpty(),
        validator.validate(BankingProductEligibilityV1.Builder.from(data)
            .additionalValue("Residency Status Description").build()).toString());

  }


  @Test
  @DisplayName("BankingProductEligibilityV1 for Natural Person")
  void bankingProductEligibilityNaturalPerson() {
    BankingProductEligibilityV1 data = new BankingProductEligibilityV1.Builder()
        .type(BankingProductEligibilityType.NATURAL_PERSON).buildPartial();

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is invalid
    assertFalse(
        validator.validate(BankingProductEligibilityV1.Builder.from(data)
            .additionalValue("Invalid").build()).isEmpty(),
        validator.validate(BankingProductEligibilityV1.Builder.from(data)
            .additionalValue("Invalid").build()).toString());
  }



  @Test
  @DisplayName("BankingProductEligibilityV1 for Other")
  void bankingProductEligibilityOther() {
    BankingProductEligibilityV1 data =
        new BankingProductEligibilityV1.Builder().type(BankingProductEligibilityType.OTHER).buildPartial();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

 // Correct with additional information defined
    assertTrue(
        validator.validate(BankingProductEligibilityV1.Builder.from(data)
            .additionalInfo("Additional Information on Other Feature").build()).isEmpty(),
        validator.validate(BankingProductEligibilityV1.Builder.from(data)
            .additionalValue("Additional Information on Other Feature").build()).toString());
  }

}
