package io.biza.cdr.babelfish.tests.v1.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.math.BigDecimal;
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
import io.biza.cdr.babelfish.v1.enumerations.CommonEmailAddressPurpose;
import io.biza.cdr.babelfish.v1.model.banking.BankingAccount;
import io.biza.cdr.babelfish.v1.model.banking.BankingBalance;
import io.biza.cdr.babelfish.v1.model.banking.BankingProduct;
import io.biza.cdr.babelfish.v1.model.common.CommonEmailAddress;
import io.biza.cdr.babelfish.v1.model.common.LinksPaginated;
import io.biza.cdr.babelfish.v1.model.common.MetaPaginated;
import io.biza.cdr.babelfish.v1.response.ResponseBankingAccountList;
import io.biza.cdr.babelfish.v1.response.ResponseBankingProductList;
import io.biza.cdr.babelfish.v1.response.ResponseBankingProductListData;

@DisplayName("CommonEmailAddress V1 Tests")
public class CommonEmailAddressV1Test {
  private Validator validator;
  
  // TODO: Verify one and only one isPreferred in set

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid CommonEmailAddress")
  void commonPhoneNumber() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_COMMON_EMAIL_ADDRESS).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_COMMON_EMAIL_ADDRESS).toString());
  }
  
  @Test
  @DisplayName("CommonEmailAddress Mandatory Fields (Home)")
  void commonPhoneNumberMandatoryFieldsHome() {
    CommonEmailAddress data = new CommonEmailAddress();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.purpose(CommonEmailAddressPurpose.HOME);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.address("test@test.com");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.address("invalid");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
  }
  
}
