package io.biza.cdr.babelfish.tests.v1;

import java.math.BigDecimal;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.List;
import io.biza.cdr.babelfish.v1.enumerations.BankingProductCategory;
import io.biza.cdr.babelfish.v1.enumerations.BankingProductConstraintType;
import io.biza.cdr.babelfish.v1.enumerations.BankingProductDepositRateType;
import io.biza.cdr.babelfish.v1.enumerations.BankingProductDiscountEligibilityType;
import io.biza.cdr.babelfish.v1.enumerations.BankingProductDiscountType;
import io.biza.cdr.babelfish.v1.enumerations.BankingProductEligibilityType;
import io.biza.cdr.babelfish.v1.enumerations.BankingProductFeatureType;
import io.biza.cdr.babelfish.v1.enumerations.BankingProductFeeType;
import io.biza.cdr.babelfish.v1.enumerations.BankingProductLendingRateType;
import io.biza.cdr.babelfish.v1.model.banking.BankingProduct;
import io.biza.cdr.babelfish.v1.model.banking.BankingProductBundle;
import io.biza.cdr.babelfish.v1.model.banking.BankingProductConstraint;
import io.biza.cdr.babelfish.v1.model.banking.BankingProductDepositRate;
import io.biza.cdr.babelfish.v1.model.banking.BankingProductDetail;
import io.biza.cdr.babelfish.v1.model.banking.BankingProductEligibility;
import io.biza.cdr.babelfish.v1.model.banking.BankingProductFeature;
import io.biza.cdr.babelfish.v1.model.banking.BankingProductFee;
import io.biza.cdr.babelfish.v1.model.banking.BankingProductFeeDiscount;
import io.biza.cdr.babelfish.v1.model.banking.BankingProductFeeDiscountEligibility;
import io.biza.cdr.babelfish.v1.model.banking.BankingProductLendingRate;

/**
 * ModelConstants This defines valid models for manipulation within test cases
 *
 */
public class ModelConstants {
  public static final URI DEFAULT_FIRST_URI = URI.create("http://localhost/?page=1");
  public static final URI DEFAULT_SELF_URI = URI.create("http://localhost/?page=3");
  public static final URI DEFAULT_LAST_URI = URI.create("http://localhost/?page=10");
  public static final URI DEFAULT_PREV_URI = URI.create("http://localhost/?page=2");
  public static final URI DEFAULT_NEXT_URI = URI.create("http://localhost/?page=4");
  public static final List<String> DEFAULT_ACCOUNT_IDS =
      List.of("0be1c793-87ba-4942-95bd-4c972ec43a2d", "d5305a6c-b828-4651-bbcc-b3ea7264d387");
  public static final BankingProduct DEFAULT_BANKING_PRODUCT =
      new BankingProduct().productId("test").lastUpdated(OffsetDateTime.now())
          .productCategory(BankingProductCategory.BUSINESS_LOANS).name("Test")
          .description("Test Description").brand("ACME").isTailored(false);
  public static final BankingProductDetail DEFAULT_BANKING_PRODUCT_DETAIL =
      new BankingProductDetail().productId("test").lastUpdated(OffsetDateTime.now())
          .productCategory(BankingProductCategory.BUSINESS_LOANS).name("Test")
          .description("Test Description").brand("ACME").isTailored(false);
  public static final BankingProductBundle DEFAULT_BANKING_PRODUCT_BUNDLE =
      new BankingProductBundle().name("Bundle Name").description("Bundle Description");
  public static final BankingProductFeature DEFAULT_BANKING_PRODUCT_FEATURE =
      new BankingProductFeature().featureType(BankingProductFeatureType.ADDITIONAL_CARDS)
          .additionalValue("10");
  public static final BankingProductConstraint DEFAULT_BANKING_PRODUCT_CONSTRAINT =
      new BankingProductConstraint().constraintType(BankingProductConstraintType.MAX_BALANCE)
          .additionalValue("10.00");
  public static final BankingProductEligibility DEFAULT_BANKING_PRODUCT_ELIGIBILITY =
      new BankingProductEligibility().eligibilityType(BankingProductEligibilityType.BUSINESS);
  public static final BankingProductFee DEFAULT_BANKING_PRODUCT_FEE =
      new BankingProductFee().name("Fee Name").feeType(BankingProductFeeType.PERIODIC).additionalValue("P1D").amount(new BigDecimal("10.00"));
  public static final BankingProductFeeDiscount DEFAULT_BANKING_PRODUCT_FEE_DISCOUNT =
      new BankingProductFeeDiscount().description("Discount Description").amount(new BigDecimal("10.00")).discountType(BankingProductDiscountType.BALANCE)
          .additionalValue("100.00");
  public static final BankingProductFeeDiscountEligibility DEFAULT_BANKING_PRODUCT_FEE_DISCOUNT_ELIGIBILITY =
      new BankingProductFeeDiscountEligibility()
          .discountEligibilityType(BankingProductDiscountEligibilityType.BUSINESS);
  public static final BankingProductDepositRate DEFAULT_BANKING_PRODUCT_DEPOSIT_RATE =
      new BankingProductDepositRate().depositRateType(BankingProductDepositRateType.VARIABLE).rate(new BigDecimal("0.05"));
  public static final BankingProductLendingRate DEFAULT_BANKING_PRODUCT_LENDING_RATE =
      new BankingProductLendingRate().lendingRateType(BankingProductLendingRateType.VARIABLE).rate(new BigDecimal("0.05"));

}
