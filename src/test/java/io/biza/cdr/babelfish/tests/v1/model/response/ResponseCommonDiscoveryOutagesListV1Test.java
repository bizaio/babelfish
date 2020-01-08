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
import io.biza.cdr.babelfish.v1.response.ResponseCommonDiscoveryOutagesList;

@DisplayName("ResponseCommonDiscoveryOutagesList V1 Tests")
public class ResponseCommonDiscoveryOutagesListV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid ResponseCommonDiscoveryOutagesList")
  void discoveryOutagesList() {
    assertTrue(
        validator.validate(ModelConstants.DEFAULT_RESPONSE_COMMON_DISCOVERY_OUTAGES_LIST).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_RESPONSE_COMMON_DISCOVERY_OUTAGES_LIST)
            .toString());
  }
  
  @Test
  @DisplayName("ResponseCommonDiscoveryOutagesList Mandatory Fields")
  void discoveryOutagesListMandatoryFields() {
    ResponseCommonDiscoveryOutagesList data = new ResponseCommonDiscoveryOutagesList();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.setLinks(ModelConstants.DEFAULT_LINKS);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.setMeta(ModelConstants.DEFAULT_META);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.setData(ModelConstants.DEFAULT_RESPONSE_COMMON_DISCOVERY_OUTAGES_LIST_DATA);
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

}
