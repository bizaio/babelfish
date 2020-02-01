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
package io.biza.babelfish.cdr.models.responses.container;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductV2;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Valid
@Builder
@Data
public class ResponseBankingProductV2ListData {

  @Schema(
      description = "The list of products returned.  If the filter results in an empty set then this array may have no records",
      required = true)
  @JsonProperty("products")
  @NotNull
  @Valid
  List<BankingProductV2> products;

  public List<BankingProductV2> products() {
    return getProducts();
  }

  public ResponseBankingProductV2ListData products(List<BankingProductV2> products) {
    setProducts(products);
    return this;
  }
}
