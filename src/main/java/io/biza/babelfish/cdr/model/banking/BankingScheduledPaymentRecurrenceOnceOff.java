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
package io.biza.babelfish.cdr.model.banking;

import java.time.LocalDate;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.converter.cdr.LocalDateToStringConverter;
import io.biza.babelfish.converter.cdr.StringToLocalDateConverter;
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
@Schema(
    description = "Indicates that the payment is a once off payment on a specific future date. Mandatory if recurrenceUType is set to onceOff")
public abstract class BankingScheduledPaymentRecurrenceOnceOff<T> {
  @Schema(description = "The scheduled date for the once off payment", required = true,
      type = "string", format = "date")
  @NotNull
  @JsonSerialize(converter = LocalDateToStringConverter.class)
  @JsonDeserialize(converter = StringToLocalDateConverter.class)
  @JsonProperty("nonBusinessDayTreatment")
  LocalDate paymentDate;

  public LocalDate paymentDate() {
    return getPaymentDate();
  }

  @SuppressWarnings("unchecked")
  public T paymentDate(LocalDate paymentDate) {
    setPaymentDate(paymentDate);
    return (T) this;
  }
}
