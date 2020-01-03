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
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Valid
@BabelFishModel(description = "Domestic Payment Payee Details")
public interface BankingDomesticPayeeAccount {

  @BabelFishModelProperty(description = "Name of the account to pay to", required = true)
  @JsonGetter("accountName")
  public String getAccountName();

  @JsonSetter("accountName")
  public void setAccountName(@NotNull String accountName);

  public default BankingDomesticPayeeAccount accountName(@NotNull String accountName) {
    setAccountName(accountName);
    return this;
  }

  @BabelFishModelProperty(description = "BSB of the account to pay to", required = true)
  @JsonGetter("bsb")
  public String getBsb();

  @JsonSetter("bsb")
  public void setBsb(@NotNull String bsb);

  public default BankingDomesticPayeeAccount bsb(@NotNull String bsb) {
    setBsb(bsb);
    return this;
  }

  @BabelFishModelProperty(description = "Number of the account to pay to", required = true)
  @JsonGetter("accountNumber")
  public String getAccountNumber();

  @JsonSetter("accountNumber")
  public String setAccountNumber(@NotNull String accountNumber);

  public default BankingDomesticPayeeAccount accountNumber(@NotNull String accountNumber) {
    setAccountNumber(accountNumber);
    return this;
  }
}
