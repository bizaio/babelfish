package io.biza.cdr.babelfish.tests.v1.model.response;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.cdr.babelfish.tests.v1.ModelConstants;
import io.biza.cdr.babelfish.v1.response.container.ResponseCommonDiscoveryOutagesListData;

@DisplayName("ResponseDiscoveryOutagesListData V1 Tests")
public class ResponseDiscoveryOutagesListDataV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid ResponseDiscoveryOutagesListData")
  void responseDiscoveryOutagesListData() {
    ResponseCommonDiscoveryOutagesListData data =
        ModelConstants.DEFAULT_RESPONSE_COMMON_DISCOVERY_OUTAGES_LIST_DATA;
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("ResponseDiscoveryOutagesListData Mandatory Fields")
  void responseDiscoveryOutagesListDataMandatoryFields() {

    ResponseCommonDiscoveryOutagesListData data = new ResponseCommonDiscoveryOutagesListData();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.outages(List.of(ModelConstants.DEFAULT_COMMON_DISCOVERY_OUTAGE));
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());


  }

}
