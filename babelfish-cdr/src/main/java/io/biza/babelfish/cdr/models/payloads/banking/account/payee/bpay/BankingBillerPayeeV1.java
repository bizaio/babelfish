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
package io.biza.babelfish.cdr.models.payloads.banking.account.payee.bpay;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Valid
@ToString
@EqualsAndHashCode(callSuper = false)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Representation of a BPAY Payee", name = "BankingBillerPayeeV1")
public class BankingBillerPayeeV1 extends
    io.biza.babelfish.cdr.abstracts.payloads.banking.account.payee.bpay.BankingBillerPayeeV1 {
  @Schema(description = "BPAY Biller Code of the Biller", required = true)
  @NotNull
  @JsonProperty("billerCode")
  String billerCode;

  @Schema(
      description = "BPAY CRN of the Biller. If the contents of the CRN match the format of a Credit Card PAN then it should be masked using the rules applicable for the MaskedPANString common type")
  @NotNull
  @JsonProperty("crn")
  String crn;

  @Schema(description = "Name of the Biller", required = true)
  @NotNull
  @JsonProperty("billerName")
  String billerName;

}
