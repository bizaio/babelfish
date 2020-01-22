/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU Lesser General Public License as published by the Free Software Foundation, either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *******************************************************************************/
package io.biza.babelfish.cdr.model.common;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.biza.babelfish.cdr.v1.enumerations.PayloadTypeAddress;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Valid
@ToString
@EqualsAndHashCode

@Schema(description = "Physical Address Detail")
public abstract class CommonPhysicalAddress<T> {
  @Schema(description = "The type of address object present", required = true)
  @JsonProperty("addressUType")
  @NotNull
  @Valid
  PayloadTypeAddress type;

  public PayloadTypeAddress type() {
    return getType();
  }

  @SuppressWarnings("unchecked")
  public T type(PayloadTypeAddress addressType) {
    setType(addressType);
    return (T) this;
  }

  @Schema(description = "Address in Simple Address format")
  @JsonProperty("simple")
  @Valid
  CommonSimpleAddress<?> simple;

  public CommonSimpleAddress<?> simple() {
    return getSimple();
  }

  @SuppressWarnings("unchecked")
  public T simple(CommonSimpleAddress<?> simple) {
    setSimple(simple);
    return (T) this;
  }

  @Schema(description = "Address in PAF Format")
  @JsonProperty("paf")
  @Valid
  CommonPAFAddress<?> paf;

  public CommonPAFAddress<?> paf() {
    return getPaf();
  }

  @SuppressWarnings("unchecked")
  public T paf(CommonPAFAddress<?> paf) {
    setPaf(paf);
    return (T) this;
  }
}
