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
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Valid
@BabelFishModel(description = "International Payee Beneficiary and Bank Details")
public abstract class BankingInternationalPayee<T> {
  @BabelFishModelProperty(required = true)
  @NonNull
  @NotNull
  BankingInternationalPayeeBeneficiaryDetails<?> beneficiaryDetails;

  public BankingInternationalPayeeBeneficiaryDetails<?> beneficiaryDetails() {
    return getBeneficiaryDetails();
  }

  @SuppressWarnings("unchecked")
  public T beneficiaryDetails(BankingInternationalPayeeBeneficiaryDetails<?> beneficiaryDetails) {
    setBeneficiaryDetails(beneficiaryDetails);
    return (T) this;
  }

  @BabelFishModelProperty(required = true)
  @NonNull
  @NotNull
  BankingInternationalPayeeBankDetails<?> bankDetails;

  public BankingInternationalPayeeBankDetails<?> bankDetails() {
    return getBankDetails();
  }

  @SuppressWarnings("unchecked")
  public T bankDetails(BankingInternationalPayeeBankDetails<?> bankDetails) {
    setBankDetails(bankDetails);
    return (T) this;
  }
}
