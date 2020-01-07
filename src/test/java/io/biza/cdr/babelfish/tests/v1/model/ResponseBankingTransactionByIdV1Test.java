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
import io.biza.cdr.babelfish.v1.response.ResponseBankingProductById;
import io.biza.cdr.babelfish.v1.response.ResponseBankingTransactionById;

@DisplayName("ResponseBankingTransactionById V1 Tests")
public class ResponseBankingTransactionByIdV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid ResponseBankingTransactionById")
  void responseBankingTransactionById() {
    ResponseBankingTransactionById data = ModelConstants.DEFAULT_RESPONSE_BANKING_TRANSACTION_BY_ID;
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("ResponseBankingTransactionById Mandatory Fields")
  void responseBankingTransactionByIdRequiredFields() {
    ResponseBankingTransactionById data = new ResponseBankingTransactionById();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.setLinks(ModelConstants.DEFAULT_LINKS);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.setData(ModelConstants.DEFAULT_BANKING_TRANSACTION_DETAIL);
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.setMeta(ModelConstants.DEFAULT_META);
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

}