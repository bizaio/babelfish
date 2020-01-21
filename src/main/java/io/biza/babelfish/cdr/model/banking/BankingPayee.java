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
import io.biza.babelfish.cdr.converters.LocalDateToStringConverter;
import io.biza.babelfish.cdr.converters.StringToLocalDateConverter;
import io.biza.babelfish.cdr.v1.enumerations.BankingPayeeType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Valid
@ToString
@EqualsAndHashCode
@Schema(description = "Banking Payee Basic Information")
public abstract class BankingPayee<T> {
  @Schema(description = "ID of the payee adhering to the rules of ID permanence", required = true)
  @NonNull
  @NotNull
  @JsonProperty("payeeId")
  String payeeId;

  public String payeeId() {
    return getPayeeId();
  }

  @SuppressWarnings("unchecked")
  public T payeeId(String payeeId) {
    setPayeeId(payeeId);
    return (T) this;
  }

  @Schema(description = "The short display name of the payee as provided by the customer",
      required = true)
  @NonNull
  @NotNull
  @JsonProperty("nickname")
  String nickname;

  public String nickname() {
    return getNickname();
  }

  @SuppressWarnings("unchecked")
  public T nickname(String nickname) {
    setNickname(nickname);
    return (T) this;
  }

  @Schema(description = "A description of the payee provided by the customer")
  @JsonProperty("description")
  String description;

  public String description() {
    return getDescription();
  }

  @SuppressWarnings("unchecked")
  public T description(String description) {
    setDescription(description);
    return (T) this;
  }

  @Schema(
      description = "The type of payee. DOMESTIC means a registered payee for domestic payments including NPP. INTERNATIONAL means a registered payee for international payments. BILLER means a registered payee for BPAY",
      required = true)
  @NonNull
  @NotNull
  @JsonProperty("type")
  BankingPayeeType payeeType;

  public BankingPayeeType payeeType() {
    return getPayeeType();
  }

  @SuppressWarnings("unchecked")
  public T payeeType(BankingPayeeType type) {
    setPayeeType(type);
    return (T) this;
  }

  @Schema(description = "The date the payee was created by the customer", type = "string", format = "date")
  @JsonSerialize(converter = LocalDateToStringConverter.class)
  @JsonDeserialize(converter = StringToLocalDateConverter.class)
  @JsonProperty("creationDate")
  private LocalDate creationDate;

  public LocalDate creationDate() {
    return getCreationDate();
  }

  @SuppressWarnings("unchecked")
  public T creationDate(LocalDate creationDate) {
    setCreationDate(creationDate);
    return (T) this;
  }
}
