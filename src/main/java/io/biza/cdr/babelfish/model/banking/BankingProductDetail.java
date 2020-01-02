package io.biza.cdr.babelfish.model.banking;

import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@Builder
@Data
@Valid
@BabelFishModel(description =  "Banking Product Detailed Information", parent = BankingProduct.class)
public class BankingProductDetail {
	
	@JsonUnwrapped
	@BabelFishModelProperty(
			hidden = true
	)	
	BankingProduct bankingProduct;

    @BabelFishModelProperty(
        description =  "An array of bundles that this product participates in.  Each bundle is described by free form information but also by a list of product IDs of the other products that are included in the bundle.  It is assumed that the current product is included in the bundle also"
    )
    List<BankingProductBundle> bundles;

    @BabelFishModelProperty(
        description =  "Array of features available for the product"
    )
    List<BankingProductFeature> features;

    @BabelFishModelProperty(
        description =  "Constraints on the application for or operation of the product such as minimum balances or limit thresholds"
    )
    List<BankingProductConstraint> constraints;

    @BabelFishModelProperty(
        description =  "Eligibility criteria for the product"
    )
    List<BankingProductEligibility> eligibility;

    @BabelFishModelProperty(
        description =  "Fees applicable for the product"
    )
    List<BankingProductFee> fees;

    @BabelFishModelProperty(
        description =  "Interest rates available for deposits"
    )
    List<BankingProductDepositRate> depositRates;

    @BabelFishModelProperty(
        description =  "Interest rates charged against lending balances"
    )
    List<BankingProductLendingRate> lendingRates;
}
