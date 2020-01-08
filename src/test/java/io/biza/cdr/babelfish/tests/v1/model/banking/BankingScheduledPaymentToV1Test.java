package io.biza.cdr.babelfish.tests.v1.model.banking;

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
import io.biza.cdr.babelfish.v1.enumerations.PayloadTypeBankingScheduledPaymentTo;
import io.biza.cdr.babelfish.v1.model.banking.BankingScheduledPaymentTo;

@DisplayName("BankingScheduledPaymentTo V1 Tests")
public class BankingScheduledPaymentToV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid BankingScheduledPaymentTo")
  void bankingScheduledPaymentTo() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_BANKING_SCHEDULED_PAYMENT_TO).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_BANKING_SCHEDULED_PAYMENT_TO).toString());
  }

  @Test
  @DisplayName("BankingScheduledPaymentTo for AccountId")
  void bankingScheduledPaymentToMandatoryFieldsAccountId() {
    BankingScheduledPaymentTo data = new BankingScheduledPaymentTo();
    data.type(PayloadTypeBankingScheduledPaymentTo.ACCOUNT_ID);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    data.accountId(UUID.randomUUID().toString());
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

  @Test
  @DisplayName("BankingScheduledPaymentTo for PayeeId")
  void bankingScheduledPaymentToMandatoryFieldsPayeeId() {
    BankingScheduledPaymentTo data = new BankingScheduledPaymentTo();
    data.type(PayloadTypeBankingScheduledPaymentTo.PAYEE_ID);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    data.payeeId(UUID.randomUUID().toString());
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("BankingScheduledPaymentTo for Domestic")
  void bankingScheduledPaymentToMandatoryFieldsDomestic() {
    BankingScheduledPaymentTo data = new BankingScheduledPaymentTo();
    data.type(PayloadTypeBankingScheduledPaymentTo.DOMESTIC);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    data.domestic(ModelConstants.DEFAULT_BANKING_DOMESTIC_PAYEE);
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }


  @Test
  @DisplayName("BankingScheduledPaymentTo for Biller")
  void bankingScheduledPaymentToMandatoryFieldsBiller() {
    BankingScheduledPaymentTo data = new BankingScheduledPaymentTo();
    data.type(PayloadTypeBankingScheduledPaymentTo.BILLER);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    data.biller(ModelConstants.DEFAULT_BANKING_BILLER_PAYEE);
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("BankingScheduledPaymentTo for International")
  void bankingScheduledPaymentToMandatoryFieldsInternational() {
    BankingScheduledPaymentTo data = new BankingScheduledPaymentTo();
    data.type(PayloadTypeBankingScheduledPaymentTo.INTERNATIONAL);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    data.international(ModelConstants.DEFAULT_BANKING_INTERNATIONAL_PAYEE);
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }
}
