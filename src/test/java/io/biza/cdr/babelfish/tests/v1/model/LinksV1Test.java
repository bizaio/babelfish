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
import io.biza.cdr.babelfish.v1.model.common.Links;
import io.biza.cdr.babelfish.v1.model.common.Links;
import io.biza.cdr.babelfish.v1.response.ResponseBankingAccountsBalanceById;

@DisplayName("Links V1 Tests")
public class LinksV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid Links")
  void responseLinks() {
    Links data = ModelConstants.DEFAULT_LINKS;
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("Links Mandatory Fields")
  void responseLinksMandatoryFields() {
    Links data = new Links();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.self(ModelConstants.DEFAULT_SELF_URI);
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }
}
