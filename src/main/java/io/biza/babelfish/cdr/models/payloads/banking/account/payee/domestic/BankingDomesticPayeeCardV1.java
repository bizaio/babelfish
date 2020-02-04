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
import javax.validation.constraints.AssertTrue;
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
@EqualsAndHashCode
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Domestic Payee Card Number Details", name = "BankingDomesticPayeeCardV1")
public class BankingDomesticPayeeCardV1 {
  @Schema(description = "Name of the account to pay to", required = true)
  @NotNull
  @JsonProperty("cardNumber")
  String cardNumber;

  @AssertTrue(message = "Card Number MUST be Masked PAN Format")
  private boolean isPanMasked() {
    if (cardNumber() == null) {
      return true;
    }
    // We check masked PAN by checking for explicit types that are unmasked
    if (cardNumber().matches("\\d{4}-\\d{4}-\\d{4}-\\d{4}")
        || cardNumber().matches("\\d{4} \\d{4} \\d{4} \\d{4}") || cardNumber().matches("\\d{16}")) {
      return false;
    } else {
      return true;
    }
  }
}
