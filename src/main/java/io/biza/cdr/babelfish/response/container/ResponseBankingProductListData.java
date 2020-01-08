/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * 
 * public ANY WARRANTY() { return getWARRANTY(); }
 * 
 * @SuppressWarnings("unchecked") public T WARRANTY(ANY WARRANTY) { setWARRANTY(WARRANTY); return
 * (T) this; } even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See
 * the GNU General Public License for more details.
 *******************************************************************************/
package io.biza.cdr.babelfish.response.container;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.biza.cdr.babelfish.model.banking.BankingProduct;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Valid
@ToString
@EqualsAndHashCode


@BabelFishModel(description = "Object containing a list of BankingProduct objects")
public abstract class ResponseBankingProductListData<T> {
  @BabelFishModelProperty(
      description = "The list of products returned.  If the filter results in an empty set then this array may have no records",
      required = true)
  @JsonProperty("products")
  @NotNull
  @NonNull
  @Valid
  public List<BankingProduct<?>> products;

  public List<BankingProduct<?>> products() {
    return getProducts();
  }

  @SuppressWarnings("unchecked")
  public T products(List<BankingProduct<?>> products) {
    setProducts(products);
    return (T) this;
  }
}
