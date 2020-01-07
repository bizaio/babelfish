package io.biza.cdr.babelfish.tests.v1.model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.cdr.babelfish.tests.v1.ModelConstants;
import io.biza.cdr.babelfish.v1.response.ResponseBankingPayeeById;

@DisplayName("ResponseBankingTransactionById V1 Tests")
public class ResponseBankingPayeeByIdV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid ResponseBankingPayeeById")
  void responseBankingPayeeById() {
    ResponseBankingPayeeById data = ModelConstants.DEFAULT_RESPONSE_BANKING_PAYEE_BY_ID;
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }


}
