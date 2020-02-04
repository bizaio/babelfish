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
package io.biza.babelfish.cdr.tests.v1.model.request;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.babelfish.cdr.models.payloads.common.AccountIdsListV1;
import io.biza.babelfish.cdr.models.payloads.MetaV1;
import io.biza.babelfish.cdr.models.requests.RequestAccountIdsV1;
import io.biza.babelfish.cdr.tests.v1.model.ModelConstants;

@DisplayName("RequestAccountIds V1 Tests")
public class RequestAccountIdsTest {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid RequestAccountIds")
  void createValidRequestAccountIds() {
    RequestAccountIdsV1 data = new RequestAccountIdsV1()
        .data(new AccountIdsListV1().accountIds(ModelConstants.DEFAULT_ACCOUNT_IDS));
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("Create valid RequestAccountIds with Meta")
  void createValidRequestAccountIdsWithMetaV1() {
    RequestAccountIdsV1 data = new RequestAccountIdsV1()
        .data(new AccountIdsListV1().accountIds(ModelConstants.DEFAULT_ACCOUNT_IDS)).meta(new MetaV1());
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("Create invalid RequestAccountIds with empty AccountIds")
  void createInvalidRequestAccountIds() {
    RequestAccountIdsV1 data =
        new RequestAccountIdsV1().data(new AccountIdsListV1().accountIds(List.of())).meta(new MetaV1());
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

}
