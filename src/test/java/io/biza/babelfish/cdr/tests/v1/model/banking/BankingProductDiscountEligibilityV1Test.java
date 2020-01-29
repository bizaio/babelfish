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
import io.biza.babelfish.enumerations.cdr.BankingProductDiscountEligibilityType;
import io.biza.babelfish.interfaces.cdr.banking.product.BankingProductDepositRateV1;
import io.biza.babelfish.interfaces.cdr.banking.product.BankingProductFeeDiscountEligibilityV1;

@DisplayName("BankingProductDiscountEligibility V1 Tests")
public class BankingProductDiscountEligibilityV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Valid BankingProductFeeDiscountEligibilityV1")
  void bankingProductFeeDiscountEligibility() {
    assertTrue(
        validator.validate(ModelConstants.DEFAULT_BANKING_PRODUCT_FEE_DISCOUNT_ELIGIBILITY)
            .isEmpty(),
        validator.validate(ModelConstants.DEFAULT_BANKING_PRODUCT_FEE_DISCOUNT_ELIGIBILITY)
            .toString());
  }

  @Test
  @DisplayName("BankingProductFeeDiscountEligibilityV1 for Business")
  void bankingProductFeeDiscountEligibilityBusiness() {
    BankingProductFeeDiscountEligibilityV1 data =
        new BankingProductFeeDiscountEligibilityV1.Builder()
            .type(BankingProductDiscountEligibilityType.BUSINESS).buildPartial();

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is invalid
    assertFalse(
        validator.validate(BankingProductFeeDiscountEligibilityV1.Builder.from(data)
            .additionalValue("Invalid").build()).isEmpty(),
        validator.validate(BankingProductFeeDiscountEligibilityV1.Builder.from(data)
            .additionalValue("Invalid").build()).toString());
  }

  @Test
  @DisplayName("BankingProductFeeDiscountEligibilityV1 for Pension Recipient")
  void bankingProductFeeDiscountEligibilityPensionRecipient() {
    BankingProductFeeDiscountEligibilityV1 data =
        new BankingProductFeeDiscountEligibilityV1.Builder()
            .type(BankingProductDiscountEligibilityType.PENSION_RECIPIENT).buildPartial();

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is also correct
    assertTrue(
        validator.validate(BankingProductFeeDiscountEligibilityV1.Builder.from(data)
            .additionalValue("Pension Recipient Explanation").build()).isEmpty(),
        validator.validate(BankingProductFeeDiscountEligibilityV1.Builder.from(data)
            .additionalValue("Pension Recipient Explanation").build()).toString());
  }

  @Test
  @DisplayName("BankingProductFeeDiscountEligibilityV1 for Minimum Age")
  void bankingProductFeeDiscountEligibilityMinAge() {
    BankingProductFeeDiscountEligibilityV1 data =
        new BankingProductFeeDiscountEligibilityV1.Builder()
            .type(BankingProductDiscountEligibilityType.MIN_AGE).buildPartial();

    // Null Value is invalid
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Invalid number
    assertFalse(
        validator.validate(BankingProductFeeDiscountEligibilityV1.Builder.from(data)
            .additionalValue("Not a Number").build()).isEmpty(),
        validator.validate(BankingProductFeeDiscountEligibilityV1.Builder.from(data)
            .additionalValue("Not a Number").build()).toString());

    // A decimal is invalid
    assertFalse(
        validator.validate(BankingProductFeeDiscountEligibilityV1.Builder.from(data)
            .additionalValue("5.00").build()).isEmpty(),
        validator.validate(BankingProductFeeDiscountEligibilityV1.Builder.from(data)
            .additionalValue("5.00").build()).toString());

    // A single integer is valid
    assertTrue(
        validator.validate(
            BankingProductFeeDiscountEligibilityV1.Builder.from(data).additionalValue("18").build())
            .isEmpty(),
        validator.validate(
            BankingProductFeeDiscountEligibilityV1.Builder.from(data).additionalValue("18").build())
            .toString());

  }

  @Test
  @DisplayName("BankingProductFeeDiscountEligibilityV1 for Maximum Age")
  void bankingProductFeeDiscountEligibilityMaxAge() {
    BankingProductFeeDiscountEligibilityV1 data =
        new BankingProductFeeDiscountEligibilityV1.Builder()
            .type(BankingProductDiscountEligibilityType.MAX_AGE).buildPartial();

    // Null Value is invalid
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Invalid number
    assertFalse(
        validator.validate(BankingProductFeeDiscountEligibilityV1.Builder.from(data)
            .additionalValue("Not a Number").build()).isEmpty(),
        validator.validate(BankingProductFeeDiscountEligibilityV1.Builder.from(data)
            .additionalValue("Not a Number").build()).toString());

    // A decimal is invalid
    assertFalse(
        validator.validate(BankingProductFeeDiscountEligibilityV1.Builder.from(data)
            .additionalValue("5.00").build()).isEmpty(),
        validator.validate(BankingProductFeeDiscountEligibilityV1.Builder.from(data)
            .additionalValue("5.00").build()).toString());

    // A single integer is valid
    assertTrue(
        validator.validate(
            BankingProductFeeDiscountEligibilityV1.Builder.from(data).additionalValue("18").build())
            .isEmpty(),
        validator.validate(
            BankingProductFeeDiscountEligibilityV1.Builder.from(data).additionalValue("18").build())
            .toString());

  }

  @Test
  @DisplayName("BankingProductFeeDiscountEligibilityV1 for Minimum Income")
  void bankingProductFeeDiscountEligibilityMinIncome() {
    BankingProductFeeDiscountEligibilityV1 data =
        new BankingProductFeeDiscountEligibilityV1.Builder()
            .type(BankingProductDiscountEligibilityType.MIN_INCOME).buildPartial();

    // Null Value invalid
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Invalid number
    assertFalse(
        validator.validate(BankingProductFeeDiscountEligibilityV1.Builder.from(data)
            .additionalValue("Not a Number").build()).isEmpty(),
        validator.validate(BankingProductFeeDiscountEligibilityV1.Builder.from(data)
            .additionalValue("Not a Number").build()).toString());

    // A number value which is not in AmountString format is invalid
    assertFalse(
        validator.validate(
            BankingProductFeeDiscountEligibilityV1.Builder.from(data).additionalValue("18").build())
            .isEmpty(),
        validator.validate(
            BankingProductFeeDiscountEligibilityV1.Builder.from(data).additionalValue("18").build())
            .toString());

    // AmountString formatted value is valid
    assertTrue(
        validator.validate(BankingProductFeeDiscountEligibilityV1.Builder.from(data)
            .additionalValue("5.00").build()).isEmpty(),
        validator.validate(BankingProductFeeDiscountEligibilityV1.Builder.from(data)
            .additionalValue("5.00").build()).toString());


  }

  @Test
  @DisplayName("BankingProductFeeDiscountEligibilityV1 for Minimum Turnover")
  void bankingProductFeeDiscountEligibilityMinTurnover() {
    BankingProductFeeDiscountEligibilityV1 data =
        new BankingProductFeeDiscountEligibilityV1.Builder()
            .type(BankingProductDiscountEligibilityType.MIN_TURNOVER).buildPartial();

    // Null Value invalid
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

 // Invalid number
    assertFalse(
        validator.validate(BankingProductFeeDiscountEligibilityV1.Builder.from(data)
            .additionalValue("Not a Number").build()).isEmpty(),
        validator.validate(BankingProductFeeDiscountEligibilityV1.Builder.from(data)
            .additionalValue("Not a Number").build()).toString());

    // A number value which is not in AmountString format is invalid
    assertFalse(
        validator.validate(
            BankingProductFeeDiscountEligibilityV1.Builder.from(data).additionalValue("18").build())
            .isEmpty(),
        validator.validate(
            BankingProductFeeDiscountEligibilityV1.Builder.from(data).additionalValue("18").build())
            .toString());

    // AmountString formatted value is valid
    assertTrue(
        validator.validate(BankingProductFeeDiscountEligibilityV1.Builder.from(data)
            .additionalValue("5.00").build()).isEmpty(),
        validator.validate(BankingProductFeeDiscountEligibilityV1.Builder.from(data)
            .additionalValue("5.00").build()).toString());

  }

  @Test
  @DisplayName("BankingProductFeeDiscountEligibilityV1 for Staff")
  void bankingProductFeeDiscountEligibilityStaff() {
    BankingProductFeeDiscountEligibilityV1 data =
        new BankingProductFeeDiscountEligibilityV1.Builder()
            .type(BankingProductDiscountEligibilityType.STAFF).buildPartial();

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is invalid
    assertFalse(
        validator.validate(BankingProductFeeDiscountEligibilityV1.Builder.from(data)
            .additionalValue("Invalid").build()).isEmpty(),
        validator.validate(BankingProductFeeDiscountEligibilityV1.Builder.from(data)
            .additionalValue("Invalid").build()).toString());
    
  }

  @Test
  @DisplayName("BankingProductFeeDiscountEligibilityV1 for Student")
  void bankingProductFeeDiscountEligibilityStudent() {
    BankingProductFeeDiscountEligibilityV1 data =
        new BankingProductFeeDiscountEligibilityV1.Builder()
            .type(BankingProductDiscountEligibilityType.STUDENT).buildPartial();

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is ok for discount eligibility
    assertTrue(
        validator.validate(BankingProductFeeDiscountEligibilityV1.Builder.from(data)
            .additionalValue("Explanation about the Student conditions for discount eligibility").build()).isEmpty(),
        validator.validate(BankingProductFeeDiscountEligibilityV1.Builder.from(data)
            .additionalValue("Explanation about the Student conditions for discount eligibility").build()).toString());
  }


  @Test
  @DisplayName("BankingProductFeeDiscountEligibilityV1 for Employment Status")
  void bankingProductFeeDiscountEligibilityEmploymentStatus() {

    // Missing description
    BankingProductFeeDiscountEligibilityV1 data =
        new BankingProductFeeDiscountEligibilityV1.Builder()
            .type(BankingProductDiscountEligibilityType.EMPLOYMENT_STATUS).buildPartial();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Correct with name
    assertTrue(
        validator.validate(BankingProductFeeDiscountEligibilityV1.Builder.from(data)
            .additionalValue("Employment Status Description").build()).isEmpty(),
        validator.validate(BankingProductFeeDiscountEligibilityV1.Builder.from(data)
            .additionalValue("Employment Status Description").build()).toString());
    

  }

  @Test
  @DisplayName("BankingProductFeeDiscountEligibilityV1 for Residency Status")
  void bankingProductFeeDiscountEligibilityResidencyStatus() {

    // Missing description
    BankingProductFeeDiscountEligibilityV1 data =
        new BankingProductFeeDiscountEligibilityV1.Builder()
            .type(BankingProductDiscountEligibilityType.RESIDENCY_STATUS).buildPartial();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Correct with name
    assertTrue(
        validator.validate(BankingProductFeeDiscountEligibilityV1.Builder.from(data)
            .additionalValue("Residency Status Description").build()).isEmpty(),
        validator.validate(BankingProductFeeDiscountEligibilityV1.Builder.from(data)
            .additionalValue("Residency Status Description").build()).toString());
    

  }


  @Test
  @DisplayName("BankingProductFeeDiscountEligibilityV1 for Natural Person")
  void bankingProductFeeDiscountEligibilityNaturalPerson() {
    BankingProductFeeDiscountEligibilityV1 data =
        new BankingProductFeeDiscountEligibilityV1.Builder()
            .type(BankingProductDiscountEligibilityType.NATURAL_PERSON).buildPartial();

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is invalid
    assertFalse(
        validator.validate(BankingProductFeeDiscountEligibilityV1.Builder.from(data)
            .additionalValue("Invalid").build()).isEmpty(),
        validator.validate(BankingProductFeeDiscountEligibilityV1.Builder.from(data)
            .additionalValue("Invalid").build()).toString());
    
  }

  @Test
  @DisplayName("BankingProductFeeDiscountEligibilityV1 for Introductory Rate")
  void bankingProductFeeDiscountEligibilityIntroductoryRate() {
    BankingProductFeeDiscountEligibilityV1 data =
        new BankingProductFeeDiscountEligibilityV1.Builder()
            .type(BankingProductDiscountEligibilityType.INTRODUCTORY).buildPartial();

    // Null Value invalid
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    // Invalid String
    assertFalse(
        validator.validate(BankingProductFeeDiscountEligibilityV1.Builder.from(data)
            .additionalValue("Not a Duration").build()).isEmpty(),
        validator.validate(BankingProductFeeDiscountEligibilityV1.Builder.from(data)
            .additionalValue("Not a Duration").build()).toString());

    // Duration String formatted value is valid
    assertTrue(
        validator
            .validate(BankingProductFeeDiscountEligibilityV1.Builder.from(data).additionalValue("P1D").build())
            .isEmpty(),
        validator
            .validate(BankingProductFeeDiscountEligibilityV1.Builder.from(data).additionalValue("P1D").build())
            .toString());

  }

  @Test
  @DisplayName("BankingProductFeeDiscountEligibilityV1 for Other")
  void bankingProductFeeDiscountEligibilityOther() {
    BankingProductFeeDiscountEligibilityV1 data =
        new BankingProductFeeDiscountEligibilityV1.Builder()
            .type(BankingProductDiscountEligibilityType.OTHER).buildPartial();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Correct with additional information defined
    assertTrue(
        validator.validate(BankingProductFeeDiscountEligibilityV1.Builder.from(data)
            .additionalInfo("Additional Information on Other Feature").build()).isEmpty(),
        validator.validate(BankingProductFeeDiscountEligibilityV1.Builder.from(data)
            .additionalValue("Additional Information on Other Feature").build()).toString());
    
  }

}
