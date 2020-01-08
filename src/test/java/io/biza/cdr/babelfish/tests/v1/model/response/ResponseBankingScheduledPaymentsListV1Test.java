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
import io.biza.cdr.babelfish.v1.response.ResponseBankingScheduledPaymentsList;

@DisplayName("ResponseBankingScheduledPaymentsList V1 Tests")
public class ResponseBankingScheduledPaymentsListV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid ResponseBankingScheduledPaymentsList")
  void responseBankingScheduledPaymentsList() {
    assertTrue(
        validator.validate(ModelConstants.DEFAULT_RESPONSE_BANKING_SCHEDULED_PAYMENTS_LIST)
            .isEmpty(),
        validator.validate(ModelConstants.DEFAULT_RESPONSE_BANKING_SCHEDULED_PAYMENTS_LIST)
            .toString());
  }

  @Test
  @DisplayName("ResponseBankingAccountList Mandatory Fields")
  void bankingScheduledPaymentsMandatoryFields() {
    ResponseBankingScheduledPaymentsList data = new ResponseBankingScheduledPaymentsList();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.setLinks(ModelConstants.DEFAULT_LINKS_PAGINATED);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.setMeta(ModelConstants.DEFAULT_META_PAGINATED);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.setData(ModelConstants.DEFAULT_RESPONSE_BANKING_SCHEDULED_PAYMENTS_LIST_DATA);
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

}
