package io.biza.cdr.babelfish.tests.v1.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
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
import io.biza.cdr.babelfish.v1.model.banking.BankingAccount;
import io.biza.cdr.babelfish.v1.model.banking.BankingAccountDetail;
import io.biza.cdr.babelfish.v1.model.banking.BankingCreditCardAccount;
import io.biza.cdr.babelfish.v1.model.banking.BankingLoanAccount;
import io.biza.cdr.babelfish.v1.model.banking.BankingProduct;
import io.biza.cdr.babelfish.v1.model.banking.BankingTermDepositAccount;
import io.biza.cdr.babelfish.v1.model.common.LinksPaginated;
import io.biza.cdr.babelfish.v1.model.common.MetaPaginated;
import io.biza.cdr.babelfish.v1.response.ResponseBankingAccountList;
import io.biza.cdr.babelfish.v1.response.ResponseBankingProductList;
import io.biza.cdr.babelfish.v1.response.ResponseBankingProductListData;

@DisplayName("BankingLoanAccount V1 Tests")
public class BankingLoanAccountV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid BankingLoanAccount")
  void bankingLoanAccount() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_BANKING_LOAN_ACCOUNT).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_BANKING_LOAN_ACCOUNT).toString());
  }

  @Test
  @DisplayName("BankingLoanAccount Mandatory Fields")
  void bankingLoanAccountMandatoryFields() {
    BankingLoanAccount data = new BankingLoanAccount();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.setLoanEndDate(LocalDate.now());
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.nextInstalmentDate(LocalDate.now());
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.repaymentFrequency(Duration.ofDays(10));
    
    // Should now be valid payload
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
  }
}
