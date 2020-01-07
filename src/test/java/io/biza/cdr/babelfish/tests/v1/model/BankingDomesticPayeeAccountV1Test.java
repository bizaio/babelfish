package io.biza.cdr.babelfish.tests.v1.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.cdr.babelfish.tests.v1.ModelConstants;
import io.biza.cdr.babelfish.v1.model.banking.BankingDomesticPayeeAccount;

@DisplayName("BankingDomesticPayeeAccount V1 Tests")
public class BankingDomesticPayeeAccountV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid BankingDomesticPayeeAccount")
  void bankingDomesticPayeeAccount() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_BANKING_DOMESTIC_PAYEE_ACCOUNT).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_BANKING_DOMESTIC_PAYEE_ACCOUNT).toString());
  }

  @Test
  @DisplayName("BankingDomesticPayeeAccount Mandatory Fields for Account")
  void bankingDomesticPayeeAccountMandatoryFieldsAccount() {
    BankingDomesticPayeeAccount data = new BankingDomesticPayeeAccount();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.bsb("123-123");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.accountNumber("12341234");

    // Should now be a valid payload
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

}
