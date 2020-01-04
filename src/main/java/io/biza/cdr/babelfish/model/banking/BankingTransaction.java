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

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.cdr.babelfish.converters.AmountStringToBigDecimalConverter;
import io.biza.cdr.babelfish.converters.BigDecimalToAmountStringConverter;
import io.biza.cdr.babelfish.converters.CurrencyToStringConverter;
import io.biza.cdr.babelfish.converters.OffsetDateTimeToDateTimeStringConverter;
import io.biza.cdr.babelfish.converters.StringToCurrencyConverter;
import io.biza.cdr.babelfish.converters.DateTimeStringToOffsetDateTimeConverter;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.v1.enumerations.BankingTransactionStatus;
import io.biza.cdr.babelfish.v1.enumerations.BankingTransactionType;
import io.biza.cdr.babelfish.v1.enumerations.PayloadTypeBankingPayee;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Valid
@BabelFishModel(description = "Banking Transaction Brief Definition")
public interface BankingTransaction {

  @BabelFishModelProperty(description = "ID of the account for which transactions are provided",
      required = true)
  @JsonGetter("accountId")
  public String getAccountId();

  @JsonSetter("accountId")
  public void setAccountId(@NotNull String accountId);

  public default BankingTransaction accountId(@NotNull String accountId) {
    setAccountId(accountId);
    return this;
  }

  @BabelFishModelProperty(
      description = "A unique ID of the transaction adhering to the standards for ID permanence. ")
  @JsonGetter("transactionId")
  public String getTransactionId();

  @JsonSetter("transactionId")
  public void setTransactionId(@NotNull String transactionId);

  public default BankingTransaction transactionId(@NotNull String transactionId) {
    setTransactionId(transactionId);
    return this;
  }

  @BabelFishModelProperty(
      description = "True if extended information is available using the transaction detail end point. False if extended data is not available",
      required = true)
  @JsonGetter("isDetailAvailable")
  public Boolean getIsDetailAvailable();

  @JsonSetter("isDetailAvailable")
  public void setIsDetailAvailable(@NotNull Boolean isDetailAvailable);

  public default BankingTransaction isDetailAvailable(@NotNull Boolean isDetailAvailable) {
    setIsDetailAvailable(isDetailAvailable);
    return this;
  }

  @BabelFishModelProperty(description = "The type of the transaction", required = true)
  @JsonGetter("type")
  public BankingTransactionType getType();

  @JsonSetter("type")
  public void setType(@NotNull BankingTransactionType type);

  public default BankingTransaction type(BankingTransactionType type) {
    setType(type);
    return this;
  }

  @BabelFishModelProperty(
      description = "Status of the transaction whether pending or posted. Note that there is currently no provision in the standards to guarantee the ability to correlate a pending transaction with an associated posted transaction",
      required = true)
  @JsonGetter("status")
  public BankingTransactionStatus getStatus();

  @JsonSetter("status")
  public void setStatus(@NotNull BankingTransactionStatus status);

  public default BankingTransaction status(BankingTransactionStatus status) {
    setStatus(status);
    return this;
  }

  @BabelFishModelProperty(
      description = "The transaction description as applied by the financial institution",
      required = true)
  @JsonGetter("description")
  public String getDescription();

  @JsonSetter("description")
  public void setDescription(@NotNull String description);

  public default BankingTransaction description(@NotNull String description) {
    setDescription(description);
    return this;
  }

  @BabelFishModelProperty(
      description = "The time the transaction was posted. This field is Mandatory if the transaction has status POSTED.  This is the time that appears on a standard statement")
  @JsonSerialize(converter = OffsetDateTimeToDateTimeStringConverter.class)
  @JsonGetter("postingDateTime")
  public LocalDateTime getPostingDateTime();

  @JsonDeserialize(converter = DateTimeStringToOffsetDateTimeConverter.class)
  @JsonSetter("postingDateTime")
  public void setPostingDateTime(LocalDateTime postingDateTime);

  public default BankingTransaction postingDateTime(LocalDateTime postingDateTime) {
    setPostingDateTime(postingDateTime);
    return this;
  }

  @BabelFishModelProperty(
      description = "Date and time at which assets become available to the account owner in case of a credit entry, or cease to be available to the account owner in case of a debit transaction entry")
  @JsonSerialize(converter = OffsetDateTimeToDateTimeStringConverter.class)
  @JsonGetter("valueDateTime")
  public LocalDateTime getValueDateTime();

  @JsonDeserialize(converter = DateTimeStringToOffsetDateTimeConverter.class)
  @JsonSetter("valueDateTime")
  public void setValueDateTime(LocalDateTime valueDateTime);

