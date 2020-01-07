package io.biza.cdr.babelfish.tests.v1.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.math.BigDecimal;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.cdr.babelfish.tests.v1.ModelConstants;
import io.biza.cdr.babelfish.v1.model.banking.BankingBalancePurse;

@DisplayName("BankingBalancePurse V1 Tests")
public class BankingBalancePurseV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid BankingBalancePurse")
  void bankingBalancePurse() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_BANKING_BALANCE_PURSE).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_BANKING_BALANCE_PURSE).toString());
  }

  @Test
  @DisplayName("BankingBalancePurse Mandatory Fields")
  void bankingBalanceMandatoryFields() {
    BankingBalancePurse data = new BankingBalancePurse();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.amount(new BigDecimal("100.00"));

    // Should now be a valid payload
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

}
