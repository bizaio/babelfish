package io.biza.cdr.babelfish.tests.v1.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDate;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.cdr.babelfish.tests.v1.ModelConstants;
import io.biza.cdr.babelfish.v1.model.banking.BankingScheduledPaymentRecurrenceOnceOff;

@DisplayName("BankingScheduledPaymentRecurrenceOnceOff V1 Tests")
public class BankingScheduledPaymentRecurrenceOnceOffV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid BankingScheduledPaymentRecurrenceOnceOff")
  void bankingScheduledPaymentRecurrenceOnceOff() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_BANKING_SCHEDULED_PAYMENT_RECURRENCE_ONCE_OFF).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_BANKING_SCHEDULED_PAYMENT_RECURRENCE_ONCE_OFF).toString());
  }
  
  @Test
  @DisplayName("BankingScheduledPaymentRecurrenceOnceOff Mandatory Fields")
  void bankingScheduledPaymentRecurrenceOnceOffMandatoryFields() {
    BankingScheduledPaymentRecurrenceOnceOff data = new BankingScheduledPaymentRecurrenceOnceOff();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.paymentDate(LocalDate.now());
    
    // Should now be a valid payload
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

}
