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
import io.biza.cdr.babelfish.v1.enumerations.CommonOrganisationType;
import io.biza.cdr.babelfish.v1.model.banking.BankingAccount;
import io.biza.cdr.babelfish.v1.model.banking.BankingBalance;
import io.biza.cdr.babelfish.v1.model.banking.BankingProduct;
import io.biza.cdr.babelfish.v1.model.common.CommonOrganisationDetail;
import io.biza.cdr.babelfish.v1.model.common.LinksPaginated;
import io.biza.cdr.babelfish.v1.model.common.MetaPaginated;
import io.biza.cdr.babelfish.v1.response.ResponseBankingAccountList;
import io.biza.cdr.babelfish.v1.response.ResponseBankingProductList;
import io.biza.cdr.babelfish.v1.response.ResponseBankingProductListData;

@DisplayName("CommonOrganisationDetail V1 Tests")
public class CommonOrganisationDetailV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid CommonOrganisationDetail")
  void commonPerson() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_COMMON_ORGANISATION_DETAIL).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_COMMON_ORGANISATION_DETAIL).toString());
  }
  
  @Test
  @DisplayName("CommonOrganisationDetail Mandatory Fields Other Organisation")
  void commonPersonMandatoryFieldsOther() {
    CommonOrganisationDetail data = new CommonOrganisationDetail();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.agentLastName("Last");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.businessName("Organisation Business Name");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.organisationType(CommonOrganisationType.OTHER);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.physicalAddresses(List.of(ModelConstants.DEFAULT_COMMON_PHYSICAL_ADDRESS_WITH_PURPOSE));
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
  }
  
  @Test
  @DisplayName("CommonOrganisationDetail Mandatory Fields Company")
  void commonPersonMandatoryFieldsCompany() {
    CommonOrganisationDetail data = new CommonOrganisationDetail();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.agentLastName("Last");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.businessName("Organisation Business Name");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.organisationType(CommonOrganisationType.COMPANY);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.acn("624123123");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.physicalAddresses(List.of(ModelConstants.DEFAULT_COMMON_PHYSICAL_ADDRESS_WITH_PURPOSE));
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
  }

}
