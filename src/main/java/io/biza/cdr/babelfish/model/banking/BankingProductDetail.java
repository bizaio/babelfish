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

import java.util.List;
import javax.validation.Valid;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Valid
@BabelFishModel(description = "Banking Product Detailed Information", parent = BankingProduct.class)
public abstract class BankingProductDetail<T extends BankingProductDetail<T>> {
  @JsonUnwrapped
  @BabelFishModelProperty(hidden = true)
  BankingProduct<?> bankingProduct;

  public BankingProduct<?> bankingProduct() {
    return getBankingProduct();
  }

  @SuppressWarnings("unchecked")
  public T bankingProduct(BankingProduct<?> bankingProduct) {
    setBankingProduct(bankingProduct);
    return (T) this;
  }

  @BabelFishModelProperty(
      description = "An array of bundles that this product participates in.  Each bundle is described by free form information but also by a list of product IDs of the other products that are included in the bundle.  It is assumed that the current product is included in the bundle also")
  List<BankingProductBundle<?>> bundles;

  @BabelFishModelProperty(description = "Array of features available for the product")
  List<BankingProductFeature<?>> features;

  @BabelFishModelProperty(
      description = "Constraints on the application for or operation of the product such as minimum balances or limit thresholds")
  List<BankingProductConstraint<?>> constraints;

  @BabelFishModelProperty(description = "Eligibility criteria for the product")
  List<BankingProductEligibility<?>> eligibility;

  @BabelFishModelProperty(description = "Fees applicable for the product")
  List<BankingProductFee<?>> fees;

  @BabelFishModelProperty(description = "Interest rates available for deposits")
  List<BankingProductDepositRate<?>> depositRates;

  @BabelFishModelProperty(description = "Interest rates charged against lending balances")
  List<BankingProductLendingRate<?>> lendingRates;
}
