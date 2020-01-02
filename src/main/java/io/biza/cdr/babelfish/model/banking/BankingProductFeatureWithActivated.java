package io.biza.cdr.babelfish.model.banking;

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
@BabelFishModel(modelName = "BankingAccountProductFeature", description = "Banking Account Product Feature", parent = BankingProductFeature.class)
public class BankingProductFeatureWithActivated {

	@JsonUnwrapped
	@BabelFishModelProperty(
			hidden = true
	)
	@Valid
	BankingProductFeature bankingProductFeature;
	
    @BabelFishModelProperty(
            description = "True if the feature is already activated and false if the feature is available for activation."
    )
    @Builder.Default
    Boolean isActivated = true;
}
