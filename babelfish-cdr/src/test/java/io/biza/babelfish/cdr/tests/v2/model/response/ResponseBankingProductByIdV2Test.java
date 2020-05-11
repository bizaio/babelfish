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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.biza.babelfish.cdr.enumerations.BankingProductCategory;
import io.biza.babelfish.cdr.models.payloads.LinksV1;
import io.biza.babelfish.cdr.models.payloads.MetaV1;
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductDetailV1;
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductDetailV2;
import io.biza.babelfish.cdr.models.responses.ResponseBankingProductByIdV2;
import io.biza.babelfish.cdr.tests.v1.model.ModelConstants;
import lombok.extern.slf4j.Slf4j;

@DisplayName("ResponseBankingProductById V1 Tests")
@Slf4j
public class ResponseBankingProductByIdV2Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid ResponseBankingProductById with empty Products")
  void createValidEmptyProductList() {
    ResponseBankingProductByIdV2 myResponse = new ResponseBankingProductByIdV2()
        .links(new LinksV1().self(ModelConstants.DEFAULT_SELF_URI))
        .data(ModelConstants.DEFAULT_BANKING_PRODUCT_DETAIL_V2.build());
    assertTrue(validator.validate(myResponse).isEmpty(), validator.validate(myResponse).toString());
  }

  @Test
  @DisplayName("Raw JSON parse and validate")
  void parseProductJsonAndValidate() throws JsonProcessingException {
    @SuppressWarnings("unused")
    ResponseBankingProductByIdV2 productResponse = new ObjectMapper().readValue(
        "{\"links\":{\"self\":\"http://localhost/cds-au/v1/banking/products/073e7e70-357d-4858-8f52-92283f4edd6f\"},\"meta\":{},"
            + "\"data\":{\"productId\":\"073e7e70-357d-4858-8f52-92283f4edd6f\",\"lastUpdated\":\"2020-02-03T06:32:27Z\","
            + "\"productCategory\":\"TRANS_AND_SAVINGS_ACCOUNTS\",\"name\":\"Example Product\",\"description\":\"Example Product Description\","
            + "\"brand\":\"ACME\",\"brandName\":\"ACME Bank\",\"isTailored\":false}}",
        ResponseBankingProductByIdV2.class);
  }

  @Test
  @DisplayName("Generate a Product Response Payload")
  void generateProductJsonAndValidate() throws JsonProcessingException {

    ResponseBankingProductByIdV2 productResponse =
        ResponseBankingProductByIdV2.builder()
            .links(LinksV1.builder().self(URI.create(
                "http://localhost/cds-au/v1/banking/products/073e7e70-357d-4858-8f52-92283f4edd6f"))
                .build())
            .meta(MetaV1.builder().build())
            .data(BankingProductDetailV2.builder().productId("073e7e70-357d-4858-8f52-92283f4edd6f")
                .lastUpdated(OffsetDateTime.now())
                .productCategory(BankingProductCategory.TRANS_AND_SAVINGS_ACCOUNTS)
                .name("Example Product").description("Example Product Description").brand("ACME")
                .isTailored(false).build())
            .build();
    Set<ConstraintViolation<ResponseBankingProductByIdV2>> productResponseValidation =
        Validation.buildDefaultValidatorFactory().getValidator().validate(productResponse);
    if (productResponseValidation.isEmpty()) {
      LOG.info(new ObjectMapper().setSerializationInclusion(Include.NON_ABSENT)
          .writeValueAsString(productResponse));
    } else {
      LOG.error("Object failed validation with errors of: {}",
          productResponseValidation.toString());
    }
  }

  @Test
  @DisplayName("Documentation enumeration example")
  void produceEnumerationLabel() {
    LOG.info("name: {} label: {}", BankingProductCategory.TRANS_AND_SAVINGS_ACCOUNTS.name(),
        BankingProductCategory.TRANS_AND_SAVINGS_ACCOUNTS.label());
    // Result: name: TRANS_AND_SAVINGS_ACCOUNTS label: Transaction & Savings
  }
}
