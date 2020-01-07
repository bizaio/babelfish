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
import io.biza.cdr.babelfish.v1.enumerations.PayloadTypeBankingDomesticPayee;
import io.biza.cdr.babelfish.v1.model.banking.BankingDomesticPayee;

@DisplayName("BankingDomesticPayee V1 Tests")
public class BankingDomesticPayeeV1Test {
  private Validator validator;
  
  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid BankingDomesticPayee")
  void bankingDomesticPayee() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_BANKING_DOMESTIC_PAYEE).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_BANKING_DOMESTIC_PAYEE).toString());
  }
  
  @Test
  @DisplayName("BankingDomesticPayee Mandatory Fields for Account")
  void bankingDomesticPayeeMandatoryFieldsAccount() {
    BankingDomesticPayee data = new BankingDomesticPayee();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.payeeAccountType(PayloadTypeBankingDomesticPayee.ACCOUNT);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.account(ModelConstants.DEFAULT_BANKING_DOMESTIC_PAYEE_ACCOUNT);
    
    // Should now be a valid payload
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }
  
  @Test
  @DisplayName("BankingDomesticPayee Mandatory Fields for Card")
  void bankingDomesticPayeeMandatoryFieldsCard() {
    BankingDomesticPayee data = new BankingDomesticPayee();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.payeeAccountType(PayloadTypeBankingDomesticPayee.CARD);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.card(ModelConstants.DEFAULT_BANKING_DOMESTIC_PAYEE_CARD);
    
    // Should now be a valid payload
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }
  
  @Test
  @DisplayName("BankingDomesticPayee Mandatory Fields for PayId")
  void bankingDomesticPayeeMandatoryFieldsPayId() {
    BankingDomesticPayee data = new BankingDomesticPayee();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.payeeAccountType(PayloadTypeBankingDomesticPayee.PAY_ID);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.payId(ModelConstants.DEFAULT_BANKING_DOMESTIC_PAYEE_PAYID);
    
    // Should now be a valid payload
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

}
