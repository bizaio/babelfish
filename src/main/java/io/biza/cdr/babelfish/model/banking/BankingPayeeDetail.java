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
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.v1.enumerations.PayloadTypeBankingPayee;
import io.biza.cdr.babelfish.support.BabelFishModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Valid
@BabelFishModel(description = "Banking Payee Detailed Information", parent = BankingPayee.class)
public interface BankingPayeeDetail extends BankingPayee {

  @BabelFishModelProperty(
      description = "Type of object included that describes the payee in detail", required = true)
  @JsonGetter("payeeUType")
  public PayloadTypeBankingPayee getPayeeType();

  @JsonSetter("payeeUType")
  public void setPayeeType(PayloadTypeBankingPayee payeeUType);

  public default BankingPayeeDetail payeeType(PayloadTypeBankingPayee payeeUType) {
    setPayeeType(payeeUType);
    return this;
  }

  @BabelFishModelProperty(description = "Domestic Payee Object")
  @JsonGetter("domestic")
  public BankingDomesticPayee getDomestic();

  @JsonSetter("domestic")
  public void setDomestic(BankingDomesticPayee domestic);

  public default BankingPayeeDetail domestic(BankingDomesticPayee domestic) {
    setDomestic(domestic);
    return this;
  }

  @BabelFishModelProperty(description = "Biller Payee Object")
  @JsonGetter("biller")
  public BankingBillerPayee getBiller();

  @JsonSetter("biller")
  public void setBiller(BankingBillerPayee biller);

  public default BankingPayeeDetail biller(BankingBillerPayee biller) {
    setBiller(biller);
    return this;
  }

  @BabelFishModelProperty(description = "International Payee Object")
  @JsonGetter("international")
  public BankingInternationalPayee getInternational();

  @JsonSetter("international")
  public void setInternational(BankingInternationalPayee international);

  public default BankingPayeeDetail international(BankingInternationalPayee international) {
    setInternational(international);
    return this;
  }

}
