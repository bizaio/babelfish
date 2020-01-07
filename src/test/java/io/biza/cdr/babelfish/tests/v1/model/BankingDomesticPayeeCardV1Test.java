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
import io.biza.cdr.babelfish.v1.model.banking.BankingDomesticPayeeCard;
import io.biza.cdr.babelfish.v1.model.banking.BankingProduct;
import io.biza.cdr.babelfish.v1.model.common.LinksPaginated;
import io.biza.cdr.babelfish.v1.model.common.MetaPaginated;
import io.biza.cdr.babelfish.v1.response.ResponseBankingAccountList;
import io.biza.cdr.babelfish.v1.response.ResponseBankingProductList;
import io.biza.cdr.babelfish.v1.response.ResponseBankingProductListData;

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
