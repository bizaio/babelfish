package io.biza.cdr.babelfish.tests.v1.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.net.URI;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.cdr.babelfish.v1.model.CDRResponse;
import io.biza.cdr.babelfish.v1.model.common.Links;
import io.biza.cdr.babelfish.v1.model.common.Meta;

@DisplayName("CDR Response V1 Tests")
public class CDRResponseV1Test {
  private Validator validator;
  
  @BeforeEach
  public void setup() {
      ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
      validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid CDR Response V1")
  void createValidCDRResponse() {
    CDRResponse myResponse = new CDRResponse().links(new Links().self(URI.create("http://localhost/"))).meta(new Meta());
    assertTrue(validator.validate(myResponse).isEmpty());
  }
  
  @Test
  @DisplayName("Create CDR Response V1 with Missing Links and Meta")
  void createCDRResponseWithMissingLinksAndMeta() {
    CDRResponse myResponse = new CDRResponse();
    assertFalse(validator.validate(myResponse).isEmpty());
  }
  
  @Test
  @DisplayName("Create CDR Response V1 with Missing Meta")
  void createCDRResponseWithMissingMeta() {
    CDRResponse myResponse = new CDRResponse().links(new Links().self(URI.create("http://localhost/")));
    assertFalse(validator.validate(myResponse).isEmpty());
  }

  @Test
  @DisplayName("Create CDR Response V1 with Missing Links")
  void createCDRResponseWithMissingLinks() {
    CDRResponse myResponse = new CDRResponse().meta(new Meta());
    assertFalse(validator.validate(myResponse).isEmpty());
  }


  @Test
  @DisplayName("Create CDR Response V1 with Links and broken self link")
  void createCDRResponseWithMissingLinksSelf() {
    CDRResponse myResponse = new CDRResponse().links(new Links()).meta(new Meta());
    assertFalse(validator.validate(myResponse).isEmpty());
  }

}
