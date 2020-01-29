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
package io.biza.babelfish.cdr.abstracts.payloads.banking.account.payee.scheduled;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.biza.babelfish.cdr.enumerations.BankingScheduledPaymentStatus;
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
@Schema(description = "Describes a Scheduled Payment")
public abstract class BankingScheduledPayment<T> {
  @Schema(
      description = "A unique ID of the scheduled payment adhering to the standards for ID permanence",
      required = true)
  @NotNull
  @JsonProperty("scheduledPaymentId")
  String scheduledPaymentId;

  public String scheduledPaymentId() {
    return getScheduledPaymentId();
  }

  @SuppressWarnings("unchecked")
  public T scheduledPaymentId(String scheduledPaymentId) {
    setScheduledPaymentId(scheduledPaymentId);
    return (T) this;
  }

  @Schema(description = "The short display name of the payee as provided by the customer")
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
      description = "The reference for the transaction that will be used by the originating institution for the purposes of constructing a statement narrative on the payer’s account. Empty string if no data provided",
      required = true)
  @NotNull
  @JsonProperty("payerReference")
  String payerReference;

  public String payerReference() {
    return getPayerReference();
  }

  @SuppressWarnings("unchecked")
  public T payerReference(String payerReference) {
    setPayerReference(payerReference);
    return (T) this;
  }

  @Schema(
      description = "The reference for the transaction that will be provided by the originating institution. Empty string if no data provided",
      required = true)
  @NotNull
  @JsonProperty("payeeReference")
  String payeeReference;

  public String payeeReference() {
    return getPayeeReference();
  }

  @SuppressWarnings("unchecked")
  public T payeeReference(String payeeReference) {
    setPayeeReference(payeeReference);
    return (T) this;
  }

  @Schema(
      description = "Indicates whether the schedule is currently active. The value SKIP is equivalent to ACTIVE except that the customer has requested the next normal occurrence to be skipped.",
      required = true)
  @NotNull
  @JsonProperty("status")
  BankingScheduledPaymentStatus status;

  public BankingScheduledPaymentStatus status() {
    return getStatus();
  }

  @SuppressWarnings("unchecked")
  public T status(BankingScheduledPaymentStatus status) {
    setStatus(status);
    return (T) this;
  }

  @Schema(required = true)
  @NotNull
  @JsonProperty("from")
  @Valid
  BankingScheduledPaymentFrom<?> from;

  public BankingScheduledPaymentFrom<?> from() {
    return getFrom();
  }

  @SuppressWarnings("unchecked")
  public T from(BankingScheduledPaymentFrom<?> from) {
    setFrom(from);
    return (T) this;
  }

  @Schema(required = true)
  @NotNull
  @JsonProperty("paymentSet")
  @Valid
  List<BankingScheduledPaymentSet<?>> paymentSet;

  public List<BankingScheduledPaymentSet<?>> paymentSet() {
    return getPaymentSet();
  }

  @SuppressWarnings("unchecked")
  public T paymentSet(List<BankingScheduledPaymentSet<?>> paymentSet) {
    setPaymentSet(paymentSet);
    return (T) this;
  }

  @Schema(required = true)
  @NotNull
  @JsonProperty("recurrence")
  @Valid
  BankingScheduledPaymentRecurrence<?> recurrence;

  public BankingScheduledPaymentRecurrence<?> recurrence() {
    return getRecurrence();
  }

  @SuppressWarnings("unchecked")
  public T recurrence(BankingScheduledPaymentRecurrence<?> recurrence) {
    setRecurrence(recurrence);
    return (T) this;
  }
}
