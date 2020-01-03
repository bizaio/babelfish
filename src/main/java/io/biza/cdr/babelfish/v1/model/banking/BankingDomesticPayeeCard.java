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
import javax.validation.constraints.AssertTrue;
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
@BabelFishModel(description =  "Domestic Payee Card Number Details")
public class BankingDomesticPayeeCard {

    @BabelFishModelProperty(
        description =  "Name of the account to pay to",
        required = true
    )
    @NonNull
    @NotNull
    String cardNumber;
    
    @AssertTrue(message = "Card Number MUST be Masked PAN Format")
    private boolean isPanMasked() {
        if(cardNumber.matches("(\\w{4} ){3}\\w{4}")) {
            if(cardNumber.matches("(x{4} ){3}\\w{4}")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
