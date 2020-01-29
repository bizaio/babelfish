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
import io.biza.babelfish.cdr.v1.model.banking.BankingAccount;
import io.biza.babelfish.cdr.v1.model.banking.BankingAccountDetail;
import io.biza.babelfish.cdr.v1.model.banking.BankingAuthorisedEntity;
import io.biza.babelfish.cdr.v1.model.banking.BankingBalance;
import io.biza.babelfish.cdr.v1.model.banking.BankingBalancePurse;
import io.biza.babelfish.cdr.v1.model.banking.BankingBillerPayee;
import io.biza.babelfish.cdr.v1.model.banking.BankingCreditCardAccount;
import io.biza.babelfish.cdr.v1.model.banking.BankingDirectDebit;
import io.biza.babelfish.cdr.v1.model.banking.BankingDomesticPayee;
import io.biza.babelfish.cdr.v1.model.banking.BankingDomesticPayeeAccount;
import io.biza.babelfish.cdr.v1.model.banking.BankingDomesticPayeeCard;
import io.biza.babelfish.cdr.v1.model.banking.BankingDomesticPayeePayId;
import io.biza.babelfish.cdr.v1.model.banking.BankingInternationalPayee;
import io.biza.babelfish.cdr.v1.model.banking.BankingInternationalPayeeBankDetails;
import io.biza.babelfish.cdr.v1.model.banking.BankingInternationalPayeeBeneficiaryDetails;
import io.biza.babelfish.cdr.v1.model.banking.BankingLoanAccount;
import io.biza.babelfish.cdr.v1.model.banking.BankingPayee;
import io.biza.babelfish.cdr.v1.model.banking.BankingPayeeDetail;
import io.biza.babelfish.cdr.v1.model.banking.BankingScheduledPayment;
import io.biza.babelfish.cdr.v1.model.banking.BankingScheduledPaymentFrom;
import io.biza.babelfish.cdr.v1.model.banking.BankingScheduledPaymentInterval;
import io.biza.babelfish.cdr.v1.model.banking.BankingScheduledPaymentRecurrence;
import io.biza.babelfish.cdr.v1.model.banking.BankingScheduledPaymentRecurrenceEventBased;
import io.biza.babelfish.cdr.v1.model.banking.BankingScheduledPaymentRecurrenceIntervalSchedule;
import io.biza.babelfish.cdr.v1.model.banking.BankingScheduledPaymentRecurrenceLastWeekday;
import io.biza.babelfish.cdr.v1.model.banking.BankingScheduledPaymentRecurrenceOnceOff;
import io.biza.babelfish.cdr.v1.model.banking.BankingScheduledPaymentSet;
import io.biza.babelfish.cdr.v1.model.banking.BankingScheduledPaymentTo;
import io.biza.babelfish.cdr.v1.model.banking.BankingTermDepositAccount;
import io.biza.babelfish.cdr.v1.model.banking.BankingTransaction;
import io.biza.babelfish.cdr.v1.model.banking.BankingTransactionDetail;
import io.biza.babelfish.cdr.v1.model.banking.BankingTransactionDetailExtendedData;
import io.biza.babelfish.cdr.v1.model.common.CommonDiscoveryOutage;
import io.biza.babelfish.cdr.v1.model.common.CommonDiscoveryStatus;
import io.biza.babelfish.cdr.v1.model.common.CommonEmailAddress;
import io.biza.babelfish.cdr.v1.model.common.CommonOrganisation;
import io.biza.babelfish.cdr.v1.model.common.CommonOrganisationDetail;
import io.biza.babelfish.cdr.v1.model.common.CommonPAFAddress;
import io.biza.babelfish.cdr.v1.model.common.CommonPerson;
import io.biza.babelfish.cdr.v1.model.common.CommonPersonDetail;
import io.biza.babelfish.cdr.v1.model.common.CommonPhoneNumber;
import io.biza.babelfish.cdr.v1.model.common.CommonPhysicalAddress;
import io.biza.babelfish.cdr.v1.model.common.CommonPhysicalAddressWithPurpose;
import io.biza.babelfish.cdr.v1.model.common.CommonSimpleAddress;
import io.biza.babelfish.cdr.v1.model.common.Links;
import io.biza.babelfish.cdr.v1.model.common.LinksPaginated;
import io.biza.babelfish.cdr.v1.model.common.Meta;
import io.biza.babelfish.cdr.v1.model.common.MetaPaginated;
import io.biza.babelfish.cdr.v1.response.ResponseBankingAccountById;
import io.biza.babelfish.cdr.v1.response.ResponseBankingAccountList;
import io.biza.babelfish.cdr.v1.response.ResponseBankingAccountsBalanceList;
import io.biza.babelfish.cdr.v1.response.ResponseBankingDirectDebitAuthorisationList;
import io.biza.babelfish.cdr.v1.response.ResponseBankingPayeeById;
import io.biza.babelfish.cdr.v1.response.ResponseBankingPayeeList;
import io.biza.babelfish.cdr.v1.response.ResponseBankingScheduledPaymentsList;
import io.biza.babelfish.cdr.v1.response.ResponseBankingTransactionById;
import io.biza.babelfish.cdr.v1.response.ResponseBankingTransactionList;
import io.biza.babelfish.cdr.v1.response.ResponseCommonCustomer;
import io.biza.babelfish.cdr.v1.response.ResponseCommonCustomerDetail;
import io.biza.babelfish.cdr.v1.response.ResponseCommonDiscoveryOutagesList;
import io.biza.babelfish.cdr.v1.response.ResponseCommonDiscoveryStatus;
import io.biza.babelfish.cdr.v1.response.ResponseErrorList;
import io.biza.babelfish.cdr.v1.response.container.ResponseBankingAccountListData;
import io.biza.babelfish.cdr.v1.response.container.ResponseBankingAccountsBalanceListData;
import io.biza.babelfish.cdr.v1.response.container.ResponseBankingDirectDebitAuthorisationListData;
import io.biza.babelfish.cdr.v1.response.container.ResponseBankingPayeeListData;
import io.biza.babelfish.cdr.v1.response.container.ResponseBankingScheduledPaymentsListData;
import io.biza.babelfish.cdr.v1.response.container.ResponseBankingTransactionListData;
import io.biza.babelfish.cdr.v1.response.container.ResponseCommonCustomerData;
import io.biza.babelfish.cdr.v1.response.container.ResponseCommonCustomerDetailData;
import io.biza.babelfish.cdr.v1.response.container.ResponseCommonDiscoveryOutagesListData;
import io.biza.babelfish.enumerations.cdr.AddressPAFStateType;
import io.biza.babelfish.enumerations.cdr.AddressPurpose;
import io.biza.babelfish.enumerations.cdr.BankingPayeeType;
import io.biza.babelfish.enumerations.cdr.BankingProductCategory;
import io.biza.babelfish.enumerations.cdr.BankingProductConstraintType;
import io.biza.babelfish.enumerations.cdr.BankingProductDepositRateType;
import io.biza.babelfish.enumerations.cdr.BankingProductDiscountEligibilityType;
import io.biza.babelfish.enumerations.cdr.BankingProductDiscountType;
import io.biza.babelfish.enumerations.cdr.BankingProductEligibilityType;
import io.biza.babelfish.enumerations.cdr.BankingProductFeatureType;
import io.biza.babelfish.enumerations.cdr.BankingProductFeeType;
import io.biza.babelfish.enumerations.cdr.BankingProductLendingRateType;
import io.biza.babelfish.enumerations.cdr.BankingScheduledPaymentStatus;
import io.biza.babelfish.enumerations.cdr.BankingTermDepositMaturityInstructions;
import io.biza.babelfish.enumerations.cdr.BankingTransactionService;
import io.biza.babelfish.enumerations.cdr.BankingTransactionStatus;
import io.biza.babelfish.enumerations.cdr.BankingTransactionType;
import io.biza.babelfish.enumerations.cdr.CommonDiscoveryStatusType;
import io.biza.babelfish.enumerations.cdr.CommonEmailAddressPurpose;
import io.biza.babelfish.enumerations.cdr.CommonOrganisationType;
import io.biza.babelfish.enumerations.cdr.CommonPhoneNumberPurpose;
import io.biza.babelfish.enumerations.cdr.CommonUnitOfMeasureType;
import io.biza.babelfish.enumerations.cdr.CommonWeekDay;
import io.biza.babelfish.enumerations.cdr.PayloadTypeAddress;
import io.biza.babelfish.enumerations.cdr.PayloadTypeBankingDomesticPayee;
import io.biza.babelfish.enumerations.cdr.PayloadTypeBankingDomesticPayeePayId;
import io.biza.babelfish.enumerations.cdr.PayloadTypeBankingPayee;
import io.biza.babelfish.enumerations.cdr.PayloadTypeBankingScheduledPaymentRecurrence;
import io.biza.babelfish.enumerations.cdr.PayloadTypeBankingScheduledPaymentTo;
import io.biza.babelfish.enumerations.cdr.PayloadTypeCustomer;
import io.biza.babelfish.interfaces.cdr.banking.product.BankingProductBundleV1;
import io.biza.babelfish.interfaces.cdr.banking.product.BankingProductConstraintV1;
import io.biza.babelfish.interfaces.cdr.banking.product.BankingProductDepositRateV1;
import io.biza.babelfish.interfaces.cdr.banking.product.BankingProductDetailV2;
import io.biza.babelfish.interfaces.cdr.banking.product.BankingProductDiscountV1;
import io.biza.babelfish.interfaces.cdr.banking.product.BankingProductEligibilityV1;
import io.biza.babelfish.interfaces.cdr.banking.product.BankingProductFeatureV1;
import io.biza.babelfish.interfaces.cdr.banking.product.BankingProductFeeDiscountEligibilityV1;
import io.biza.babelfish.interfaces.cdr.banking.product.BankingProductFeeV1;
import io.biza.babelfish.interfaces.cdr.banking.product.BankingProductLendingRateV1;
import io.biza.babelfish.interfaces.cdr.banking.product.BankingProductRateTierV1;
import io.biza.babelfish.interfaces.cdr.banking.product.BankingProductV1;
import io.biza.babelfish.interfaces.cdr.banking.product.BankingProductV2;

