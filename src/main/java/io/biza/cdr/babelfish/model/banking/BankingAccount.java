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
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.v1.enumerations.BankingAccountStatus;
import io.biza.cdr.babelfish.v1.enumerations.BankingProductCategory;
import io.biza.cdr.babelfish.converters.LocalDateToStringConverter;
import io.biza.cdr.babelfish.converters.StringToLocalDateConverter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Valid
@BabelFishModel(description = "An Australian Bank Account")
public interface BankingAccount {

  @BabelFishModelProperty(
      description = "A unique ID of the account adhering to the standards for ID permanence",
      required = true)
  @JsonGetter("accountId")
  public String getAccountId();

  @JsonSetter("accountId")
  public void setAccountId(@NotNull String accountId);

  public default BankingAccount accountId(@NotNull String accountId) {
    setAccountId(accountId);
    return this;
  }

  @BabelFishModelProperty(description = "Date that the account was created (if known)",
      dataType = "java.lang.String")
  @JsonSerialize(converter = LocalDateToStringConverter.class)
  @JsonGetter("creationDate")
  public LocalDate getCreationDate();

  @JsonSetter("creationDate")
  @JsonDeserialize(converter = StringToLocalDateConverter.class)
  public void setCreationDate(LocalDate creationDate);

  public default BankingAccount creationDate(LocalDate creationDate) {
    setCreationDate(creationDate);
    return this;
  }

  @BabelFishModelProperty(
      description = "The display name of the account as defined by the bank. This should not incorporate account numbers or PANs. If it does the values should be masked according to the rules of the MaskedAccountString common type.",
      required = true)
  @JsonGetter("displayName")
  public String getDisplayName();

  @JsonSetter("displayName")
  public void setDisplayName(@NotNull String displayName);

  public default BankingAccount displayName(@NotNull String displayName) {
    setDisplayName(displayName);
    return this;
  }

  @BabelFishModelProperty(description = "A customer supplied nick name for the account")
  @JsonGetter("nickname")
  public String getNickname();

  @JsonSetter("nickname")
  public void setNickname(String nickname);

  public default BankingAccount nickname(String nickname) {
    setNickname(nickname);
    return this;
  }

  @BabelFishModelProperty(
      description = "Open or closed status for the account. If not present then OPEN is assumed")
  @JsonGetter("openStatus")
  public BankingAccountStatus getAccountStatus();

  @JsonSetter("openStatus")
  public void setAccountStatus(BankingAccountStatus bankingAccountStatus);

  public default BankingAccount accountStatus(BankingAccountStatus bankingAccountStatus) {
    setAccountStatus(bankingAccountStatus);
    return this;
  }

  @BabelFishModelProperty(
      description = "Flag indicating that the customer associated with the authorisation is an owner of the account. Does not indicate sole ownership, however. If not present then 'true' is assumed")
  @JsonGetter("isOwned")
  public Boolean isOwned();

  @JsonSetter("isOwned")
  public void setIsOwned(Boolean isOwned);

  public default BankingAccount isOwned(Boolean isOwned) {
    setIsOwned(isOwned);
    return this;
  }

  @BabelFishModelProperty(
      description = "A masked version of the account. Whether BSB/Account Number, Credit Card PAN or another number",
      required = true)
  @JsonGetter("maskedNumber")
  public String getMaskedNumber();

  @JsonSetter("maskedNumber")
  public void setMaskedNumber(@NotNull String maskedNumber);

  public default BankingAccount maskedNumber(@NotNull String maskedNumber) {
    setMaskedNumber(maskedNumber);
    return this;
  }

  @BabelFishModelProperty(description = "The category to which a product or account belongs.",
      required = true)
  @JsonGetter("productCategory")
  public BankingProductCategory getProductCategory();

  @JsonSetter("productCategory")
  public void setProductCategory(@NotNull BankingProductCategory productCategory);

  public default BankingAccount productCategory(@NotNull BankingProductCategory productCategory) {
    setProductCategory(productCategory);
    return this;
  }

  @BabelFishModelProperty(
      description = "The unique identifier of the account as defined by the account holder (akin to model number for the account)",
      required = true)
  @JsonGetter("productName")
  public String getProductName();

  @JsonSetter("productName")
  public void setProductName(@NotNull String productName);

  public default BankingAccount productName(@NotNull String productName) {
    setProductName(productName);
    return this;
  }
}
