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
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
@Valid
@BabelFishModel(description =  "Domestic Payment Payee Details")
public abstract class BankingDomesticPayeeAccount {

    @BabelFishModelProperty(
        description =  "Name of the account to pay to",
        required = true
    )
    @NonNull
    @NotNull
    String accountName;

    @BabelFishModelProperty(
        description =  "BSB of the account to pay to",
        required = true
    )
    @NonNull
    @NotNull
    String bsb;

    @BabelFishModelProperty(
        description =  "Number of the account to pay to",
        required = true
    )
    @NonNull
    @NotNull
    String accountNumber;
}