/**
 * ModelConstants This defines valid models for manipulation within test cases
 *
 */
public class ModelConstants {
  public URI DEFAULT_FIRST_URI = URI.create("http://localhost/?page=1");
  public URI DEFAULT_SELF_URI = URI.create("http://localhost/?page=3");
  public URI DEFAULT_LAST_URI = URI.create("http://localhost/?page=10");
  public URI DEFAULT_PREV_URI = URI.create("http://localhost/?page=2");
  public URI DEFAULT_NEXT_URI = URI.create("http://localhost/?page=4");
  public List<String> DEFAULT_ACCOUNT_IDS =
      List.of("0be1c793-87ba-4942-95bd-4c972ec43a2d", "d5305a6c-b828-4651-bbcc-b3ea7264d387");
  public BankingProductV1 DEFAULT_BANKING_PRODUCT_V1 =
      new BankingProductV1.Builder().productId("test").lastUpdated(OffsetDateTime.now())
          .productCategory(BankingProductCategory.BUSINESS_LOANS).name("Test")
          .description("Test Description").brand("ACME").isTailored(false).build();
  public BankingProductV2 DEFAULT_BANKING_PRODUCT_V2 =
      new BankingProductV2.Builder().productId("test").lastUpdated(OffsetDateTime.now())
          .productCategory(BankingProductCategory.BUSINESS_LOANS).name("Test")
          .description("Test Description").brand("ACME").isTailored(false).build();
  public BankingProductDetailV2 DEFAULT_BANKING_PRODUCT_DETAIL_V1 =
      new BankingProductDetailV2.Builder().productId("test").lastUpdated(OffsetDateTime.now())
          .productCategory(BankingProductCategory.BUSINESS_LOANS).name("Test")
          .description("Test Description").brand("ACME").isTailored(false).build();
  public BankingProductDetailV2 DEFAULT_BANKING_PRODUCT_DETAIL_V2 =
      new BankingProductDetailV2.Builder().productId("test").lastUpdated(OffsetDateTime.now())
          .productCategory(BankingProductCategory.BUSINESS_LOANS).name("Test")
          .description("Test Description").brand("ACME").isTailored(false).build();
  public BankingProductBundleV1 DEFAULT_BANKING_PRODUCT_BUNDLE =
      new BankingProductBundleV1.Builder().name("Bundle Name").description("Bundle Description")
          .build();
  public BankingProductFeatureV1 DEFAULT_BANKING_PRODUCT_FEATURE =
      new BankingProductFeatureV1.Builder().type(BankingProductFeatureType.ADDITIONAL_CARDS)
          .additionalValue("10").build();
  public BankingProductConstraintV1 DEFAULT_BANKING_PRODUCT_CONSTRAINT =
      new BankingProductConstraintV1.Builder().type(BankingProductConstraintType.MAX_BALANCE)
          .additionalValue("10.00").build();
  public BankingProductEligibilityV1 DEFAULT_BANKING_PRODUCT_ELIGIBILITY =
      new BankingProductEligibilityV1.Builder().type(BankingProductEligibilityType.BUSINESS)
          .build();
  public BankingProductFeeV1 DEFAULT_BANKING_PRODUCT_FEE =
      new BankingProductFeeV1.Builder().name("Fee Name").type(BankingProductFeeType.PERIODIC)
          .additionalValue("P1D").amount(new BigDecimal("10.00")).build();
  public BankingProductDiscountV1 DEFAULT_BANKING_PRODUCT_FEE_DISCOUNT =
      new BankingProductDiscountV1.Builder().description("Discount Description")
          .amount(new BigDecimal("10.00")).type(BankingProductDiscountType.BALANCE)
          .additionalValue("100.00").build();
  public BankingProductFeeDiscountEligibilityV1 DEFAULT_BANKING_PRODUCT_FEE_DISCOUNT_ELIGIBILITY =
      new BankingProductFeeDiscountEligibilityV1.Builder()
          .type(BankingProductDiscountEligibilityType.BUSINESS).build();
  public BankingProductDepositRateV1 DEFAULT_BANKING_PRODUCT_DEPOSIT_RATE =
      new BankingProductDepositRateV1.Builder().type(BankingProductDepositRateType.VARIABLE)
          .rate(new BigDecimal("0.05")).build();
  public BankingProductLendingRateV1 DEFAULT_BANKING_PRODUCT_LENDING_RATE =
      new BankingProductLendingRateV1.Builder().type(BankingProductLendingRateType.VARIABLE)
          .rate(new BigDecimal("0.05")).build();
  public BankingProductRateTierV1 DEFAULT_BANKING_PRODUCT_RATE_TIER =
      new BankingProductRateTierV1.Builder().name("Rate Tier Name")
          .unitOfMeasure(CommonUnitOfMeasureType.DOLLAR).minimumValue(new BigDecimal("100.00"))
          .build();

