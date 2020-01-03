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
package io.biza.cdr.babelfish.v1.model.banking;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
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
@BabelFishModel(description =  "Banking Transaction Detail X2P101 Payload")
public class BankingTransactionDetailExtendedDataX2p101Payload {

    @BabelFishModelProperty(
        description =  "An extended string description. Only present if specified by the extensionUType field",
        required = true
    )
    @NonNull
    @NotNull
    String extendedDescription;

    @BabelFishModelProperty(
        description =  "An end to end ID for the payment created at initiation"
    )
    String endToEndId;

    @BabelFishModelProperty(
        description =  "Purpose of the payment.  Format is defined by NPP standards for the x2p1.01 overlay service"
    )
    String purposeCode;
}
