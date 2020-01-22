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
package io.biza.babelfish.cdr.response.container;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.biza.babelfish.cdr.model.banking.BankingProduct;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Valid
@ToString(callSuper = true)
@EqualsAndHashCode


@Schema(description = "Object containing a list of BankingProduct objects")
public abstract class ResponseBankingProductListData<T> {
  @Schema(
      description = "The list of products returned.  If the filter results in an empty set then this array may have no records",
      required = true)
  @JsonProperty("products")
  @NotNull
  @Valid
  List<BankingProduct<?>> products;

  public List<BankingProduct<?>> products() {
    return getProducts();
  }

  @SuppressWarnings("unchecked")
  public T products(List<BankingProduct<?>> products) {
    setProducts(products);
    return (T) this;
  }
}
