package io.biza.cdr.babelfish.tests.v1.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
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
import io.biza.cdr.babelfish.v1.enumerations.BankingProductDiscountType;
import io.biza.cdr.babelfish.v1.enumerations.BankingProductFeatureType;
import io.biza.cdr.babelfish.v1.model.CDRResponse;
import io.biza.cdr.babelfish.v1.model.CDRResponsePaginated;
import io.biza.cdr.babelfish.v1.model.banking.BankingProduct;
import io.biza.cdr.babelfish.v1.model.banking.BankingProductBundle;
import io.biza.cdr.babelfish.v1.model.banking.BankingProductFeature;
import io.biza.cdr.babelfish.v1.model.banking.BankingProductFeeDiscount;
import io.biza.cdr.babelfish.v1.model.common.AccountIdsList;
import io.biza.cdr.babelfish.v1.model.common.Links;
import io.biza.cdr.babelfish.v1.model.common.LinksPaginated;
import io.biza.cdr.babelfish.v1.model.common.Meta;
import io.biza.cdr.babelfish.v1.model.common.MetaPaginated;
import io.biza.cdr.babelfish.v1.request.RequestAccountIds;
import io.biza.cdr.babelfish.v1.response.ResponseBankingProductList;
import io.biza.cdr.babelfish.v1.response.ResponseBankingProductListData;

@DisplayName("BankingProductFeeDiscount V1 Tests")
public class BankingProductFeeDiscountV1Test {
  private Validator validator;
  
  // TODO: Test amount, balanceRate, transactionRate, feeRate, accruedRate variability

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }
  
  // TODO: Enforce description field

  @Test
  @DisplayName("Valid BankingProductFeeDiscount")
  void bankingProductFeeDiscount() {
    assertTrue(validator.validate(ModelConstants.DEFAULT_BANKING_PRODUCT_FEE_DISCOUNT).isEmpty(),
        validator.validate(ModelConstants.DEFAULT_BANKING_PRODUCT_FEE_DISCOUNT).toString());
  }

  @Test
  @DisplayName("BankingProductFeeDiscount for Balance")
  void bankingProductFeeDiscountBalance() {
    BankingProductFeeDiscount data =
        new BankingProductFeeDiscount().description("Discount Description").amount(new BigDecimal("10.00")).discountType(BankingProductDiscountType.BALANCE);

    // Null Value invalid
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Invalid number
    data.additionalValue("Not A Number");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // A number value which is not in AmountString format is invalid
    data.additionalValue("10");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // AmountString formatted value is valid
    data.additionalValue("10.00");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

  @Test
  @DisplayName("BankingProductFeeDiscount for Deposits")
  void bankingProductFeeDiscountDeposits() {
    BankingProductFeeDiscount data =
        new BankingProductFeeDiscount().description("Discount Description").amount(new BigDecimal("10.00")).discountType(BankingProductDiscountType.DEPOSITS);

    // Null Value invalid
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Invalid number
    data.additionalValue("Not A Number");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // A number value which is not in AmountString format is invalid
    data.additionalValue("10");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // AmountString formatted value is valid
    data.additionalValue("10.00");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

  @Test
  @DisplayName("BankingProductFeeDiscount for Payments")
  void bankingProductFeeDiscountPayments() {
    BankingProductFeeDiscount data =
        new BankingProductFeeDiscount().description("Discount Description").amount(new BigDecimal("10.00")).discountType(BankingProductDiscountType.PAYMENTS);

    // Null Value invalid
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Invalid number
    data.additionalValue("Not A Number");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // A number value which is not in AmountString format is invalid
    data.additionalValue("10");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // AmountString formatted value is valid
    data.additionalValue("10.00");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

  }

  @Test
  @DisplayName("BankingProductFeeDiscount for Fee Cap")
  void bankingProductFeeDiscountFeeCap() {
    BankingProductFeeDiscount data =
        new BankingProductFeeDiscount().description("Discount Description").amount(new BigDecimal("10.00")).discountType(BankingProductDiscountType.FEE_CAP);

    // Null Value invalid
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Invalid String
    data.additionalValue("Not a Duration");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Duration String formatted value is valid
    data.additionalValue("P1D");
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // TODO: Add tests for associated rate entry fields

  }

  @Test
  @DisplayName("BankingProductFeeDiscount for Eligibility Only")
  void bankingProductFeeDiscountEligibilityOnly() {
    BankingProductFeeDiscount data =
        new BankingProductFeeDiscount().description("Discount Description").amount(new BigDecimal("10.00")).discountType(BankingProductDiscountType.FEE_CAP);

    // Null Value is correct
    assertTrue(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // Any value specified is invalid
    data.additionalValue("Invalid");
    assertFalse(validator.validate(data).isEmpty(), validator.validate(data).toString());

    // TODO: Add tests for associated eligibility field population

  }

}
