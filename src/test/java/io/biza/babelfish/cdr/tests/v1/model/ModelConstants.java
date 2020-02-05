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
import io.biza.babelfish.cdr.models.payloads.banking.account.BankingAccountDetailV1.BankingAccountDetailV1Builder;
import io.biza.babelfish.cdr.models.payloads.banking.account.BankingAccountV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.BankingAccountV1.BankingAccountV1Builder;
import io.biza.babelfish.cdr.models.payloads.banking.account.BankingCreditCardAccountV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.BankingCreditCardAccountV1.BankingCreditCardAccountV1Builder;
import io.biza.babelfish.cdr.models.payloads.banking.account.BankingLoanAccountV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.BankingLoanAccountV1.BankingLoanAccountV1Builder;
import io.biza.babelfish.cdr.models.payloads.banking.account.BankingTermDepositAccountV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.BankingTermDepositAccountV1.BankingTermDepositAccountV1Builder;
import io.biza.babelfish.cdr.models.payloads.banking.account.balance.BankingBalanceV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.balance.BankingBalanceV1.BankingBalanceV1Builder;
import io.biza.babelfish.cdr.models.payloads.banking.account.balance.BankingBalancePurseV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.balance.BankingBalancePurseV1.BankingBalancePurseV1Builder;
import io.biza.babelfish.cdr.models.payloads.banking.account.directdebit.BankingAuthorisedEntityV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.directdebit.BankingAuthorisedEntityV1.BankingAuthorisedEntityV1Builder;
import io.biza.babelfish.cdr.models.payloads.banking.account.directdebit.BankingDirectDebitV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.directdebit.BankingDirectDebitV1.BankingDirectDebitV1Builder;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.BankingPayeeV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.BankingPayeeV1.BankingPayeeV1Builder;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.BankingPayeeDetailV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.BankingPayeeDetailV1.BankingPayeeDetailV1Builder;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.bpay.BankingBillerPayeeV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.bpay.BankingBillerPayeeV1.BankingBillerPayeeV1Builder;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.domestic.BankingDomesticPayeeV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.domestic.BankingDomesticPayeeV1.BankingDomesticPayeeV1Builder;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.domestic.BankingDomesticPayeeAccountV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.domestic.BankingDomesticPayeeAccountV1.BankingDomesticPayeeAccountV1Builder;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.domestic.BankingDomesticPayeeCardV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.domestic.BankingDomesticPayeeCardV1.BankingDomesticPayeeCardV1Builder;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.domestic.BankingDomesticPayeePayIdV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.domestic.BankingDomesticPayeePayIdV1.BankingDomesticPayeePayIdV1Builder;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.international.BankingInternationalPayeeV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.international.BankingInternationalPayeeV1.BankingInternationalPayeeV1Builder;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.international.BankingInternationalPayeeBankDetailsV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.international.BankingInternationalPayeeBankDetailsV1.BankingInternationalPayeeBankDetailsV1Builder;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.international.BankingInternationalPayeeBeneficiaryDetailsV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.international.BankingInternationalPayeeBeneficiaryDetailsV1.BankingInternationalPayeeBeneficiaryDetailsV1Builder;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.scheduled.BankingScheduledPaymentV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.scheduled.BankingScheduledPaymentV1.BankingScheduledPaymentV1Builder;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.scheduled.BankingScheduledPaymentFromV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.scheduled.BankingScheduledPaymentFromV1.BankingScheduledPaymentFromV1Builder;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.scheduled.BankingScheduledPaymentIntervalV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.scheduled.BankingScheduledPaymentIntervalV1.BankingScheduledPaymentIntervalV1Builder;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.scheduled.BankingScheduledPaymentRecurrenceV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.scheduled.BankingScheduledPaymentRecurrenceV1.BankingScheduledPaymentRecurrenceV1Builder;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.scheduled.BankingScheduledPaymentRecurrenceEventBasedV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.scheduled.BankingScheduledPaymentRecurrenceEventBasedV1.BankingScheduledPaymentRecurrenceEventBasedV1Builder;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.scheduled.BankingScheduledPaymentRecurrenceIntervalScheduleV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.scheduled.BankingScheduledPaymentRecurrenceIntervalScheduleV1.BankingScheduledPaymentRecurrenceIntervalScheduleV1Builder;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.scheduled.BankingScheduledPaymentRecurrenceLastWeekdayV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.scheduled.BankingScheduledPaymentRecurrenceLastWeekdayV1.BankingScheduledPaymentRecurrenceLastWeekdayV1Builder;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.scheduled.BankingScheduledPaymentRecurrenceOnceOffV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.scheduled.BankingScheduledPaymentRecurrenceOnceOffV1.BankingScheduledPaymentRecurrenceOnceOffV1Builder;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.scheduled.BankingScheduledPaymentSetV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.scheduled.BankingScheduledPaymentSetV1.BankingScheduledPaymentSetV1Builder;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.scheduled.BankingScheduledPaymentToV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.scheduled.BankingScheduledPaymentToV1.BankingScheduledPaymentToV1Builder;
import io.biza.babelfish.cdr.models.payloads.banking.account.transaction.BankingTransactionV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.transaction.BankingTransactionV1.BankingTransactionV1Builder;
import io.biza.babelfish.cdr.models.payloads.banking.account.transaction.BankingTransactionDetailV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.transaction.BankingTransactionDetailV1.BankingTransactionDetailV1Builder;
import io.biza.babelfish.cdr.models.payloads.banking.account.transaction.BankingTransactionDetailExtendedDataV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.transaction.BankingTransactionDetailExtendedDataV1.BankingTransactionDetailExtendedDataV1Builder;
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductBundleV1;
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductBundleV1.BankingProductBundleV1Builder;
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductConstraintV1;
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductConstraintV1.BankingProductConstraintV1Builder;
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductDepositRateV1;
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductDepositRateV1.BankingProductDepositRateV1Builder;
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductDetailV1;
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductDetailV1.BankingProductDetailV1Builder;
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductDetailV2;
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductDetailV2.BankingProductDetailV2Builder;
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductDiscountV1;
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductDiscountV1.BankingProductDiscountV1Builder;
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductEligibilityV1;
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductEligibilityV1.BankingProductEligibilityV1Builder;
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductFeatureV1;
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductFeatureV1.BankingProductFeatureV1Builder;
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductFeeV1;
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductFeeV1.BankingProductFeeV1Builder;
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductDiscountEligibilityV1;
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductDiscountEligibilityV1.BankingProductDiscountEligibilityV1Builder;
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductLendingRateV1;
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductLendingRateV1.BankingProductLendingRateV1Builder;
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductRateTierV1;
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductRateTierV1.BankingProductRateTierV1Builder;
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductV1;
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductV2;
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductV2.BankingProductV2Builder;
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductV1.BankingProductV1Builder;
import io.biza.babelfish.cdr.models.payloads.common.CommonDiscoveryOutageV1;
import io.biza.babelfish.cdr.models.payloads.common.CommonDiscoveryOutageV1.CommonDiscoveryOutageV1Builder;
import io.biza.babelfish.cdr.models.payloads.common.CommonDiscoveryStatusV1;
import io.biza.babelfish.cdr.models.payloads.common.CommonDiscoveryStatusV1.CommonDiscoveryStatusV1Builder;
import io.biza.babelfish.cdr.models.payloads.common.CommonEmailAddressV1;
import io.biza.babelfish.cdr.models.payloads.common.CommonEmailAddressV1.CommonEmailAddressV1Builder;
import io.biza.babelfish.cdr.models.payloads.common.CommonOrganisationV1;
import io.biza.babelfish.cdr.models.payloads.common.CommonOrganisationV1.CommonOrganisationV1Builder;
import io.biza.babelfish.cdr.models.payloads.common.CommonOrganisationDetail;
import io.biza.babelfish.cdr.models.payloads.common.CommonOrganisationDetail.CommonOrganisationDetailBuilder;
import io.biza.babelfish.cdr.models.payloads.common.CommonPAFAddressV1;
import io.biza.babelfish.cdr.models.payloads.common.CommonPAFAddressV1.CommonPAFAddressV1Builder;
import io.biza.babelfish.cdr.models.payloads.common.CommonPersonV1;
import io.biza.babelfish.cdr.models.payloads.common.CommonPersonV1.CommonPersonV1Builder;
import io.biza.babelfish.cdr.models.payloads.common.CommonPersonDetailV1;
import io.biza.babelfish.cdr.models.payloads.common.CommonPersonDetailV1.CommonPersonDetailV1Builder;
import io.biza.babelfish.cdr.models.payloads.common.CommonPhoneNumberV1;
import io.biza.babelfish.cdr.models.payloads.common.CommonPhoneNumberV1.CommonPhoneNumberV1Builder;
import io.biza.babelfish.cdr.models.payloads.common.CommonPhysicalAddressV1;
import io.biza.babelfish.cdr.models.payloads.common.CommonPhysicalAddressV1.CommonPhysicalAddressV1Builder;
import io.biza.babelfish.cdr.models.payloads.common.CommonPhysicalAddressWithPurposeV1;
import io.biza.babelfish.cdr.models.payloads.common.CommonPhysicalAddressWithPurposeV1.CommonPhysicalAddressWithPurposeV1Builder;
import io.biza.babelfish.cdr.models.payloads.common.CommonSimpleAddressV1;
import io.biza.babelfish.cdr.models.payloads.common.CommonSimpleAddressV1.CommonSimpleAddressV1Builder;
import io.biza.babelfish.cdr.models.payloads.ErrorV1;
import io.biza.babelfish.cdr.models.payloads.ErrorV1.ErrorV1Builder;
import io.biza.babelfish.cdr.models.payloads.LinksPaginatedV1;
import io.biza.babelfish.cdr.models.payloads.LinksPaginatedV1.LinksPaginatedV1Builder;
import io.biza.babelfish.cdr.models.payloads.LinksV1;
import io.biza.babelfish.cdr.models.payloads.LinksV1.LinksV1Builder;
import io.biza.babelfish.cdr.models.payloads.MetaV1;
import io.biza.babelfish.cdr.models.payloads.MetaV1.MetaV1Builder;
import io.biza.babelfish.cdr.models.payloads.MetaPaginatedV1;
import io.biza.babelfish.cdr.models.payloads.MetaPaginatedV1.MetaPaginatedV1Builder;
import io.biza.babelfish.cdr.models.responses.ResponseBankingAccountByIdV1;
import io.biza.babelfish.cdr.models.responses.ResponseBankingAccountByIdV1.ResponseBankingAccountByIdV1Builder;
import io.biza.babelfish.cdr.models.responses.ResponseBankingAccountListV1;
import io.biza.babelfish.cdr.models.responses.ResponseBankingAccountListV1.ResponseBankingAccountListV1Builder;
import io.biza.babelfish.cdr.models.responses.ResponseBankingAccountsBalanceListV1;
import io.biza.babelfish.cdr.models.responses.ResponseBankingAccountsBalanceListV1.ResponseBankingAccountsBalanceListV1Builder;
import io.biza.babelfish.cdr.models.responses.ResponseBankingDirectDebitAuthorisationListV1;
import io.biza.babelfish.cdr.models.responses.ResponseBankingDirectDebitAuthorisationListV1.ResponseBankingDirectDebitAuthorisationListV1Builder;
import io.biza.babelfish.cdr.models.responses.ResponseBankingPayeeByIdV1;
import io.biza.babelfish.cdr.models.responses.ResponseBankingPayeeByIdV1.ResponseBankingPayeeByIdV1Builder;
import io.biza.babelfish.cdr.models.responses.ResponseBankingPayeeListV1;
import io.biza.babelfish.cdr.models.responses.ResponseBankingPayeeListV1.ResponseBankingPayeeListV1Builder;
import io.biza.babelfish.cdr.models.responses.ResponseBankingScheduledPaymentsListV1;
import io.biza.babelfish.cdr.models.responses.ResponseBankingScheduledPaymentsListV1.ResponseBankingScheduledPaymentsListV1Builder;
import io.biza.babelfish.cdr.models.responses.ResponseBankingTransactionByIdV1;
import io.biza.babelfish.cdr.models.responses.ResponseBankingTransactionByIdV1.ResponseBankingTransactionByIdV1Builder;
import io.biza.babelfish.cdr.models.responses.ResponseBankingTransactionListV1;
import io.biza.babelfish.cdr.models.responses.ResponseBankingTransactionListV1.ResponseBankingTransactionListV1Builder;
import io.biza.babelfish.cdr.models.responses.ResponseCommonCustomerV1;
import io.biza.babelfish.cdr.models.responses.ResponseCommonCustomerV1.ResponseCommonCustomerV1Builder;
import io.biza.babelfish.cdr.models.responses.ResponseCommonCustomerDetailV1;
import io.biza.babelfish.cdr.models.responses.ResponseCommonCustomerDetailV1.ResponseCommonCustomerDetailV1Builder;
import io.biza.babelfish.cdr.models.responses.ResponseCommonDiscoveryOutagesListV1;
import io.biza.babelfish.cdr.models.responses.ResponseCommonDiscoveryOutagesListV1.ResponseCommonDiscoveryOutagesListV1Builder;
import io.biza.babelfish.cdr.models.responses.ResponseCommonDiscoveryStatusV1;
import io.biza.babelfish.cdr.models.responses.ResponseCommonDiscoveryStatusV1.ResponseCommonDiscoveryStatusV1Builder;
import io.biza.babelfish.cdr.models.responses.ResponseErrorListV1;
import io.biza.babelfish.cdr.models.responses.ResponseErrorListV1.ResponseErrorListV1Builder;
import io.biza.babelfish.cdr.models.responses.container.ResponseBankingAccountListDataV1;
import io.biza.babelfish.cdr.models.responses.container.ResponseBankingAccountListDataV1.ResponseBankingAccountListDataV1Builder;
import io.biza.babelfish.cdr.models.responses.container.ResponseBankingAccountsBalanceListDataV1;
import io.biza.babelfish.cdr.models.responses.container.ResponseBankingAccountsBalanceListDataV1.ResponseBankingAccountsBalanceListDataV1Builder;
import io.biza.babelfish.cdr.models.responses.container.ResponseBankingDirectDebitAuthorisationListDataV1;
import io.biza.babelfish.cdr.models.responses.container.ResponseBankingDirectDebitAuthorisationListDataV1.ResponseBankingDirectDebitAuthorisationListDataV1Builder;
import io.biza.babelfish.cdr.models.responses.container.ResponseBankingPayeeListDataV1;
import io.biza.babelfish.cdr.models.responses.container.ResponseBankingPayeeListDataV1.ResponseBankingPayeeListDataV1Builder;
import io.biza.babelfish.cdr.models.responses.container.ResponseBankingScheduledPaymentsListDataV1;
import io.biza.babelfish.cdr.models.responses.container.ResponseBankingScheduledPaymentsListDataV1.ResponseBankingScheduledPaymentsListDataV1Builder;
import io.biza.babelfish.cdr.models.responses.container.ResponseBankingTransactionListDataV1;
import io.biza.babelfish.cdr.models.responses.container.ResponseBankingTransactionListDataV1.ResponseBankingTransactionListDataV1Builder;
import io.biza.babelfish.cdr.models.responses.container.ResponseCommonCustomerDataV1;
import io.biza.babelfish.cdr.models.responses.container.ResponseCommonCustomerDataV1.ResponseCommonCustomerDataV1Builder;
import io.biza.babelfish.cdr.models.responses.container.ResponseCommonCustomerDetailDataV1;
import io.biza.babelfish.cdr.models.responses.container.ResponseCommonCustomerDetailDataV1.ResponseCommonCustomerDetailDataV1Builder;
import io.biza.babelfish.cdr.models.responses.container.ResponseCommonDiscoveryOutagesListDataV1;
import io.biza.babelfish.cdr.models.responses.container.ResponseCommonDiscoveryOutagesListDataV1.ResponseCommonDiscoveryOutagesListDataV1Builder;
import io.biza.babelfish.cdr.support.customtypes.ApcaNumberType;

