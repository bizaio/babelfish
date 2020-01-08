package io.biza.cdr.babelfish.tests.v1.model.response;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.cdr.babelfish.tests.v1.ModelConstants;
import io.biza.cdr.babelfish.v1.response.ResponseBankingAccountById;

@DisplayName("ResponseBankingAccountById V1 Tests")
public class ResponseBankingAccountByIdV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid ResponseBankingAccountById")
  void responseBankingAccountById() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_RESPONSE_BANKING_ACCOUNT_BY_ID).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_RESPONSE_BANKING_ACCOUNT_BY_ID).toString());
  }

  @Test
  @DisplayName("ResponseBankingAccountById Mandatory Fields")
  void responseBankingAccountByIdMandatoryFields() {
    ResponseBankingAccountById data = new ResponseBankingAccountById();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.setLinks(ModelConstants.DEFAULT_LINKS);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.setData(ModelConstants.DEFAULT_BANKING_ACCOUNT_DETAIL);
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.setMeta(ModelConstants.DEFAULT_META);
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }
}
