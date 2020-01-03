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
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.cdr.babelfish.converters.UriStringToUriConverter;
import io.biza.cdr.babelfish.converters.UriToUriStringConverter;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.support.FormatChecker;
import io.biza.cdr.babelfish.v1.enumerations.BankingProductDiscountEligibilityType;
import io.biza.cdr.babelfish.v1.enumerations.BankingProductDiscountType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Valid
@BabelFishModel(description = "Banking Product Discount Eligibility Details")
public interface BankingProductFeeDiscountEligibility {

    @BabelFishModelProperty(description = "The type of the specific eligibility constraint for a discount", required = true)
    @JsonGetter("discountEligibilityType")
    public BankingProductDiscountEligibilityType getType();

    @JsonSetter("discountEligibilityType")
    public void setType(@NotNull BankingProductDiscountEligibilityType type);

    public default BankingProductFeeDiscountEligibility type(@NotNull BankingProductDiscountEligibilityType type) {
      setType(type);
      return this;
    }

    @BabelFishModelProperty(
        description = "Generic field containing additional information relevant to the [discountType](#tocSproductdiscounttypedoc) specified. Whether mandatory or not is dependent on the value of [discountType](#tocSproductdiscounttypedoc)")
    @JsonGetter("additionalValue")
    public String getAdditionalValue();

    @JsonSetter("additionalValue")
    public void setAdditionalValue(String additionalValue);

    public default BankingProductFeeDiscountEligibility additionalValue(@NotNull String additionalValue) {
      setAdditionalValue(additionalValue);
      return this;
    }

    @BabelFishModelProperty(description = "Display text providing more information on this eligibility constraint")
    @JsonGetter("additionalInfo")
    public String getAdditionalInfo();

    @JsonSetter("additionalInfo")
    public void setAdditionalInfo(String additionalInfo);

    public default BankingProductFeeDiscountEligibility additionalInfo(@NotNull String additionalInfo) {
      setAdditionalInfo(additionalInfo);
      return this;
    }

    @BabelFishModelProperty(description = "Link to a web page with more information on this eligibility constraint",
        dataType = "java.lang.String")
    @JsonSerialize(converter = UriToUriStringConverter.class)
    @JsonGetter("additionalInfoUri")
    public URI getAdditionalInfoUri();

    @JsonDeserialize(converter = UriStringToUriConverter.class)
    @JsonSetter("additionalInfoUri")
    public void setAdditionalInfoUri(URI additionalInfoUri);

    public default BankingProductFeeDiscountEligibility additionalInfoUri(URI additionalInfoUri) {
      setAdditionalInfoUri(additionalInfoUri);
      return this;
    }
}
