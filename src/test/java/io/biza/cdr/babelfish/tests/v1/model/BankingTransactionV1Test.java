package io.biza.cdr.babelfish.tests.v1.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.cdr.babelfish.tests.v1.ModelConstants;
import io.biza.cdr.babelfish.v1.enumerations.BankingProductCategory;
import io.biza.cdr.babelfish.v1.enumerations.BankingTermDepositMaturityInstructions;
import io.biza.cdr.babelfish.v1.enumerations.BankingTransactionStatus;
import io.biza.cdr.babelfish.v1.enumerations.BankingTransactionType;
import io.biza.cdr.babelfish.v1.model.banking.BankingAccount;
import io.biza.cdr.babelfish.v1.model.banking.BankingAccountDetail;
import io.biza.cdr.babelfish.v1.model.banking.BankingProduct;
import io.biza.cdr.babelfish.v1.model.banking.BankingTermDepositAccount;
import io.biza.cdr.babelfish.v1.model.banking.BankingTransaction;
import io.biza.cdr.babelfish.v1.model.common.LinksPaginated;
import io.biza.cdr.babelfish.v1.model.common.MetaPaginated;
import io.biza.cdr.babelfish.v1.response.ResponseBankingAccountList;
import io.biza.cdr.babelfish.v1.response.ResponseBankingProductList;
import io.biza.cdr.babelfish.v1.response.ResponseBankingProductListData;

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
  void bankingAccountDetail() {
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
    
    data.status(BankingTransactionStatus.POSTED);
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
