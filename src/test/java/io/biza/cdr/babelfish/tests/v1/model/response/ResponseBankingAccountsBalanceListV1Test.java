package io.biza.cdr.babelfish.tests.v1.model.response;

import static org.junit.jupiter.api.Assertions.assertTrue;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.cdr.babelfish.tests.v1.ModelConstants;
import io.biza.cdr.babelfish.v1.response.ResponseBankingAccountsBalanceList;

@DisplayName("ResponseBankingAccountsBalanceList V1 Tests")
public class ResponseBankingAccountsBalanceListV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid ResponseBankingAccountsBalanceList")
  void responseBankingAccountsBalanceList() {
    ResponseBankingAccountsBalanceList data =
        ModelConstants.DEFAULT_RESPONSE_BANKING_ACCOUNTS_BALANCE_LIST;
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

}
