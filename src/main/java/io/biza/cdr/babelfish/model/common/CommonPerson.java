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
package io.biza.cdr.babelfish.model.common;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.converters.OffsetDateTimeToDateTimeStringConverter;
import io.biza.cdr.babelfish.converters.DateTimeStringToOffsetDateTimeConverter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

@BabelFishModel(description = "Person definition in brief")
public interface CommonPerson {

  @BabelFishModelProperty(
      description = "The date and time that this record was last updated by the customer.  If no update has occurred then this date should reflect the initial creation date for the data")
  @JsonGetter("lastUpdateTime")
  public LocalDateTime getLastUpdateTime();

  @JsonSetter("lastUpdateTime")
  public void setLastUpdateTime(LocalDateTime lastUpdateTime);

  public default CommonPerson lastUpdateTime(LocalDateTime lastUpdateTime) {
    setLastUpdateTime(lastUpdateTime);
    return this;
  }

  @BabelFishModelProperty(
      description = "For people with single names this field need not be present.  The single name should be in the lastName field")
  @JsonGetter("firstName")
  public String getFirstName();

  @JsonSetter("firstName")
  public void setFirstName(String firstName);

  public default CommonPerson firstName(String firstName) {
    setFirstName(firstName);
    return this;
  }

  @BabelFishModelProperty(
      description = "For people with single names the single name should be in this field",
      required = true)

  @JsonGetter("lastName")
  public String getLastName();

  @JsonSetter("lastName")
  public void setLastName(String lastName);

  public default CommonPerson lastName(String lastName) {
    setLastName(lastName);
    return this;
  }

  @BabelFishModelProperty(description = "Field is mandatory but array may be empty",
      required = true)
  @JsonGetter("middleNames")
  public List<String> getMiddleNames();

  @JsonSetter("middleNames")
  public void setMiddleNames(List<String> middleNames);

  public default CommonPerson middleNames(List<String> middleNames) {
    setMiddleNames(middleNames);
    return this;
  }

  @BabelFishModelProperty(
      description = "Also known as title or salutation.  The prefix to the name (e.g. Mr, Mrs, Ms, Miss, Sir, etc)")
  
  @JsonGetter("prefix")
  public String getPrefix();

  @JsonSetter("prefix")
  public void setPrefix(String prefix);

  public default CommonPerson prefix(String prefix) {
    setPrefix(prefix);
    return this;
  }

  @BabelFishModelProperty(description = "Used for a trailing suffix to the name (e.g. Jr)")
  @JsonGetter("suffix")
  public String getSuffix();

  @JsonSetter("suffix")
  public void setSuffix(String suffix);

  public default CommonPerson suffix(String suffix) {
    setSuffix(suffix);
    return this;
  }

  @BabelFishModelProperty(
      description = "Value is a valid [ANZCO v1.2](http://www.abs.gov.au/ANZSCO) Standard Occupation classification.")
  @JsonGetter("occupationCode")
  public String getOccupationCode();

  @JsonSetter("occupationCode")
  public void setOccupationCode(String occupationCode);

  public default CommonPerson occupationCode(String occupationCode) {
    setOccupationCode(occupationCode);
    return this;
  }
}