  public LinksPaginatedV1 DEFAULT_LINKS_PAGINATED =
      new LinksPaginatedV1.Builder().self(ModelConstants.DEFAULT_SELF_URI)
          .first(ModelConstants.DEFAULT_FIRST_URI).next(ModelConstants.DEFAULT_NEXT_URI)
          .last(ModelConstants.DEFAULT_LAST_URI).prev(ModelConstants.DEFAULT_PREV_URI);

  public Links DEFAULT_LINKS = new Links().self(ModelConstants.DEFAULT_SELF_URI);

  public Meta DEFAULT_META = new Meta();
  public MetaPaginated DEFAULT_META_PAGINATED =
      new MetaPaginated().totalPages(10).totalRecords(100);
  public BankingAccount DEFAULT_BANKING_ACCOUNT = new BankingAccount()
      .accountId(UUID.randomUUID().toString()).displayName("Display Name")
      .maskedNumber("XXXX XXXX XXXX 1234").productCategory(BankingProductCategory.BUSINESS_LOANS)
      .productName("Business Loan Example");
  public ResponseBankingAccountListData DEFAULT_RESPONSE_BANKING_ACCOUNT_LIST_DATA =
      new ResponseBankingAccountListData().accounts(List.of(DEFAULT_BANKING_ACCOUNT));

