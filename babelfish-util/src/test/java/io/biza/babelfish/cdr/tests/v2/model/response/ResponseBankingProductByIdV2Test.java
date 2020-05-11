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
import io.biza.babelfish.cdr.support.BabelfishConverter;
import lombok.extern.slf4j.Slf4j;

@DisplayName("ResponseBankingProductById V2 Tests")
@Slf4j
public class ResponseBankingProductByIdV2Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Downgrade Payload from v2 to v1")
  void downgradePayloadToV1() {
    BankingProductDetailV2 detail = BankingProductDetailV2.builder()
        .productId("073e7e70-357d-4858-8f52-92283f4edd6f").lastUpdated(OffsetDateTime.now())
        .productCategory(BankingProductCategory.TRANS_AND_SAVINGS_ACCOUNTS).name("Example Product")
        .description("Example Product Description").brand("ACME").isTailored(false).build();

    BankingProductDetailV1 downgradedDetail =
        BabelfishConverter.convert(detail, BankingProductDetailV1.class);

    assertEquals(detail.productId(), downgradedDetail.productId());
    assertEquals(detail.lastUpdated(), downgradedDetail.lastUpdated());
    assertEquals(detail.productCategory(), downgradedDetail.productCategory());
    assertEquals(detail.name(), downgradedDetail.name());
    assertEquals(detail.description(), downgradedDetail.description());
    assertEquals(detail.isTailored(), downgradedDetail.isTailored());

    LOG.info("V1 payload is: {}", downgradedDetail);
    LOG.info("V2 payload is: {}", detail);
  }

}
