/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * 
 * public ANY WARRANTY() { return getWARRANTY(); }
 * 
 * @SuppressWarnings("unchecked") public T WARRANTY(ANY WARRANTY) { setWARRANTY(WARRANTY); return
 * (T) this; } even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See
 * the GNU General Public License for more details.
 *******************************************************************************/
package io.biza.cdr.babelfish.model.banking;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.v1.enumerations.PayloadTypeBankingScheduledPayment;
import io.biza.cdr.babelfish.support.BabelFishModel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Valid
@BabelFishModel(
    description = "Object containing details of the destination of the payment. Used to specify a variety of payment destination types")
public abstract class BankingScheduledPaymentTo<T extends BankingScheduledPaymentTo<T>> {
  @BabelFishModelProperty(
      description = "The type of object provided that specifies the destination of the funds for the payment.",
      required = true)
  @NonNull
  @NotNull
  PayloadTypeBankingScheduledPayment toUType;

  public PayloadTypeBankingScheduledPayment toUType() {
    return getToUType();
  }

  @SuppressWarnings("unchecked")
  public T toUType(PayloadTypeBankingScheduledPayment toUType) {
    setToUType(toUType);
    return (T) this;
  }

  @BabelFishModelProperty(
      description = "Present if toUType is set to accountId. Indicates that the payment is to another account that is accessible under the current consent")
  String accountId;

  public String accountId() {
    return getAccountId();
  }

  @SuppressWarnings("unchecked")
  public T accountId(String accountId) {
    setAccountId(accountId);
    return (T) this;
  }

  @BabelFishModelProperty(
      description = "Present if toUType is set to payeeId. Indicates that the payment is to registered payee that can be accessed using the payee end point. If the Bank Payees scope has not been consented to then a payeeId should not be provided and the full payee details should be provided instead")
  String payeeId;

  public String payeeId() {
    return getPayeeId();
  }

  @SuppressWarnings("unchecked")
  public T payeeId(String payeeId) {
    setPayeeId(payeeId);
    return (T) this;
  }

  BankingDomesticPayee<?> domestic;

  public BankingDomesticPayee<?> domestic() {
    return getDomestic();
  }

  @SuppressWarnings("unchecked")
  public T domestic(BankingDomesticPayee<?> domestic) {
    setDomestic(domestic);
    return (T) this;
  }

  BankingBillerPayee<?> biller;

  public BankingBillerPayee<?> biller() {
    return getBiller();
  }

  @SuppressWarnings("unchecked")
  public T biller(BankingBillerPayee<?> biller) {
    setBiller(biller);
    return (T) this;
  }

  BankingInternationalPayee<?> international;

  public BankingInternationalPayee<?> international() {
    return getInternational();
  }

  @SuppressWarnings("unchecked")
  public T international(BankingInternationalPayee<?> international) {
    setInternational(international);
    return (T) this;
  }

}
