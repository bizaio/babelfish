package io.biza.cdr.babelfish.tests.v1.model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.cdr.babelfish.tests.v1.ModelConstants;
import io.biza.cdr.babelfish.v1.model.common.Links;
import io.biza.cdr.babelfish.v1.response.ResponseBankingAccountsBalanceById;

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
    ResponseBankingAccountsBalanceById myResponse =
        new ResponseBankingAccountsBalanceById().links(new Links().self(ModelConstants.DEFAULT_SELF_URI))
            .data(ModelConstants.DEFAULT_BANKING_BALANCE);
    assertTrue(validator.validate(myResponse).isEmpty(), validator.validate(myResponse).toString());
  }

}
