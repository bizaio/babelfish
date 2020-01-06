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
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.v1.enumerations.PayloadTypeBankingPayee;
import io.biza.cdr.babelfish.support.BabelFishModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Valid
@BabelFishModel(description = "Banking Payee Detailed Information", parent = BankingPayee.class)
public abstract class BankingPayeeDetail<T> {
  @JsonUnwrapped
  @BabelFishModelProperty(hidden = true)
  BankingPayee<?> bankingPayee;

  public BankingPayee<?> bankingPayee() {
    return getBankingPayee();
  }

  @SuppressWarnings("unchecked")
  public T bankingPayee(BankingPayee<?> bankingPayee) {
    setBankingPayee(bankingPayee);
    return (T) this;
  }

  @BabelFishModelProperty(
      description = "Type of object included that describes the payee in detail", required = true)
  PayloadTypeBankingPayee payeeUType;

  public PayloadTypeBankingPayee payeeUType() {
    return getPayeeUType();
  }

  @SuppressWarnings("unchecked")
  public T payeeUType(PayloadTypeBankingPayee payeeUType) {
    setPayeeUType(payeeUType);
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
