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
import io.biza.cdr.babelfish.v1.enumerations.BankingPayeeType;
import io.biza.cdr.babelfish.v1.enumerations.BankingProductCategory;
import io.biza.cdr.babelfish.v1.model.banking.BankingAccount;
import io.biza.cdr.babelfish.v1.model.banking.BankingBalance;
import io.biza.cdr.babelfish.v1.model.banking.BankingPayee;
import io.biza.cdr.babelfish.v1.model.banking.BankingProduct;
import io.biza.cdr.babelfish.v1.model.banking.BankingScheduledPaymentFrom;
import io.biza.cdr.babelfish.v1.model.common.LinksPaginated;
import io.biza.cdr.babelfish.v1.model.common.MetaPaginated;
import io.biza.cdr.babelfish.v1.response.ResponseBankingAccountList;
import io.biza.cdr.babelfish.v1.response.ResponseBankingProductList;
import io.biza.cdr.babelfish.v1.response.ResponseBankingProductListData;

@DisplayName("BankingScheduledPaymentFrom V1 Tests")
public class BankingScheduledPaymentFromV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid BankingScheduledPaymentFrom")
  void bankingScheduledPaymentFrom() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_BANKING_SCHEDULED_PAYMENT_FROM).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_BANKING_SCHEDULED_PAYMENT_FROM).toString());
  }
  
  @Test
  @DisplayName("BankingScheduledPaymentFrom Mandatory Fields")
  void bankingScheduledPaymentFromMandatoryFields() {
    BankingScheduledPaymentFrom data = new BankingScheduledPaymentFrom();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.accountId(UUID.randomUUID().toString());
    
    // Should now be a valid payload
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

}
