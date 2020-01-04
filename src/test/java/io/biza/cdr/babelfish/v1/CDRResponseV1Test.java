package io.biza.cdr.babelfish.v1;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.net.URI;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import io.biza.cdr.babelfish.v1.model.CDRResponse;
import io.biza.cdr.babelfish.v1.model.common.Links;
import io.biza.cdr.babelfish.v1.model.common.Meta;

public class CDRResponseV1Test {
  private Validator validator;
  
  @BeforeEach
  public void setup() {
      ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
      validator = factory.getValidator();
  }

  @Test
  void createValidBaseResponse() {
    CDRResponse myResponse = new CDRResponse();
    myResponse.links(new Links().self(URI.create("http://localhost/"))).meta(new Meta());
    assertTrue(validator.validate(myResponse).isEmpty());
  }
  
  @Test
  void createBaseResponseWithMissingLinksAndMeta() {
    CDRResponse myResponse = new CDRResponse();
    assertFalse(validator.validate(myResponse).isEmpty());
  }
  
  @Test
  void createBaseResponseWithMissingMeta() {
    CDRResponse myResponse = new CDRResponse();
    myResponse.links(new Links().self(URI.create("http://localhost/")));
    assertFalse(validator.validate(myResponse).isEmpty());
  }

  @Test
  void createBaseResponseWithMissingLinks() {
    CDRResponse myResponse = new CDRResponse();
    myResponse.meta(new Meta());
    assertFalse(validator.validate(myResponse).isEmpty());
  }


  @Test
  void createBaseResponseWithMissingLinksSelf() {
    CDRResponse myResponse = new CDRResponse();
    myResponse.links(new Links());
    myResponse.meta(new Meta());
    assertFalse(validator.validate(myResponse).isEmpty());
  }

}
