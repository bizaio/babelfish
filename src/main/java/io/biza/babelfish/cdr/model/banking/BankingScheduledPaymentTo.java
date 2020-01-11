/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *******************************************************************************/
package io.biza.babelfish.cdr.model.banking;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.biza.babelfish.cdr.v1.enumerations.PayloadTypeBankingScheduledPaymentTo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Valid
@ToString
@EqualsAndHashCode

@Schema(
    description = "Object containing details of the destination of the payment. Used to specify a variety of payment destination types")
public abstract class BankingScheduledPaymentTo<T> {
  @Schema(
      description = "The type of object provided that specifies the destination of the funds for the payment.",
      required = true)
  @NonNull
  @NotNull
  @JsonProperty("toUType")
  PayloadTypeBankingScheduledPaymentTo type;

  public PayloadTypeBankingScheduledPaymentTo type() {
    return getType();
  }

  @SuppressWarnings("unchecked")
  public T type(PayloadTypeBankingScheduledPaymentTo toUType) {
    setType(toUType);
    return (T) this;
  }

  @Schema(
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

  @Schema(
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
