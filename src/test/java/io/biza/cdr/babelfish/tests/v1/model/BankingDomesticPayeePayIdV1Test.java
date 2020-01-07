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
import io.biza.cdr.babelfish.v1.enumerations.PayloadTypeBankingDomesticPayeePayId;
import io.biza.cdr.babelfish.v1.model.banking.BankingDomesticPayeePayId;

@DisplayName("BankingDomesticPayeePayId V1 Tests")
public class BankingDomesticPayeePayIdV1Test {
  private Validator validator;
  
  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid BankingDomesticPayeePayId")
  void bankingDomesticPayeePayId() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_BANKING_DOMESTIC_PAYEE_PAYID).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_BANKING_DOMESTIC_PAYEE_PAYID).toString());
  }
  
  @Test
  @DisplayName("BankingDomesticPayeePayId Mandatory Fields for Email")
  void bankingDomesticPayeePayIdMandatoryFieldsEmail() {
    BankingDomesticPayeePayId data = new BankingDomesticPayeePayId();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.type(PayloadTypeBankingDomesticPayeePayId.EMAIL);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.identifier("invalid email address");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.identifier("valid@email.com");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }
  
  @Test
  @DisplayName("BankingDomesticPayeePayId Mandatory Fields for ABN")
  void bankingDomesticPayeePayIdMandatoryFieldsABN() {
    BankingDomesticPayeePayId data = new BankingDomesticPayeePayId();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.type(PayloadTypeBankingDomesticPayeePayId.ABN);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    // invalid text abn
    data.identifier("invalid abn");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    // invalid length abn
    data.identifier("12341234");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    // checksum failure abn
    data.identifier("12345678901");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    // valid abn (Biza Pty Ltd) with spaces
    data.identifier("54 624 797 655");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    // valid abn (Biza Pty Ltd) without spaces
    data.identifier("54624797655");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }
  
  @Test
  @DisplayName("BankingDomesticPayeePayId Mandatory Fields for PhoneNumber")
  void bankingDomesticPayeePayIdMandatoryFieldsPhoneNumber() {
    BankingDomesticPayeePayId data = new BankingDomesticPayeePayId();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.type(PayloadTypeBankingDomesticPayeePayId.TELEPHONE);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    // invalid text phone
    data.identifier("invalid phone");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    // valid australian phone number
    data.identifier("(02) 3307 1234");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    // valid australian phone number in international format
    data.identifier("+61233071234");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    // valid australian mobile number with area code (should be valid for FNN's too)
    data.identifier("0401123123");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }
  
  @Test
  @DisplayName("BankingDomesticPayeePayId Mandatory Fields for OrgIdentifier")
  void bankingDomesticPayeePayIdMandatoryFieldsOrgIdentifier() {
    BankingDomesticPayeePayId data = new BankingDomesticPayeePayId();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.type(PayloadTypeBankingDomesticPayeePayId.ORG_IDENTIFIER);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    // random text
    // TODO: Find PayId Org Identifier
    data.identifier("org identifier");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

}
