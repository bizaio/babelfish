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
package io.biza.babelfish.cdr.model.banking;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.biza.babelfish.enumerations.cdr.PayloadTypeBankingDomesticPayee;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Valid
@ToString
@EqualsAndHashCode

@Schema(description = "Representation of a Domestic Payee Detail")
public abstract class BankingDomesticPayee<T> {
  @Schema(description = "Type of account object included.", required = true)
  @NotNull
  @JsonProperty("payeeAccountUType")
  PayloadTypeBankingDomesticPayee payeeAccountType;

  public PayloadTypeBankingDomesticPayee payeeAccountType() {
    return getPayeeAccountType();
  }

  @SuppressWarnings("unchecked")
  public T payeeAccountType(PayloadTypeBankingDomesticPayee payeeAccountType) {
    setPayeeAccountType(payeeAccountType);
    return (T) this;
  }

  @JsonProperty("account")
  @Valid
  BankingDomesticPayeeAccount<?> account;

  public BankingDomesticPayeeAccount<?> account() {
    return getAccount();
  }

  @SuppressWarnings("unchecked")
  public T account(BankingDomesticPayeeAccount<?> account) {
    setAccount(account);
    return (T) this;
  }

  @JsonProperty("card")
  @Valid
  BankingDomesticPayeeCard<?> card;

  public BankingDomesticPayeeCard<?> card() {
    return getCard();
  }

  @SuppressWarnings("unchecked")
  public T card(BankingDomesticPayeeCard<?> card) {
    setCard(card);
    return (T) this;
  }

  @JsonProperty("payId")
  @Valid
  BankingDomesticPayeePayId<?> payId;

  public BankingDomesticPayeePayId<?> payId() {
    return getPayId();
  }

  @SuppressWarnings("unchecked")
  public T payId(BankingDomesticPayeePayId<?> payId) {
    setPayId(payId);
    return (T) this;
  }

}
