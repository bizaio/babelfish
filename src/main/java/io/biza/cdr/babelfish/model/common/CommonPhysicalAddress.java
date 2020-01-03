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

import java.net.URI;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.v1.enumerations.PayloadTypeAddress;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

@BabelFishModel(description =  "Physical Address Detail")
public interface CommonPhysicalAddress {

    @BabelFishModelProperty(
        description =  "The type of address object present",
        required = true
    )
    @JsonGetter("addressUType")
    public PayloadTypeAddress getAddressType();
    @JsonSetter("addressUType")
    public void setAddressUType(PayloadTypeAddress addressUType);
    public default CommonPhysicalAddress addressUType(PayloadTypeAddress addressUType) {
      setAddressUType(addressUType);
      return this;
    }
    

    @BabelFishModelProperty(description = "Address in Simple Address format")
    @JsonGetter("simple")
    public CommonSimpleAddress getSimple();
    @JsonSetter("simple")
    public void setSimple(CommonSimpleAddress simple);
    public default CommonPhysicalAddress simple(CommonSimpleAddress simple) {
      setSimple(simple);
      return this;
    }
    
    @BabelFishModelProperty(description = "Address in PAF Format")
    @JsonGetter("paf")
    public CommonPAFAddress getPaf();
    @JsonSetter("paf")
    public void setPaf(CommonPAFAddress paf);
    public default CommonPhysicalAddress paf(CommonPAFAddress paf) {
      setPaf(paf);
      return this;
    }
    
}
