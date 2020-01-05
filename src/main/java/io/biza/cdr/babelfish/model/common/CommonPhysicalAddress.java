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
import io.biza.cdr.babelfish.v1.enumerations.PayloadTypeAddress;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Valid
@BabelFishModel(description = "Physical Address Detail")
public abstract class CommonPhysicalAddress<T extends CommonPhysicalAddress<T>> {
  @BabelFishModelProperty(description = "The type of address object present", required = true)
  @JsonProperty("addressUType")
  @NotNull
  @NonNull
  public PayloadTypeAddress addressType;

  public PayloadTypeAddress addressType() {
    return getAddressType();
  }

  @SuppressWarnings("unchecked")
  public T addressType(PayloadTypeAddress addressType) {
    setAddressType(addressType);
    return (T) this;
  }

  @BabelFishModelProperty(description = "Address in Simple Address format")
  @JsonProperty("simple")
  public CommonSimpleAddress<?> simple;

  public CommonSimpleAddress<?> simple() {
    return getSimple();
  }

  @SuppressWarnings("unchecked")
  public T simple(CommonSimpleAddress<?> simple) {
    setSimple(simple);
    return (T) this;
  }

  @BabelFishModelProperty(description = "Address in PAF Format")
  @JsonProperty("paf")
  public CommonPAFAddress<?> paf;

  public CommonPAFAddress<?> paf() {
    return getPaf();
  }

  @SuppressWarnings("unchecked")
  public T paf(CommonPAFAddress<?> paf) {
    setPaf(paf);
    return (T) this;
  }
}
