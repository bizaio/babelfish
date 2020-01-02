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

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import io.biza.cdr.babelfish.enumerations.BankingTransactionService;
import io.biza.cdr.babelfish.enumerations.PayloadTypeTransactionExtension;
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
@BabelFishModel(description =  "Banking Transaction Detailed Extended Data")
public class BankingTransactionDetailExtendedData {

    @BabelFishModelProperty(
        description =  "Label of the originating payer. Mandatory for inbound payment"
    )
    String payer;

    @BabelFishModelProperty(
        description =  "Label of the target PayID.  Mandatory for an outbound payment. The name assigned to the BSB/Account Number or PayID (by the owner of the PayID)"
    )
    String payee;

    @BabelFishModelProperty(
        description =  "Optional extended data provided specific to transaction originated via NPP"
    )
    PayloadTypeTransactionExtension extensionUType;

    @BabelFishModelProperty
    BankingTransactionDetailExtendedDataX2p101Payload x2p101Payload;

    @BabelFishModelProperty(
        description =  "Identifier of the applicable overlay service.",
        required = true
    )
    @NonNull
    @NotNull
    BankingTransactionService service;
}