/**
 * ModelConstants This defines valid models for manipulation within test cases
 *
 */
public class ModelConstants {
  public static URI DEFAULT_FIRST_URI = URI.create("http://localhost/?page=1");
  public static URI DEFAULT_SELF_URI = URI.create("http://localhost/?page=3");
  public static URI DEFAULT_LAST_URI = URI.create("http://localhost/?page=10");
  public static URI DEFAULT_PREV_URI = URI.create("http://localhost/?page=2");
  public static URI DEFAULT_NEXT_URI = URI.create("http://localhost/?page=4");
  public static List<String> DEFAULT_ACCOUNT_IDS =
      List.of("0be1c793-87ba-4942-95bd-4c972ec43a2d", "d5305a6c-b828-4651-bbcc-b3ea7264d387");
  public static BankingProductV1Builder DEFAULT_BANKING_PRODUCT_V1 =
      BankingProductV1.builder().productId("test")
          .lastUpdated(OffsetDateTime.now()).productCategory(BankingProductCategory.BUSINESS_LOANS)
          .name("Test").description("Test Description").brand("ACME").isTailored(false);
  public static BankingProductV2Builder DEFAULT_BANKING_PRODUCT_V2 =
      BankingProductV2.builder().productId("test")
          .lastUpdated(OffsetDateTime.now()).productCategory(BankingProductCategory.BUSINESS_LOANS)
          .name("Test").description("Test Description").brand("ACME").isTailored(false);
  public static BankingProductDetailV1Builder DEFAULT_BANKING_PRODUCT_DETAIL_V1 =
      BankingProductDetailV1.builder().productId("test")
          .lastUpdated(OffsetDateTime.now()).productCategory(BankingProductCategory.BUSINESS_LOANS)
          .name("Test").description("Test Description").brand("ACME").isTailored(false);
  public static BankingProductDetailV2Builder DEFAULT_BANKING_PRODUCT_DETAIL_V2 =
      BankingProductDetailV2.builder().productId("test")
          .lastUpdated(OffsetDateTime.now()).productCategory(BankingProductCategory.BUSINESS_LOANS)
          .name("Test").description("Test Description").brand("ACME").isTailored(false);
  public static BankingProductBundleV1Builder DEFAULT_BANKING_PRODUCT_BUNDLE =
      BankingProductBundleV1.builder().name("Bundle Name").description("Bundle Description");
  public static BankingProductFeatureV1Builder DEFAULT_BANKING_PRODUCT_FEATURE =
      BankingProductFeatureV1.builder().featureType(BankingProductFeatureType.ADDITIONAL_CARDS)
          .additionalValue("10");
  public static BankingProductConstraintV1Builder DEFAULT_BANKING_PRODUCT_CONSTRAINT =
      BankingProductConstraintV1.builder().constraintType(BankingProductConstraintType.MAX_BALANCE)
          .additionalValue("10.00");
  public static BankingProductEligibilityV1Builder DEFAULT_BANKING_PRODUCT_ELIGIBILITY =
      BankingProductEligibilityV1.builder().eligibilityType(BankingProductEligibilityType.BUSINESS);
  public static BankingProductFeeV1Builder DEFAULT_BANKING_PRODUCT_FEE =
      BankingProductFeeV1.builder().name("Fee Name").feeType(BankingProductFeeType.PERIODIC)
          .additionalValue("P1D").amount(new BigDecimal("10.00"));
  public static BankingProductDiscountV1Builder DEFAULT_BANKING_PRODUCT_FEE_DISCOUNT =
      BankingProductDiscountV1.builder().description("Discount Description")
          .amount(new BigDecimal("10.00")).discountType(BankingProductDiscountType.BALANCE)
          .additionalValue("100.00");
  public static BankingProductDiscountEligibilityV1Builder DEFAULT_BANKING_PRODUCT_FEE_DISCOUNT_ELIGIBILITY =
      BankingProductDiscountEligibilityV1.builder()
          .discountEligibilityType(BankingProductDiscountEligibilityType.BUSINESS);
  public static BankingProductDepositRateV1Builder DEFAULT_BANKING_PRODUCT_DEPOSIT_RATE =
      BankingProductDepositRateV1.builder().depositRateType(BankingProductDepositRateType.VARIABLE)
          .rate(new BigDecimal("0.05"));
  public static BankingProductLendingRateV1Builder DEFAULT_BANKING_PRODUCT_LENDING_RATE =
      BankingProductLendingRateV1.builder().lendingRateType(BankingProductLendingRateType.VARIABLE)
          .rate(new BigDecimal("0.05"));
  public static BankingProductRateTierV1Builder DEFAULT_BANKING_PRODUCT_RATE_TIER =
      BankingProductRateTierV1.builder().name("Rate Tier Name")
          .unitOfMeasure(CommonUnitOfMeasureType.DOLLAR).minimumValue(new BigDecimal("100.00"));

