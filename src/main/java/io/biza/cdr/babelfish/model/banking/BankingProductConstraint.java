package io.biza.cdr.babelfish.model.banking;

import java.net.URI;
import java.util.Arrays;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.support.FormatChecker;
import io.biza.cdr.babelfish.converters.UriToUriStringConverter;
import io.biza.cdr.babelfish.enumerations.BankingProductConstraintType;
import io.biza.cdr.babelfish.support.BabelFishModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@Builder
@Data
@Valid
@BabelFishModel(description = "Banking Product Constraint Definition")
public class BankingProductConstraint {

    @BabelFishModelProperty(
            description = "The type of constraint described.  See the next section for an overview of valid values and their meaning",
            required = true)
    @NonNull
    @NotNull
    BankingProductConstraintType constraintType;

    @BabelFishModelProperty(
            description = "Generic field containing additional information relevant to the [constraintType](#tocSproductconstrainttypedoc) specified.  Whether mandatory or not is dependent on the value of [constraintType](#tocSproductconstrainttypedoc)")
    String additionalValue;

    @BabelFishModelProperty(description = "Display text providing more information the constraint")
    String additionalInfo;

    @BabelFishModelProperty(description = "Link to a web page with more information on the constraint", dataType = "java.lang.String")
    @JsonSerialize(converter = UriToUriStringConverter.class)
    URI additionalInfoUri;

    @AssertTrue(
            message = "Additional Value must be an Amount String when Eligibility type is MIN_BALANCE, MAX_BALANCE, OPENING_BALANCE, MAX_LIMIT or MIN_LIMIT")
    private boolean isValueAmount() {
        return Arrays.asList(new BankingProductConstraintType[] {  BankingProductConstraintType.MIN_BALANCE, BankingProductConstraintType.MAX_BALANCE, BankingProductConstraintType.OPENING_BALANCE,
                BankingProductConstraintType.MIN_LIMIT, BankingProductConstraintType.MAX_LIMIT }).contains(constraintType)
                        ? FormatChecker.isDecimal(additionalValue)
                        : true;
    }
}
