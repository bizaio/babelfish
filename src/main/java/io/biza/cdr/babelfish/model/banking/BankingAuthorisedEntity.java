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

import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@Builder
@Data
@Valid
@BabelFishModel(description =  "Authorised Entity details")
public class BankingAuthorisedEntity {

    @BabelFishModelProperty(
        description =  "Description of the authorised entity derived from previously executed direct debits"
    )
    String description;

    @BabelFishModelProperty(
        description =  "Name of the financial institution through which the direct debit will be executed. Is required unless the payment is made via a credit card scheme"
    )
    String financialInstitution;

    @BabelFishModelProperty(
        description =  "Australian Business Number for the authorised entity"
    )
    String abn;

    @BabelFishModelProperty(
        description =  "Australian Company Number for the authorised entity"
    )
    String acn;

    @BabelFishModelProperty(
        description =  "Australian Registered Body Number for the authorised entity"
    )
    String arbn;
}
