package io.biza.cdr.babelfish.tests.v1.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Locale;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.cdr.babelfish.Constants;
import io.biza.cdr.babelfish.tests.v1.ModelConstants;
import io.biza.cdr.babelfish.v1.enumerations.AddressPAFStateType;
import io.biza.cdr.babelfish.v1.enumerations.PayloadTypeAddress;
import io.biza.cdr.babelfish.v1.enumerations.PayloadTypeCustomer;
import io.biza.cdr.babelfish.v1.model.common.CommonPAFAddress;
import io.biza.cdr.babelfish.v1.model.common.Links;
import io.biza.cdr.babelfish.v1.response.ResponseBankingAccountsBalanceById;

@DisplayName("CommonPAFAddress V1 Tests")
public class CommonPAFAddressV1Test {
  private Validator validator;
  
  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid CommonPAFAddress")
  void responseCommonPAFAddress() {
    CommonPAFAddress data = ModelConstants.DEFAULT_COMMON_PAF_ADDRESS;
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("CommonPAFAddress Mandatory Fields")
  void responseCommonPAFAddressMandatoryFieldsAustralia() {
    CommonPAFAddress data = new CommonPAFAddress();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.localityName("Cobargo");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.postcode("2550");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.state(AddressPAFStateType.NSW);
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
  }
  
}
