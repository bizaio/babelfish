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
package io.biza.babelfish.cdr.models.payloads.banking.account.payee;

import java.time.LocalDate;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.cdr.converters.LocalDateToStringConverter;
import io.biza.babelfish.cdr.converters.StringToLocalDateConverter;
import io.biza.babelfish.cdr.enumerations.BankingPayeeType;
import io.biza.babelfish.cdr.enumerations.PayloadTypeBankingPayee;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.bpay.BankingBillerPayeeV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.domestic.BankingDomesticPayeeV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.international.BankingInternationalPayeeV1;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Valid
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Banking Payee Detailed Information", name = "BankingPayeeDetailV1")
public class BankingPayeeDetailV1
    extends io.biza.babelfish.cdr.abstracts.payloads.banking.BankingPayeeDetailV1 {

  @Schema(description = "ID of the payee adhering to the rules of ID permanence", required = true)
  @NotEmpty
  @JsonProperty("payeeId")
  String payeeId;

  @Schema(description = "The short display name of the payee as provided by the customer",
      required = true)
  @NotEmpty
  @JsonProperty("nickname")
  String nickname;

  @Schema(description = "A description of the payee provided by the customer")
  @JsonProperty("description")
  String description;

  @Schema(
      description = "The type of payee. DOMESTIC means a registered payee for domestic payments including NPP. INTERNATIONAL means a registered payee for international payments. BILLER means a registered payee for BPAY",
      required = true)
  @NotNull
  @JsonProperty("type")
  BankingPayeeType payeeType;

  @Schema(description = "The date the payee was created by the customer", type = "string",
      format = "date")
  @JsonSerialize(converter = LocalDateToStringConverter.class)
  @JsonDeserialize(converter = StringToLocalDateConverter.class)
  @JsonProperty("creationDate")
  LocalDate creationDate;

  @Schema(description = "Type of object included that describes the payee in detail",
      required = true)
  @JsonProperty("payeeUType")
  @NotNull
  PayloadTypeBankingPayee type;

  @JsonProperty("domestic")
  @Valid
  BankingDomesticPayeeV1 domestic;

  @JsonProperty("biller")
  @Valid
  BankingBillerPayeeV1 biller;

  @JsonProperty("international")
  @Valid
  BankingInternationalPayeeV1 international;

}
