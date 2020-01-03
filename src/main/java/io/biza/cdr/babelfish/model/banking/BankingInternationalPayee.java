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
@BabelFishModel(description = "International Payee Beneficiary and Bank Details")
public interface BankingInternationalPayee {

  @BabelFishModelProperty(required = true)
  @JsonGetter("beneficiaryDetails")
  public BankingInternationalPayeeBeneficiaryDetails getBeneficiaryDetails();

  @JsonSetter("beneficiaryDetails")
  public void setBeneficiaryDetails(
      @NotNull BankingInternationalPayeeBeneficiaryDetails beneficiaryDetails);

  public default BankingInternationalPayee beneficiaryDetails(
      @NotNull BankingInternationalPayeeBeneficiaryDetails beneficiaryDetails) {
    setBeneficiaryDetails(beneficiaryDetails);
    return this;
  }

  @BabelFishModelProperty(required = true)
  @JsonGetter("bankDetails")
  public BankingInternationalPayeeBankDetails getBankDetails();

  @JsonSetter("bankDetails")
  public void setBankDetails(@NotNull BankingInternationalPayeeBankDetails bankDetails);

  public default BankingInternationalPayee bankDetails(
      @NotNull BankingInternationalPayeeBankDetails bankDetails) {
    setBankDetails(bankDetails);
    return this;
  }
}
