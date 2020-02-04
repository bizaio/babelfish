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
package io.biza.babelfish.cdr.models.payloads.banking.account.payee.scheduled;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.biza.babelfish.cdr.enumerations.BankingScheduledPaymentStatus;
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
@Schema(description = "Describes a Scheduled Payment", name = "BankingScheduledPaymentV1")
public class BankingScheduledPaymentV1 {
  @Schema(
      description = "A unique ID of the scheduled payment adhering to the standards for ID permanence",
      required = true)
  @NotNull
  @JsonProperty("scheduledPaymentId")
  String scheduledPaymentId;

  @Schema(description = "The short display name of the payee as provided by the customer")
  @JsonProperty("nickname")
  String nickname;

  @Schema(
      description = "The reference for the transaction that will be used by the originating institution for the purposes of constructing a statement narrative on the payer’s account. Empty string if no data provided",
      required = true)
  @NotNull
  @JsonProperty("payerReference")
  String payerReference;

  @Schema(
      description = "The reference for the transaction that will be provided by the originating institution. Empty string if no data provided",
      required = true)
  @NotNull
  @JsonProperty("payeeReference")
  String payeeReference;

  @Schema(
      description = "Indicates whether the schedule is currently active. The value SKIP is equivalent to ACTIVE except that the customer has requested the next normal occurrence to be skipped.",
      required = true)
  @NotNull
  @JsonProperty("status")
  BankingScheduledPaymentStatus status;

  @Schema(required = true)
  @NotNull
  @JsonProperty("from")
  @Valid
  BankingScheduledPaymentFromV1 from;

  @Schema(required = true)
  @NotNull
  @JsonProperty("paymentSet")
  @Valid
  List<BankingScheduledPaymentSetV1> paymentSet;

  @Schema(required = true)
  @NotNull
  @JsonProperty("recurrence")
  @Valid
  BankingScheduledPaymentRecurrenceV1 recurrence;
}
