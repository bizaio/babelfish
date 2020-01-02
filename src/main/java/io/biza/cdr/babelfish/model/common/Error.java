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
@BabelFishModel(description =  "Error Information")
public class Error {

    @BabelFishModelProperty(
        description =  "Must be one of the following: 0001 – Account not able to be found",
        required = true
    )
    @NonNull
    @NotNull
    String code;

    @BabelFishModelProperty(
        description =  "Must be one of the following: Invalid account",
        required = true
    )
    @NonNull
    @NotNull
    String title;

    @BabelFishModelProperty(
        description =  "ID of the account not found",
        required = true
    )
    @NonNull
    @NotNull
    String detail;

    @BabelFishModelProperty(
        description =  "Optional additional data for specific error types"
    )
    Object meta;
}
