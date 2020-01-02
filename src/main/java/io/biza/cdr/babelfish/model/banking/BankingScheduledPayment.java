/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *******************************************************************************/
package io.biza.cdr.babelfish.model.banking;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.enumerations.BankingScheduledPaymentStatus;
import io.biza.cdr.babelfish.support.BabelFishModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@Builder
@Data
@Valid
@BabelFishModel(description =  "Describes a Scheduled Payment")
public class BankingScheduledPayment {

    @BabelFishModelProperty(
        description =  "A unique ID of the scheduled payment adhering to the standards for ID permanence",
        required = true
    )
    @NonNull
    @NotNull
    String scheduledPaymentId;

    @BabelFishModelProperty(
        description =  "The short display name of the payee as provided by the customer"
    )
    String nickname;

    @BabelFishModelProperty(
        description =  "The reference for the transaction that will be used by the originating institution for the purposes of constructing a statement narrative on the payer’s account. Empty string if no data provided",
        required = true
    )
    @NonNull
    @NotNull
    String payerReference;

    @BabelFishModelProperty(
        description =  "The reference for the transaction that will be provided by the originating institution. Empty string if no data provided",
        required = true
    )
    @NonNull
    @NotNull
    String payeeReference;

    @BabelFishModelProperty(
        description =  "Indicates whether the schedule is currently active. The value SKIP is equivalent to ACTIVE except that the customer has requested the next normal occurrence to be skipped.",
        required = true
    )
    @NonNull
    @NotNull
    BankingScheduledPaymentStatus status;

    @BabelFishModelProperty(
        required = true
    )
    @NonNull
    @NotNull
    BankingScheduledPaymentFrom from;

    @BabelFishModelProperty(
        required = true
    )
    @NonNull
    @NotNull
    List<BankingScheduledPaymentSet> paymentSet;

    @BabelFishModelProperty(
        required = true
    )
    @NonNull
    @NotNull
    BankingScheduledPaymentRecurrence recurrence;
}
