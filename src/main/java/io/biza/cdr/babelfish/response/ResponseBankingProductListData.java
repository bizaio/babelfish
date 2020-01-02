package io.biza.cdr.babelfish.response;

import java.util.List;

import javax.validation.Valid;

import io.biza.cdr.babelfish.model.banking.BankingProduct;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Data
@Valid
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
public class ResponseBankingProductListData {

    @BabelFishModelProperty(
        description =  "The list of products returned.  If the filter results in an empty set then this array may have no records",
        required = true
    )
    List<BankingProduct> products;
}
