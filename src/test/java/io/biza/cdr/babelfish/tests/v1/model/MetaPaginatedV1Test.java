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
import io.biza.cdr.babelfish.v1.model.common.MetaPaginated;
import io.biza.cdr.babelfish.v1.model.common.MetaPaginated;
import io.biza.cdr.babelfish.v1.model.common.MetaPaginated;
import io.biza.cdr.babelfish.v1.response.ResponseBankingAccountsBalanceById;

@DisplayName("MetaPaginated V1 Tests")
public class MetaPaginatedV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid MetaPaginated")
  void responseMetaPaginated() {
    MetaPaginated data = ModelConstants.DEFAULT_META_PAGINATED;
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("MetaPaginated Mandatory Fields")
  void responseMetaPaginatedMandatoryFields() {
    MetaPaginated data = new MetaPaginated();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.totalRecords(100);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.totalPages(10);
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }
}
