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
import io.biza.cdr.babelfish.v1.model.banking.BankingScheduledPaymentSet;

@DisplayName("BankingScheduledPaymentSet V1 Tests")
public class BankingScheduledPaymentSetV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid BankingScheduledPaymentSet")
  void bankingScheduledPayment() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_BANKING_SCHEDULED_PAYMENT_SET).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_BANKING_SCHEDULED_PAYMENT_SET).toString());
  }

  @Test
  @DisplayName("BankingScheduledPaymentSet Mandatory Fields")
  void bankingScheduledPaymentMandatoryFields() {
    BankingScheduledPaymentSet data = new BankingScheduledPaymentSet();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.to(ModelConstants.DEFAULT_BANKING_SCHEDULED_PAYMENT_TO);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.amount(new BigDecimal("10.00"));
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
  }

  @Test
  @DisplayName("BankingScheduledPaymentSet Mandatory Fields with Amount Calculated")
  void bankingScheduledPaymentMandatoryFieldsAmountCalculated() {
    BankingScheduledPaymentSet data = new BankingScheduledPaymentSet().to(ModelConstants.DEFAULT_BANKING_SCHEDULED_PAYMENT_TO);
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.isAmountCalculated(true);
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
  }

  
}
