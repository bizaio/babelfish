/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *******************************************************************************/
package io.biza.cdr.babelfish.model.banking;

import java.net.URI;
import java.util.Arrays;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.biza.cdr.babelfish.converters.UriToUriStringConverter;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.support.FormatChecker;
import io.biza.cdr.babelfish.v1.enumerations.BankingProductDiscountEligibilityType;
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
