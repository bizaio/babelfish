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

import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.support.FormatChecker;
import io.biza.cdr.babelfish.v1.enumerations.BankingProductConstraintType;
import io.biza.cdr.babelfish.converters.UriStringToUriConverter;
import io.biza.cdr.babelfish.converters.UriToUriStringConverter;
import io.biza.cdr.babelfish.support.BabelFishModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Valid
@BabelFishModel(description = "Banking Product Constraint Definition")
public interface BankingProductConstraint {

    @BabelFishModelProperty(
            description = "The type of constraint described.  See the next section for an overview of valid values and their meaning",
            required = true)
    @JsonGetter("constraintType")
    public BankingProductConstraintType getConstraintType();
    
    @JsonSetter("constraintType")
    public void setConstraintType(@NotNull BankingProductConstraintType constraintType);
    
    public default BankingProductConstraint contraintType(@NotNull BankingProductConstraintType constraintType) {
      setConstraintType(constraintType);
      return this;
    }
    
    @BabelFishModelProperty(
            description = "Generic field containing additional information relevant to the [constraintType](#tocSproductconstrainttypedoc) specified.  Whether mandatory or not is dependent on the value of [constraintType](#tocSproductconstrainttypedoc)")
    @JsonGetter("additionalValue")
    public String getAdditionalValue();
    
    @JsonSetter("additionalValue")
    public void setAdditionalValue(String additionalValue);
    
    public default BankingProductConstraint additionalValue(@NotNull String additionalValue) {
      setAdditionalValue(additionalValue);
      return this;
    }
    
    @BabelFishModelProperty(description = "Display text providing more information the constraint")
    @JsonGetter("additionalInfo")
    public String getAdditionalInfo();
    
    @JsonSetter("additionalInfo")
    public void setAdditionalInfo(String additionalInfo);
    
    public default BankingProductConstraint additionalInfo(@NotNull String additionalInfo) {
      setAdditionalInfo(additionalInfo);
      return this;
    }

    @BabelFishModelProperty(description = "Link to a web page with more information on the constraint", dataType = "java.lang.String")
    @JsonSerialize(converter = UriToUriStringConverter.class)
    @JsonGetter("additionalInfoUri")
    public URI getAdditionalInfoUri();
    
    @JsonDeserialize(converter = UriStringToUriConverter.class)
    @JsonSetter("additionalInfoUri")
    public void setAdditionalInfoUri(URI additionalInfoUri);
    
    public default BankingProductConstraint additionalInfoUri(URI additionalInfoUri) {
      setAdditionalInfoUri(additionalInfoUri);
      return this;
    }
}