  public BankingAccountDetail DEFAULT_BANKING_ACCOUNT_DETAIL =
      new BankingAccountDetail().accountId(UUID.randomUUID().toString()).displayName("Display Name")
          .maskedNumber("XXXX XXXX XXXX 1234")
          .productCategory(BankingProductCategory.BUSINESS_LOANS)
          .productName("Business Loan Example");
  public BankingTermDepositAccount DEFAULT_BANKING_TERM_DEPOSIT_ACCOUNT =
      new BankingTermDepositAccount().lodgementDate(LocalDate.now()).maturityDate(LocalDate.now())
          .maturityInstructions(BankingTermDepositMaturityInstructions.ROLLED_OVER);
  public BankingCreditCardAccount DEFAULT_BANKING_CREDIT_CARD_ACCOUNT =
      new BankingCreditCardAccount().minPaymentAmount(new BigDecimal("10.00"))
          .paymentDueAmount(new BigDecimal("10.00")).paymentDueDate(LocalDate.now());
  public BankingLoanAccount DEFAULT_BANKING_LOAN_ACCOUNT =
      new BankingLoanAccount().loanEndDate(LocalDate.now()).nextInstalmentDate(LocalDate.now())
          .repaymentFrequency(Duration.ofDays(10));

  public ResponseBankingAccountList DEFAULT_RESPONSE_BANKING_ACCOUNT_LIST =
      new ResponseBankingAccountList().links(DEFAULT_LINKS_PAGINATED).meta(DEFAULT_META_PAGINATED)
          .data(DEFAULT_RESPONSE_BANKING_ACCOUNT_LIST_DATA);

  public BankingPayee DEFAULT_BANKING_PAYEE =
      new BankingPayee().payeeId(UUID.randomUUID().toString()).nickname("Payee Nickname")
          .payeeType(BankingPayeeType.DOMESTIC);

