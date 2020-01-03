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
import io.biza.cdr.babelfish.v1.enumerations.PayloadTypeBankingDomesticPayee;
import io.biza.cdr.babelfish.support.BabelFishModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Valid
@BabelFishModel(description = "Representation of a Domestic Payee Detail")
public interface BankingDomesticPayee {

  @BabelFishModelProperty(description = "Type of account object included.", required = true)
  @JsonGetter("payeeAccountUType")
  public PayloadTypeBankingDomesticPayee getAccountType();

  @JsonSetter("payeeAccountUType")
  public void setAccountType(PayloadTypeBankingDomesticPayee accountType);

  public default BankingDomesticPayee accountType(PayloadTypeBankingDomesticPayee accountType) {
    setAccountType(accountType);
    return this;
  }

  @BabelFishModelProperty(description = "Domestic Payee Account")
  @JsonGetter("account")
  public BankingDomesticPayeeAccount getAccount();

  @JsonSetter("account")
  public void setAccount(BankingDomesticPayeeAccount account);

  public default BankingDomesticPayee account(BankingDomesticPayeeAccount account) {
    setAccount(account);
    return this;
  }

  @BabelFishModelProperty(description = "Domestic Payee Card")
  @JsonGetter("card")
  public BankingDomesticPayeeCard getCard();

  @JsonSetter("card")
  public void setCard(BankingDomesticPayeeCard card);

  public default BankingDomesticPayee card(BankingDomesticPayeeCard card) {
    setCard(card);
    return this;
  }

  @BabelFishModelProperty(description = "Domestic Payee PayID")
  @JsonGetter("payId")
  public BankingDomesticPayeePayId getPayId();

  @JsonSetter("payId")
  public void setPayId(BankingDomesticPayeePayId payId);

  public default BankingDomesticPayee payId(BankingDomesticPayeePayId payId) {
    setPayId(payId);
    return this;
  }


}