  public static LinksPaginatedV1Builder DEFAULT_LINKS_PAGINATED =
      LinksPaginatedV1.builder().self(DEFAULT_SELF_URI)
          .first(DEFAULT_FIRST_URI).next(DEFAULT_NEXT_URI)
          .last(DEFAULT_LAST_URI).prev(DEFAULT_PREV_URI);

  public static LinksV1Builder DEFAULT_LINKS = LinksV1.builder().self(DEFAULT_SELF_URI);

  public static MetaV1Builder DEFAULT_META = MetaV1.builder();
  public static MetaPaginatedV1Builder DEFAULT_META_PAGINATED =
      MetaPaginatedV1.builder().totalPages(10).totalRecords(100);
  public static BankingAccountV1Builder DEFAULT_BANKING_ACCOUNT = BankingAccountV1.builder()
      .accountId(UUID.randomUUID().toString()).displayName("Display Name")
      .maskedNumber("XXXX XXXX XXXX 1234").productCategory(BankingProductCategory.BUSINESS_LOANS)
      .productName("Business Loan Example");
  public static ResponseBankingAccountListDataV1Builder DEFAULT_RESPONSE_BANKING_ACCOUNT_LIST_DATA =
      ResponseBankingAccountListDataV1.builder().accounts(List.of(DEFAULT_BANKING_ACCOUNT.build()));

