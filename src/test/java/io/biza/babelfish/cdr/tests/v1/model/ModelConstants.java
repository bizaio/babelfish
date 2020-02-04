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
package io.biza.babelfish.cdr.tests.v1.model;

import java.math.BigDecimal;
import java.net.URI;
import java.time.Duration;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.Period;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import io.biza.babelfish.cdr.enumerations.AddressPAFStateType;
import io.biza.babelfish.cdr.enumerations.AddressPurpose;
import io.biza.babelfish.cdr.enumerations.BankingPayeeType;
import io.biza.babelfish.cdr.enumerations.BankingProductCategory;
import io.biza.babelfish.cdr.enumerations.BankingProductConstraintType;
import io.biza.babelfish.cdr.enumerations.BankingProductDepositRateType;
import io.biza.babelfish.cdr.enumerations.BankingProductDiscountEligibilityType;
import io.biza.babelfish.cdr.enumerations.BankingProductDiscountType;
import io.biza.babelfish.cdr.enumerations.BankingProductEligibilityType;
import io.biza.babelfish.cdr.enumerations.BankingProductFeatureType;
import io.biza.babelfish.cdr.enumerations.BankingProductFeeType;
import io.biza.babelfish.cdr.enumerations.BankingProductLendingRateType;
import io.biza.babelfish.cdr.enumerations.BankingScheduledPaymentStatus;
import io.biza.babelfish.cdr.enumerations.BankingTermDepositMaturityInstructions;
import io.biza.babelfish.cdr.enumerations.BankingTransactionService;
import io.biza.babelfish.cdr.enumerations.BankingTransactionStatus;
import io.biza.babelfish.cdr.enumerations.BankingTransactionType;
import io.biza.babelfish.cdr.enumerations.CommonDiscoveryStatusType;
import io.biza.babelfish.cdr.enumerations.CommonEmailAddressPurpose;
import io.biza.babelfish.cdr.enumerations.CommonOrganisationType;
import io.biza.babelfish.cdr.enumerations.CommonPhoneNumberPurpose;
import io.biza.babelfish.cdr.enumerations.CommonUnitOfMeasureType;
import io.biza.babelfish.cdr.enumerations.CommonWeekDay;
import io.biza.babelfish.cdr.enumerations.PayloadTypeAddress;
import io.biza.babelfish.cdr.enumerations.PayloadTypeBankingDomesticPayee;
import io.biza.babelfish.cdr.enumerations.PayloadTypeBankingDomesticPayeePayId;
import io.biza.babelfish.cdr.enumerations.PayloadTypeBankingPayee;
import io.biza.babelfish.cdr.enumerations.PayloadTypeBankingScheduledPaymentRecurrence;
import io.biza.babelfish.cdr.enumerations.PayloadTypeBankingScheduledPaymentTo;
import io.biza.babelfish.cdr.enumerations.PayloadTypeCustomer;
import io.biza.babelfish.cdr.models.payloads.banking.account.BankingAccountDetailV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.BankingAccountV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.BankingCreditCardAccountV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.BankingLoanAccountV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.BankingTermDepositAccountV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.balance.BankingBalanceV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.balance.BankingBalancePurseV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.directdebit.BankingAuthorisedEntityV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.directdebit.BankingDirectDebitV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.BankingPayeeV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.BankingPayeeDetailV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.bpay.BankingBillerPayeeV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.domestic.BankingDomesticPayeeV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.domestic.BankingDomesticPayeeAccountV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.domestic.BankingDomesticPayeeCardV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.domestic.BankingDomesticPayeePayIdV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.international.BankingInternationalPayeeV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.international.BankingInternationalPayeeBankDetailsV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.international.BankingInternationalPayeeBeneficiaryDetailsV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.scheduled.BankingScheduledPaymentV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.scheduled.BankingScheduledPaymentFromV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.scheduled.BankingScheduledPaymentIntervalV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.scheduled.BankingScheduledPaymentRecurrenceV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.scheduled.BankingScheduledPaymentRecurrenceEventBasedV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.scheduled.BankingScheduledPaymentRecurrenceIntervalScheduleV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.scheduled.BankingScheduledPaymentRecurrenceLastWeekdayV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.scheduled.BankingScheduledPaymentRecurrenceOnceOffV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.scheduled.BankingScheduledPaymentSetV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.scheduled.BankingScheduledPaymentToV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.transaction.BankingTransactionV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.transaction.BankingTransactionDetailV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.transaction.BankingTransactionDetailExtendedDataV1;
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductBundleV1;
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductConstraintV1;
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductDepositRateV1;
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductDetailV1;
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductDetailV2;
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductDiscountV1;
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductEligibilityV1;
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductFeatureV1;
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductFeeV1;
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductFeeDiscountEligibilityV1;
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductLendingRateV1;
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductRateTierV1;
import io.biza.babelfish.cdr.models.payloads.common.CommonDiscoveryOutageV1;
import io.biza.babelfish.cdr.models.payloads.common.CommonDiscoveryStatusV1;
import io.biza.babelfish.cdr.models.payloads.common.CommonEmailAddressV1;
import io.biza.babelfish.cdr.models.payloads.common.CommonOrganisationV1;
import io.biza.babelfish.cdr.models.payloads.common.CommonOrganisationDetail;
import io.biza.babelfish.cdr.models.payloads.common.CommonPAFAddressV1;
import io.biza.babelfish.cdr.models.payloads.common.CommonPersonV1;
import io.biza.babelfish.cdr.models.payloads.common.CommonPersonDetailV1;
import io.biza.babelfish.cdr.models.payloads.common.CommonPhoneNumberV1;
import io.biza.babelfish.cdr.models.payloads.common.CommonPhysicalAddressV1;
import io.biza.babelfish.cdr.models.payloads.common.CommonPhysicalAddressWithPurposeV1;
import io.biza.babelfish.cdr.models.payloads.common.CommonSimpleAddressV1;
import io.biza.babelfish.cdr.models.payloads.ErrorV1;
import io.biza.babelfish.cdr.models.payloads.LinksPaginatedV1;
import io.biza.babelfish.cdr.models.payloads.LinksV1;
import io.biza.babelfish.cdr.models.payloads.MetaV1;
import io.biza.babelfish.cdr.models.payloads.MetaPaginatedV1;
import io.biza.babelfish.cdr.models.responses.ResponseBankingAccountByIdV1;
import io.biza.babelfish.cdr.models.responses.ResponseBankingAccountListV1;
import io.biza.babelfish.cdr.models.responses.ResponseBankingAccountsBalanceListV1;
import io.biza.babelfish.cdr.models.responses.ResponseBankingDirectDebitAuthorisationListV1;
import io.biza.babelfish.cdr.models.responses.ResponseBankingPayeeByIdV1;
import io.biza.babelfish.cdr.models.responses.ResponseBankingPayeeListV1;
import io.biza.babelfish.cdr.models.responses.ResponseBankingScheduledPaymentsListV1;
import io.biza.babelfish.cdr.models.responses.ResponseBankingTransactionByIdV1;
import io.biza.babelfish.cdr.models.responses.ResponseBankingTransactionListV1;
import io.biza.babelfish.cdr.models.responses.ResponseCommonCustomerV1;
import io.biza.babelfish.cdr.models.responses.ResponseCommonCustomerDetail;
import io.biza.babelfish.cdr.models.responses.ResponseCommonDiscoveryOutagesListV1;
import io.biza.babelfish.cdr.models.responses.ResponseCommonDiscoveryStatusV1;
import io.biza.babelfish.cdr.models.responses.ResponseErrorListV1;
import io.biza.babelfish.cdr.models.responses.container.ResponseBankingAccountListDataV1;
import io.biza.babelfish.cdr.models.responses.container.ResponseBankingAccountsBalanceListDataV1;
import io.biza.babelfish.cdr.models.responses.container.ResponseBankingDirectDebitAuthorisationListDataV1;
import io.biza.babelfish.cdr.models.responses.container.ResponseBankingPayeeListDataV1;
import io.biza.babelfish.cdr.models.responses.container.ResponseBankingScheduledPaymentsListDataV1;
import io.biza.babelfish.cdr.models.responses.container.ResponseBankingTransactionListDataV1;
import io.biza.babelfish.cdr.models.responses.container.ResponseCommonCustomerDataV1;
import io.biza.babelfish.cdr.models.responses.container.ResponseCommonCustomerDetailDataV1;
import io.biza.babelfish.cdr.models.responses.container.ResponseCommonDiscoveryOutagesListDataV1;

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
  public static final io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductV1 DEFAULT_BANKING_PRODUCT_V1 =
      new io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductV1().productId("test")
          .lastUpdated(OffsetDateTime.now()).productCategory(BankingProductCategory.BUSINESS_LOANS)
          .name("Test").description("Test Description").brand("ACME").tailored(false);
  public static final io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductV2 DEFAULT_BANKING_PRODUCT_V2 =
      new io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductV2().productId("test")
          .lastUpdated(OffsetDateTime.now()).productCategory(BankingProductCategory.BUSINESS_LOANS)
          .name("Test").description("Test Description").brand("ACME").tailored(false);
  public static final BankingProductDetailV1 DEFAULT_BANKING_PRODUCT_DETAIL_V1 =
      new BankingProductDetailV1().productId("test")
          .lastUpdated(OffsetDateTime.now()).productCategory(BankingProductCategory.BUSINESS_LOANS)
          .name("Test").description("Test Description").brand("ACME").tailored(false);
  public static final BankingProductDetailV2 DEFAULT_BANKING_PRODUCT_DETAIL_V2 =
      new BankingProductDetailV2().productId("test")
          .lastUpdated(OffsetDateTime.now()).productCategory(BankingProductCategory.BUSINESS_LOANS)
          .name("Test").description("Test Description").brand("ACME").tailored(false);
  public static final BankingProductBundleV1 DEFAULT_BANKING_PRODUCT_BUNDLE =
      new BankingProductBundleV1().name("Bundle Name").description("Bundle Description");
  public static final BankingProductFeatureV1 DEFAULT_BANKING_PRODUCT_FEATURE =
      new BankingProductFeatureV1().featureType(BankingProductFeatureType.ADDITIONAL_CARDS)
          .additionalValue("10");
  public static final BankingProductConstraintV1 DEFAULT_BANKING_PRODUCT_CONSTRAINT =
      new BankingProductConstraintV1().constraintType(BankingProductConstraintType.MAX_BALANCE)
          .additionalValue("10.00");
  public static final BankingProductEligibilityV1 DEFAULT_BANKING_PRODUCT_ELIGIBILITY =
      new BankingProductEligibilityV1().eligibilityType(BankingProductEligibilityType.BUSINESS);
  public static final BankingProductFeeV1 DEFAULT_BANKING_PRODUCT_FEE =
      new BankingProductFeeV1().name("Fee Name").feeType(BankingProductFeeType.PERIODIC)
          .additionalValue("P1D").amount(new BigDecimal("10.00"));
  public static final BankingProductDiscountV1 DEFAULT_BANKING_PRODUCT_FEE_DISCOUNT =
      new BankingProductDiscountV1().description("Discount Description")
          .amount(new BigDecimal("10.00")).discountType(BankingProductDiscountType.BALANCE)
          .additionalValue("100.00");
  public static final BankingProductFeeDiscountEligibilityV1 DEFAULT_BANKING_PRODUCT_FEE_DISCOUNT_ELIGIBILITY =
      new BankingProductFeeDiscountEligibilityV1()
          .discountEligibilityType(BankingProductDiscountEligibilityType.BUSINESS);
  public static final BankingProductDepositRateV1 DEFAULT_BANKING_PRODUCT_DEPOSIT_RATE =
      new BankingProductDepositRateV1().depositRateType(BankingProductDepositRateType.VARIABLE)
          .rate(new BigDecimal("0.05"));
  public static final BankingProductLendingRateV1 DEFAULT_BANKING_PRODUCT_LENDING_RATE =
      new BankingProductLendingRateV1().lendingRateType(BankingProductLendingRateType.VARIABLE)
          .rate(new BigDecimal("0.05"));
  public static final BankingProductRateTierV1 DEFAULT_BANKING_PRODUCT_RATE_TIER =
      new BankingProductRateTierV1().name("Rate Tier Name")
          .unitOfMeasure(CommonUnitOfMeasureType.DOLLAR).minimumValue(new BigDecimal("100.00"));

  public static final LinksPaginatedV1 DEFAULT_LINKS_PAGINATED =
      new LinksPaginatedV1().self(ModelConstants.DEFAULT_SELF_URI)
          .first(ModelConstants.DEFAULT_FIRST_URI).next(ModelConstants.DEFAULT_NEXT_URI)
          .last(ModelConstants.DEFAULT_LAST_URI).prev(ModelConstants.DEFAULT_PREV_URI);

  public static final LinksV1 DEFAULT_LINKS = new LinksV1().self(ModelConstants.DEFAULT_SELF_URI);

  public static final MetaV1 DEFAULT_META = new MetaV1();
  public static final MetaPaginatedV1 DEFAULT_META_PAGINATED =
      new MetaPaginatedV1().totalPages(10).totalRecords(100);
  public static final BankingAccountV1 DEFAULT_BANKING_ACCOUNT = new BankingAccountV1()
      .accountId(UUID.randomUUID().toString()).displayName("Display Name")
      .maskedNumber("XXXX XXXX XXXX 1234").productCategory(BankingProductCategory.BUSINESS_LOANS)
      .productName("Business Loan Example");
  public static final ResponseBankingAccountListDataV1 DEFAULT_RESPONSE_BANKING_ACCOUNT_LIST_DATA =
      new ResponseBankingAccountListDataV1().accounts(List.of(DEFAULT_BANKING_ACCOUNT));

  public static final BankingAccountDetailV1 DEFAULT_BANKING_ACCOUNT_DETAIL =
      new BankingAccountDetailV1().accountId(UUID.randomUUID().toString()).displayName("Display Name")
          .maskedNumber("XXXX XXXX XXXX 1234")
          .productCategory(BankingProductCategory.BUSINESS_LOANS)
          .productName("Business Loan Example");
  public static final BankingTermDepositAccountV1 DEFAULT_BANKING_TERM_DEPOSIT_ACCOUNT =
      new BankingTermDepositAccountV1().lodgementDate(LocalDate.now()).maturityDate(LocalDate.now())
          .maturityInstructions(BankingTermDepositMaturityInstructions.ROLLED_OVER);
  public static final BankingCreditCardAccountV1 DEFAULT_BANKING_CREDIT_CARD_ACCOUNT =
      new BankingCreditCardAccountV1().minPaymentAmount(new BigDecimal("10.00"))
          .paymentDueAmount(new BigDecimal("10.00")).paymentDueDate(LocalDate.now());
  public static final BankingLoanAccountV1 DEFAULT_BANKING_LOAN_ACCOUNT =
      new BankingLoanAccountV1().loanEndDate(LocalDate.now()).nextInstalmentDate(LocalDate.now())
          .repaymentFrequency(Duration.ofDays(10));

  public static final ResponseBankingAccountListV1 DEFAULT_RESPONSE_BANKING_ACCOUNT_LIST =
      new ResponseBankingAccountListV1().links(DEFAULT_LINKS_PAGINATED).meta(DEFAULT_META_PAGINATED)
          .data(DEFAULT_RESPONSE_BANKING_ACCOUNT_LIST_DATA);

  public static final BankingPayeeV1 DEFAULT_BANKING_PAYEE =
      new BankingPayeeV1().payeeId(UUID.randomUUID().toString()).nickname("Payee Nickname")
          .payeeType(BankingPayeeType.DOMESTIC);

  public static final ResponseBankingPayeeListDataV1 DEFAULT_RESPONSE_BANKING_PAYEE_LIST_DATA =
      new ResponseBankingPayeeListDataV1().payees(List.of(DEFAULT_BANKING_PAYEE));

  public static final ResponseBankingPayeeListV1 DEFAULT_RESPONSE_BANKING_PAYEE_LIST =
      new ResponseBankingPayeeListV1().links(DEFAULT_LINKS_PAGINATED).meta(DEFAULT_META_PAGINATED)
          .data(DEFAULT_RESPONSE_BANKING_PAYEE_LIST_DATA);

  public static final ResponseBankingAccountByIdV1 DEFAULT_RESPONSE_BANKING_ACCOUNT_BY_ID =
      new ResponseBankingAccountByIdV1().links(DEFAULT_LINKS).meta(DEFAULT_META)
          .data(DEFAULT_BANKING_ACCOUNT_DETAIL);
  public static final BankingTransactionDetailExtendedDataV1 DEFAULT_BANKING_TRANSACTION_DETAIL_EXTENDED_DATA =
      new BankingTransactionDetailExtendedDataV1().service(BankingTransactionService.X2P101);

  public static final BankingTransactionDetailV1 DEFAULT_BANKING_TRANSACTION_DETAIL =
      new BankingTransactionDetailV1().accountId(UUID.randomUUID().toString())
          .transactionId(UUID.randomUUID().toString()).isDetailAvailable(false)
          .type(BankingTransactionType.PAYMENT).status(BankingTransactionStatus.POSTED)
          .description("Transaction Description").amount(new BigDecimal("10.00")).reference("")
          .postingDateTime(OffsetDateTime.now())
          .extendedData(ModelConstants.DEFAULT_BANKING_TRANSACTION_DETAIL_EXTENDED_DATA);

  public static final ResponseBankingTransactionByIdV1 DEFAULT_RESPONSE_BANKING_TRANSACTION_BY_ID =
      new ResponseBankingTransactionByIdV1().links(DEFAULT_LINKS)
          .data(DEFAULT_BANKING_TRANSACTION_DETAIL);


  public static final BankingTransactionV1 DEFAULT_BANKING_TRANSACTION = new BankingTransactionV1()
      .accountId(UUID.randomUUID().toString()).transactionId(UUID.randomUUID().toString())
      .isDetailAvailable(false).type(BankingTransactionType.PAYMENT)
      .status(BankingTransactionStatus.POSTED).description("Transaction Description")
      .amount(new BigDecimal("10.00")).reference("").postingDateTime(OffsetDateTime.now());

  public static final ResponseBankingTransactionListDataV1 DEFAULT_RESPONSE_BANKING_TRANSACTION_LIST_DATA =
      new ResponseBankingTransactionListDataV1().transactions(List.of(DEFAULT_BANKING_TRANSACTION));

  public static final ResponseBankingTransactionListV1 DEFAULT_RESPONSE_BANKING_TRANSACTION_LIST =
      new ResponseBankingTransactionListV1().meta(DEFAULT_META_PAGINATED)
          .links(DEFAULT_LINKS_PAGINATED).data(DEFAULT_RESPONSE_BANKING_TRANSACTION_LIST_DATA);

  public static final BankingBalanceV1 DEFAULT_BANKING_BALANCE =
      new BankingBalanceV1().accountId(UUID.randomUUID().toString())
          .currentBalance(new BigDecimal("1000.00")).availableBalance(new BigDecimal("500.00"));

  public static final ResponseBankingAccountsBalanceListDataV1 DEFAULT_BANKING_ACCOUNTS_BALANCE_LIST_DATA =
      new ResponseBankingAccountsBalanceListDataV1().balances(List.of(DEFAULT_BANKING_BALANCE));

  public static final ResponseBankingAccountsBalanceListV1 DEFAULT_RESPONSE_BANKING_ACCOUNTS_BALANCE_LIST =
      new ResponseBankingAccountsBalanceListV1().meta(DEFAULT_META_PAGINATED)
          .links(DEFAULT_LINKS_PAGINATED).data(DEFAULT_BANKING_ACCOUNTS_BALANCE_LIST_DATA);


  public static final BankingBalancePurseV1 DEFAULT_BANKING_BALANCE_PURSE =
      new BankingBalancePurseV1().amount(new BigDecimal("100.00"));

  public static final BankingBillerPayeeV1 DEFAULT_BANKING_BILLER_PAYEE =
      new BankingBillerPayeeV1().billerName("Energy Australia").billerCode("3111").crn("81752861");
  public static final BankingDomesticPayeeAccountV1 DEFAULT_BANKING_DOMESTIC_PAYEE_ACCOUNT =
      new BankingDomesticPayeeAccountV1().bsb("123-123").accountNumber("12341234");
  public static final BankingDomesticPayeeCardV1 DEFAULT_BANKING_DOMESTIC_PAYEE_CARD =
      new BankingDomesticPayeeCardV1().cardNumber("XXXX XXXX XXXX 1234");
  public static final BankingDomesticPayeePayIdV1 DEFAULT_BANKING_DOMESTIC_PAYEE_PAYID =
      new BankingDomesticPayeePayIdV1().type(PayloadTypeBankingDomesticPayeePayId.EMAIL)
          .identifier("valid@email.com");

  public static final BankingInternationalPayeeBeneficiaryDetailsV1 DEFAULT_BANKING_INTERNATIONAL_PAYEE_BENEFICIARY_DETAILS =
      new BankingInternationalPayeeBeneficiaryDetailsV1().country(Locale.forLanguageTag("en-AU"));
  public static final BankingInternationalPayeeBankDetailsV1 DEFAULT_BANKING_INTERNATIONAL_PAYEE_BANK_DETAILS =
      new BankingInternationalPayeeBankDetailsV1().accountNumber("1234123412341234");
  public static final BankingAuthorisedEntityV1 DEFAULT_BANKING_AUTHORISED_ENTITY =
      new BankingAuthorisedEntityV1();

  public static final BankingDirectDebitV1 DEFAULT_BANKING_DIRECT_DEBIT =
      new BankingDirectDebitV1().accountId(UUID.randomUUID().toString())
          .authorisedEntity(ModelConstants.DEFAULT_BANKING_AUTHORISED_ENTITY);
  public static final ResponseBankingDirectDebitAuthorisationListDataV1 DEFAULT_RESPONSE_BANKING_DIRECT_DEBIT_AUTHORISATION_LIST_DATA =
      new ResponseBankingDirectDebitAuthorisationListDataV1()
          .directDebitAuthorisations(List.of(DEFAULT_BANKING_DIRECT_DEBIT));
  public static final ResponseBankingDirectDebitAuthorisationListV1 DEFAULT_RESPONSE_BANKING_DIRECT_DEBIT_AUTHORISATION_LIST =
      new ResponseBankingDirectDebitAuthorisationListV1()
          .data(DEFAULT_RESPONSE_BANKING_DIRECT_DEBIT_AUTHORISATION_LIST_DATA);



  public static final BankingScheduledPaymentFromV1 DEFAULT_BANKING_SCHEDULED_PAYMENT_FROM =
      new BankingScheduledPaymentFromV1().accountId(UUID.randomUUID().toString());
  public static final BankingScheduledPaymentToV1 DEFAULT_BANKING_SCHEDULED_PAYMENT_TO =
      new BankingScheduledPaymentToV1().type(PayloadTypeBankingScheduledPaymentTo.ACCOUNT_ID)
          .accountId(UUID.randomUUID().toString());
  public static final BankingScheduledPaymentSetV1 DEFAULT_BANKING_SCHEDULED_PAYMENT_SET =
      new BankingScheduledPaymentSetV1().to(ModelConstants.DEFAULT_BANKING_SCHEDULED_PAYMENT_TO)
          .amount(new BigDecimal("10.00"));
  public static final BankingScheduledPaymentRecurrenceOnceOffV1 DEFAULT_BANKING_SCHEDULED_PAYMENT_RECURRENCE_ONCE_OFF =
      new BankingScheduledPaymentRecurrenceOnceOffV1().paymentDate(LocalDate.now());
  public static final BankingScheduledPaymentRecurrenceV1 DEFAULT_BANKING_SCHEDULED_PAYMENT_RECURRENCE =
      new BankingScheduledPaymentRecurrenceV1()
          .type(PayloadTypeBankingScheduledPaymentRecurrence.ONCE_OFF)
          .onceOff(ModelConstants.DEFAULT_BANKING_SCHEDULED_PAYMENT_RECURRENCE_ONCE_OFF);
  public static final BankingScheduledPaymentRecurrenceLastWeekdayV1 DEFAULT_BANKING_SCHEDULED_PAYMENT_RECURRENCE_LAST_WEEKDAY =
      new BankingScheduledPaymentRecurrenceLastWeekdayV1().interval(Period.ofDays(30))
          .lastWeekDay(CommonWeekDay.MON);
  public static final BankingScheduledPaymentRecurrenceEventBasedV1 DEFAULT_BANKING_SCHEDULED_PAYMENT_RECURRENCE_EVENT_BASED =
      new BankingScheduledPaymentRecurrenceEventBasedV1()
          .description("Event Based Payment Description");
  public static final BankingScheduledPaymentIntervalV1 DEFAULT_BANKING_SCHEDULED_PAYMENT_INTERVAL =
      new BankingScheduledPaymentIntervalV1().interval(Period.ofDays(30));
  public static final CommonDiscoveryStatusV1 DEFAULT_COMMON_DISCOVERY_STATUS =
      new CommonDiscoveryStatusV1().status(CommonDiscoveryStatusType.OK)
          .updateTime(OffsetDateTime.now());
  public static final ResponseCommonDiscoveryStatusV1 DEFAULT_RESPONSE_COMMON_DISCOVERY_STATUS =
      new ResponseCommonDiscoveryStatusV1().data(DEFAULT_COMMON_DISCOVERY_STATUS)
          .links(DEFAULT_LINKS);

  public static final CommonDiscoveryOutageV1 DEFAULT_COMMON_DISCOVERY_OUTAGE =
      new CommonDiscoveryOutageV1().outageTime(OffsetDateTime.now()).duration(Duration.ofHours(1))
          .explanation("Outage Explanation");
  public static final ResponseCommonDiscoveryOutagesListDataV1 DEFAULT_RESPONSE_COMMON_DISCOVERY_OUTAGES_LIST_DATA =
      new ResponseCommonDiscoveryOutagesListDataV1()
          .outages(List.of(ModelConstants.DEFAULT_COMMON_DISCOVERY_OUTAGE));

  public static final CommonPersonV1 DEFAULT_COMMON_PERSON = new CommonPersonV1().lastName("Last");
  public static final CommonOrganisationV1 DEFAULT_COMMON_ORGANISATION =
      new CommonOrganisationV1().agentLastName("Last").businessName("Organisation Business Name")
          .organisationType(CommonOrganisationType.OTHER);;
  public static final CommonPhoneNumberV1 DEFAULT_COMMON_PHONE_NUMBER =
      new CommonPhoneNumberV1().purpose(CommonPhoneNumberPurpose.HOME).number("0733076000")
          .fullNumber("tel:+61-073-307-6000").areaCode("7");
  public static final CommonEmailAddressV1 DEFAULT_COMMON_EMAIL_ADDRESS =
      new CommonEmailAddressV1().purpose(CommonEmailAddressPurpose.HOME).address("test@test.com");
  public static final CommonSimpleAddressV1 DEFAULT_COMMON_SIMPLE_ADDRESS = new CommonSimpleAddressV1()
      .addressLine1("10 McGill Street").postcode("2550").city("Cobargo").state("NSW");
  public static final CommonPAFAddressV1 DEFAULT_COMMON_PAF_ADDRESS = new CommonPAFAddressV1()
      .localityName("Cobargo").postcode("2550").state(AddressPAFStateType.NSW);

  public static final CommonPhysicalAddressWithPurposeV1 DEFAULT_COMMON_PHYSICAL_ADDRESS_WITH_PURPOSE =
      new CommonPhysicalAddressWithPurposeV1().purpose(AddressPurpose.REGISTERED)
          .type(PayloadTypeAddress.SIMPLE).simple(DEFAULT_COMMON_SIMPLE_ADDRESS);

  public static final ErrorV1 DEFAULT_ERROR =
      new ErrorV1().code("0001 – Account not able to be found")
          .title("Invalid account").detail(UUID.randomUUID().toString());
  public static final BankingInternationalPayeeV1 DEFAULT_BANKING_INTERNATIONAL_PAYEE =
      new BankingInternationalPayeeV1()
          .beneficiaryDetails(
              ModelConstants.DEFAULT_BANKING_INTERNATIONAL_PAYEE_BENEFICIARY_DETAILS)
          .bankDetails(ModelConstants.DEFAULT_BANKING_INTERNATIONAL_PAYEE_BANK_DETAILS);
  public static final BankingScheduledPaymentRecurrenceIntervalScheduleV1 DEFAULT_BANKING_SCHEDULED_PAYMENT_RECURRENCE_INTERVAL_SCHEDULE =
      new BankingScheduledPaymentRecurrenceIntervalScheduleV1()
          .intervals(List.of(ModelConstants.DEFAULT_BANKING_SCHEDULED_PAYMENT_INTERVAL));
  public static final ResponseErrorListV1 DEFAULT_RESPONSE_ERROR_LIST =
      new ResponseErrorListV1().errors(List.of(ModelConstants.DEFAULT_ERROR));
  public static final CommonPhysicalAddressV1 DEFAULT_COMMON_PHYSICAL_ADDRESS =
      new CommonPhysicalAddressV1().type(PayloadTypeAddress.SIMPLE)
          .simple(ModelConstants.DEFAULT_COMMON_SIMPLE_ADDRESS);

  public static final ResponseCommonDiscoveryOutagesListV1 DEFAULT_RESPONSE_COMMON_DISCOVERY_OUTAGES_LIST =
      new ResponseCommonDiscoveryOutagesListV1().links(ModelConstants.DEFAULT_LINKS)
          .meta(ModelConstants.DEFAULT_META)
          .data(ModelConstants.DEFAULT_RESPONSE_COMMON_DISCOVERY_OUTAGES_LIST_DATA);

  public static final BankingScheduledPaymentV1 DEFAULT_BANKING_SCHEDULED_PAYMENT =
      new BankingScheduledPaymentV1().scheduledPaymentId(UUID.randomUUID().toString())
          .payerReference("Payer Reference").payeeReference("Payee Reference")
          .payeeReference("Payee Reference").status(BankingScheduledPaymentStatus.ACTIVE)
          .from(ModelConstants.DEFAULT_BANKING_SCHEDULED_PAYMENT_FROM)
          .paymentSet(List.of(ModelConstants.DEFAULT_BANKING_SCHEDULED_PAYMENT_SET))
          .recurrence(ModelConstants.DEFAULT_BANKING_SCHEDULED_PAYMENT_RECURRENCE);

  public static final ResponseCommonCustomerDataV1 DEFAULT_RESPONSE_COMMON_CUSTOMER_DATA =
      new ResponseCommonCustomerDataV1().type(PayloadTypeCustomer.PERSON)
          .person(ModelConstants.DEFAULT_COMMON_PERSON);

  public static final ResponseCommonCustomerV1 DEFAULT_RESPONSE_COMMON_CUSTOMER =
      new ResponseCommonCustomerV1().links(ModelConstants.DEFAULT_LINKS)
          .data(ModelConstants.DEFAULT_RESPONSE_COMMON_CUSTOMER_DATA);

  public static final CommonPersonDetailV1 DEFAULT_COMMON_PERSON_DETAIL =
      new CommonPersonDetailV1().lastName("Last")
          .physicalAddresses(List.of(ModelConstants.DEFAULT_COMMON_PHYSICAL_ADDRESS_WITH_PURPOSE));
  public static final CommonOrganisationDetail DEFAULT_COMMON_ORGANISATION_DETAIL =
      new CommonOrganisationDetail().agentLastName("Last")
          .businessName("Organisation Business Name").organisationType(CommonOrganisationType.OTHER)
          .physicalAddresses(List.of(ModelConstants.DEFAULT_COMMON_PHYSICAL_ADDRESS_WITH_PURPOSE));

  public static final BankingDomesticPayeeV1 DEFAULT_BANKING_DOMESTIC_PAYEE =
      new BankingDomesticPayeeV1().payeeAccountType(PayloadTypeBankingDomesticPayee.ACCOUNT)
          .account(ModelConstants.DEFAULT_BANKING_DOMESTIC_PAYEE_ACCOUNT);

  public static final BankingPayeeDetailV1 DEFAULT_BANKING_PAYEE_DETAIL =
      new BankingPayeeDetailV1().payeeId(UUID.randomUUID().toString()).nickname("Payee Nickname")
          .payeeType(BankingPayeeType.DOMESTIC).type(PayloadTypeBankingPayee.DOMESTIC)
          .domestic(ModelConstants.DEFAULT_BANKING_DOMESTIC_PAYEE);

  public static final ResponseCommonCustomerDetailDataV1 DEFAULT_RESPONSE_COMMON_CUSTOMER_DETAIL_DATA =
      new ResponseCommonCustomerDetailDataV1().type(PayloadTypeCustomer.PERSON)
          .person(ModelConstants.DEFAULT_COMMON_PERSON_DETAIL);

  public static final ResponseCommonCustomerDetail DEFAULT_RESPONSE_COMMON_CUSTOMER_DETAIL =
      new ResponseCommonCustomerDetail().links(ModelConstants.DEFAULT_LINKS)
          .data(ModelConstants.DEFAULT_RESPONSE_COMMON_CUSTOMER_DETAIL_DATA);

  public static final BankingPayeeDetailV1 DEFAULT_PAYEE_DETAIL = new BankingPayeeDetailV1()
      .type(PayloadTypeBankingPayee.DOMESTIC).payeeId(UUID.randomUUID().toString()).nickname("Default Payee Detail")
      .payeeType(BankingPayeeType.DOMESTIC).domestic(DEFAULT_BANKING_DOMESTIC_PAYEE);
  public static final ResponseBankingPayeeByIdV1 DEFAULT_RESPONSE_BANKING_PAYEE_BY_ID =
      new ResponseBankingPayeeByIdV1().links(DEFAULT_LINKS).data(DEFAULT_PAYEE_DETAIL);

  public static final ResponseBankingScheduledPaymentsListDataV1 DEFAULT_RESPONSE_BANKING_SCHEDULED_PAYMENTS_LIST_DATA =
      new ResponseBankingScheduledPaymentsListDataV1().scheduledPayments(List.of(DEFAULT_BANKING_SCHEDULED_PAYMENT));
  
  public static final ResponseBankingScheduledPaymentsListV1 DEFAULT_RESPONSE_BANKING_SCHEDULED_PAYMENTS_LIST =
      new ResponseBankingScheduledPaymentsListV1().links(DEFAULT_LINKS_PAGINATED)
          .meta(DEFAULT_META_PAGINATED).data(DEFAULT_RESPONSE_BANKING_SCHEDULED_PAYMENTS_LIST_DATA);

}
