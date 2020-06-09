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
import io.biza.babelfish.cdr.enumerations.BankingProductDiscountEligibilityType;
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductDiscountEligibilityV1;
import io.biza.babelfish.cdr.tests.v1.model.ModelConstants;

@DisplayName("BankingProductDiscountEligibility V1 Tests")
public class BankingProductDiscountEligibilityV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Valid BankingProductFeeDiscountEligibility")
  void bankingProductFeeDiscountEligibility() {
    assertTrue(
        validator.validate(ModelConstants.DEFAULT_BANKING_PRODUCT_FEE_DISCOUNT_ELIGIBILITY)
            .isEmpty(),
        validator.validate(ModelConstants.DEFAULT_BANKING_PRODUCT_FEE_DISCOUNT_ELIGIBILITY)
            .toString());
  }

  @Test
  @DisplayName("BankingProductFeeDiscountEligibility for Business")
  void bankingProductFeeDiscountEligibilityBusiness() {
    BankingProductDiscountEligibilityV1 data = new BankingProductDiscountEligibilityV1()
        .discountEligibilityType(BankingProductDiscountEligibilityType.BUSINESS);

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is invalid
    data.additionalValue("Invalid");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("BankingProductFeeDiscountEligibility for Pension Recipient")
  void bankingProductFeeDiscountEligibilityPensionRecipient() {
    BankingProductDiscountEligibilityV1 data = new BankingProductDiscountEligibilityV1()
        .discountEligibilityType(BankingProductDiscountEligibilityType.PENSION_RECIPIENT);

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is also correct
    data.additionalValue("Pension Recipient Explanation");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("BankingProductFeeDiscountEligibility for Minimum Age")
  void bankingProductFeeDiscountEligibilityMinAge() {
    BankingProductDiscountEligibilityV1 data = new BankingProductDiscountEligibilityV1()
        .discountEligibilityType(BankingProductDiscountEligibilityType.MIN_AGE);

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
  @DisplayName("BankingProductFeeDiscountEligibility for Maximum Age")
  void bankingProductFeeDiscountEligibilityMaxAge() {
    BankingProductDiscountEligibilityV1 data = new BankingProductDiscountEligibilityV1()
        .discountEligibilityType(BankingProductDiscountEligibilityType.MAX_AGE);

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
  @DisplayName("BankingProductFeeDiscountEligibility for Minimum Income")
  void bankingProductFeeDiscountEligibilityMinIncome() {
    BankingProductDiscountEligibilityV1 data = new BankingProductDiscountEligibilityV1()
        .discountEligibilityType(BankingProductDiscountEligibilityType.MIN_INCOME);

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
  @DisplayName("BankingProductFeeDiscountEligibility for Minimum Turnover")
  void bankingProductFeeDiscountEligibilityMinTurnover() {
    BankingProductDiscountEligibilityV1 data = new BankingProductDiscountEligibilityV1()
        .discountEligibilityType(BankingProductDiscountEligibilityType.MIN_TURNOVER);

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
  @DisplayName("BankingProductFeeDiscountEligibility for Staff")
  void bankingProductFeeDiscountEligibilityStaff() {
    BankingProductDiscountEligibilityV1 data = new BankingProductDiscountEligibilityV1()
        .discountEligibilityType(BankingProductDiscountEligibilityType.STAFF);

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is invalid
    data.additionalValue("Invalid");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("BankingProductFeeDiscountEligibility for Student")
  void bankingProductFeeDiscountEligibilityStudent() {
    BankingProductDiscountEligibilityV1 data = new BankingProductDiscountEligibilityV1()
        .discountEligibilityType(BankingProductDiscountEligibilityType.STUDENT);

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is ok for discount eligibility
    data.additionalValue("Explanation about the Student conditions for discount eligibility");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }


  @Test
  @DisplayName("BankingProductFeeDiscountEligibility for Employment Status")
  void bankingProductFeeDiscountEligibilityEmploymentStatus() {

    // Missing description
    BankingProductDiscountEligibilityV1 data = new BankingProductDiscountEligibilityV1()
        .discountEligibilityType(BankingProductDiscountEligibilityType.EMPLOYMENT_STATUS);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Correct with name
    data.additionalValue("Employment Status Description");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

  @Test
  @DisplayName("BankingProductFeeDiscountEligibility for Residency Status")
  void bankingProductFeeDiscountEligibilityResidencyStatus() {

    // Missing description
    BankingProductDiscountEligibilityV1 data = new BankingProductDiscountEligibilityV1()
        .discountEligibilityType(BankingProductDiscountEligibilityType.RESIDENCY_STATUS);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Correct with name
    data.additionalValue("Residency Status Description");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }


  @Test
  @DisplayName("BankingProductFeeDiscountEligibility for Natural Person")
  void bankingProductFeeDiscountEligibilityNaturalPerson() {
    BankingProductDiscountEligibilityV1 data = new BankingProductDiscountEligibilityV1()
        .discountEligibilityType(BankingProductDiscountEligibilityType.NATURAL_PERSON);

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is invalid
    data.additionalValue("Invalid");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("BankingProductFeeDiscountEligibility for Introductory Rate")
  void bankingProductFeeDiscountEligibilityIntroductoryRate() {
    BankingProductDiscountEligibilityV1 data = new BankingProductDiscountEligibilityV1()
        .discountEligibilityType(BankingProductDiscountEligibilityType.INTRODUCTORY);

    // Null Value invalid
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Invalid String
    data.additionalValue("Not a Duration");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Duration String formatted value is valid
    data.additionalValue("P1D");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("BankingProductFeeDiscountEligibility for Other")
  void bankingProductFeeDiscountEligibilityOther() {
    BankingProductDiscountEligibilityV1 data = new BankingProductDiscountEligibilityV1()
        .discountEligibilityType(BankingProductDiscountEligibilityType.OTHER);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Correct with additional information defined
    data.additionalInfo("Additional Information on Other feature");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

}
