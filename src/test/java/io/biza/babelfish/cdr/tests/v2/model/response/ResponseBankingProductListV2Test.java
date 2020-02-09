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
package io.biza.babelfish.cdr.tests.v2.model.response;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductV2;
import io.biza.babelfish.cdr.models.responses.ResponseBankingProductListV2;
import io.biza.babelfish.cdr.models.responses.container.ResponseBankingProductListDataV2;
import io.biza.babelfish.cdr.tests.v1.model.ModelConstants;

@DisplayName("ResponseBankingProductList V1 Tests")
public class ResponseBankingProductListV2Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid ResponseBankingProductList with empty Products")
  void createValidEmptyProductList() {
    ResponseBankingProductListV2 myResponse = new ResponseBankingProductListV2()
        .links(ModelConstants.DEFAULT_LINKS_PAGINATED.build())
        .meta(ModelConstants.DEFAULT_META_PAGINATED.build())
        .data(ResponseBankingProductListDataV2.builder().products(List.of()).build());
    assertTrue(validator.validate(myResponse).isEmpty(), validator.validate(myResponse).toString());
  }

  @Test
  @DisplayName("Create valid ResponseBankingProductList with empty Products")
  void createValidProductList() {
    ResponseBankingProductListV2 myResponse = new ResponseBankingProductListV2()
        .links(ModelConstants.DEFAULT_LINKS_PAGINATED.build())
        .meta(ModelConstants.DEFAULT_META_PAGINATED.build())
        .data(ResponseBankingProductListDataV2.builder()
            .products(List.of(ModelConstants.DEFAULT_BANKING_PRODUCT_V2.build())).build());
    assertTrue(validator.validate(myResponse).isEmpty(), validator.validate(myResponse).toString());
  }

  @Test
  @DisplayName("Create valid ResponseBankingProductList with Invalid Product Data")
  void createValidProductListWithInvalidProduct() {
    ResponseBankingProductListV2 myResponse = new ResponseBankingProductListV2()
        .links(ModelConstants.DEFAULT_LINKS_PAGINATED.build())
        .meta(ModelConstants.DEFAULT_META_PAGINATED.build())
        .data(ResponseBankingProductListDataV2.builder().products(List.of(new BankingProductV2())).build());
    assertFalse(validator.validate(myResponse).isEmpty(),
        validator.validate(myResponse).toString());
  }

}
