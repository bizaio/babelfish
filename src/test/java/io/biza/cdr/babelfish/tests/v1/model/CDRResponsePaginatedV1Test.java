package io.biza.cdr.babelfish.tests.v1.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.net.URI;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.LoggerFactory;
import io.biza.cdr.babelfish.v1.model.CDRResponsePaginated;
import io.biza.cdr.babelfish.v1.model.common.LinksPaginated;
import io.biza.cdr.babelfish.v1.model.common.MetaPaginated;

@DisplayName("CDR Paginated Response V1 Tests")
public class CDRResponsePaginatedV1Test {
  private Validator validator;

  public static URI DEFAULT_URI = URI.create("http://localhost/");

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  void createValidCDRResponsePaginated() {
    CDRResponsePaginated myResponse = new CDRResponsePaginated();
    myResponse
        .links(new LinksPaginated().self(DEFAULT_URI).first(DEFAULT_URI).next(DEFAULT_URI)
            .last(DEFAULT_URI).prev(DEFAULT_URI))
        .meta(new MetaPaginated().totalPages(10).totalRecords(100));
    assertTrue(validator.validate(myResponse).isEmpty(), validator.validate(myResponse).toString());
  }

  @Test
  void invalidResponsePaginatedWithMissingLinksPaginatedAndMetaPaginated() {
    CDRResponsePaginated myResponse = new CDRResponsePaginated();
    assertFalse(validator.validate(myResponse).isEmpty());
  }

  @Test
  void invalidResponsePaginatedWithMissingMetaPaginated() {
    CDRResponsePaginated myResponse = new CDRResponsePaginated();
    myResponse.links(new LinksPaginated().self(DEFAULT_URI).first(DEFAULT_URI).next(DEFAULT_URI)
        .last(DEFAULT_URI).prev(DEFAULT_URI));
    assertFalse(validator.validate(myResponse).isEmpty());
  }

  @Test
  void invalidResponsePaginatedWithMissingLinksPaginated() {
    CDRResponsePaginated myResponse = new CDRResponsePaginated();
    myResponse.meta(new MetaPaginated());
    assertFalse(validator.validate(myResponse).isEmpty());
  }


  @Test
  void invalidResponsePaginatedWithMissingLinksPaginatedSelf() {
    CDRResponsePaginated myResponse = new CDRResponsePaginated();
    myResponse.links(new LinksPaginated().first(DEFAULT_URI).next(DEFAULT_URI).last(DEFAULT_URI)
        .prev(DEFAULT_URI));
    myResponse.meta(new MetaPaginated());
    assertFalse(validator.validate(myResponse).isEmpty());
  }

  @Test
  void invalidResponsePaginatedWithPreviousPageButFirstPageMissing() {
    CDRResponsePaginated myResponse = new CDRResponsePaginated();
    myResponse.links(new LinksPaginated().self(DEFAULT_URI).next(DEFAULT_URI).last(DEFAULT_URI)
        .prev(DEFAULT_URI)).meta(new MetaPaginated().totalPages(10).totalRecords(100));
    assertFalse(validator.validate(myResponse).isEmpty(),
        validator.validate(myResponse).toString());
  }

  @Test
  void invalidResponsePaginatedWithNextPageButLastPageMissing() {
    CDRResponsePaginated myResponse = new CDRResponsePaginated();
    myResponse.links(new LinksPaginated().self(DEFAULT_URI).first(DEFAULT_URI).next(DEFAULT_URI)
        .prev(DEFAULT_URI)).meta(new MetaPaginated().totalPages(10).totalRecords(100));
    assertFalse(validator.validate(myResponse).isEmpty(),
        validator.validate(myResponse).toString());
  }

  @Test
  void invalidResponsePaginatedWithSinglePage() {
    CDRResponsePaginated myResponse = new CDRResponsePaginated();
    myResponse.links(new LinksPaginated().self(DEFAULT_URI).first(DEFAULT_URI).last(DEFAULT_URI))
        .meta(new MetaPaginated().totalPages(1).totalRecords(10));
    assertFalse(validator.validate(myResponse).isEmpty(),
        validator.validate(myResponse).toString());
  }

  @Test
  void invalidResponsePaginatedWithSinglePageAndMoreThanOnePageTotal() {
    CDRResponsePaginated myResponse = new CDRResponsePaginated();
    myResponse.links(new LinksPaginated().self(DEFAULT_URI))
        .meta(new MetaPaginated().totalPages(10).totalRecords(10));
    assertFalse(validator.validate(myResponse).isEmpty(),
        validator.validate(myResponse).toString());
  }

  @Test
  void invalidResponsePaginatedWithZeroRecords() {
    CDRResponsePaginated myResponse =
        (CDRResponsePaginated) new CDRResponsePaginated().links(new LinksPaginated().self(DEFAULT_URI))
        .meta(new MetaPaginated().totalPages(1).totalRecords(0));
    assertFalse(validator.validate(myResponse).isEmpty(),
        validator.validate(myResponse).toString());
  }
  /**
   * @Test void invalidResponsePaginatedWithZeroPages() { CDRResponsePaginated myResponse = new
   *       CDRResponsePaginated(); myResponse.links((new LinksPaginated()).self(DEFAULT_URI))
   *       .meta((new MetaPaginated()).totalPages(0).totalRecords(10));
   *       assertFalse(validator.validate(myResponse).isEmpty(),
   *       validator.validate(myResponse).toString()); }
   */

}