  public static BankingAccountDetailV1Builder DEFAULT_BANKING_ACCOUNT_DETAIL =
      BankingAccountDetailV1.builder().accountId(UUID.randomUUID().toString()).displayName("Display Name")
          .maskedNumber("XXXX XXXX XXXX 1234")
          .productCategory(BankingProductCategory.BUSINESS_LOANS)
          .productName("Business Loan Example");
  public static BankingTermDepositAccountV1Builder DEFAULT_BANKING_TERM_DEPOSIT_ACCOUNT =
      BankingTermDepositAccountV1.builder().lodgementDate(LocalDate.now()).maturityDate(LocalDate.now())
          .maturityInstructions(BankingTermDepositMaturityInstructions.ROLLED_OVER);
  public static BankingCreditCardAccountV1Builder DEFAULT_BANKING_CREDIT_CARD_ACCOUNT =
      BankingCreditCardAccountV1.builder().minPaymentAmount(new BigDecimal("10.00"))
          .paymentDueAmount(new BigDecimal("10.00")).paymentDueDate(LocalDate.now());
  public static BankingLoanAccountV1Builder DEFAULT_BANKING_LOAN_ACCOUNT =
      BankingLoanAccountV1.builder().loanEndDate(LocalDate.now()).nextInstalmentDate(LocalDate.now())
          .repaymentFrequency(Duration.ofDays(10));

