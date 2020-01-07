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
import io.biza.cdr.babelfish.v1.enumerations.PayloadTypeBankingScheduledPaymentTo;
import io.biza.cdr.babelfish.v1.model.banking.BankingScheduledPaymentSet;
import io.biza.cdr.babelfish.v1.model.banking.BankingScheduledPaymentTo;
import io.biza.cdr.babelfish.v1.model.banking.BankingProduct;
import io.biza.cdr.babelfish.v1.model.common.LinksPaginated;
import io.biza.cdr.babelfish.v1.model.common.MetaPaginated;
import io.biza.cdr.babelfish.v1.response.ResponseBankingProductList;
import io.biza.cdr.babelfish.v1.response.ResponseBankingProductListData;

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
    data.toUType(PayloadTypeBankingScheduledPaymentTo.ACCOUNT_ID);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    data.accountId(UUID.randomUUID().toString());
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }
  
  @Test
  @DisplayName("BankingScheduledPaymentTo for PayeeId")
  void bankingScheduledPaymentToMandatoryFieldsPayeeId() {
    BankingScheduledPaymentTo data = new BankingScheduledPaymentTo();
    data.toUType(PayloadTypeBankingScheduledPaymentTo.PAYEE_ID);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    data.payeeId(UUID.randomUUID().toString());
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }
  
  @Test
  @DisplayName("BankingScheduledPaymentTo for Domestic")
  void bankingScheduledPaymentToMandatoryFieldsDomestic() {
    BankingScheduledPaymentTo data = new BankingScheduledPaymentTo();
    data.toUType(PayloadTypeBankingScheduledPaymentTo.DOMESTIC);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    data.domestic(ModelConstants.DEFAULT_BANKING_DOMESTIC_PAYEE);
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }


  @Test
  @DisplayName("BankingScheduledPaymentTo for Biller")
  void bankingScheduledPaymentToMandatoryFieldsBiller() {
    BankingScheduledPaymentTo data = new BankingScheduledPaymentTo();
    data.toUType(PayloadTypeBankingScheduledPaymentTo.BILLER);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    data.biller(ModelConstants.DEFAULT_BANKING_BILLER_PAYEE);
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }
  
  @Test
  @DisplayName("BankingScheduledPaymentTo for International")
  void bankingScheduledPaymentToMandatoryFieldsInternational() {
    BankingScheduledPaymentTo data = new BankingScheduledPaymentTo();
    data.toUType(PayloadTypeBankingScheduledPaymentTo.INTERNATIONAL);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    data.international(ModelConstants.DEFAULT_BANKING_INTERNATIONAL_PAYEE);
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }
}
