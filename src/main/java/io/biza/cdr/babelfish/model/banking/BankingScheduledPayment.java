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

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.v1.enumerations.BankingScheduledPaymentStatus;
import io.biza.cdr.babelfish.support.BabelFishModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Valid
@BabelFishModel(description = "Describes a Scheduled Payment")
public interface BankingScheduledPayment {

  @BabelFishModelProperty(
      description = "A unique ID of the scheduled payment adhering to the standards for ID permanence",
      required = true)
  @JsonGetter("scheduledPaymentId")
  public String getId();

  @JsonSetter("scheduledPaymentId")
  public void setId(@NotNull String scheduledPaymentId);

  public default BankingScheduledPayment id(@NotNull String scheduledPaymentId) {
    setId(scheduledPaymentId);
    return this;
  }

  @BabelFishModelProperty(
      description = "The short display name of the payee as provided by the customer")
  @JsonGetter("nickname")
  public String getNickname();

  @JsonSetter("nickname")
  public void setNickname(String nickname);

  public default BankingScheduledPayment nickname(String nickname) {
    setNickname(nickname);
    return this;
  }

  @BabelFishModelProperty(
      description = "The reference for the transaction that will be used by the originating institution for the purposes of constructing a statement narrative on the payer’s account. Empty string if no data provided",
      required = true)
  @JsonGetter("payerReference")
  public String getPayerReference();

  @JsonSetter("payerReference")
  public void setPayerReference(@NotNull String payerReference);

  public default BankingScheduledPayment payerReference(@NotNull String payerReference) {
    setPayerReference(payerReference);
    return this;
  }

  @BabelFishModelProperty(
      description = "The reference for the transaction that will be provided by the originating institution. Empty string if no data provided",
      required = true)
  @JsonGetter("payeeReference")
  public String getPayeeReference();

  @JsonSetter("payeeReference")
  public void setPayeeReference(@NotNull String payeeReference);

  public default BankingScheduledPayment payeeReference(@NotNull String payeeReference) {
    setPayeeReference(payeeReference);
    return this;
  }

  @BabelFishModelProperty(
      description = "Indicates whether the schedule is currently active. The value SKIP is equivalent to ACTIVE except that the customer has requested the next normal occurrence to be skipped.",
      required = true)
  @JsonGetter("status")
  public BankingScheduledPaymentStatus getStatus();

  @JsonSetter("status")
  public void setStatus(@NotNull BankingScheduledPaymentStatus status);

  public default BankingScheduledPayment status(@NotNull BankingScheduledPaymentStatus status) {
    setStatus(status);
    return this;
  }

  @BabelFishModelProperty(required = true)
  @JsonGetter("from")
  public BankingScheduledPaymentFrom getFrom();

  @JsonSetter("from")
  public void setFrom(@NotNull BankingScheduledPaymentFrom from);

  public default BankingScheduledPayment from(@NotNull BankingScheduledPaymentFrom from) {
    setFrom(from);
    return this;
  }

  @BabelFishModelProperty(required = true)
  @JsonGetter("paymentSet")
  public BankingScheduledPaymentFrom getPaymentSet();

  @JsonSetter("paymentSet")
  public void setPaymentSet(@NotNull List<BankingScheduledPaymentSet> paymentSet);

  public default BankingScheduledPayment paymentSet(
      @NotNull List<BankingScheduledPaymentSet> paymentSet) {
    setPaymentSet(paymentSet);
    return this;
  }

  @BabelFishModelProperty(required = true)
  @JsonGetter("recurrence")
  public BankingScheduledPaymentRecurrence getRecurrence();

  @JsonSetter("recurrence")
  public void setRecurrence(@NotNull BankingScheduledPaymentRecurrence recurrence);

  public default BankingScheduledPayment recurrence(
      @NotNull BankingScheduledPaymentRecurrence recurrence) {
    setRecurrence(recurrence);
    return this;
  }
}
