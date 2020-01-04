/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *******************************************************************************/
package io.biza.cdr.babelfish.model.banking;

import java.net.URI;
import java.util.Arrays;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.support.FormatChecker;
import io.biza.cdr.babelfish.v1.enumerations.BankingProductFeatureType;
import io.biza.cdr.babelfish.converters.UriToUriStringConverter;
import io.biza.cdr.babelfish.support.BabelFishModel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
@Valid
@BabelFishModel(description = "A Banking Product Feature")
public abstract class BankingProductFeature {
  @BabelFishModelProperty(description = "The type of feature described", required = true)
  BankingProductFeatureType featureType;

  @BabelFishModelProperty(
      description = "Generic field containing additional information relevant to the [featureType](#tocSproductfeaturetypedoc) specified. Whether mandatory or not is dependent on the value of the [featureType.](#tocSproductfeaturetypedoc)")
  String additionalValue;

  @BabelFishModelProperty(
      description = "Display text providing more information on the feature. Mandatory if the [feature type](#tocSproductfeaturetypedoc) is set to OTHER")
  String additionalInfo;

  @BabelFishModelProperty(description = "Link to a web page with more information on this feature",
      dataType = "java.lang.String")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  URI additionalInfoUri;

  @AssertTrue(message = "Additional Information must be populated when Feature type is OTHER")
  private boolean isInfoDefined() {
    return Arrays.asList(new BankingProductFeatureType[] {
        BankingProductFeatureType.OTHER
    }).contains(featureType) ? FormatChecker.isDefined(additionalInfo) : true;
  }

  @AssertTrue(
      message = "Additional Value must be an Duration String when Feature type is INTEREST_FREE or INTEREST_FREE_TRANSFERS")
  private boolean isValueDuration() {
    return Arrays.asList(new BankingProductFeatureType[] {
        BankingProductFeatureType.INTEREST_FREE, BankingProductFeatureType.INTEREST_FREE_TRANSFERS
    }).contains(featureType) ? FormatChecker.isDuration(additionalValue) : true;
  }

  @AssertTrue(
      message = "Additional Value must be a Positive Integer when Feature type is FREE_TXNS or BONUS_REWARDS")
  private boolean isValuePositiveInteger() {
    return Arrays.asList(new BankingProductFeatureType[] {
        BankingProductFeatureType.FREE_TXNS, BankingProductFeatureType.BONUS_REWARDS
    }).contains(featureType) ? FormatChecker.isPositiveInteger(additionalValue) : true;
  }

  @AssertTrue(
      message = "Additional Value must be an Amount String when Eligibility type is FREE_TXNS_ALLOWANCE")
  private boolean isValueAmount() {
    return Arrays.asList(new BankingProductFeatureType[] {
        BankingProductFeatureType.FREE_TXNS_ALLOWANCE
    }).contains(featureType) ? FormatChecker.isDecimal(additionalValue) : true;
  }
}
