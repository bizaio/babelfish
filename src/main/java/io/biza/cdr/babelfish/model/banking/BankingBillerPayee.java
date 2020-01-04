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
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.support.BabelFishModel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
@Valid
@BabelFishModel(description =  "Representation of a BPAY Payee")
public abstract class BankingBillerPayee {

    @BabelFishModelProperty(
        description =  "BPAY Biller Code of the Biller",
        required = true
    )
    @NotNull
    @NonNull
    String billerCode;

    @BabelFishModelProperty(
        description =  "BPAY CRN of the Biller. If the contents of the CRN match the format of a Credit Card PAN then it should be masked using the rules applicable for the MaskedPANString common type"
    )
    @NotNull
    @NonNull
    String crn;

    @BabelFishModelProperty(
        description =  "Name of the Biller",
        required = true
    )
    @NotNull
    @NonNull
    String billerName;
    
    @AssertTrue(message = "BPAY CRN of Card Format MUST be Masked")
    private boolean isCrnMasked() {
        if(crn.matches("(\\w{4} ){3}\\w{4}")) {
            if(crn.matches("(x{4} ){3}\\w{4}")) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }
}
