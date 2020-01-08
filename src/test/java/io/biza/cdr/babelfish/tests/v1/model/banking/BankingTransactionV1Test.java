package io.biza.cdr.babelfish.tests.v1.model.banking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.cdr.babelfish.tests.v1.ModelConstants;
import io.biza.cdr.babelfish.v1.enumerations.BankingTransactionStatus;
import io.biza.cdr.babelfish.v1.enumerations.BankingTransactionType;
import io.biza.cdr.babelfish.v1.model.banking.BankingTransaction;

@DisplayName("BankingTransaction V1 Tests")
public class BankingTransactionV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid BankingTransaction")
  void bankingTransaction() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_BANKING_TRANSACTION).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_BANKING_TRANSACTION).toString());
  }

  @Test
  @DisplayName("BankingTransaction Mandatory Fields for POSTED Transactions")
  void bankingTransactionMandatoryFieldsPosted() {
    BankingTransaction data = new BankingTransaction();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.accountId(UUID.randomUUID().toString());
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.transactionId(UUID.randomUUID().toString());
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.isDetailAvailable(false);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.type(BankingTransactionType.PAYMENT);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.status(BankingTransactionStatus.POSTED);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    data.description("Transaction Description");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
        
    data.amount(new BigDecimal("10.00"));
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.reference("");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.postingDateTime(LocalDateTime.now());
    // Should now be valid
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
  }
  
  @Test
  @DisplayName("BankingTransaction Mandatory Fields for PENDING Transactions")
  void bankingTransactionMandatoryFieldsPending() {
    BankingTransaction data = new BankingTransaction();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.accountId(UUID.randomUUID().toString());
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.transactionId(UUID.randomUUID().toString());
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.isDetailAvailable(false);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.type(BankingTransactionType.PAYMENT);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.status(BankingTransactionStatus.PENDING);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.description("Transaction Description");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.amount(new BigDecimal("10.00"));
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.reference("");
    
    // Should now be valid
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
  }
}
