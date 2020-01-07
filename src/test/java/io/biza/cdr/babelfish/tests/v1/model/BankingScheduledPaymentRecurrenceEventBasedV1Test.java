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
import io.biza.cdr.babelfish.v1.model.banking.BankingScheduledPaymentRecurrenceEventBased;

@DisplayName("BankingScheduledPaymentRecurrenceEventBased V1 Tests")
public class BankingScheduledPaymentRecurrenceEventBasedV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid BankingScheduledPaymentRecurrenceEventBased")
  void bankingScheduledPaymentRecurrenceEventBased() {
    assertTrue(
        validator.validate(ModelConstants.DEFAULT_BANKING_SCHEDULED_PAYMENT_RECURRENCE_EVENT_BASED)
            .isEmpty(),
        validator.validate(ModelConstants.DEFAULT_BANKING_SCHEDULED_PAYMENT_RECURRENCE_EVENT_BASED)
            .toString());
  }

  @Test
  @DisplayName("BankingScheduledPaymentRecurrenceEventBased Mandatory Fields")
  void bankingScheduledPaymentRecurrenceEventBasedMandatoryFields() {
    BankingScheduledPaymentRecurrenceEventBased data =
        new BankingScheduledPaymentRecurrenceEventBased();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.description("Event Based Payment Description");

    // Should now be a valid payload
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

}
