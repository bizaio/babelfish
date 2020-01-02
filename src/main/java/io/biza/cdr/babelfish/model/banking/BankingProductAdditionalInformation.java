package io.biza.cdr.babelfish.model.banking;

import java.net.URI;

import javax.validation.Valid;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.converters.UriToUriStringConverter;
import io.biza.cdr.babelfish.support.BabelFishModel;
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
@BabelFishModel(description =  "Object that contains links to additional information on specific topics")
public class BankingProductAdditionalInformation {

    @BabelFishModelProperty(
        description =  "General overview of the product",
        dataType = "java.lang.String"
    )
    @JsonSerialize(converter = UriToUriStringConverter.class)
    URI overviewUri;

    @BabelFishModelProperty(
        description =  "Terms and conditions for the product",
        dataType = "java.lang.String"
    )
    @JsonSerialize(converter = UriToUriStringConverter.class)
    URI termsUri;

    @BabelFishModelProperty(
        description =  "Eligibility rules and criteria for the product",
        dataType = "java.lang.String"
    )
    @JsonSerialize(converter = UriToUriStringConverter.class)
    URI eligibilityUri;

    @BabelFishModelProperty(
        description =  "Description of fees, pricing, discounts, exemptions and bonuses for the product",
        dataType = "java.lang.String"
    )
    @JsonSerialize(converter = UriToUriStringConverter.class)
    URI feesAndPricingUri;

    @BabelFishModelProperty(
        description =  "Description of a bundle that this product can be part of",
        dataType = "java.lang.String"
    )
    @JsonSerialize(converter = UriToUriStringConverter.class)
    URI bundleUri;
}
