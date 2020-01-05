package io.biza.cdr.babelfish.tests.v1.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.List;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.cdr.babelfish.tests.v1.ModelConstants;
import io.biza.cdr.babelfish.v1.model.CDRResponse;
import io.biza.cdr.babelfish.v1.model.CDRResponsePaginated;
import io.biza.cdr.babelfish.v1.model.banking.BankingProduct;
import io.biza.cdr.babelfish.v1.model.banking.BankingProductBundle;
import io.biza.cdr.babelfish.v1.model.common.AccountIdsList;
import io.biza.cdr.babelfish.v1.model.common.Links;
import io.biza.cdr.babelfish.v1.model.common.LinksPaginated;
import io.biza.cdr.babelfish.v1.model.common.Meta;
import io.biza.cdr.babelfish.v1.model.common.MetaPaginated;
import io.biza.cdr.babelfish.v1.request.RequestAccountIds;
import io.biza.cdr.babelfish.v1.response.ResponseBankingProductList;
import io.biza.cdr.babelfish.v1.response.ResponseBankingProductListData;

@DisplayName("BankingProductBundle V1 Tests")
public class BankingProductBundleV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid BankingProductBundle")
  void createValidBankingProductBundle() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_BANKING_PRODUCT_BUNDLE).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_BANKING_PRODUCT_BUNDLE).toString());
  }

  @Test
  @DisplayName("Create valid BankingProductBundle with empty name")
  void createBankingProductBundleWithEmptyName()
      throws IllegalAccessException, InvocationTargetException {
    BankingProductBundle data = new BankingProductBundle();
    BeanUtils.copyProperties(data, ModelConstants.DEFAULT_BANKING_PRODUCT_BUNDLE);
    data.name("");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

  @Test
  @DisplayName("Create valid BankingProductBundle with empty description")
  void createBankingProductBundleWithEmptyDescription()
      throws IllegalAccessException, InvocationTargetException {
    BankingProductBundle data = new BankingProductBundle();
    BeanUtils.copyProperties(data, ModelConstants.DEFAULT_BANKING_PRODUCT_BUNDLE);
    data.description("");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());
  }

}