  public ResponseBankingPayeeListData DEFAULT_RESPONSE_BANKING_PAYEE_LIST_DATA =
      new ResponseBankingPayeeListData().payees(List.of(DEFAULT_BANKING_PAYEE));

  public ResponseBankingPayeeList DEFAULT_RESPONSE_BANKING_PAYEE_LIST =
      new ResponseBankingPayeeList().links(DEFAULT_LINKS_PAGINATED).meta(DEFAULT_META_PAGINATED)
          .data(DEFAULT_RESPONSE_BANKING_PAYEE_LIST_DATA);

  public ResponseBankingAccountById DEFAULT_RESPONSE_BANKING_ACCOUNT_BY_ID =
      new ResponseBankingAccountById().links(DEFAULT_LINKS).meta(DEFAULT_META)
          .data(DEFAULT_BANKING_ACCOUNT_DETAIL);
  public BankingTransactionDetailExtendedData DEFAULT_BANKING_TRANSACTION_DETAIL_EXTENDED_DATA =
      new BankingTransactionDetailExtendedData().service(BankingTransactionService.X2P101);

  public BankingTransactionDetail DEFAULT_BANKING_TRANSACTION_DETAIL =
      new BankingTransactionDetail().accountId(UUID.randomUUID().toString())
          .transactionId(UUID.randomUUID().toString()).isDetailAvailable(false)
          .type(BankingTransactionType.PAYMENT).status(BankingTransactionStatus.POSTED)
          .description("Transaction Description").amount(new BigDecimal("10.00")).reference("")
          .postingDateTime(OffsetDateTime.now())
          .extendedData(ModelConstants.DEFAULT_BANKING_TRANSACTION_DETAIL_EXTENDED_DATA);

  public ResponseBankingTransactionById DEFAULT_RESPONSE_BANKING_TRANSACTION_BY_ID =
      new ResponseBankingTransactionById().links(DEFAULT_LINKS)
          .data(DEFAULT_BANKING_TRANSACTION_DETAIL);


  public BankingTransaction DEFAULT_BANKING_TRANSACTION = new BankingTransaction()
      .accountId(UUID.randomUUID().toString()).transactionId(UUID.randomUUID().toString())
      .isDetailAvailable(false).type(BankingTransactionType.PAYMENT)
      .status(BankingTransactionStatus.POSTED).description("Transaction Description")
      .amount(new BigDecimal("10.00")).reference("").postingDateTime(OffsetDateTime.now());

  public ResponseBankingTransactionListData DEFAULT_RESPONSE_BANKING_TRANSACTION_LIST_DATA =
      new ResponseBankingTransactionListData().transactions(List.of(DEFAULT_BANKING_TRANSACTION));

  public ResponseBankingTransactionList DEFAULT_RESPONSE_BANKING_TRANSACTION_LIST =
      new ResponseBankingTransactionList().meta(DEFAULT_META_PAGINATED)
          .links(DEFAULT_LINKS_PAGINATED).data(DEFAULT_RESPONSE_BANKING_TRANSACTION_LIST_DATA);

  public BankingBalance DEFAULT_BANKING_BALANCE =
      new BankingBalance().accountId(UUID.randomUUID().toString())
          .currentBalance(new BigDecimal("1000.00")).availableBalance(new BigDecimal("500.00"));

  public ResponseBankingAccountsBalanceListData DEFAULT_BANKING_ACCOUNTS_BALANCE_LIST_DATA =
      new ResponseBankingAccountsBalanceListData().balances(List.of(DEFAULT_BANKING_BALANCE));

  public ResponseBankingAccountsBalanceList DEFAULT_RESPONSE_BANKING_ACCOUNTS_BALANCE_LIST =
      new ResponseBankingAccountsBalanceList().meta(DEFAULT_META_PAGINATED)
          .links(DEFAULT_LINKS_PAGINATED).data(DEFAULT_BANKING_ACCOUNTS_BALANCE_LIST_DATA);


  public BankingBalancePurse DEFAULT_BANKING_BALANCE_PURSE =
      new BankingBalancePurse().amount(new BigDecimal("100.00"));

  public BankingBillerPayee DEFAULT_BANKING_BILLER_PAYEE =
      new BankingBillerPayee().billerName("Energy Australia").billerCode("3111").crn("81752861");
  public BankingDomesticPayeeAccount DEFAULT_BANKING_DOMESTIC_PAYEE_ACCOUNT =
      new BankingDomesticPayeeAccount().bsb("123-123").accountNumber("12341234");
  public BankingDomesticPayeeCard DEFAULT_BANKING_DOMESTIC_PAYEE_CARD =
      new BankingDomesticPayeeCard().cardNumber("XXXX XXXX XXXX 1234");
  public BankingDomesticPayeePayId DEFAULT_BANKING_DOMESTIC_PAYEE_PAYID =
      new BankingDomesticPayeePayId().type(PayloadTypeBankingDomesticPayeePayId.EMAIL)
          .identifier("valid@email.com");

