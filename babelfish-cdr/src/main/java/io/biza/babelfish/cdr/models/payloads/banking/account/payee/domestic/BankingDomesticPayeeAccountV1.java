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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.cdr.converters.ApcaNumberToStringConverter;
import io.biza.babelfish.cdr.converters.StringToApcaNumberConverter;
import io.biza.babelfish.cdr.support.customtypes.ApcaNumberType;
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
@Schema(description = "Domestic Payment Payee Details", name = "BankingDomesticPayeeAccountV1")
public class BankingDomesticPayeeAccountV1 {
  @Schema(description = "Name of the account to pay to")
  @JsonProperty("accountName")
  String accountName;

  @Schema(description = "BSB of the account to pay to", required = true, type = "string")
  @NotNull
  @JsonProperty("bsb")
  @JsonSerialize(converter = ApcaNumberToStringConverter.class)
  @JsonDeserialize(converter = StringToApcaNumberConverter.class)
  ApcaNumberType bsb;

  @Schema(description = "Number of the account to pay to", required = true)
  @NotEmpty
  @JsonProperty("accountNumber")
  String accountNumber;
}
