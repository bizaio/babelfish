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
package io.biza.cdr.babelfish.model.common;

import java.time.LocalDateTime;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Valid
@BabelFishModel(description = "Person definition in brief")
public abstract class CommonPerson<T extends CommonPerson<T>> {
  @BabelFishModelProperty(
      description = "The date and time that this record was last updated by the customer.  If no update has occurred then this date should reflect the initial creation date for the data")
  @JsonProperty("lastUpdateTime")
  public LocalDateTime lastUpdateTime;

  public LocalDateTime lastUpdateTime() {
    return getLastUpdateTime();
  }

  @SuppressWarnings("unchecked")
  public T lastUpdateTime(LocalDateTime lastUpdateTime) {
    setLastUpdateTime(lastUpdateTime);
    return (T) this;
  }

  @BabelFishModelProperty(
      description = "For people with single names this field need not be present.  The single name should be in the lastName field")
  @JsonProperty("firstName")
  public String firstName;

  public String firstName() {
    return getFirstName();
  }

  @SuppressWarnings("unchecked")
  public T firstName(String firstName) {
    setFirstName(firstName);
    return (T) this;
  }

  @BabelFishModelProperty(
      description = "For people with single names the single name should be in this field",
      required = true)
  @JsonProperty("lastName")
  @NotNull
  @NonNull
  public String lastName;

  public String lastName() {
    return getLastName();
  }

  @SuppressWarnings("unchecked")
  public T lastName(String lastName) {
    setLastName(lastName);
    return (T) this;
  }

  @BabelFishModelProperty(description = "Field is mandatory but array may be empty",
      required = true)
  @JsonProperty("middleNames")
  @NonNull
  @NotNull
  public List<String> middleNames = List.of();

  @BabelFishModelProperty(
      description = "Also known as title or salutation.  The prefix to the name (e.g. Mr, Mrs, Ms, Miss, Sir, etc)")
  @JsonProperty("prefix")
  public String prefix;

  public String prefix() {
    return getPrefix();
  }

  @SuppressWarnings("unchecked")
  public T prefix(String prefix) {
    setPrefix(prefix);
    return (T) this;
  }

  @BabelFishModelProperty(description = "Used for a trailing suffix to the name (e.g. Jr)")
  @JsonProperty("suffix")
  public String suffix;

  public String suffix() {
    return getSuffix();
  }

  @SuppressWarnings("unchecked")
  public T suffix(String suffix) {
    setSuffix(suffix);
    return (T) this;
  }

  @BabelFishModelProperty(
      description = "Value is a valid [ANZCO v1.2](http://www.abs.gov.au/ANZSCO) Standard Occupation classification.")
  @JsonProperty("occupationCode")
  public String occupationCode;

  public String occupationCode() {
    return getOccupationCode();
  }

  @SuppressWarnings("unchecked")
  public T occupationCode(String occupationCode) {
    setOccupationCode(occupationCode);
    return (T) this;
  }
}
