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
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.v1.enumerations.PayloadTypeBankingDomesticPayeePayId;
import io.biza.cdr.babelfish.support.BabelFishModel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
@Valid
@BabelFishModel(description =  "Domestic Payee PayID Detail")
public abstract class BankingDomesticPayeePayId {

    @BabelFishModelProperty(
        description =  "The name assigned to the PayID by the owner of the PayID"
    )
    String name;

    @BabelFishModelProperty(
        description =  "The identifier of the PayID (dependent on type)",
        required = true
    )
    @NonNull
    @NotNull
    String identifier;

    @BabelFishModelProperty(
        description =  "The type of the PayID",
        required = true
    )
    @NonNull
    @NotNull
    PayloadTypeBankingDomesticPayeePayId type;
}
