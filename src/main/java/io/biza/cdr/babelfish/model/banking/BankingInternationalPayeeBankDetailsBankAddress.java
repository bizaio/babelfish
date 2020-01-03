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
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Valid
@BabelFishModel(description =  "International Payee Bank Address Details")
public interface BankingInternationalPayeeBankDetailsBankAddress {

    @BabelFishModelProperty(
        description =  "Name of the recipient Bank",
        required = true
    )
    @JsonGetter("name")
    public String getName();
    
    @JsonSetter("name")
    public void setName(@NotNull String name);
    
    public default BankingInternationalPayeeBankDetailsBankAddress name(@NotNull String name) {
      setName(name);
      return this;
    }
    
    @BabelFishModelProperty(
        description =  "Address of the recipient Bank",
        required = true
    )
    @JsonGetter("address")
    public String getAddress();
    
    @JsonSetter("address")
    public void setAddress(@NotNull String address);
    
    public default BankingInternationalPayeeBankDetailsBankAddress address(@NotNull String address) {
      setAddress(address);
      return this;
    }
}
