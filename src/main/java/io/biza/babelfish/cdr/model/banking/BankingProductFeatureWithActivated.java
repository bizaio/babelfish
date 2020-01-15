/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *******************************************************************************/
package io.biza.babelfish.cdr.model.banking;

import javax.validation.Valid;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Valid
@ToString
@EqualsAndHashCode(callSuper = true)
@Schema(name = "BankingAccountProductFeature",
    description = "Banking Account Product Feature", allOf = { BankingProductFeature.class })
public abstract class BankingProductFeatureWithActivated<T> extends BankingProductFeature<T> {
  @Schema(
      description = "True if the feature is already activated and false if the feature is available for activation.")
  Boolean isActivated = true;

  public Boolean isActivated() {
    return getIsActivated();
  }

  @SuppressWarnings("unchecked")
  public T isActivated(Boolean isActivated) {
    setIsActivated(isActivated);
    return (T) this;
  }
}