  public default BankingTransaction valueDateTime(LocalDateTime valueDateTime) {
    setValueDateTime(valueDateTime);
    return this;
  }

  @BabelFishModelProperty(
      description = "The time the transaction was executed by the originating customer, if available")
  @JsonSerialize(converter = OffsetDateTimeToDateTimeStringConverter.class)
  @JsonGetter("executionDateTime")
  public LocalDateTime getExecutionDateTime();

  @JsonDeserialize(converter = DateTimeStringToOffsetDateTimeConverter.class)
  @JsonSetter("executionDateTime")
  public void setExecutionDateTime(LocalDateTime executionDateTime);

  public default BankingTransaction executionDateTime(LocalDateTime executionDateTime) {
    setExecutionDateTime(executionDateTime);
    return this;
  }

  @BabelFishModelProperty(
      description = "The value of the transaction. Negative values mean money was outgoing from the account",
      required = true, dataType = "java.lang.String")
  @JsonSerialize(converter = BigDecimalToAmountStringConverter.class)
  @JsonGetter("amount")
  public BigDecimal getAmount();

  @JsonDeserialize(converter = AmountStringToBigDecimalConverter.class)
  @JsonSetter("amount")
  public void setAmount(BigDecimal amount);

  public default BankingTransaction amount(BigDecimal amount) {
    setAmount(amount);
    return this;
  }

  @BabelFishModelProperty(
      description = "The currency for the transaction amount. AUD assumed if not present",
      dataType = "java.lang.String")
  @JsonSerialize(converter = BigDecimalToAmountStringConverter.class)
  @JsonGetter("currency")
  public Currency getCurrency();

  @JsonDeserialize(converter = AmountStringToBigDecimalConverter.class)
  @JsonSetter("currency")
  public void setCurrency(Currency currency);

  public default BankingTransaction currency(Currency currency) {
    setCurrency(currency);
    return this;
  }

  @BabelFishModelProperty(
      description = "The reference for the transaction provided by the originating institution. Empty string if no data provided",
      required = true)
  @JsonGetter("reference")
  public String getReference();

  @JsonSetter("reference")
  public void setReference(@NotNull String reference);

  public default BankingTransaction reference(@NotNull String reference) {
    setReference(reference);
    return this;
  }

  @BabelFishModelProperty(
      description = "Name of the merchant for an outgoing payment to a merchant")
  @JsonGetter("merchantName")
  public String getMerchantName();

  @JsonSetter("merchantName")
  public void setMerchantName(String merchantName);

  public default BankingTransaction merchantName(String merchantName) {
    setMerchantName(merchantName);
    return this;
  }

  @BabelFishModelProperty(
      description = "The merchant category code (or MCC) for an outgoing payment to a merchant")
  @JsonGetter("merchantCategoryCode")
  public String getMerchantCategoryCode();

  @JsonSetter("merchantCategoryCode")
  public void setMerchantCategoryCode(String merchantCategoryCode);

  public default BankingTransaction merchantCategoryCode(String merchantCategoryCode) {
    setMerchantCategoryCode(merchantCategoryCode);
    return this;
  }

  @BabelFishModelProperty(description = "BPAY Biller Code for the transaction (if available)")
  @JsonGetter("billerCode")
  public String getBillerCode();

  @JsonSetter("billerCode")
  public void setBillerCode(@NotNull String billerCode);

  public default BankingTransaction billerCode(@NotNull String billerCode) {
    setBillerCode(billerCode);
    return this;
  }

  @BabelFishModelProperty(
      description = "Name of the BPAY biller for the transaction (if available)")
  @JsonGetter("billerName")
  public String getBillerName();

  @JsonSetter("billerName")
  public void setBillerName(@NotNull String billerName);

  public default BankingTransaction billerName(@NotNull String billerName) {
    setBillerName(billerName);
    return this;
  }

  @BabelFishModelProperty(description = "BPAY CRN for the transaction (if available)")
  @JsonGetter("crn")
  public String getCrn();

  @JsonSetter("crn")
  public void setCrn(@NotNull String crn);

  public default BankingTransaction crn(@NotNull String crn) {
    setCrn(crn);
    return this;
  }

  @BabelFishModelProperty(description = "6 Digit APCA number for the initiating institution")
  @JsonGetter("apcaNumber")
  public String getApcaNumber();

  @JsonSetter("apcaNumber")
  public void setApcaNumber(String apcaNumber);

  public default BankingTransaction apcaNumber(String apcaNumber) {
    setApcaNumber(apcaNumber);
    return this;
  }
}
