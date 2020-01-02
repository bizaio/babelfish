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
package io.biza.cdr.babelfish.model.common;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import io.biza.cdr.babelfish.enumerations.PayloadTypeAddress;
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
@BabelFishModel(description =  "Physical Address Detail")
public class CommonPhysicalAddress {

    @BabelFishModelProperty(
        description =  "The type of address object present",
        required = true
    )
    @NonNull
    @NotNull
    private PayloadTypeAddress addressUType;

    private CommonSimpleAddress simple;
    private CommonPAFAddress paf;
    
    @AssertTrue(
            message = "One and only one of simple or paf should be populated based on addressUType")
    private boolean isUTypePopulated() {
        if (addressUType.equals(PayloadTypeAddress.SIMPLE)) {
            return simple != null && paf == null;
        } else if (addressUType.equals(PayloadTypeAddress.PAF)) {
            return paf != null && simple == null;
        }

        return false;
    }
}