  public BankingInternationalPayeeBeneficiaryDetails DEFAULT_BANKING_INTERNATIONAL_PAYEE_BENEFICIARY_DETAILS =
      new BankingInternationalPayeeBeneficiaryDetails().country(Locale.forLanguageTag("en-AU"));
  public BankingInternationalPayeeBankDetails DEFAULT_BANKING_INTERNATIONAL_PAYEE_BANK_DETAILS =
      new BankingInternationalPayeeBankDetails().accountNumber("1234123412341234");
  public BankingAuthorisedEntity DEFAULT_BANKING_AUTHORISED_ENTITY =
      new BankingAuthorisedEntity();

  public BankingDirectDebit DEFAULT_BANKING_DIRECT_DEBIT =
      new BankingDirectDebit().accountId(UUID.randomUUID().toString())
          .authorisedEntity(ModelConstants.DEFAULT_BANKING_AUTHORISED_ENTITY);
  public ResponseBankingDirectDebitAuthorisationListData DEFAULT_RESPONSE_BANKING_DIRECT_DEBIT_AUTHORISATION_LIST_DATA =
      new ResponseBankingDirectDebitAuthorisationListData()
          .directDebitAuthorisations(List.of(DEFAULT_BANKING_DIRECT_DEBIT));
  public ResponseBankingDirectDebitAuthorisationList DEFAULT_RESPONSE_BANKING_DIRECT_DEBIT_AUTHORISATION_LIST =
      new ResponseBankingDirectDebitAuthorisationList()
          .data(DEFAULT_RESPONSE_BANKING_DIRECT_DEBIT_AUTHORISATION_LIST_DATA);



  public BankingScheduledPaymentFrom DEFAULT_BANKING_SCHEDULED_PAYMENT_FROM =
      new BankingScheduledPaymentFrom().accountId(UUID.randomUUID().toString());
  public BankingScheduledPaymentTo DEFAULT_BANKING_SCHEDULED_PAYMENT_TO =
      new BankingScheduledPaymentTo().type(PayloadTypeBankingScheduledPaymentTo.ACCOUNT_ID)
          .accountId(UUID.randomUUID().toString());
  public BankingScheduledPaymentSet DEFAULT_BANKING_SCHEDULED_PAYMENT_SET =
      new BankingScheduledPaymentSet().to(ModelConstants.DEFAULT_BANKING_SCHEDULED_PAYMENT_TO)
          .amount(new BigDecimal("10.00"));
  public BankingScheduledPaymentRecurrenceOnceOff DEFAULT_BANKING_SCHEDULED_PAYMENT_RECURRENCE_ONCE_OFF =
      new BankingScheduledPaymentRecurrenceOnceOff().paymentDate(LocalDate.now());
  public BankingScheduledPaymentRecurrence DEFAULT_BANKING_SCHEDULED_PAYMENT_RECURRENCE =
      new BankingScheduledPaymentRecurrence()
          .recurrenceUType(PayloadTypeBankingScheduledPaymentRecurrence.ONCE_OFF)
          .onceOff(ModelConstants.DEFAULT_BANKING_SCHEDULED_PAYMENT_RECURRENCE_ONCE_OFF);
  public BankingScheduledPaymentRecurrenceLastWeekday DEFAULT_BANKING_SCHEDULED_PAYMENT_RECURRENCE_LAST_WEEKDAY =
      new BankingScheduledPaymentRecurrenceLastWeekday().interval(Period.ofDays(30))
          .lastWeekDay(CommonWeekDay.MON);
  public BankingScheduledPaymentRecurrenceEventBased DEFAULT_BANKING_SCHEDULED_PAYMENT_RECURRENCE_EVENT_BASED =
      new BankingScheduledPaymentRecurrenceEventBased()
          .description("Event Based Payment Description");
  public BankingScheduledPaymentInterval DEFAULT_BANKING_SCHEDULED_PAYMENT_INTERVAL =
      new BankingScheduledPaymentInterval().interval(Period.ofDays(30));
  public CommonDiscoveryStatus DEFAULT_COMMON_DISCOVERY_STATUS =
      new CommonDiscoveryStatus().status(CommonDiscoveryStatusType.OK)
          .updateTime(OffsetDateTime.now());
  public ResponseCommonDiscoveryStatus DEFAULT_RESPONSE_COMMON_DISCOVERY_STATUS =
      new ResponseCommonDiscoveryStatus().data(DEFAULT_COMMON_DISCOVERY_STATUS)
          .links(DEFAULT_LINKS);

