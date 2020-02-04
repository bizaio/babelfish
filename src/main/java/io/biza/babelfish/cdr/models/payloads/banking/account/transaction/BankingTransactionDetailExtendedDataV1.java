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
package io.biza.babelfish.cdr.models.payloads.banking.account.transaction;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.biza.babelfish.cdr.enumerations.BankingTransactionService;
import io.biza.babelfish.cdr.enumerations.PayloadTypeTransactionExtension;
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
@Schema(description = "Banking Transaction Detailed Extended Data", name = "BankingTransactionDetailExtendedDataV1")
public class BankingTransactionDetailExtendedDataV1 {
  @Schema(description = "Label of the originating payer. Mandatory for inbound payment")
  @JsonProperty("payer")
  String payer;

  @Schema(
      description = "Label of the target PayID.  Mandatory for an outbound payment. The name assigned to the BSB/Account Number or PayID (by the owner of the PayID)")
  @JsonProperty("payee")
  String payee;

  @Schema(
      description = "Optional extended data provided specific to transaction originated via NPP")
  @JsonProperty("extensionUType")
  PayloadTypeTransactionExtension extensionUType;

  @Schema(description = "X2P1.01 Payload Details")
  @JsonProperty("x2p101Payload")
  @Valid
  BankingTransactionDetailExtendedDataX2p101PayloadV1 x2p101Payload;

  @Schema(description = "Identifier of the applicable overlay service.", required = true)
  @NotNull
  @JsonProperty("service")
  @Valid
  BankingTransactionService service;
}
