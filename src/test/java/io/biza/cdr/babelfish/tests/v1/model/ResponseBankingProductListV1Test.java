package io.biza.cdr.babelfish.tests.v1.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.net.URI;
import java.util.List;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.biza.cdr.babelfish.tests.v1.ModelConstants;
import io.biza.cdr.babelfish.v1.model.CDRResponse;
import io.biza.cdr.babelfish.v1.model.CDRResponsePaginated;
import io.biza.cdr.babelfish.v1.model.banking.BankingProduct;
import io.biza.cdr.babelfish.v1.model.common.AccountIdsList;
import io.biza.cdr.babelfish.v1.model.common.Links;
import io.biza.cdr.babelfish.v1.model.common.LinksPaginated;
import io.biza.cdr.babelfish.v1.model.common.Meta;
import io.biza.cdr.babelfish.v1.model.common.MetaPaginated;
import io.biza.cdr.babelfish.v1.request.RequestAccountIds;
import io.biza.cdr.babelfish.v1.response.ResponseBankingProductList;
import io.biza.cdr.babelfish.v1.response.ResponseBankingProductListData;

@DisplayName("ResponseBankingProductList V1 Tests")
public class ResponseBankingProductListV1Test {
  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  @DisplayName("Create valid ResponseBankingProductList with empty Products")
  void createValidEmptyProductList() {
    ResponseBankingProductList myResponse = new ResponseBankingProductList()
        .links(new LinksPaginated().self(ModelConstants.DEFAULT_SELF_URI)
            .first(ModelConstants.DEFAULT_FIRST_URI).next(ModelConstants.DEFAULT_NEXT_URI)
            .last(ModelConstants.DEFAULT_LAST_URI).prev(ModelConstants.DEFAULT_PREV_URI))
        .meta(new MetaPaginated().totalPages(10).totalRecords(100))
        .data(new ResponseBankingProductListData().products(List.of()));
    assertTrue(validator.validate(myResponse).isEmpty(), validator.validate(myResponse).toString());
  }

  @Test
  @DisplayName("Create valid ResponseBankingProductList with empty Products")
  void createValidProductList() {
    ResponseBankingProductList myResponse = new ResponseBankingProductList()
        .links(new LinksPaginated().self(ModelConstants.DEFAULT_SELF_URI)
            .first(ModelConstants.DEFAULT_FIRST_URI).next(ModelConstants.DEFAULT_NEXT_URI)
            .last(ModelConstants.DEFAULT_LAST_URI).prev(ModelConstants.DEFAULT_PREV_URI))
        .meta(new MetaPaginated().totalPages(10).totalRecords(100))
        .data(new ResponseBankingProductListData()
            .products(List.of(ModelConstants.DEFAULT_BANKING_PRODUCT)));
    assertTrue(validator.validate(myResponse).isEmpty(), validator.validate(myResponse).toString());
  }

  @Test
  @DisplayName("Create valid ResponseBankingProductList with Invalid Product Data")
  void createValidProductListWithInvalidProduct() {
    ResponseBankingProductList myResponse = new ResponseBankingProductList()
        .links(new LinksPaginated().self(ModelConstants.DEFAULT_SELF_URI)
            .first(ModelConstants.DEFAULT_FIRST_URI).next(ModelConstants.DEFAULT_NEXT_URI)
            .last(ModelConstants.DEFAULT_LAST_URI).prev(ModelConstants.DEFAULT_PREV_URI))
        .meta(new MetaPaginated().totalPages(10).totalRecords(100))
        .data(new ResponseBankingProductListData().products(List.of(new BankingProduct())));
    assertFalse(validator.validate(myResponse).isEmpty(),
        validator.validate(myResponse).toString());
  }

}