  public CommonDiscoveryOutage DEFAULT_COMMON_DISCOVERY_OUTAGE =
      new CommonDiscoveryOutage().outageTime(OffsetDateTime.now()).duration(Duration.ofHours(1))
          .explanation("Outage Explanation");
  public ResponseCommonDiscoveryOutagesListData DEFAULT_RESPONSE_COMMON_DISCOVERY_OUTAGES_LIST_DATA =
      new ResponseCommonDiscoveryOutagesListData()
          .outages(List.of(ModelConstants.DEFAULT_COMMON_DISCOVERY_OUTAGE));

  public CommonPerson DEFAULT_COMMON_PERSON = new CommonPerson().lastName("Last");
  public CommonOrganisation DEFAULT_COMMON_ORGANISATION =
      new CommonOrganisation().agentLastName("Last").businessName("Organisation Business Name")
          .organisationType(CommonOrganisationType.OTHER);;
  public CommonPhoneNumber DEFAULT_COMMON_PHONE_NUMBER =
      new CommonPhoneNumber().purpose(CommonPhoneNumberPurpose.HOME).number("0733076000")
          .fullNumber("tel:+61-073-307-6000").areaCode("7");
  public CommonEmailAddress DEFAULT_COMMON_EMAIL_ADDRESS =
      new CommonEmailAddress().purpose(CommonEmailAddressPurpose.HOME).address("test@test.com");
  public CommonSimpleAddress DEFAULT_COMMON_SIMPLE_ADDRESS = new CommonSimpleAddress()
      .addressLine1("10 McGill Street").postcode("2550").city("Cobargo").state("NSW");
  public CommonPAFAddress DEFAULT_COMMON_PAF_ADDRESS = new CommonPAFAddress()
      .localityName("Cobargo").postcode("2550").state(AddressPAFStateType.NSW);

  public CommonPhysicalAddressWithPurpose DEFAULT_COMMON_PHYSICAL_ADDRESS_WITH_PURPOSE =
      new CommonPhysicalAddressWithPurpose().purpose(AddressPurpose.REGISTERED)
          .type(PayloadTypeAddress.SIMPLE).simple(DEFAULT_COMMON_SIMPLE_ADDRESS);

  public io.biza.babelfish.cdr.v1.model.common.Error DEFAULT_ERROR =
      new io.biza.babelfish.cdr.v1.model.common.Error().code("0001 – Account not able to be found")
          .title("Invalid account").detail(UUID.randomUUID().toString());
  public BankingInternationalPayee DEFAULT_BANKING_INTERNATIONAL_PAYEE =
      new BankingInternationalPayee()
          .beneficiaryDetails(
              ModelConstants.DEFAULT_BANKING_INTERNATIONAL_PAYEE_BENEFICIARY_DETAILS)
          .bankDetails(ModelConstants.DEFAULT_BANKING_INTERNATIONAL_PAYEE_BANK_DETAILS);
  public BankingScheduledPaymentRecurrenceIntervalSchedule DEFAULT_BANKING_SCHEDULED_PAYMENT_RECURRENCE_INTERVAL_SCHEDULE =
      new BankingScheduledPaymentRecurrenceIntervalSchedule()
          .intervals(List.of(ModelConstants.DEFAULT_BANKING_SCHEDULED_PAYMENT_INTERVAL));
  public ResponseErrorList DEFAULT_RESPONSE_ERROR_LIST =
      new ResponseErrorList().errors(List.of(ModelConstants.DEFAULT_ERROR));
  public CommonPhysicalAddress DEFAULT_COMMON_PHYSICAL_ADDRESS =
      new CommonPhysicalAddress().type(PayloadTypeAddress.SIMPLE)
          .simple(ModelConstants.DEFAULT_COMMON_SIMPLE_ADDRESS);

  public ResponseCommonDiscoveryOutagesList DEFAULT_RESPONSE_COMMON_DISCOVERY_OUTAGES_LIST =
      new ResponseCommonDiscoveryOutagesList().links(ModelConstants.DEFAULT_LINKS)
          .meta(ModelConstants.DEFAULT_META)
          .data(ModelConstants.DEFAULT_RESPONSE_COMMON_DISCOVERY_OUTAGES_LIST_DATA);

