package io.biza.cdr.babelfish.tests.v1.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.math.BigDecimal;
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
import io.biza.cdr.babelfish.v1.model.banking.BankingAccount;
import io.biza.cdr.babelfish.v1.model.banking.BankingBalance;
import io.biza.cdr.babelfish.v1.model.banking.BankingDirectDebit;
import io.biza.cdr.babelfish.v1.model.banking.BankingProduct;
import io.biza.cdr.babelfish.v1.model.common.LinksPaginated;
import io.biza.cdr.babelfish.v1.model.common.MetaPaginated;
import io.biza.cdr.babelfish.v1.response.ResponseBankingAccountList;
import io.biza.cdr.babelfish.v1.response.ResponseBankingProductList;
import io.biza.cdr.babelfish.v1.response.ResponseBankingProductListData;

@DisplayName("BankingDirectDebit V1 Tests")
public class BankingDirectDebitV1Test {
  private Validator validator;
  
  // TODO: Add lastDebitAmount check when lastDebitDateTime is defined

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid BankingDirectDebit")
  void bankingDirectDebit() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_BANKING_DIRECT_DEBIT).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_BANKING_DIRECT_DEBIT).toString());
  }
  
  @Test
  @DisplayName("BankingDirectDebit Mandatory Fields")
  void bankingDirectDebitMandatoryFields() {
    BankingDirectDebit data = new BankingDirectDebit();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.accountId(UUID.randomUUID().toString());
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.authorisedEntity(ModelConstants.DEFAULT_BANKING_AUTHORISED_ENTITY);

    // Should now be a valid payload
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

}
