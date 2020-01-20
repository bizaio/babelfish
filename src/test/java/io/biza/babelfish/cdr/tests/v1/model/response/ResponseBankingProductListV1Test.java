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
package io.biza.babelfish.cdr.tests.v1.model.response;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.babelfish.cdr.tests.v1.model.ModelConstants;
import io.biza.babelfish.cdr.v1.model.common.LinksPaginated;
import io.biza.babelfish.cdr.v1.model.common.MetaPaginated;
import io.biza.babelfish.cdr.v1.response.ResponseBankingProductList;
import io.biza.babelfish.cdr.v1.response.container.ResponseBankingProductListData;
import io.biza.babelfish.cdr.v2.model.banking.BankingProduct;

@DisplayName("ResponseBankingProductList V1 Tests")
public class ResponseBankingProductListV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid ResponseBankingProductList with empty Products")
  void createValidEmptyProductList() {
    ResponseBankingProductList myResponse = new ResponseBankingProductList()
        .links(new LinksPaginated().self(ModelConstants.DEFAULT_SELF_URI)
            .first(ModelConstants.DEFAULT_FIRST_URI).next(ModelConstants.DEFAULT_NEXT_URI)
            .last(ModelConstants.DEFAULT_LAST_URI).prev(ModelConstants.DEFAULT_PREV_URI))
        .meta(new MetaPaginated().totalPages(10).totalRecords(100))
        .data(new ResponseBankingProductListData().products(List.of()));
    assertTrue(validator.validate(myResponse).isEmpty(), validator.validate(myResponse).toString());
  }

  @Test
  @DisplayName("Create valid ResponseBankingProductList with empty Products")
  void createValidProductList() {
    ResponseBankingProductList myResponse = new ResponseBankingProductList()
        .links(new LinksPaginated().self(ModelConstants.DEFAULT_SELF_URI)
            .first(ModelConstants.DEFAULT_FIRST_URI).next(ModelConstants.DEFAULT_NEXT_URI)
            .last(ModelConstants.DEFAULT_LAST_URI).prev(ModelConstants.DEFAULT_PREV_URI))
        .meta(new MetaPaginated().totalPages(10).totalRecords(100))
        .data(new ResponseBankingProductListData()
            .products(List.of(ModelConstants.DEFAULT_BANKING_PRODUCT_V1)));
    assertTrue(validator.validate(myResponse).isEmpty(), validator.validate(myResponse).toString());
  }

  @Test
  @DisplayName("Create valid ResponseBankingProductList with Invalid Product Data")
  void createValidProductListWithInvalidProduct() {
    ResponseBankingProductList myResponse = new ResponseBankingProductList()
        .links(new LinksPaginated().self(ModelConstants.DEFAULT_SELF_URI)
            .first(ModelConstants.DEFAULT_FIRST_URI).next(ModelConstants.DEFAULT_NEXT_URI)
            .last(ModelConstants.DEFAULT_LAST_URI).prev(ModelConstants.DEFAULT_PREV_URI))
        .meta(new MetaPaginated().totalPages(10).totalRecords(100))
        .data(new ResponseBankingProductListData().products(List.of(new BankingProduct())));
    assertFalse(validator.validate(myResponse).isEmpty(),
        validator.validate(myResponse).toString());
  }

}
