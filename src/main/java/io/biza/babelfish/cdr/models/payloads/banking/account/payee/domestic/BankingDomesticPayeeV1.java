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
package io.biza.babelfish.cdr.models.payloads.banking.account.payee.domestic;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.biza.babelfish.cdr.enumerations.PayloadTypeBankingDomesticPayee;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Valid
@ToString
@EqualsAndHashCode
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Representation of a Domestic Payee Detail", name = "BankingDomesticPayeeV1")
public class BankingDomesticPayeeV1 extends
    io.biza.babelfish.cdr.abstracts.payloads.banking.account.payee.domestic.BankingDomesticPayeeV1 {
  @Schema(description = "Type of account object included.", required = true)
  @NotNull
  @JsonProperty("payeeAccountUType")
  PayloadTypeBankingDomesticPayee payeeAccountType;

  @JsonProperty("account")
  @Valid
  BankingDomesticPayeeAccountV1 account;

  @JsonProperty("card")
  @Valid
  BankingDomesticPayeeCardV1 card;

  @JsonProperty("payId")
  @Valid
  BankingDomesticPayeePayIdV1 payId;

}
