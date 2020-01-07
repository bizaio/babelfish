package io.biza.cdr.babelfish.tests.v1.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.cdr.babelfish.tests.v1.ModelConstants;
import io.biza.cdr.babelfish.v1.model.common.Links;
import io.biza.cdr.babelfish.v1.response.ResponseBankingAccountList;
import io.biza.cdr.babelfish.v1.response.ResponseBankingPayeeById;
import io.biza.cdr.babelfish.v1.response.ResponseBankingProductById;
import io.biza.cdr.babelfish.v1.response.ResponseBankingTransactionById;
import io.biza.cdr.babelfish.v1.response.ResponseErrorList;

@DisplayName("ResponseErrorList V1 Tests")
public class ResponseErrorListV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid ResponseErrorList")
  void responseErrorList() {
    ResponseErrorList data = ModelConstants.DEFAULT_RESPONSE_ERROR_LIST;
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }


}
