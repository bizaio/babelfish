package io.biza.cdr.babelfish.tests.v1.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.cdr.babelfish.tests.v1.ModelConstants;
import io.biza.cdr.babelfish.v1.model.banking.BankingScheduledPaymentRecurrenceIntervalSchedule;

@DisplayName("BankingScheduledPaymentRecurrenceIntervalSchedule V1 Tests")
public class BankingScheduledPaymentRecurrenceIntervalScheduleV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid BankingScheduledPaymentRecurrenceIntervalSchedule")
  void bankingScheduledPaymentRecurrenceIntervalSchedule() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_BANKING_SCHEDULED_PAYMENT_RECURRENCE_INTERVAL_SCHEDULE).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_BANKING_SCHEDULED_PAYMENT_RECURRENCE_INTERVAL_SCHEDULE).toString());
  }
  
  @Test
  @DisplayName("BankingScheduledPaymentRecurrenceIntervalSchedule Mandatory Fields")
  void bankingScheduledPaymentRecurrenceIntervalScheduleMandatoryFields() {
    BankingScheduledPaymentRecurrenceIntervalSchedule data = new BankingScheduledPaymentRecurrenceIntervalSchedule();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.intervals(List.of(ModelConstants.DEFAULT_BANKING_SCHEDULED_PAYMENT_INTERVAL));
    
    // Should now be a valid payload
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

}
