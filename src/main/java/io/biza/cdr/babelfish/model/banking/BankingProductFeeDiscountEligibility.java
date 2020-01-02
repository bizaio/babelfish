package io.biza.cdr.babelfish.model.banking;

import java.net.URI;
import java.util.Arrays;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.biza.cdr.babelfish.converters.UriToUriStringConverter;
import io.biza.cdr.babelfish.enumerations.BankingProductDiscountEligibilityType;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.support.FormatChecker;
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
@BabelFishModel(description = "Banking Product Discount Eligibility Details")
public class BankingProductFeeDiscountEligibility {

    @BabelFishModelProperty(description = "The type of the specific eligibility constraint for a discount", required = true)
    BankingProductDiscountEligibilityType discountEligibilityType;

    String additionalValue;

    @BabelFishModelProperty(description = "Display text providing more information on this eligibility constraint")
    String additionalInfo;

    @BabelFishModelProperty(description = "Link to a web page with more information on this eligibility constraint",
            dataType = "java.lang.String")
    @JsonSerialize(converter = UriToUriStringConverter.class)
    URI additionalInfoUri;
    
    @AssertTrue(message = "Additional Value must be a Positive Integer when Eligibility type is MIN_AGE or MAX_AGE")
    private boolean isValuePositiveInteger() {
        return Arrays.asList(new BankingProductDiscountEligibilityType[] { BankingProductDiscountEligibilityType.MIN_AGE, BankingProductDiscountEligibilityType.MAX_AGE}).contains(discountEligibilityType)
                ? FormatChecker.isPositiveInteger(additionalValue)
                : true;
    }
    
    @AssertTrue(message = "Additional Value must be an Amount String when Eligibility type is MIN_INCOME or MIN_TURNOVER")
    private boolean isValueAmount() {
    	 return Arrays.asList(new BankingProductDiscountEligibilityType[] { BankingProductDiscountEligibilityType.MIN_INCOME, BankingProductDiscountEligibilityType.MIN_TURNOVER}).contains(discountEligibilityType)
                ? FormatChecker.isDecimal(additionalValue)
                : true;
    }

}
