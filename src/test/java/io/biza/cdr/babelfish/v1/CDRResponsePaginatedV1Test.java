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
import io.biza.cdr.babelfish.v1.model.CDRResponsePaginated;
import io.biza.cdr.babelfish.v1.model.common.LinksPaginated;
import io.biza.cdr.babelfish.v1.model.common.MetaPaginated;

public class CDRResponsePaginatedV1Test {
  private Validator validator;
  
  @BeforeEach
  public void setup() {
      ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
      validator = factory.getValidator();
  }

  @Test
  void createValidCDRResponsePaginated() {
    CDRResponsePaginated myResponse = new CDRResponsePaginated();
    myResponse.links(new LinksPaginated().self(URI.create("http://localhost/"))).meta(new MetaPaginated());
    assertTrue(validator.validate(myResponse).isEmpty());
  }
  
  @Test
  void createCDRResponsePaginatedWithMissingLinksPaginatedAndMetaPaginated() {
    CDRResponsePaginated myResponse = new CDRResponsePaginated();
    assertFalse(validator.validate(myResponse).isEmpty());
  }
  
  @Test
  void createCDRResponsePaginatedWithMissingMetaPaginated() {
    CDRResponsePaginated myResponse = new CDRResponsePaginated();
    myResponse.links(new LinksPaginated().self(URI.create("http://localhost/")));
    assertFalse(validator.validate(myResponse).isEmpty());
  }

  @Test
  void createCDRResponsePaginatedWithMissingLinksPaginated() {
    CDRResponsePaginated myResponse = new CDRResponsePaginated();
    myResponse.meta(new MetaPaginated());
    assertFalse(validator.validate(myResponse).isEmpty());
  }


  @Test
  void createCDRResponsePaginatedWithMissingLinksPaginatedSelf() {
    CDRResponsePaginated myResponse = new CDRResponsePaginated();
    myResponse.links(new LinksPaginated());
    myResponse.meta(new MetaPaginated());
    assertFalse(validator.validate(myResponse).isEmpty());
  }

}
