package io.biza.cdr.babelfish.tests.v1.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.cdr.babelfish.tests.v1.ModelConstants;
import io.biza.cdr.babelfish.v1.enumerations.BankingProductDiscountEligibilityType;
import io.biza.cdr.babelfish.v1.model.banking.BankingProductFeeDiscountEligibility;

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
    BankingProductFeeDiscountEligibility data = new BankingProductFeeDiscountEligibility()
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
    BankingProductFeeDiscountEligibility data = new BankingProductFeeDiscountEligibility()
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
    BankingProductFeeDiscountEligibility data = new BankingProductFeeDiscountEligibility()
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
    BankingProductFeeDiscountEligibility data = new BankingProductFeeDiscountEligibility()
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
    BankingProductFeeDiscountEligibility data = new BankingProductFeeDiscountEligibility()
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
    BankingProductFeeDiscountEligibility data = new BankingProductFeeDiscountEligibility()
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
    BankingProductFeeDiscountEligibility data = new BankingProductFeeDiscountEligibility()
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
    BankingProductFeeDiscountEligibility data = new BankingProductFeeDiscountEligibility()
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
    BankingProductFeeDiscountEligibility data = new BankingProductFeeDiscountEligibility()
        .discountEligibilityType(BankingProductDiscountEligibilityType.EMPLOYMENT_STATUS);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Correct with name
    data.setAdditionalValue("Employment Status Description");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

  @Test
  @DisplayName("BankingProductFeeDiscountEligibility for Residency Status")
  void bankingProductFeeDiscountEligibilityResidencyStatus() {

    // Missing description
    BankingProductFeeDiscountEligibility data = new BankingProductFeeDiscountEligibility()
        .discountEligibilityType(BankingProductDiscountEligibilityType.RESIDENCY_STATUS);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Correct with name
    data.setAdditionalValue("Residency Status Description");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }


  @Test
  @DisplayName("BankingProductFeeDiscountEligibility for Natural Person")
  void bankingProductFeeDiscountEligibilityNaturalPerson() {
    BankingProductFeeDiscountEligibility data = new BankingProductFeeDiscountEligibility()
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
    BankingProductFeeDiscountEligibility data = new BankingProductFeeDiscountEligibility()
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
    BankingProductFeeDiscountEligibility data = new BankingProductFeeDiscountEligibility()
        .discountEligibilityType(BankingProductDiscountEligibilityType.OTHER);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Correct with additional information defined
    data.setAdditionalInfo("Additional Information on Other feature");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

}
