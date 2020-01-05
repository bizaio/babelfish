package io.biza.cdr.babelfish.tests.v1.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.net.URI;
import java.util.List;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.cdr.babelfish.tests.v1.ModelConstants;
import io.biza.cdr.babelfish.v1.model.CDRResponse;
import io.biza.cdr.babelfish.v1.model.common.AccountIdsList;
import io.biza.cdr.babelfish.v1.model.common.Links;
import io.biza.cdr.babelfish.v1.model.common.Meta;
import io.biza.cdr.babelfish.v1.request.RequestAccountIds;

@DisplayName("RequestAccountIds V1 Tests")
public class RequestAccountIdsTest {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid RequestAccountIds")
  void createValidRequestAccountIds() {
    RequestAccountIds data = new RequestAccountIds()
        .data(new AccountIdsList().accountIds(ModelConstants.DEFAULT_ACCOUNT_IDS));
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("Create valid RequestAccountIds with Meta")
  void createValidRequestAccountIdsWithMeta() {
    RequestAccountIds data = new RequestAccountIds()
        .data(new AccountIdsList().accountIds(ModelConstants.DEFAULT_ACCOUNT_IDS)).meta(new Meta());
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("Create invalid RequestAccountIds with empty AccountIds")
  void createInvalidRequestAccountIds() {
    RequestAccountIds data =
        new RequestAccountIds().data(new AccountIdsList().accountIds(List.of())).meta(new Meta());
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

}
