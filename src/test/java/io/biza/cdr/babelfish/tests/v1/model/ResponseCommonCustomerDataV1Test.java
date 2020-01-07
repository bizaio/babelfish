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
import io.biza.cdr.babelfish.v1.enumerations.PayloadTypeCustomer;
import io.biza.cdr.babelfish.v1.model.common.Links;
import io.biza.cdr.babelfish.v1.response.ResponseBankingAccountsBalanceById;
import io.biza.cdr.babelfish.v1.response.ResponseCommonCustomerData;

@DisplayName("ResponseCommonCustomerData V1 Tests")
public class ResponseCommonCustomerDataV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid ResponseCommonCustomerData")
  void responseCommonCustomerData() {
    ResponseCommonCustomerData data = ModelConstants.DEFAULT_RESPONSE_COMMON_CUSTOMER_DATA;
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("ResponseCommonCustomerData Mandatory Fields (Person)")
  void responseCommonCustomerDataMandatoryFieldsPerson() {
    ResponseCommonCustomerData data = new ResponseCommonCustomerData();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.type(PayloadTypeCustomer.PERSON);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.person(ModelConstants.DEFAULT_COMMON_PERSON);
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    // Should only have one
    data.organisation(ModelConstants.DEFAULT_COMMON_ORGANISATION);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
  }
  
  @Test
  @DisplayName("ResponseCommonCustomerData Mandatory Fields (Organisation)")
  void responseCommonCustomerDataMandatoryFieldsOrganisation() {
    ResponseCommonCustomerData data = new ResponseCommonCustomerData();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.type(PayloadTypeCustomer.ORGANISATION);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.organisation(ModelConstants.DEFAULT_COMMON_ORGANISATION);
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    // Should only have one
    data.person(ModelConstants.DEFAULT_COMMON_PERSON);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
  }
}