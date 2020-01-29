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
import io.biza.babelfish.cdr.models.payloads.common.AccountIdsList;
import io.biza.babelfish.cdr.models.payloads.common.Meta;
import io.biza.babelfish.cdr.models.requests.RequestAccountIds;
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
    RequestAccountIds data = new RequestAccountIds()
        .data(new AccountIdsList().accountIds(ModelConstants.DEFAULT_ACCOUNT_IDS));
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("Create valid RequestAccountIds with Meta")
  void createValidRequestAccountIdsWithMeta() {
    RequestAccountIds data = new RequestAccountIds()
        .data(new AccountIdsList().accountIds(ModelConstants.DEFAULT_ACCOUNT_IDS)).meta(new Meta());
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("Create invalid RequestAccountIds with empty AccountIds")
  void createInvalidRequestAccountIds() {
    RequestAccountIds data =
        new RequestAccountIds().data(new AccountIdsList().accountIds(List.of())).meta(new Meta());
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

}
