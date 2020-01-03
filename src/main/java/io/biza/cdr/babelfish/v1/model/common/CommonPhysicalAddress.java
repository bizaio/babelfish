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
package io.biza.cdr.babelfish.v1.model.common;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.v1.enumerations.PayloadTypeAddress;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Valid
public class CommonPhysicalAddress extends io.biza.cdr.babelfish.model.common.CommonPhysicalAddress {

    public CommonPhysicalAddress(@NonNull PayloadTypeAddress addressUType, CommonSimpleAddress simple, CommonPAFAddress paf) {
    super(addressUType);
    setSimple(simple);
    setPaf(paf);
  }
    
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
