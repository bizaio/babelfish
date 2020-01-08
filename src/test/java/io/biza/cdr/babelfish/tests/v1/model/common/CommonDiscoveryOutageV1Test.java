package io.biza.cdr.babelfish.tests.v1.model.common;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.Duration;
import java.time.LocalDateTime;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.cdr.babelfish.tests.v1.ModelConstants;
import io.biza.cdr.babelfish.v1.model.common.CommonDiscoveryOutage;

@DisplayName("CommonDiscoveryOutage V1 Tests")
public class CommonDiscoveryOutageV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid CommonDiscoveryOutage")
  void commonDiscoveryOutage() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_COMMON_DISCOVERY_OUTAGE).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_COMMON_DISCOVERY_OUTAGE).toString());
  }

  @Test
  @DisplayName("CommonDiscoveryOutage Mandatory Fields")
  void commonDiscoveryOutageMandatoryFields() {
    CommonDiscoveryOutage data = new CommonDiscoveryOutage();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.outageTime(LocalDateTime.now());
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.duration(Duration.ofHours(1));
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.explanation("Outage Explanation");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }
}