  public static ResponseBankingAccountListV1Builder DEFAULT_RESPONSE_BANKING_ACCOUNT_LIST =
      ResponseBankingAccountListV1.builder().links(DEFAULT_LINKS_PAGINATED.build()).meta(DEFAULT_META_PAGINATED.build())
          .data(DEFAULT_RESPONSE_BANKING_ACCOUNT_LIST_DATA.build());

  public static BankingPayeeV1Builder DEFAULT_BANKING_PAYEE =
      BankingPayeeV1.builder().payeeId(UUID.randomUUID().toString()).nickname("Payee Nickname")
          .payeeType(BankingPayeeType.DOMESTIC);

  public static ResponseBankingPayeeListDataV1Builder DEFAULT_RESPONSE_BANKING_PAYEE_LIST_DATA =
      ResponseBankingPayeeListDataV1.builder().payees(List.of(DEFAULT_BANKING_PAYEE.build()));

  public static ResponseBankingPayeeListV1Builder DEFAULT_RESPONSE_BANKING_PAYEE_LIST =
      ResponseBankingPayeeListV1.builder().links(DEFAULT_LINKS_PAGINATED.build()).meta(DEFAULT_META_PAGINATED.build())
          .data(DEFAULT_RESPONSE_BANKING_PAYEE_LIST_DATA.build());

  public static ResponseBankingAccountByIdV1Builder DEFAULT_RESPONSE_BANKING_ACCOUNT_BY_ID =
      ResponseBankingAccountByIdV1.builder().links(DEFAULT_LINKS.build()).meta(DEFAULT_META.build())
          .data(DEFAULT_BANKING_ACCOUNT_DETAIL.build());
  public static BankingTransactionDetailExtendedDataV1Builder DEFAULT_BANKING_TRANSACTION_DETAIL_EXTENDED_DATA =
      BankingTransactionDetailExtendedDataV1.builder().payer("Payer").service(BankingTransactionService.X2P101);

  public static BankingTransactionDetailV1Builder DEFAULT_BANKING_TRANSACTION_DETAIL =
      BankingTransactionDetailV1.builder().accountId(UUID.randomUUID().toString())
          .transactionId(UUID.randomUUID().toString()).isDetailAvailable(false)
          .type(BankingTransactionType.PAYMENT).status(BankingTransactionStatus.POSTED)
          .description("Transaction Description").amount(new BigDecimal("10.00")).reference("")
          .postingDateTime(OffsetDateTime.now())
          .extendedData(DEFAULT_BANKING_TRANSACTION_DETAIL_EXTENDED_DATA.build());

  public static ResponseBankingTransactionByIdV1Builder DEFAULT_RESPONSE_BANKING_TRANSACTION_BY_ID =
      ResponseBankingTransactionByIdV1.builder().links(DEFAULT_LINKS.build())
          .data(DEFAULT_BANKING_TRANSACTION_DETAIL.build());


  public static BankingTransactionV1Builder DEFAULT_BANKING_TRANSACTION = BankingTransactionV1.builder()
      .accountId(UUID.randomUUID().toString()).transactionId(UUID.randomUUID().toString())
      .isDetailAvailable(false).type(BankingTransactionType.PAYMENT)
      .status(BankingTransactionStatus.POSTED).description("Transaction Description")
      .amount(new BigDecimal("10.00")).reference("").postingDateTime(OffsetDateTime.now());

  public static ResponseBankingTransactionListDataV1Builder DEFAULT_RESPONSE_BANKING_TRANSACTION_LIST_DATA =
      ResponseBankingTransactionListDataV1.builder().transactions(List.of(DEFAULT_BANKING_TRANSACTION.build()));

  public static ResponseBankingTransactionListV1Builder DEFAULT_RESPONSE_BANKING_TRANSACTION_LIST =
      ResponseBankingTransactionListV1.builder().meta(DEFAULT_META_PAGINATED.build())
          .links(DEFAULT_LINKS_PAGINATED.build()).data(DEFAULT_RESPONSE_BANKING_TRANSACTION_LIST_DATA.build());

