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

import javax.validation.Valid;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.converters.UriStringToUriConverter;
import io.biza.cdr.babelfish.converters.UriToUriStringConverter;
import io.biza.cdr.babelfish.support.BabelFishModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Valid
@BabelFishModel(description =  "Object that contains links to additional information on specific topics")
public interface BankingProductAdditionalInformation {

    @BabelFishModelProperty(
        description =  "General overview of the product",
        dataType = "java.lang.String"
    )
    @JsonSerialize(converter = UriToUriStringConverter.class)
    @JsonGetter("overviewUri")
    public URI getOverviewUri();
    
    @JsonSetter("overviewUri")
    @JsonDeserialize(converter = UriStringToUriConverter.class)    
    public void setOverviewUri(URI overviewUri);
    
    public default BankingProductAdditionalInformation overviewUri(URI overviewUri) {
      setOverviewUri(overviewUri);
      return this;
    }
    
    @BabelFishModelProperty(
        description =  "Terms and conditions for the product",
        dataType = "java.lang.String"
    )
    @JsonSerialize(converter = UriToUriStringConverter.class)
    @JsonGetter("termsUri")
    public URI getTermsUri();
    
    @JsonSetter("termsUri")
    @JsonDeserialize(converter = UriStringToUriConverter.class)    
    public void setTermsUri(URI termsUri);
    
    public default BankingProductAdditionalInformation termsUri(URI termsUri) {
      setTermsUri(termsUri);
      return this;
    }

    @BabelFishModelProperty(
        description =  "Eligibility rules and criteria for the product",
        dataType = "java.lang.String"
    )
    @JsonSerialize(converter = UriToUriStringConverter.class)
    @JsonGetter("eligibilityUri")
    public URI getEligibilityUri();
    
    @JsonSetter("eligibilityUri")
    @JsonDeserialize(converter = UriStringToUriConverter.class)    
    public void setEligibilityUri(URI eligibilityUri);
    
    public default BankingProductAdditionalInformation eligibilityUri(URI eligibilityUri) {
      setEligibilityUri(eligibilityUri);
      return this;
    }

    @BabelFishModelProperty(
        description =  "Description of fees, pricing, discounts, exemptions and bonuses for the product",
        dataType = "java.lang.String"
    )
    @JsonSerialize(converter = UriToUriStringConverter.class)
    @JsonGetter("feesAndPricingUri")
    public URI getFeesAndPricingUri();
    
    @JsonSetter("feesAndPricingUri")
    @JsonDeserialize(converter = UriStringToUriConverter.class)    
    public void setFeesAndPricingUri(URI feesAndPricingUri);
    
    public default BankingProductAdditionalInformation feesAndPricingUri(URI feesAndPricingUri) {
      setFeesAndPricingUri(feesAndPricingUri);
      return this;
    }

    @BabelFishModelProperty(
        description =  "Description of a bundle that this product can be part of",
        dataType = "java.lang.String"
    )
    @JsonSerialize(converter = UriToUriStringConverter.class)
    @JsonGetter("bundleUri")
    public URI getBundleUri();
    
    @JsonSetter("bundleUri")
    @JsonDeserialize(converter = UriStringToUriConverter.class)    
    public void setBundleUri(URI bundleUri);
    
    public default BankingProductAdditionalInformation bundleUri(URI bundleUri) {
      setBundleUri(bundleUri);
      return this;
    }
}
