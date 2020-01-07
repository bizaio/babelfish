package io.biza.cdr.babelfish.tests.v1.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.UUID;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.cdr.babelfish.tests.v1.ModelConstants;
import io.biza.cdr.babelfish.v1.enumerations.BankingProductCategory;
import io.biza.cdr.babelfish.v1.model.banking.BankingAccount;

@DisplayName("Banking V1 Tests")
public class BankingAccountV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid BankingAccount")
  void bankingAccount() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_BANKING_ACCOUNT).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_BANKING_ACCOUNT).toString());
  }

  @Test
  @DisplayName("BankingAccount Mandatory Fields")
  void bankingAccountMandatoryFields() {
    BankingAccount data = new BankingAccount();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.setAccountId(UUID.randomUUID().toString());
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.displayName("Display Name");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.maskedNumber("XXXX XXXX XXXX 1234");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.productCategory(BankingProductCategory.BUSINESS_LOANS);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.productName("Business Loan Example");

    // Should now be a valid payload
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }
}
