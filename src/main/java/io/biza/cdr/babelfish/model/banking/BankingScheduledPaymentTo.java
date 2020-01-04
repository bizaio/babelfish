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
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.v1.enumerations.PayloadTypeBankingScheduledPayment;
import io.biza.cdr.babelfish.support.BabelFishModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Valid
@BabelFishModel(
    description = "Object containing details of the destination of the payment. Used to specify a variety of payment destination types")
public interface BankingScheduledPaymentTo {

  @BabelFishModelProperty(
      description = "The type of object provided that specifies the destination of the funds for the payment.",
      required = true)
  @JsonGetter("toUType")
  public PayloadTypeBankingScheduledPayment getType();

  @JsonSetter("toUType")
  public void setType(@NotNull PayloadTypeBankingScheduledPayment type);

  public default BankingScheduledPaymentTo type(@NotNull PayloadTypeBankingScheduledPayment type) {
    setType(type);
    return this;
  }

  @BabelFishModelProperty(
      description = "Present if toUType is set to accountId. Indicates that the payment is to another account that is accessible under the current consent")
  @JsonGetter("accountId")
  public String getAccountId();

  @JsonSetter("accountId")
  public void setAccountId(@NotNull String accountId);

  public default BankingScheduledPaymentTo accountId(@NotNull String accountId) {
    setAccountId(accountId);
    return this;
  }

  @BabelFishModelProperty(
      description = "Present if toUType is set to payeeId. Indicates that the payment is to registered payee that can be accessed using the payee end point. If the Bank Payees scope has not been consented to then a payeeId should not be provided and the full payee details should be provided instead")
  @JsonGetter("payeeId")
  public String getPayeeId();

  @JsonSetter("payeeeId")
  public void setPayeeId(@NotNull String payeeId);

  public default BankingScheduledPaymentTo payeeId(@NotNull String payeeId) {
    setPayeeId(payeeId);
    return this;
  }

  @BabelFishModelProperty(description = "Domestic Payee Object")
  @JsonGetter("domestic")
  public BankingDomesticPayee getDomestic();

  @JsonSetter("domestic")
  public void setDomestic(BankingDomesticPayee domestic);

  public default BankingScheduledPaymentTo domestic(BankingDomesticPayee domestic) {
    setDomestic(domestic);
    return this;
  }

  @BabelFishModelProperty(description = "Biller Payee Object")
  @JsonGetter("biller")
  public BankingBillerPayee getBiller();

  @JsonSetter("biller")
  public void setBiller(BankingBillerPayee biller);

  public default BankingScheduledPaymentTo biller(BankingBillerPayee biller) {
    setBiller(biller);
    return this;
  }

  @BabelFishModelProperty(description = "International Payee Object")
  @JsonGetter("international")
  public BankingInternationalPayee getInternational();

  @JsonSetter("international")
  public void setInternational(BankingInternationalPayee international);

  public default BankingScheduledPaymentTo international(BankingInternationalPayee international) {
    setInternational(international);
    return this;
  }

}
