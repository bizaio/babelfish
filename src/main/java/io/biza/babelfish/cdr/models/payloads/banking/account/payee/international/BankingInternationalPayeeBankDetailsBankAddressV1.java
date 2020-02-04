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
package io.biza.babelfish.cdr.models.payloads.banking.account.payee.international;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Valid
@ToString
@EqualsAndHashCode
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "International Payee Bank Address Details", name = "BankingInternationalPayeeBankDetailsBankAddress")
public class BankingInternationalPayeeBankDetailsBankAddressV1 {
  @Schema(description = "Name of the recipient Bank", required = true)
  @NotNull
  @JsonProperty("name")
  String name;

  @Schema(description = "Address of the recipient Bank", required = true)
  @NotNull
  @JsonProperty("address")
  String address;
}
