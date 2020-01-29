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

import static org.junit.jupiter.api.Assertions.assertTrue;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.babelfish.cdr.models.payloads.common.Links;
import io.biza.babelfish.cdr.models.responses.ResponseBankingAccountsBalanceById;
import io.biza.babelfish.cdr.tests.v1.model.ModelConstants;

@DisplayName("ResponseBankingAccountsBalanceById V1 Tests")
public class ResponseBankingAccountsBalanceByIdV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid ResponseBankingAccountsBalanceById")
  void responseBankingAccountsBalanceById() {
    ResponseBankingAccountsBalanceById myResponse = new ResponseBankingAccountsBalanceById()
        .links(new Links().self(ModelConstants.DEFAULT_SELF_URI))
        .data(ModelConstants.DEFAULT_BANKING_BALANCE);
    assertTrue(validator.validate(myResponse).isEmpty(), validator.validate(myResponse).toString());
  }

}
