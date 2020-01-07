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
import io.biza.cdr.babelfish.v1.response.ResponseBankingAccountsBalanceById;
import io.biza.cdr.babelfish.v1.response.ResponseCommonCustomerDetail;

@DisplayName("ResponseCommonCustomerDetail V1 Tests")
public class ResponseCommonCustomerDetailV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid ResponseCommonCustomerDetail")
  void responseCommonCustomerDetail() {
    ResponseCommonCustomerDetail data = ModelConstants.DEFAULT_RESPONSE_COMMON_CUSTOMER_DETAIL;
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("ResponseCommonCustomerDetail Mandatory Fields")
  void responseCommonCustomerDetailMandatoryFields() {
    ResponseCommonCustomerDetail data = new ResponseCommonCustomerDetail();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.links(ModelConstants.DEFAULT_LINKS);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.data(ModelConstants.DEFAULT_RESPONSE_COMMON_CUSTOMER_DETAIL_DATA);
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    // Should still validate with meta too
    data.meta(ModelConstants.DEFAULT_META);
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
  }
}
