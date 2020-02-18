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
package io.biza.babelfish.cdr.models.payloads.banking.account.payee.scheduled;

import java.time.LocalDate;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.cdr.converters.LocalDateToStringConverter;
import io.biza.babelfish.cdr.converters.StringToLocalDateConverter;
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
@Schema(
    description = "Indicates that the payment is a once off payment on a specific future date. Mandatory if recurrenceUType is set to onceOff",
    name = "BankingScheduledPaymentRecurrenceOnceOffV1")
public class BankingScheduledPaymentRecurrenceOnceOffV1 {
  @Schema(description = "The scheduled date for the once off payment", required = true,
      type = "string", format = "date")
  @NotNull
  @JsonSerialize(converter = LocalDateToStringConverter.class)
  @JsonDeserialize(converter = StringToLocalDateConverter.class)
  @JsonProperty("paymentDate")
  LocalDate paymentDate;

}
