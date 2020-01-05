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
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Valid
@BabelFishModel(modelName = "BankingAccountProductFeature",
    description = "Banking Account Product Feature", parent = BankingProductFeature.class)
public abstract class BankingProductFeatureWithActivated<T extends BankingProductFeatureWithActivated<T>> {
  @JsonUnwrapped
  @BabelFishModelProperty(hidden = true)
  @Valid
  BankingProductFeature<?> bankingProductFeature;

  public BankingProductFeature<?> bankingProductFeature() {
    return getBankingProductFeature();
  }

  @SuppressWarnings("unchecked")
  public T bankingProductFeature(BankingProductFeature<?> bankingProductFeature) {
    setBankingProductFeature(bankingProductFeature);
    return (T) this;
  }

  @BabelFishModelProperty(
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
