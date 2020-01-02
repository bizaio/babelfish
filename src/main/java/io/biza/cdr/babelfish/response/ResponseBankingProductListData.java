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
package io.biza.cdr.babelfish.response;

import java.util.List;

import javax.validation.Valid;

import io.biza.cdr.babelfish.model.banking.BankingProduct;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Data
@Valid
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
public class ResponseBankingProductListData {

    @BabelFishModelProperty(
        description =  "The list of products returned.  If the filter results in an empty set then this array may have no records",
        required = true
    )
    List<BankingProduct> products;
}
