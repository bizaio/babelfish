package io.biza.cdr.babelfish.tests.v1.model.banking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.cdr.babelfish.tests.v1.ModelConstants;
import io.biza.cdr.babelfish.v1.enumerations.PayloadTypeBankingScheduledPaymentRecurrence;
import io.biza.cdr.babelfish.v1.model.banking.BankingScheduledPaymentRecurrence;

@DisplayName("BankingScheduledPaymentRecurrence V1 Tests")
public class BankingScheduledPaymentRecurrenceV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid BankingScheduledPaymentRecurrence")
  void bankingScheduledPaymentRecurrence() {
    assertTrue(
        validator.validate(ModelConstants.DEFAULT_BANKING_SCHEDULED_PAYMENT_RECURRENCE).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_BANKING_SCHEDULED_PAYMENT_RECURRENCE).toString());
  }

  @Test
  @DisplayName("BankingScheduledPaymentRecurrence Mandatory Fields Once Off")
  void bankingScheduledPaymentRecurrenceMandatoryFieldsOnceOff() {
    BankingScheduledPaymentRecurrence data = new BankingScheduledPaymentRecurrence();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.recurrenceUType(PayloadTypeBankingScheduledPaymentRecurrence.ONCE_OFF);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.onceOff(ModelConstants.DEFAULT_BANKING_SCHEDULED_PAYMENT_RECURRENCE_ONCE_OFF);
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }


  @Test
  @DisplayName("BankingScheduledPaymentRecurrence Mandatory Fields Interval Schedule")
  void bankingScheduledPaymentRecurrenceMandatoryFieldsIntervalSchedule() {
    BankingScheduledPaymentRecurrence data = new BankingScheduledPaymentRecurrence();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.recurrenceUType(PayloadTypeBankingScheduledPaymentRecurrence.INTERVAL_SCHEDULE);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.intervalSchedule(
        ModelConstants.DEFAULT_BANKING_SCHEDULED_PAYMENT_RECURRENCE_INTERVAL_SCHEDULE);
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

  @Test
  @DisplayName("BankingScheduledPaymentRecurrence Mandatory Fields Last Week Day")
  void bankingScheduledPaymentRecurrenceMandatoryFieldsLastWeekDay() {
    BankingScheduledPaymentRecurrence data = new BankingScheduledPaymentRecurrence();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.recurrenceUType(PayloadTypeBankingScheduledPaymentRecurrence.LAST_WEEKDAY);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.lastWeekDay(ModelConstants.DEFAULT_BANKING_SCHEDULED_PAYMENT_RECURRENCE_LAST_WEEKDAY);
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

  @Test
  @DisplayName("BankingScheduledPaymentRecurrence Mandatory Fields Event Based")
  void bankingScheduledPaymentRecurrenceMandatoryFieldsEventBased() {
    BankingScheduledPaymentRecurrence data = new BankingScheduledPaymentRecurrence();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.recurrenceUType(PayloadTypeBankingScheduledPaymentRecurrence.EVENT_BASED);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.eventBased(ModelConstants.DEFAULT_BANKING_SCHEDULED_PAYMENT_RECURRENCE_EVENT_BASED);
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

}
