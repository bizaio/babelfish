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
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.v1.enumerations.PayloadTypeBankingDomesticPayeePayId;
import io.biza.cdr.babelfish.support.BabelFishModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Valid
@BabelFishModel(description =  "Domestic Payee PayID Detail")
public interface BankingDomesticPayeePayId {

    @BabelFishModelProperty(
        description =  "The name assigned to the PayID by the owner of the PayID"
    )
    @JsonGetter("name")
    public String getName();
    
    @JsonSetter("name")
    public void setName(String name);
    
    public default BankingDomesticPayeePayId name(String name) {
      setName(name);
      return this;
    }
    
    @BabelFishModelProperty(
        description =  "The identifier of the PayID (dependent on type)",
        required = true
    )
    @JsonGetter("identifier")
    public String getIdentifier();
    
    @JsonSetter("identifier")
    public void setIdentifier(@NotNull String identifier);
    
    public default BankingDomesticPayeePayId identifier(@NotNull String identifier) {
      setIdentifier(identifier);
      return this;
    }

    @BabelFishModelProperty(
        description =  "The type of the PayID",
        required = true
    )
    @JsonGetter("type")
    public PayloadTypeBankingDomesticPayeePayId getType();
    
    @JsonSetter("type")
    public void setType(@NotNull PayloadTypeBankingDomesticPayeePayId type);
    
    public default BankingDomesticPayeePayId type(@NotNull PayloadTypeBankingDomesticPayeePayId type) {
      setType(type);
      return this;
    }
}
