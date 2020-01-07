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
import io.biza.cdr.babelfish.v1.enumerations.BankingPayeeType;
import io.biza.cdr.babelfish.v1.model.banking.BankingPayee;

@DisplayName("BankingPayee V1 Tests")
public class BankingPayeeV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid BankingPayee")
  void bankingPayee() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_BANKING_PAYEE).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_BANKING_PAYEE).toString());
  }
  
  @Test
  @DisplayName("BankingPayee Mandatory Fields")
  void bankingPayeeMandatoryFields() {
    BankingPayee data = new BankingPayee();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.payeeId(UUID.randomUUID().toString());
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.setNickname("Payee Nickname");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.type(BankingPayeeType.DOMESTIC);
    
    // Should now be a valid payload
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

}
