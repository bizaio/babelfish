/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *******************************************************************************/
package io.biza.cdr.babelfish.model.banking;

import java.time.LocalDate;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.v1.enumerations.BankingPayeeType;
import io.biza.cdr.babelfish.converters.LocalDateToStringConverter;
import io.biza.cdr.babelfish.converters.StringToLocalDateConverter;
import io.biza.cdr.babelfish.support.BabelFishModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Valid
@BabelFishModel(description = "Banking Payee Basic Information")
public interface BankingPayee {

  @BabelFishModelProperty(description = "ID of the payee adhering to the rules of ID permanence",
      required = true)
  @JsonGetter("payeeId")
  public String getPayeeId();

  @JsonSetter("payeeeId")
  public void setPayeeId(@NotNull String payeeId);

  public default BankingPayee payeeId(@NotNull String payeeId) {
    setPayeeId(payeeId);
    return this;
  }

  @BabelFishModelProperty(
      description = "The short display name of the payee as provided by the customer",
      required = true)
  @JsonGetter("nickname")
  public String getNickname();

  @JsonSetter("nickname")
  public void setNickname(@NotNull String nickname);

  public default BankingPayee nickname(@NotNull String nickname) {
    setNickname(nickname);
    return this;
  }

  @BabelFishModelProperty(description = "A description of the payee provided by the customer")
  @JsonGetter("description")
  public String getDescription();

  @JsonSetter("description")
  public void setDescription(String description);

  public default BankingPayee description(String description) {
    setDescription(description);
    return this;
  }

  @BabelFishModelProperty(
      description = "The type of payee. DOMESTIC means a registered payee for domestic payments including NPP. INTERNATIONAL means a registered payee for international payments. BILLER means a registered payee for BPAY",
      required = true)
  @JsonGetter("type")
  public BankingPayeeType getType();

  @JsonSetter("type")
  public void setType(@NotNull BankingPayeeType type);

  public default BankingPayee type(@NotNull BankingPayeeType type) {
    setType(type);
    return this;
  }

  @BabelFishModelProperty(description = "The date the payee was created by the customer",
      dataType = "java.lang.String")
  @JsonSerialize(converter = LocalDateToStringConverter.class)
  @JsonGetter("creationDate")
  public LocalDate getCreationDate();

  @JsonDeserialize(converter = StringToLocalDateConverter.class)
  @JsonSetter("creationDate")
  public void setCreationDate(LocalDate creationDate);

  public default BankingPayee creationDate(LocalDate creationDate) {
    setCreationDate(creationDate);
    return this;
  }

}
