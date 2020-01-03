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
import javax.validation.constraints.NotNull;
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
@BabelFishModel(description =  "Defines a condition for the applicability of a tiered rate")
public interface BankingProductRateCondition {

    @BabelFishModelProperty(
        description =  "Display text providing more information on the condition"
    )
    @JsonGetter("additionalInfo")
    public String getAdditionalInfo();

    @JsonSetter("additionalInfo")
    public void setAdditionalInfo(String additionalInfo);

    public default BankingProductRateCondition additionalInfo(@NotNull String additionalInfo) {
      setAdditionalInfo(additionalInfo);
      return this;
    }

    @BabelFishModelProperty(
        description =  "Link to a web page with more information on this condition",
        dataType = "java.lang.String"
    )
    @JsonSerialize(converter = UriToUriStringConverter.class)
    @JsonGetter("additionalInfoUri")
    public URI getAdditionalInfoUri();

    @JsonDeserialize(converter = UriStringToUriConverter.class)
    @JsonSetter("additionalInfoUri")
    public void setAdditionalInfoUri(URI additionalInfoUri);

    public default BankingProductRateCondition additionalInfoUri(URI additionalInfoUri) {
      setAdditionalInfoUri(additionalInfoUri);
      return this;
    }
}
