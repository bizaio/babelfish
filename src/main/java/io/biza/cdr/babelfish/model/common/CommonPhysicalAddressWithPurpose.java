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
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.v1.enumerations.AddressPurpose;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

@Valid
@BabelFishModel(description =  "Physical Address with Purpose", parent = CommonPhysicalAddress.class)
public interface CommonPhysicalAddressWithPurpose {
	
	@JsonUnwrapped
	@BabelFishModelProperty(
			hidden = true
	)
	public CommonPhysicalAddress getPhysicalAddress();
	public void setPhysicalAddress(@NotNull CommonPhysicalAddress physicalAddress);
	public default CommonPhysicalAddressWithPurpose physicalAddress(@NotNull CommonPhysicalAddress physicalAddress) {
	  setPhysicalAddress(physicalAddress);
	  return this;
	}

    @BabelFishModelProperty(
        description =  "Enumeration of values indicating the purpose of the physical address",
        required = true
    )
    @JsonGetter("purpose")
    public AddressPurpose getPurpose();
    @JsonSetter("purpose")
    public void setPurpose(@NotNull AddressPurpose purpose);
    public default CommonPhysicalAddressWithPurpose purpose(@NotNull AddressPurpose purpose) {
      setPurpose(purpose);
      return this;
    }
}
