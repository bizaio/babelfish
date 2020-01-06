package io.biza.cdr.babelfish.tests.v1;

import java.math.BigDecimal;
import java.net.URI;
import java.time.Duration;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import io.biza.cdr.babelfish.v1.response.ResponseBankingAccountListData;
import io.biza.cdr.babelfish.v1.response.ResponseBankingAccountsBalanceList;
import io.biza.cdr.babelfish.v1.response.ResponseBankingAccountsBalanceListData;
import io.biza.cdr.babelfish.v1.response.ResponseBankingPayeeById;
import io.biza.cdr.babelfish.v1.response.ResponseBankingPayeeList;
import io.biza.cdr.babelfish.v1.response.ResponseBankingPayeeListData;
import io.biza.cdr.babelfish.v1.response.ResponseBankingScheduledPaymentsList;
import io.biza.cdr.babelfish.v1.response.ResponseBankingScheduledPaymentsListData;
import io.biza.cdr.babelfish.v1.response.ResponseBankingTransactionById;
import io.biza.cdr.babelfish.v1.response.ResponseBankingTransactionList;
import io.biza.cdr.babelfish.v1.response.ResponseBankingTransactionListData;
import io.biza.cdr.babelfish.v1.enumerations.BankingProductCategory;
import io.biza.cdr.babelfish.v1.enumerations.BankingProductConstraintType;
import io.biza.cdr.babelfish.v1.enumerations.BankingProductDepositRateType;
import io.biza.cdr.babelfish.v1.enumerations.BankingProductDiscountEligibilityType;
import io.biza.cdr.babelfish.v1.enumerations.BankingProductDiscountType;
import io.biza.cdr.babelfish.v1.enumerations.BankingProductEligibilityType;
import io.biza.cdr.babelfish.v1.enumerations.BankingProductFeatureType;
import io.biza.cdr.babelfish.v1.enumerations.BankingProductFeeType;
import io.biza.cdr.babelfish.v1.enumerations.BankingProductLendingRateType;
import io.biza.cdr.babelfish.v1.enumerations.BankingTermDepositMaturityInstructions;
import io.biza.cdr.babelfish.v1.enumerations.CommonUnitOfMeasureType;
import io.biza.cdr.babelfish.v1.model.banking.BankingAccount;
import io.biza.cdr.babelfish.v1.model.banking.BankingAccountDetail;
import io.biza.cdr.babelfish.v1.model.banking.BankingBalance;
import io.biza.cdr.babelfish.v1.model.banking.BankingCreditCardAccount;
import io.biza.cdr.babelfish.v1.model.banking.BankingLoanAccount;
import io.biza.cdr.babelfish.v1.model.banking.BankingPayee;
import io.biza.cdr.babelfish.v1.model.banking.BankingPayeeDetail;
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
import io.biza.cdr.babelfish.v1.model.banking.BankingProductRateTier;
import io.biza.cdr.babelfish.v1.model.banking.BankingTermDepositAccount;
import io.biza.cdr.babelfish.v1.model.banking.BankingTransaction;
import io.biza.cdr.babelfish.v1.model.banking.BankingTransactionDetail;
import io.biza.cdr.babelfish.v1.model.common.Links;
import io.biza.cdr.babelfish.v1.model.common.LinksPaginated;
import io.biza.cdr.babelfish.v1.model.common.Meta;
import io.biza.cdr.babelfish.v1.model.common.MetaPaginated;
import io.biza.cdr.babelfish.v1.response.ResponseBankingAccountById;
import io.biza.cdr.babelfish.v1.response.ResponseBankingAccountList;

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
      new BankingProductFee().name("Fee Name").feeType(BankingProductFeeType.PERIODIC)
          .additionalValue("P1D").amount(new BigDecimal("10.00"));
  public static final BankingProductFeeDiscount DEFAULT_BANKING_PRODUCT_FEE_DISCOUNT =
      new BankingProductFeeDiscount().description("Discount Description")
          .amount(new BigDecimal("10.00")).discountType(BankingProductDiscountType.BALANCE)
          .additionalValue("100.00");
  public static final BankingProductFeeDiscountEligibility DEFAULT_BANKING_PRODUCT_FEE_DISCOUNT_ELIGIBILITY =
      new BankingProductFeeDiscountEligibility()
          .discountEligibilityType(BankingProductDiscountEligibilityType.BUSINESS);
  public static final BankingProductDepositRate DEFAULT_BANKING_PRODUCT_DEPOSIT_RATE =
      new BankingProductDepositRate().depositRateType(BankingProductDepositRateType.VARIABLE)
          .rate(new BigDecimal("0.05"));
  public static final BankingProductLendingRate DEFAULT_BANKING_PRODUCT_LENDING_RATE =
      new BankingProductLendingRate().lendingRateType(BankingProductLendingRateType.VARIABLE)
          .rate(new BigDecimal("0.05"));
  public static final BankingProductRateTier DEFAULT_BANKING_PRODUCT_RATE_TIER =
      new BankingProductRateTier().name("Rate Tier Name")
          .unitOfMeasure(CommonUnitOfMeasureType.DOLLAR).minimumValue(new BigDecimal("100.00"));

  public static final LinksPaginated DEFAULT_LINKS_PAGINATED =
      new LinksPaginated().self(ModelConstants.DEFAULT_SELF_URI)
          .first(ModelConstants.DEFAULT_FIRST_URI).next(ModelConstants.DEFAULT_NEXT_URI)
          .last(ModelConstants.DEFAULT_LAST_URI).prev(ModelConstants.DEFAULT_PREV_URI);

  public static final Links DEFAULT_LINKS = new Links().self(ModelConstants.DEFAULT_SELF_URI);

  public static final Meta DEFAULT_META = new Meta();
  public static final MetaPaginated DEFAULT_META_PAGINATED =
      new MetaPaginated().totalPages(10).totalRecords(100);
  public static final BankingAccount DEFAULT_BANKING_ACCOUNT = new BankingAccount();
  public static final ResponseBankingAccountListData DEFAULT_RESPONSE_BANKING_ACCOUNT_LIST_DATA =
      new ResponseBankingAccountListData().accounts(List.of(DEFAULT_BANKING_ACCOUNT));

  public static final BankingAccountDetail DEFAULT_BANKING_ACCOUNT_DETAIL =
      new BankingAccountDetail();
  public static final BankingTermDepositAccount DEFAULT_BANKING_TERM_DEPOSIT_ACCOUNT =
      new BankingTermDepositAccount().lodgementDate(LocalDate.now()).maturityDate(LocalDate.now())
          .maturityInstructions(BankingTermDepositMaturityInstructions.ROLLED_OVER);
  public static final BankingCreditCardAccount DEFAULT_BANKING_CREDIT_CARD_ACCOUNT =
      new BankingCreditCardAccount();
  public static final BankingLoanAccount DEFAULT_BANKING_LOAN_ACCOUNT =
      new BankingLoanAccount().loanEndDate(LocalDate.now()).nextInstalmentDate(LocalDate.now())
          .repaymentFrequency(Duration.ofDays(10));

  public static final ResponseBankingAccountList DEFAULT_RESPONSE_BANKING_ACCOUNT_LIST =
      new ResponseBankingAccountList().links(DEFAULT_LINKS_PAGINATED).meta(DEFAULT_META_PAGINATED)
          .data(DEFAULT_RESPONSE_BANKING_ACCOUNT_LIST_DATA);

  public static final ResponseBankingScheduledPaymentsListData DEFAULT_RESPONSE_BANKING_SCHEDULED_PAYMENTS_LIST_DATA =
      new ResponseBankingScheduledPaymentsListData();

  public static final ResponseBankingScheduledPaymentsList DEFAULT_RESPONSE_BANKING_SCHEDULED_PAYMENTS_LIST =
      new ResponseBankingScheduledPaymentsList().links(DEFAULT_LINKS_PAGINATED)
          .meta(DEFAULT_META_PAGINATED).data(DEFAULT_RESPONSE_BANKING_SCHEDULED_PAYMENTS_LIST_DATA);

  public static final BankingPayee DEFAULT_BANKING_PAYEE = new BankingPayee();

  public static final ResponseBankingPayeeListData DEFAULT_RESPONSE_BANKING_PAYEE_LIST_DATA =
      new ResponseBankingPayeeListData().payees(List.of(DEFAULT_BANKING_PAYEE));

  public static final ResponseBankingPayeeList DEFAULT_RESPONSE_BANKING_PAYEE_LIST =
      new ResponseBankingPayeeList().links(DEFAULT_LINKS_PAGINATED).meta(DEFAULT_META_PAGINATED)
          .data(DEFAULT_RESPONSE_BANKING_PAYEE_LIST_DATA);

  public static final ResponseBankingAccountById DEFAULT_RESPONSE_BANKING_ACCOUNT_BY_ID =
      new ResponseBankingAccountById().links(DEFAULT_LINKS).meta(DEFAULT_META)
          .data(DEFAULT_BANKING_ACCOUNT_DETAIL);

  public static final BankingTransactionDetail DEFAULT_BANKING_TRANSACTION_DETAIL =
      new BankingTransactionDetail();

  public static final ResponseBankingTransactionById DEFAULT_RESPONSE_BANKING_TRANSACTION_BY_ID =
      new ResponseBankingTransactionById().links(DEFAULT_LINKS)
          .data(DEFAULT_BANKING_TRANSACTION_DETAIL);


  public static final BankingTransaction DEFAULT_BANKING_TRANSACTION = new BankingTransaction();
  public static final ResponseBankingTransactionListData DEFAULT_RESPONSE_BANKING_TRANSACTION_LIST_DATA =
      new ResponseBankingTransactionListData().transactions(List.of(DEFAULT_BANKING_TRANSACTION));

  public static final ResponseBankingTransactionList DEFAULT_RESPONSE_BANKING_TRANSACTION_LIST =
      new ResponseBankingTransactionList().meta(DEFAULT_META_PAGINATED)
          .links(DEFAULT_LINKS_PAGINATED).data(DEFAULT_RESPONSE_BANKING_TRANSACTION_LIST_DATA);

  public static final BankingBalance DEFAULT_BANKING_BALANCE = new BankingBalance();

  public static final ResponseBankingAccountsBalanceListData DEFAULT_BANKING_ACCOUNTS_BALANCE_LIST_DATA =
      new ResponseBankingAccountsBalanceListData().balances(List.of(DEFAULT_BANKING_BALANCE));

  public static final ResponseBankingAccountsBalanceList DEFAULT_RESPONSE_BANKING_ACCOUNTS_BALANCE_LIST =
      new ResponseBankingAccountsBalanceList().meta(DEFAULT_META_PAGINATED)
          .links(DEFAULT_LINKS_PAGINATED).data(DEFAULT_BANKING_ACCOUNTS_BALANCE_LIST_DATA);

  public static final BankingPayeeDetail DEFAULT_PAYEE_DETAIL = new BankingPayeeDetail();
  public static final ResponseBankingPayeeById DEFAULT_RESPONSE_BANKING_PAYEE_BY_ID =
      new ResponseBankingPayeeById().links(DEFAULT_LINKS).data(DEFAULT_PAYEE_DETAIL);

}