  public static BankingBalanceV1Builder DEFAULT_BANKING_BALANCE =
      BankingBalanceV1.builder().accountId(UUID.randomUUID().toString())
          .currentBalance(new BigDecimal("1000.00")).availableBalance(new BigDecimal("500.00"));

  public static ResponseBankingAccountsBalanceListDataV1Builder DEFAULT_BANKING_ACCOUNTS_BALANCE_LIST_DATA =
      ResponseBankingAccountsBalanceListDataV1.builder().balances(List.of(DEFAULT_BANKING_BALANCE.build()));

  public static ResponseBankingAccountsBalanceListV1Builder DEFAULT_RESPONSE_BANKING_ACCOUNTS_BALANCE_LIST =
      ResponseBankingAccountsBalanceListV1.builder().meta(DEFAULT_META_PAGINATED.build())
          .links(DEFAULT_LINKS_PAGINATED.build()).data(DEFAULT_BANKING_ACCOUNTS_BALANCE_LIST_DATA.build());


  public static BankingBalancePurseV1Builder DEFAULT_BANKING_BALANCE_PURSE =
      BankingBalancePurseV1.builder().amount(new BigDecimal("100.00"));

  public static BankingBillerPayeeV1Builder DEFAULT_BANKING_BILLER_PAYEE =
      BankingBillerPayeeV1.builder().billerName("Energy Australia").billerCode("3111").crn("81752861");
  public static BankingDomesticPayeeAccountV1Builder DEFAULT_BANKING_DOMESTIC_PAYEE_ACCOUNT =
      BankingDomesticPayeeAccountV1.builder().bsb(ApcaNumberType.builder().bsb("012-070").build()).accountNumber("12341234");
  public static BankingDomesticPayeeCardV1Builder DEFAULT_BANKING_DOMESTIC_PAYEE_CARD =
      BankingDomesticPayeeCardV1.builder().cardNumber("XXXX XXXX XXXX 1234");
  public static BankingDomesticPayeePayIdV1Builder DEFAULT_BANKING_DOMESTIC_PAYEE_PAYID =
      BankingDomesticPayeePayIdV1.builder().type(PayloadTypeBankingDomesticPayeePayId.EMAIL)
          .identifier("valid@email.com");

  public static BankingInternationalPayeeBeneficiaryDetailsV1Builder DEFAULT_BANKING_INTERNATIONAL_PAYEE_BENEFICIARY_DETAILS =
      BankingInternationalPayeeBeneficiaryDetailsV1.builder().country(Locale.forLanguageTag("en-AU"));
  public static BankingInternationalPayeeBankDetailsV1Builder DEFAULT_BANKING_INTERNATIONAL_PAYEE_BANK_DETAILS =
      BankingInternationalPayeeBankDetailsV1.builder().accountNumber("1234123412341234").country(Locale.forLanguageTag("en-AU"));
  public static BankingAuthorisedEntityV1Builder DEFAULT_BANKING_AUTHORISED_ENTITY =
      BankingAuthorisedEntityV1.builder();

  public static BankingDirectDebitV1Builder DEFAULT_BANKING_DIRECT_DEBIT =
      BankingDirectDebitV1.builder().accountId(UUID.randomUUID().toString())
          .authorisedEntity(DEFAULT_BANKING_AUTHORISED_ENTITY.build());
  public static ResponseBankingDirectDebitAuthorisationListDataV1Builder DEFAULT_RESPONSE_BANKING_DIRECT_DEBIT_AUTHORISATION_LIST_DATA =
      ResponseBankingDirectDebitAuthorisationListDataV1.builder()
          .directDebitAuthorisations(List.of(DEFAULT_BANKING_DIRECT_DEBIT.build()));
  public static ResponseBankingDirectDebitAuthorisationListV1Builder DEFAULT_RESPONSE_BANKING_DIRECT_DEBIT_AUTHORISATION_LIST =
      ResponseBankingDirectDebitAuthorisationListV1.builder()
          .data(DEFAULT_RESPONSE_BANKING_DIRECT_DEBIT_AUTHORISATION_LIST_DATA.build());



