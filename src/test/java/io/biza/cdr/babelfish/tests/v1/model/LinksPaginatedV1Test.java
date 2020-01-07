package io.biza.cdr.babelfish.tests.v1.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Locale;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.cdr.babelfish.Constants;
import io.biza.cdr.babelfish.tests.v1.ModelConstants;
import io.biza.cdr.babelfish.v1.enumerations.PayloadTypeAddress;
import io.biza.cdr.babelfish.v1.enumerations.PayloadTypeCustomer;
import io.biza.cdr.babelfish.v1.model.common.LinksPaginated;
import io.biza.cdr.babelfish.v1.model.common.LinksPaginated;
import io.biza.cdr.babelfish.v1.response.ResponseBankingAccountsBalanceById;

@DisplayName("LinksPaginated V1 Tests")
public class LinksPaginatedV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid LinksPaginated")
  void responseLinksPaginated() {
    LinksPaginated data = ModelConstants.DEFAULT_LINKS_PAGINATED;
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("LinksPaginated Mandatory Fields (Middle Result)")
  void linksPaginatedMandatoryFieldsMiddleResult() {
    LinksPaginated data = new LinksPaginated();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.self(ModelConstants.DEFAULT_SELF_URI);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.first(ModelConstants.DEFAULT_FIRST_URI);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.prev(ModelConstants.DEFAULT_PREV_URI);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.next(ModelConstants.DEFAULT_NEXT_URI);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.last(ModelConstants.DEFAULT_LAST_URI);
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }
  
  @Test
  @DisplayName("LinksPaginated Mandatory Fields (First Result)")
  void linksPaginatedMandatoryFieldsFirstResult() {
    LinksPaginated data = new LinksPaginated();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.self(ModelConstants.DEFAULT_FIRST_URI);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.next(ModelConstants.DEFAULT_SELF_URI);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.last(ModelConstants.DEFAULT_LAST_URI);
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }
  
  @Test
  @DisplayName("LinksPaginated Mandatory Fields (Last Result)")
  void linksPaginatedMandatoryFieldsLastResult() {
    LinksPaginated data = new LinksPaginated();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.self(ModelConstants.DEFAULT_LAST_URI);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.first(ModelConstants.DEFAULT_FIRST_URI);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.prev(ModelConstants.DEFAULT_PREV_URI);
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }
  
  @Test
  @DisplayName("LinksPaginated Mandatory Fields (First and Last)")
  void linksPaginatedMandatoryFieldsFirstAndLastPage() {
    LinksPaginated data = new LinksPaginated();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.self(ModelConstants.DEFAULT_FIRST_URI);
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

}
