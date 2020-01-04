/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *******************************************************************************/
package io.biza.cdr.babelfish.model.banking;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.v1.enumerations.PayloadTypeBankingScheduledPayment;
import io.biza.cdr.babelfish.support.BabelFishModel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
@Valid
@BabelFishModel(
    description = "Object containing details of the destination of the payment. Used to specify a variety of payment destination types")
public abstract class BankingScheduledPaymentTo {
  @BabelFishModelProperty(
      description = "The type of object provided that specifies the destination of the funds for the payment.",
      required = true)
  @NonNull
  @NotNull
  PayloadTypeBankingScheduledPayment toUType;

  @BabelFishModelProperty(
      description = "Present if toUType is set to accountId. Indicates that the payment is to another account that is accessible under the current consent")
  String accountId;

  @BabelFishModelProperty(
      description = "Present if toUType is set to payeeId. Indicates that the payment is to registered payee that can be accessed using the payee end point. If the Bank Payees scope has not been consented to then a payeeId should not be provided and the full payee details should be provided instead")
  String payeeId;

  BankingDomesticPayee domestic;

  BankingBillerPayee biller;

  BankingInternationalPayee international;

  @AssertTrue(
      message = "One and only one of accountId, payeeId, domestic, biller, international should be populated based on toUType")
  private boolean isUTypePopulated() {
    if (toUType.equals(PayloadTypeBankingScheduledPayment.ACCOUNT_ID)) {
      return accountId != null && payeeId == null && domestic == null && biller == null
          && international == null;
    } else if (toUType.equals(PayloadTypeBankingScheduledPayment.PAYEE_ID)) {
      return payeeId != null && accountId == null && domestic == null && biller == null
          && international == null;
    } else if (toUType.equals(PayloadTypeBankingScheduledPayment.DOMESTIC)) {
      return domestic != null && accountId == null && payeeId == null && biller == null
          && international == null;
    } else if (toUType.equals(PayloadTypeBankingScheduledPayment.BILLER)) {
      return biller != null && accountId == null && payeeId == null && domestic == null
          && international == null;
    } else if (toUType.equals(PayloadTypeBankingScheduledPayment.INTERNATIONAL)) {
      return international != null && accountId == null && payeeId == null && domestic == null
          && biller == null;
    }
    return false;
  }
}
