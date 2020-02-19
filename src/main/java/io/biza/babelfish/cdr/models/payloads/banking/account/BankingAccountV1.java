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
package io.biza.babelfish.cdr.models.payloads.banking.account;

import java.time.LocalDate;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.cdr.converters.LocalDateToStringConverter;
import io.biza.babelfish.cdr.converters.StringToLocalDateConverter;
import io.biza.babelfish.cdr.enumerations.BankingAccountStatus;
import io.biza.babelfish.cdr.enumerations.BankingProductCategory;
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
@Schema(description = "An Australian Bank Account", name = "BankingAccountV1")
@JsonIgnoreProperties({"owned"})
public class BankingAccountV1 {
  @Schema(description = "A unique ID of the account adhering to the standards for ID permanence",
      required = true)
  @NotEmpty(message = "Must contain a unique identifier")
  @JsonProperty("accountId")
  String accountId;

  @Schema(description = "Date that the account was created (if known)", type = "string",
      format = "date")
  @JsonSerialize(converter = LocalDateToStringConverter.class)
  @JsonDeserialize(converter = StringToLocalDateConverter.class)
  @JsonProperty("creationDate")
  LocalDate creationDate;

  @Schema(
      description = "The display name of the account as defined by the bank. This should not incorporate account numbers or PANs. If it does the values should be masked according to the rules of the MaskedAccountString common type.",
      required = true)
  @NotEmpty(message = "Must contain a display name for the account")
  @JsonProperty("displayName")
  String displayName;

  @Schema(description = "A customer supplied nick name for the account")
  @JsonProperty("nickname")
  String nickname;

  @Schema(
      description = "Open or closed status for the account. If not present then OPEN is assumed")
  @JsonProperty(value = "openStatus", defaultValue = "OPEN")
  @Builder.Default
  BankingAccountStatus openStatus = BankingAccountStatus.OPEN;

  @Schema(
      description = "Flag indicating that the customer associated with the authorisation is an owner of the account. Does not indicate sole ownership, however. If not present then 'true' is assumed")
  @JsonProperty(value = "isOwned", defaultValue = "true")
  @Builder.Default
  Boolean isOwned = true;

  @Schema(
      description = "A masked version of the account. Whether BSB/Account Number, Credit Card PAN or another number",
      required = true)
  @NotEmpty(message = "Must contain a masked number")
  @JsonProperty("maskedNumber")
  String maskedNumber;

  @Schema(description = "The category to which a product or account belongs.", required = true)
  @NotNull
  @JsonProperty("productCategory")
  BankingProductCategory productCategory;

  @Schema(
      description = "The unique identifier of the account as defined by the account holder (akin to model number for the account)",
      required = true)
  @NotEmpty(message = "Must contain a unique product identifier")
  @JsonProperty("productName")
  String productName;

}
