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
import io.biza.cdr.babelfish.v1.model.banking.BankingDomesticPayeeCard;

@DisplayName("BankingDomesticPayeeCard V1 Tests")
public class BankingDomesticPayeeCardV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid BankingDomesticPayeeCard")
  void bankingDomesticPayeeCard() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_BANKING_DOMESTIC_PAYEE_CARD).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_BANKING_DOMESTIC_PAYEE_CARD).toString());
  }

  @Test
  @DisplayName("BankingDomesticPayeeCard Mandatory Fields")
  void bankingDomesticPayeeCardMandatoryFieldsAccount() {
    BankingDomesticPayeeCard data = new BankingDomesticPayeeCard();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Unmasked PAN First
    data.cardNumber("1234 1234 1234 1234");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Unmasked PAN First with hyphens
    data.cardNumber("1234-1234-1234-1234");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Masked PAN
    data.cardNumber("XXXX XXXX XXXX 1234");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Masked PAN with Hyphens
    data.cardNumber("XXXX-XXXX-XXXX-1234");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

}
