package io.biza.cdr.babelfish.tests.v1.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.Period;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.cdr.babelfish.tests.v1.ModelConstants;
import io.biza.cdr.babelfish.v1.model.banking.BankingScheduledPaymentInterval;

@DisplayName("BankingScheduledPaymentInterval V1 Tests")
public class BankingScheduledPaymentIntervalV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid BankingScheduledPaymentInterval")
  void bankingScheduledPaymentInterval() {
    assertTrue(
        validator.validate(ModelConstants.DEFAULT_BANKING_SCHEDULED_PAYMENT_INTERVAL).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_BANKING_SCHEDULED_PAYMENT_INTERVAL).toString());
  }

  @Test
  @DisplayName("BankingScheduledPaymentInterval Mandatory Fields")
  void bankingScheduledPaymentIntervalMandatoryFields() {
    BankingScheduledPaymentInterval data = new BankingScheduledPaymentInterval();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.interval(Period.ofDays(30));

    // Should now be a valid payload
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

}