  public static BankingScheduledPaymentFromV1Builder DEFAULT_BANKING_SCHEDULED_PAYMENT_FROM =
      BankingScheduledPaymentFromV1.builder().accountId(UUID.randomUUID().toString());
  public static BankingScheduledPaymentToV1Builder DEFAULT_BANKING_SCHEDULED_PAYMENT_TO =
      BankingScheduledPaymentToV1.builder().type(PayloadTypeBankingScheduledPaymentTo.ACCOUNT_ID)
          .accountId(UUID.randomUUID().toString());
  public static BankingScheduledPaymentSetV1Builder DEFAULT_BANKING_SCHEDULED_PAYMENT_SET =
      BankingScheduledPaymentSetV1.builder().to(DEFAULT_BANKING_SCHEDULED_PAYMENT_TO.build())
          .amount(new BigDecimal("10.00"));
  public static BankingScheduledPaymentRecurrenceOnceOffV1Builder DEFAULT_BANKING_SCHEDULED_PAYMENT_RECURRENCE_ONCE_OFF =
      BankingScheduledPaymentRecurrenceOnceOffV1.builder().paymentDate(LocalDate.now());
  public static BankingScheduledPaymentRecurrenceV1Builder DEFAULT_BANKING_SCHEDULED_PAYMENT_RECURRENCE =
      BankingScheduledPaymentRecurrenceV1.builder()
          .type(PayloadTypeBankingScheduledPaymentRecurrence.ONCE_OFF)
          .onceOff(DEFAULT_BANKING_SCHEDULED_PAYMENT_RECURRENCE_ONCE_OFF.build());
  public static BankingScheduledPaymentRecurrenceLastWeekdayV1Builder DEFAULT_BANKING_SCHEDULED_PAYMENT_RECURRENCE_LAST_WEEKDAY =
      BankingScheduledPaymentRecurrenceLastWeekdayV1.builder().interval(Period.ofDays(30))
          .lastWeekDay(CommonWeekDay.MON);
  public static BankingScheduledPaymentRecurrenceEventBasedV1Builder DEFAULT_BANKING_SCHEDULED_PAYMENT_RECURRENCE_EVENT_BASED =
      BankingScheduledPaymentRecurrenceEventBasedV1.builder()
          .description("Event Based Payment Description");
  public static BankingScheduledPaymentIntervalV1Builder DEFAULT_BANKING_SCHEDULED_PAYMENT_INTERVAL =
      BankingScheduledPaymentIntervalV1.builder().interval(Period.ofDays(30));
  public static CommonDiscoveryStatusV1Builder DEFAULT_COMMON_DISCOVERY_STATUS =
      CommonDiscoveryStatusV1.builder().status(CommonDiscoveryStatusType.OK)
          .updateTime(OffsetDateTime.now());
  public static ResponseCommonDiscoveryStatusV1Builder DEFAULT_RESPONSE_COMMON_DISCOVERY_STATUS =
      ResponseCommonDiscoveryStatusV1.builder().data(DEFAULT_COMMON_DISCOVERY_STATUS.build())
          .links(DEFAULT_LINKS.build());

  public static CommonDiscoveryOutageV1Builder DEFAULT_COMMON_DISCOVERY_OUTAGE =
      CommonDiscoveryOutageV1.builder().outageTime(OffsetDateTime.now()).duration(Duration.ofHours(1))
          .explanation("Outage Explanation");
  public static ResponseCommonDiscoveryOutagesListDataV1Builder DEFAULT_RESPONSE_COMMON_DISCOVERY_OUTAGES_LIST_DATA =
      ResponseCommonDiscoveryOutagesListDataV1.builder()
          .outages(List.of(DEFAULT_COMMON_DISCOVERY_OUTAGE.build()));

  public static CommonPersonV1Builder DEFAULT_COMMON_PERSON = CommonPersonV1.builder().lastName("Last");
  public static CommonOrganisationV1Builder DEFAULT_COMMON_ORGANISATION =
      CommonOrganisationV1.builder().agentLastName("Last").businessName("Organisation Business Name")
          .organisationType(CommonOrganisationType.OTHER);;
  public static CommonPhoneNumberV1Builder DEFAULT_COMMON_PHONE_NUMBER =
      CommonPhoneNumberV1.builder().purpose(CommonPhoneNumberPurpose.HOME).number("0733076000")
          .fullNumber("tel:+61-073-307-6000").areaCode("7");
  public static CommonEmailAddressV1Builder DEFAULT_COMMON_EMAIL_ADDRESS =
      CommonEmailAddressV1.builder().purpose(CommonEmailAddressPurpose.HOME).address("test@test.com");
  public static CommonSimpleAddressV1Builder DEFAULT_COMMON_SIMPLE_ADDRESS = CommonSimpleAddressV1.builder()
      .addressLine1("10 McGill Street").postcode("2550").city("Cobargo").state("NSW");
  public static CommonPAFAddressV1Builder DEFAULT_COMMON_PAF_ADDRESS = CommonPAFAddressV1.builder()
      .localityName("Cobargo").postcode("2550").state(AddressPAFStateType.NSW);

  public static CommonPhysicalAddressWithPurposeV1Builder DEFAULT_COMMON_PHYSICAL_ADDRESS_WITH_PURPOSE =
      CommonPhysicalAddressWithPurposeV1.builder().purpose(AddressPurpose.REGISTERED)
          .type(PayloadTypeAddress.SIMPLE).simple(DEFAULT_COMMON_SIMPLE_ADDRESS.build());

  public static ErrorV1Builder DEFAULT_ERROR =
      ErrorV1.builder().code("0001 – Account not able to be found")
          .title("Invalid account").detail(UUID.randomUUID().toString());
  public static BankingInternationalPayeeV1Builder DEFAULT_BANKING_INTERNATIONAL_PAYEE =
      BankingInternationalPayeeV1.builder()
          .beneficiaryDetails(
              DEFAULT_BANKING_INTERNATIONAL_PAYEE_BENEFICIARY_DETAILS.build())
          .bankDetails(DEFAULT_BANKING_INTERNATIONAL_PAYEE_BANK_DETAILS.build());
  public static BankingScheduledPaymentRecurrenceIntervalScheduleV1Builder DEFAULT_BANKING_SCHEDULED_PAYMENT_RECURRENCE_INTERVAL_SCHEDULE =
      BankingScheduledPaymentRecurrenceIntervalScheduleV1.builder()
          .intervals(List.of(DEFAULT_BANKING_SCHEDULED_PAYMENT_INTERVAL.build()));
  public static ResponseErrorListV1Builder DEFAULT_RESPONSE_ERROR_LIST =
      ResponseErrorListV1.builder().errors(List.of(DEFAULT_ERROR.build()));
  public static CommonPhysicalAddressV1Builder DEFAULT_COMMON_PHYSICAL_ADDRESS =
      CommonPhysicalAddressV1.builder().type(PayloadTypeAddress.SIMPLE)
          .simple(DEFAULT_COMMON_SIMPLE_ADDRESS.build());

