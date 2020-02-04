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
package io.biza.babelfish.cdr.models.payloads.banking.account.directdebit;

import javax.validation.Valid;
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
@Schema(description = "Authorised Entity details", name = "BankingAuthorisedEntity")
public class BankingAuthorisedEntityV1 {
  @Schema(
      description = "Description of the authorised entity derived from previously executed direct debits")
  @JsonProperty("description")
  String description;

  @Schema(
      description = "Name of the financial institution through which the direct debit will be executed. Is required unless the payment is made via a credit card scheme")
  @JsonProperty("financialInstitution")
  String financialInstitution;

  @Schema(description = "Australian Business Number for the authorised entity")
  @JsonProperty("abn")
  String abn;

  @Schema(description = "Australian Company Number for the authorised entity")
  @JsonProperty("acn")
  String acn;

  @Schema(description = "Australian Registered Body Number for the authorised entity")
  @JsonProperty("arbn")
  String arbn;
}
