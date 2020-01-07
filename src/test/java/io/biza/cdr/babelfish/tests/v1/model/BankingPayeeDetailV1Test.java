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
import io.biza.cdr.babelfish.v1.enumerations.PayloadTypeBankingPayee;
import io.biza.cdr.babelfish.v1.model.banking.BankingAccount;
import io.biza.cdr.babelfish.v1.model.banking.BankingBalance;
import io.biza.cdr.babelfish.v1.model.banking.BankingPayee;
import io.biza.cdr.babelfish.v1.model.banking.BankingPayeeDetail;
import io.biza.cdr.babelfish.v1.model.banking.BankingProduct;
import io.biza.cdr.babelfish.v1.model.common.LinksPaginated;
import io.biza.cdr.babelfish.v1.model.common.MetaPaginated;
import io.biza.cdr.babelfish.v1.response.ResponseBankingAccountList;
import io.biza.cdr.babelfish.v1.response.ResponseBankingProductList;
import io.biza.cdr.babelfish.v1.response.ResponseBankingProductListData;

@DisplayName("BankingPayeeDetail V1 Tests")
public class BankingPayeeDetailV1Test {
  private Validator validator;
  
  // TODO: Correlate BankingPayeeType with PayloadTypeBankingPayee

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid BankingPayeeDetail")
  void bankingPayeeDetail() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_BANKING_PAYEE_DETAIL).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_BANKING_PAYEE_DETAIL).toString());
  }
  
  @Test
  @DisplayName("BankingPayeeDetail Mandatory Fields for Domestic")
  void bankingPayeeDetailMandatoryFieldsDomestic() {
    BankingPayeeDetail data = new BankingPayeeDetail();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.payeeId(UUID.randomUUID().toString());
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.nickname("Payee Nickname");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.type(BankingPayeeType.DOMESTIC);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.payeeUType(PayloadTypeBankingPayee.DOMESTIC);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.domestic(ModelConstants.DEFAULT_BANKING_DOMESTIC_PAYEE);
    
    // Should now be a valid payload
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }
  
  @Test
  @DisplayName("BankingPayeeDetail Mandatory Fields for Biller")
  void bankingPayeeDetailMandatoryFieldsBiller() {
    BankingPayeeDetail data = new BankingPayeeDetail();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.payeeId(UUID.randomUUID().toString());
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.nickname("Payee Nickname");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.type(BankingPayeeType.BILLER);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.payeeUType(PayloadTypeBankingPayee.BILLER);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.biller(ModelConstants.DEFAULT_BANKING_BILLER_PAYEE);
    
    // Should now be a valid payload
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }
  
  @Test
  @DisplayName("BankingPayeeDetail Mandatory Fields for International")
  void bankingPayeeDetailMandatoryFieldsInternational() {
    BankingPayeeDetail data = new BankingPayeeDetail();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.payeeId(UUID.randomUUID().toString());
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.nickname("Payee Nickname");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.type(BankingPayeeType.INTERNATIONAL);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.payeeUType(PayloadTypeBankingPayee.INTERNATIONAL);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.international(ModelConstants.DEFAULT_BANKING_INTERNATIONAL_PAYEE);
    
    // Should now be a valid payload
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

}