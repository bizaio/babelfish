/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *******************************************************************************/
package io.biza.cdr.babelfish.model.common;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.v1.enumerations.AddressPurpose;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Valid
@BabelFishModel(description = "Physical Address with Purpose", parent = CommonPhysicalAddress.class)
public abstract class CommonPhysicalAddressWithPurpose<T>
    extends CommonPhysicalAddress<T> {
  @BabelFishModelProperty(
      description = "Enumeration of values indicating the purpose of the physical address",
      required = true)
  @JsonProperty("purpose")
  @NotNull
  @NonNull
  public AddressPurpose purpose;

  public AddressPurpose purpose() {
    return getPurpose();
  }

  @SuppressWarnings("unchecked")
  public T purpose(AddressPurpose purpose) {
    setPurpose(purpose);
    return (T) this;
  }
}
