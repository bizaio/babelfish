package io.biza.cdr.babelfish.model.banking;

import java.net.URI;
import java.util.Arrays;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.biza.cdr.babelfish.converters.UriToUriStringConverter;
import io.biza.cdr.babelfish.enumerations.BankingProductEligibilityType;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.support.FormatChecker;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@SuperBuilder
@Data
@Valid
@BabelFishModel(description =  "Eligibility criteria to obtain a particular banking product")
public class BankingProductEligibility {
    @BabelFishModelProperty(
        description =  "The type of eligibility criteria described.  See the next section for an overview of valid values and their meaning",
        required = true
    )
    @NonNull
    @NotNull
    BankingProductEligibilityType eligibilityType;

    @BabelFishModelProperty(
        description =  "Generic field containing additional information relevant to the [eligibilityType](#tocSproducteligibilitytypedoc) specified.  Whether mandatory or not is dependent on the value of [eligibilityType](#tocSproducteligibilitytypedoc)"
    )
    String additionalValue;

    @BabelFishModelProperty(
        description =  "Display text providing more information on the eligibility criteria. Mandatory if the [eligibilityType](#tocSproducteligibilitytypedoc) field is set to OTHER"
    )
    String additionalInfo;

    @BabelFishModelProperty(
        description =  "Link to a web page with more information on this eligibility criteria",
        dataType = "java.lang.String"
    )
    @JsonSerialize(converter = UriToUriStringConverter.class)
    URI additionalInfoUri;
    
    @AssertTrue(message = "Additional Information must be populated when Eligibility type is OTHER")
    private boolean isInfoDefined() {
        return Arrays.asList(new BankingProductEligibilityType[] { BankingProductEligibilityType.OTHER}).contains(eligibilityType)
                ? FormatChecker.isDefined(additionalInfo)
                : true;
    }
    
    @AssertTrue(message = "Additional Value must be a Positive Integer when Eligibility type is MIN_AGE or MAX_AGE")
    private boolean isValuePositiveInteger() {
    	return Arrays.asList(new BankingProductEligibilityType[] { BankingProductEligibilityType.MIN_AGE, BankingProductEligibilityType.MAX_AGE}).contains(eligibilityType)
                ? FormatChecker.isPositiveInteger(additionalValue)
                : true;
    }

    @AssertTrue(message = "Additional Value must be an Amount String when Eligibility type is MIN_INCOME or MIN_TURNOVER")
    private boolean isValueAmount() { 
    	return Arrays.asList(new BankingProductEligibilityType[] { BankingProductEligibilityType.MIN_INCOME, BankingProductEligibilityType.MIN_TURNOVER}).contains(eligibilityType)
                ? FormatChecker.isDecimal(additionalValue)
                : true;
    }
}
