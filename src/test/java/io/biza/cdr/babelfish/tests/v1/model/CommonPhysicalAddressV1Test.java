package io.biza.cdr.babelfish.tests.v1.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.cdr.babelfish.tests.v1.ModelConstants;
import io.biza.cdr.babelfish.v1.enumerations.PayloadTypeAddress;
import io.biza.cdr.babelfish.v1.model.common.CommonPhysicalAddress;

@DisplayName("CommonPhysicalAddress V1 Tests")
public class CommonPhysicalAddressV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid CommonPhysicalAddress")
  void responseCommonPhysicalAddress() {
    CommonPhysicalAddress data = ModelConstants.DEFAULT_COMMON_PHYSICAL_ADDRESS;
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("CommonPhysicalAddress Mandatory Fields (Simple)")
  void responseCommonPhysicalAddressMandatoryFieldsPerson() {
    CommonPhysicalAddress data = new CommonPhysicalAddress();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.addressType(PayloadTypeAddress.SIMPLE);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.simple(ModelConstants.DEFAULT_COMMON_SIMPLE_ADDRESS);
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    // Should only have one
    data.paf(ModelConstants.DEFAULT_COMMON_PAF_ADDRESS);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
  }
  
  @Test
  @DisplayName("CommonPhysicalAddress Mandatory Fields (PAF)")
  void responseCommonPhysicalAddressMandatoryFieldsOrganisation() {
    CommonPhysicalAddress data = new CommonPhysicalAddress();
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.addressType(PayloadTypeAddress.PAF);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    data.paf(ModelConstants.DEFAULT_COMMON_PAF_ADDRESS);
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
    // Should only have one
    data.simple(ModelConstants.DEFAULT_COMMON_SIMPLE_ADDRESS);
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
    
  }
}
