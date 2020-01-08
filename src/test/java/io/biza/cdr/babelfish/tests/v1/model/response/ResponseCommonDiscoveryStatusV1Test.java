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
import io.biza.cdr.babelfish.v1.response.ResponseCommonDiscoveryStatus;

@DisplayName("ResponseCommonDiscoveryStatus V1 Tests")
public class ResponseCommonDiscoveryStatusV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid ResponseCommonDiscoveryStatus")
  void responseCommonDiscoveryStatus() {
    ResponseCommonDiscoveryStatus data = ModelConstants.DEFAULT_RESPONSE_COMMON_DISCOVERY_STATUS;
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("ResponseCommonDiscoveryStatus Mandatory Fields")
  void responseCommonDiscoveryStatusMandatoryFields() {
    ResponseCommonDiscoveryStatus data = new ResponseCommonDiscoveryStatus();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.links(ModelConstants.DEFAULT_LINKS);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.data(ModelConstants.DEFAULT_COMMON_DISCOVERY_STATUS);
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    // Should still validate with meta too
    data.meta(ModelConstants.DEFAULT_META);
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
  }
}
