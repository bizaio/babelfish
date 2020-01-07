package io.biza.cdr.babelfish.tests.v1.model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.cdr.babelfish.tests.v1.ModelConstants;

@DisplayName("ResponseBankingDirectDebitAuthorisationList V1 Tests")
public class ResponseBankingDirectDebitAuthorisationListV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid ResponseBankingDirectDebitAuthorisationList")
  void responseBankingDirectDebitAuthorisationList() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_RESPONSE_BANKING_ACCOUNT_LIST).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_RESPONSE_BANKING_ACCOUNT_LIST).toString());
  }

}