  public static ResponseCommonDiscoveryOutagesListV1Builder DEFAULT_RESPONSE_COMMON_DISCOVERY_OUTAGES_LIST =
      ResponseCommonDiscoveryOutagesListV1.builder().links(DEFAULT_LINKS.build())
          .meta(DEFAULT_META.build())
          .data(DEFAULT_RESPONSE_COMMON_DISCOVERY_OUTAGES_LIST_DATA.build());

  public static BankingScheduledPaymentV1Builder DEFAULT_BANKING_SCHEDULED_PAYMENT =
      BankingScheduledPaymentV1.builder().scheduledPaymentId(UUID.randomUUID().toString())
          .payerReference("Payer Reference").payeeReference("Payee Reference")
          .payeeReference("Payee Reference").status(BankingScheduledPaymentStatus.ACTIVE)
          .from(DEFAULT_BANKING_SCHEDULED_PAYMENT_FROM.build())
          .paymentSet(List.of(DEFAULT_BANKING_SCHEDULED_PAYMENT_SET.build()))
          .recurrence(DEFAULT_BANKING_SCHEDULED_PAYMENT_RECURRENCE.build());

  public static ResponseCommonCustomerDataV1Builder DEFAULT_RESPONSE_COMMON_CUSTOMER_DATA =
      ResponseCommonCustomerDataV1.builder().type(PayloadTypeCustomer.PERSON)
          .person(DEFAULT_COMMON_PERSON.build());

  public static ResponseCommonCustomerV1Builder DEFAULT_RESPONSE_COMMON_CUSTOMER =
      ResponseCommonCustomerV1.builder().links(DEFAULT_LINKS.build())
          .data(DEFAULT_RESPONSE_COMMON_CUSTOMER_DATA.build());

  public static CommonPersonDetailV1Builder DEFAULT_COMMON_PERSON_DETAIL =
      CommonPersonDetailV1.builder().lastName("Last")
          .physicalAddresses(List.of(DEFAULT_COMMON_PHYSICAL_ADDRESS_WITH_PURPOSE.build()));
  public static CommonOrganisationDetailBuilder DEFAULT_COMMON_ORGANISATION_DETAIL =
      CommonOrganisationDetail.builder().agentLastName("Last")
          .businessName("Organisation Business Name").organisationType(CommonOrganisationType.OTHER)
          .physicalAddresses(List.of(DEFAULT_COMMON_PHYSICAL_ADDRESS_WITH_PURPOSE.build()));

  public static BankingDomesticPayeeV1Builder DEFAULT_BANKING_DOMESTIC_PAYEE =
      BankingDomesticPayeeV1.builder().payeeAccountType(PayloadTypeBankingDomesticPayee.ACCOUNT)
          .account(DEFAULT_BANKING_DOMESTIC_PAYEE_ACCOUNT.build());

  public static BankingPayeeDetailV1Builder DEFAULT_BANKING_PAYEE_DETAIL =
      BankingPayeeDetailV1.builder().payeeId(UUID.randomUUID().toString()).nickname("Payee Nickname")
          .payeeType(BankingPayeeType.DOMESTIC).type(PayloadTypeBankingPayee.DOMESTIC)
          .domestic(DEFAULT_BANKING_DOMESTIC_PAYEE.build());

  public static ResponseCommonCustomerDetailDataV1Builder DEFAULT_RESPONSE_COMMON_CUSTOMER_DETAIL_DATA =
      ResponseCommonCustomerDetailDataV1.builder().type(PayloadTypeCustomer.PERSON)
          .person(DEFAULT_COMMON_PERSON_DETAIL.build());

  public static ResponseCommonCustomerDetailV1Builder DEFAULT_RESPONSE_COMMON_CUSTOMER_DETAIL =
      ResponseCommonCustomerDetailV1.builder().links(DEFAULT_LINKS.build())
          .data(DEFAULT_RESPONSE_COMMON_CUSTOMER_DETAIL_DATA.build());

  public static BankingPayeeDetailV1Builder DEFAULT_PAYEE_DETAIL = BankingPayeeDetailV1.builder()
      .type(PayloadTypeBankingPayee.DOMESTIC).payeeId(UUID.randomUUID().toString()).nickname("Default Payee Detail")
      .payeeType(BankingPayeeType.DOMESTIC).domestic(DEFAULT_BANKING_DOMESTIC_PAYEE.build());
  public static ResponseBankingPayeeByIdV1Builder DEFAULT_RESPONSE_BANKING_PAYEE_BY_ID =
      ResponseBankingPayeeByIdV1.builder().links(DEFAULT_LINKS.build()).data(DEFAULT_PAYEE_DETAIL.build());

  public static ResponseBankingScheduledPaymentsListDataV1Builder DEFAULT_RESPONSE_BANKING_SCHEDULED_PAYMENTS_LIST_DATA =
      ResponseBankingScheduledPaymentsListDataV1.builder().scheduledPayments(List.of(DEFAULT_BANKING_SCHEDULED_PAYMENT.build()));
  
  public static ResponseBankingScheduledPaymentsListV1Builder DEFAULT_RESPONSE_BANKING_SCHEDULED_PAYMENTS_LIST =
      ResponseBankingScheduledPaymentsListV1.builder().links(DEFAULT_LINKS_PAGINATED.build())
          .meta(DEFAULT_META_PAGINATED.build()).data(DEFAULT_RESPONSE_BANKING_SCHEDULED_PAYMENTS_LIST_DATA.build());

}