  public BankingScheduledPayment DEFAULT_BANKING_SCHEDULED_PAYMENT =
      new BankingScheduledPayment().scheduledPaymentId(UUID.randomUUID().toString())
          .payerReference("Payer Reference").payeeReference("Payee Reference")
          .payeeReference("Payee Reference").status(BankingScheduledPaymentStatus.ACTIVE)
          .from(ModelConstants.DEFAULT_BANKING_SCHEDULED_PAYMENT_FROM)
          .paymentSet(List.of(ModelConstants.DEFAULT_BANKING_SCHEDULED_PAYMENT_SET))
          .recurrence(ModelConstants.DEFAULT_BANKING_SCHEDULED_PAYMENT_RECURRENCE);

  public ResponseCommonCustomerData DEFAULT_RESPONSE_COMMON_CUSTOMER_DATA =
      new ResponseCommonCustomerData().type(PayloadTypeCustomer.PERSON)
          .person(ModelConstants.DEFAULT_COMMON_PERSON);

  public ResponseCommonCustomer DEFAULT_RESPONSE_COMMON_CUSTOMER =
      new ResponseCommonCustomer().links(ModelConstants.DEFAULT_LINKS)
          .data(ModelConstants.DEFAULT_RESPONSE_COMMON_CUSTOMER_DATA);

  public CommonPersonDetail DEFAULT_COMMON_PERSON_DETAIL =
      new CommonPersonDetail().lastName("Last")
          .physicalAddresses(List.of(ModelConstants.DEFAULT_COMMON_PHYSICAL_ADDRESS_WITH_PURPOSE));
  public CommonOrganisationDetail DEFAULT_COMMON_ORGANISATION_DETAIL =
      new CommonOrganisationDetail().agentLastName("Last")
          .businessName("Organisation Business Name").organisationType(CommonOrganisationType.OTHER)
          .physicalAddresses(List.of(ModelConstants.DEFAULT_COMMON_PHYSICAL_ADDRESS_WITH_PURPOSE));

  public BankingDomesticPayee DEFAULT_BANKING_DOMESTIC_PAYEE =
      new BankingDomesticPayee().payeeAccountType(PayloadTypeBankingDomesticPayee.ACCOUNT)
          .account(ModelConstants.DEFAULT_BANKING_DOMESTIC_PAYEE_ACCOUNT);

  public BankingPayeeDetail DEFAULT_BANKING_PAYEE_DETAIL =
      new BankingPayeeDetail().payeeId(UUID.randomUUID().toString()).nickname("Payee Nickname")
          .payeeType(BankingPayeeType.DOMESTIC).type(PayloadTypeBankingPayee.DOMESTIC)
          .domestic(ModelConstants.DEFAULT_BANKING_DOMESTIC_PAYEE);

  public ResponseCommonCustomerDetailData DEFAULT_RESPONSE_COMMON_CUSTOMER_DETAIL_DATA =
      new ResponseCommonCustomerDetailData().type(PayloadTypeCustomer.PERSON)
          .person(ModelConstants.DEFAULT_COMMON_PERSON_DETAIL);

  public ResponseCommonCustomerDetail DEFAULT_RESPONSE_COMMON_CUSTOMER_DETAIL =
      new ResponseCommonCustomerDetail().links(ModelConstants.DEFAULT_LINKS)
          .data(ModelConstants.DEFAULT_RESPONSE_COMMON_CUSTOMER_DETAIL_DATA);

  public BankingPayeeDetail DEFAULT_PAYEE_DETAIL =
      new BankingPayeeDetail().type(PayloadTypeBankingPayee.DOMESTIC)
          .payeeId(UUID.randomUUID().toString()).nickname("Default Payee Detail")
          .payeeType(BankingPayeeType.DOMESTIC).domestic(DEFAULT_BANKING_DOMESTIC_PAYEE);
  public ResponseBankingPayeeById DEFAULT_RESPONSE_BANKING_PAYEE_BY_ID =
      new ResponseBankingPayeeById().links(DEFAULT_LINKS).data(DEFAULT_PAYEE_DETAIL);

  public ResponseBankingScheduledPaymentsListData DEFAULT_RESPONSE_BANKING_SCHEDULED_PAYMENTS_LIST_DATA =
      new ResponseBankingScheduledPaymentsListData()
          .scheduledPayments(List.of(DEFAULT_BANKING_SCHEDULED_PAYMENT));

  public ResponseBankingScheduledPaymentsList DEFAULT_RESPONSE_BANKING_SCHEDULED_PAYMENTS_LIST =
      new ResponseBankingScheduledPaymentsList().links(DEFAULT_LINKS_PAGINATED)
          .meta(DEFAULT_META_PAGINATED).data(DEFAULT_RESPONSE_BANKING_SCHEDULED_PAYMENTS_LIST_DATA);

}
