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
package io.biza.babelfish.cdr.abstracts.payloads.banking.account.payee.international;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
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

@Schema(description = "International Payee Beneficiary and Bank Details")
public abstract class BankingInternationalPayee<T> {
  @Schema(required = true)
  @NotNull
  @JsonProperty("beneficiaryDetails")
  @Valid
  BankingInternationalPayeeBeneficiaryDetails<?> beneficiaryDetails;

  public BankingInternationalPayeeBeneficiaryDetails<?> beneficiaryDetails() {
    return getBeneficiaryDetails();
  }

  @SuppressWarnings("unchecked")
  public T beneficiaryDetails(BankingInternationalPayeeBeneficiaryDetails<?> beneficiaryDetails) {
    setBeneficiaryDetails(beneficiaryDetails);
    return (T) this;
  }

  @Schema(required = true)
  @NotNull
  @JsonProperty("bankDetails")
  @Valid
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
