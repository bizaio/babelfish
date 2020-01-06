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
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.v1.enumerations.BankingAccountStatus;
import io.biza.cdr.babelfish.v1.enumerations.BankingProductCategory;
import io.biza.cdr.babelfish.converters.LocalDateToStringConverter;
import io.biza.cdr.babelfish.converters.StringToLocalDateConverter;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Valid
@BabelFishModel(description = "An Australian Bank Account")
public abstract class BankingAccount<T> {
  @BabelFishModelProperty(
      description = "A unique ID of the account adhering to the standards for ID permanence",
      required = true)
  @NonNull
  @NotNull
  String accountId;

  public String accountId() {
    return getAccountId();
  }

  @SuppressWarnings("unchecked")
  public T accountId(String accountId) {
    setAccountId(accountId);
    return (T) this;
  }

  @BabelFishModelProperty(description = "Date that the account was created (if known)",
      dataType = "java.lang.String")
  @JsonSerialize(converter = LocalDateToStringConverter.class)
  @JsonDeserialize(converter = StringToLocalDateConverter.class)
  private LocalDate creationDate;

  public LocalDate creationDate() {
    return getCreationDate();
  }

  @SuppressWarnings("unchecked")
  public T creationDate(LocalDate creationDate) {
    setCreationDate(creationDate);
    return (T) this;
  }

  @BabelFishModelProperty(
      description = "The display name of the account as defined by the bank. This should not incorporate account numbers or PANs. If it does the values should be masked according to the rules of the MaskedAccountString common type.",
      required = true)
  @NonNull
  @NotNull
  String displayName;

  public String displayName() {
    return getDisplayName();
  }

  @SuppressWarnings("unchecked")
  public T displayName(String displayName) {
    setDisplayName(displayName);
    return (T) this;
  }

  @BabelFishModelProperty(description = "A customer supplied nick name for the account")
  String nickname;

  public String nickname() {
    return getNickname();
  }

  @SuppressWarnings("unchecked")
  public T nickname(String nickname) {
    setNickname(nickname);
    return (T) this;
  }

  @BabelFishModelProperty(
      description = "Open or closed status for the account. If not present then OPEN is assumed")
  BankingAccountStatus openStatus = BankingAccountStatus.OPEN;

  public BankingAccountStatus openStatus() {
    return getOpenStatus();
  }

  @SuppressWarnings("unchecked")
  public T openStatus(BankingAccountStatus openStatus) {
    setOpenStatus(openStatus);
    return (T) this;
  }

  @BabelFishModelProperty(
      description = "Flag indicating that the customer associated with the authorisation is an owner of the account. Does not indicate sole ownership, however. If not present then 'true' is assumed")
  Boolean isOwned = true;

  public Boolean isOwned() {
    return getIsOwned();
  }

  @SuppressWarnings("unchecked")
  public T isOwned(Boolean isOwned) {
    setIsOwned(isOwned);
    return (T) this;
  }

  @BabelFishModelProperty(
      description = "A masked version of the account. Whether BSB/Account Number, Credit Card PAN or another number",
      required = true)
  @NotNull
  @NonNull
  String maskedNumber;

  public String maskedNumber() {
    return getMaskedNumber();
  }

  @SuppressWarnings("unchecked")
  public T maskedNumber(String maskedNumber) {
    setMaskedNumber(maskedNumber);
    return (T) this;
  }

  @BabelFishModelProperty(description = "The category to which a product or account belongs.",
      required = true)
  @NotNull
  @NonNull
  BankingProductCategory productCategory;

  public BankingProductCategory productCategory() {
    return getProductCategory();
  }

  @SuppressWarnings("unchecked")
  public T productCategory(BankingProductCategory productCategory) {
    setProductCategory(productCategory);
    return (T) this;
  }

  @BabelFishModelProperty(
      description = "The unique identifier of the account as defined by the account holder (akin to model number for the account)",
      required = true)
  @NotNull
  @NonNull
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
