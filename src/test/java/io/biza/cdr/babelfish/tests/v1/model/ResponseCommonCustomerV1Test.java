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
import io.biza.cdr.babelfish.v1.response.ResponseCommonCustomer;

@DisplayName("ResponseCommonCustomer V1 Tests")
public class ResponseCommonCustomerV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid ResponseCommonCustomer")
  void responseCommonDiscoveryStatus() {
    ResponseCommonCustomer data = ModelConstants.DEFAULT_RESPONSE_COMMON_CUSTOMER;
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("ResponseCommonCustomer Mandatory Fields")
  void responseCommonDiscoveryStatusMandatoryFields() {
    ResponseCommonCustomer data = new ResponseCommonCustomer();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.links(ModelConstants.DEFAULT_LINKS);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.data(ModelConstants.DEFAULT_RESPONSE_COMMON_CUSTOMER_DATA);
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    // Should still validate with meta too
    data.meta(ModelConstants.DEFAULT_META);
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
  }
}