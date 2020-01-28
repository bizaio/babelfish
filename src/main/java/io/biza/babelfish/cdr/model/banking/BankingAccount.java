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
import io.biza.babelfish.enumerations.cdr.BankingAccountStatus;
import io.biza.babelfish.enumerations.cdr.BankingProductCategory;
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
@Schema(description = "An Australian Bank Account")
public abstract class BankingAccount<T> {
  @Schema(description = "A unique ID of the account adhering to the standards for ID permanence",
      required = true)
  @NotNull
  @JsonProperty("accountId")
  String accountId;

  public String accountId() {
    return getAccountId();
  }

  @SuppressWarnings("unchecked")
  public T accountId(String accountId) {
    setAccountId(accountId);
    return (T) this;
  }

  @Schema(description = "Date that the account was created (if known)", type = "string",
      format = "date")
  @JsonSerialize(converter = LocalDateToStringConverter.class)
  @JsonDeserialize(converter = StringToLocalDateConverter.class)
  @JsonProperty("creationDate")
  LocalDate creationDate;

  public LocalDate creationDate() {
    return getCreationDate();
  }

  @SuppressWarnings("unchecked")
  public T creationDate(LocalDate creationDate) {
    setCreationDate(creationDate);
    return (T) this;
  }

  @Schema(
      description = "The display name of the account as defined by the bank. This should not incorporate account numbers or PANs. If it does the values should be masked according to the rules of the MaskedAccountString common type.",
      required = true)
  @NotNull
  @JsonProperty("displayName")
  String displayName;

  public String displayName() {
    return getDisplayName();
  }

  @SuppressWarnings("unchecked")
  public T displayName(String displayName) {
    setDisplayName(displayName);
    return (T) this;
  }

  @Schema(description = "A customer supplied nick name for the account")
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

  @Schema(
      description = "Open or closed status for the account. If not present then OPEN is assumed")
  @JsonProperty("openStatus")
  BankingAccountStatus openStatus = BankingAccountStatus.OPEN;

  public BankingAccountStatus openStatus() {
    return getOpenStatus();
  }

  @SuppressWarnings("unchecked")
  public T openStatus(BankingAccountStatus openStatus) {
    setOpenStatus(openStatus);
    return (T) this;
  }

  @Schema(
      description = "Flag indicating that the customer associated with the authorisation is an owner of the account. Does not indicate sole ownership, however. If not present then 'true' is assumed")
  @JsonProperty("isOwned")
  Boolean isOwned = true;

  public Boolean isOwned() {
    return getIsOwned();
  }

  @SuppressWarnings("unchecked")
  public T isOwned(Boolean isOwned) {
    setIsOwned(isOwned);
    return (T) this;
  }

  @Schema(
      description = "A masked version of the account. Whether BSB/Account Number, Credit Card PAN or another number",
      required = true)
  @NotNull
  @JsonProperty("maskedNumber")
  String maskedNumber;

  public String maskedNumber() {
    return getMaskedNumber();
  }

  @SuppressWarnings("unchecked")
  public T maskedNumber(String maskedNumber) {
    setMaskedNumber(maskedNumber);
    return (T) this;
  }

  @Schema(description = "The category to which a product or account belongs.", required = true)
  @NotNull
  @JsonProperty("productCategory")
  BankingProductCategory productCategory;

  public BankingProductCategory productCategory() {
    return getProductCategory();
  }

  @SuppressWarnings("unchecked")
  public T productCategory(BankingProductCategory productCategory) {
    setProductCategory(productCategory);
    return (T) this;
  }

  @Schema(
      description = "The unique identifier of the account as defined by the account holder (akin to model number for the account)",
      required = true)
  @NotNull
  @JsonProperty("productName")
  String productName;

  public String productName() {
    return getProductName();
  }

  @SuppressWarnings("unchecked")
  public T productName(String productName) {
    setProductName(productName);
    return (T) this;
  }
}
