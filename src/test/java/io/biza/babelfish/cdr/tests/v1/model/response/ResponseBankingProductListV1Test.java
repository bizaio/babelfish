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
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductV1;
import io.biza.babelfish.cdr.models.payloads.common.LinksPaginated;
import io.biza.babelfish.cdr.models.payloads.common.MetaPaginated;
import io.biza.babelfish.cdr.models.responses.ResponseBankingProductV1List;
import io.biza.babelfish.cdr.models.responses.container.ResponseBankingProductV1ListData;
import io.biza.babelfish.cdr.tests.v1.model.ModelConstants;

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
    ResponseBankingProductV1List myResponse = new ResponseBankingProductV1List()
        .links(new LinksPaginated().self(ModelConstants.DEFAULT_SELF_URI)
            .first(ModelConstants.DEFAULT_FIRST_URI).next(ModelConstants.DEFAULT_NEXT_URI)
            .last(ModelConstants.DEFAULT_LAST_URI).prev(ModelConstants.DEFAULT_PREV_URI))
        .meta(new MetaPaginated().totalPages(10).totalRecords(100))
        .data(ResponseBankingProductV1ListData.builder().products(List.of()).build());
    assertTrue(validator.validate(myResponse).isEmpty(), validator.validate(myResponse).toString());
  }

  @Test
  @DisplayName("Create valid ResponseBankingProductList with empty Products")
  void createValidProductList() {
    ResponseBankingProductV1List myResponse = new ResponseBankingProductV1List()
        .links(new LinksPaginated().self(ModelConstants.DEFAULT_SELF_URI)
            .first(ModelConstants.DEFAULT_FIRST_URI).next(ModelConstants.DEFAULT_NEXT_URI)
            .last(ModelConstants.DEFAULT_LAST_URI).prev(ModelConstants.DEFAULT_PREV_URI))
        .meta(new MetaPaginated().totalPages(10).totalRecords(100))
        .data(ResponseBankingProductV1ListData.builder()
            .products(List.of(ModelConstants.DEFAULT_BANKING_PRODUCT_V1)).build());
    assertTrue(validator.validate(myResponse).isEmpty(), validator.validate(myResponse).toString());
  }

  @Test
  @DisplayName("Create valid ResponseBankingProductList with Invalid Product Data")
  void createValidProductListWithInvalidProduct() {
    ResponseBankingProductV1List myResponse = new ResponseBankingProductV1List()
        .links(new LinksPaginated().self(ModelConstants.DEFAULT_SELF_URI)
            .first(ModelConstants.DEFAULT_FIRST_URI).next(ModelConstants.DEFAULT_NEXT_URI)
            .last(ModelConstants.DEFAULT_LAST_URI).prev(ModelConstants.DEFAULT_PREV_URI))
        .meta(new MetaPaginated().totalPages(10).totalRecords(100))
        .data(ResponseBankingProductV1ListData.builder().products(List.of(new BankingProductV1())).build());
    assertFalse(validator.validate(myResponse).isEmpty(),
        validator.validate(myResponse).toString());
  }

}
