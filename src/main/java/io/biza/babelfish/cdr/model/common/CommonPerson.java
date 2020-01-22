/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU Lesser General Public License as published by the Free Software Foundation, either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *******************************************************************************/
package io.biza.babelfish.cdr.model.common;

import java.time.OffsetDateTime;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Valid
@ToString
@EqualsAndHashCode

@Schema(description = "Person definition in brief")
public abstract class CommonPerson<T> {
  @Schema(
      description = "The date and time that this record was last updated by the customer.  If no update has occurred then this date should reflect the initial creation date for the data",
      format = "date-time")
  @JsonProperty("lastUpdateTime")
  OffsetDateTime lastUpdateTime;

  public OffsetDateTime lastUpdateTime() {
    return getLastUpdateTime();
  }

  @SuppressWarnings("unchecked")
  public T lastUpdateTime(OffsetDateTime lastUpdateTime) {
    setLastUpdateTime(lastUpdateTime);
    return (T) this;
  }

  @Schema(
      description = "For people with single names this field need not be present.  The single name should be in the lastName field")
  @JsonProperty("firstName")
  String firstName;

  public String firstName() {
    return getFirstName();
  }

  @SuppressWarnings("unchecked")
  public T firstName(String firstName) {
    setFirstName(firstName);
    return (T) this;
  }

  @Schema(description = "For people with single names the single name should be in this field",
      required = true)
  @JsonProperty("lastName")
  @NotNull
  String lastName;

  public String lastName() {
    return getLastName();
  }

  @SuppressWarnings("unchecked")
  public T lastName(String lastName) {
    setLastName(lastName);
    return (T) this;
  }

  @Schema(description = "Field is mandatory but array may be empty", required = true)
  @JsonProperty("middleNames")
  @NotNull
  List<String> middleNames = List.of();

  @Schema(
      description = "Also known as title or salutation.  The prefix to the name (e.g. Mr, Mrs, Ms, Miss, Sir, etc)")
  @JsonProperty("prefix")
  String prefix;

  public String prefix() {
    return getPrefix();
  }

  @SuppressWarnings("unchecked")
  public T prefix(String prefix) {
    setPrefix(prefix);
    return (T) this;
  }

  @Schema(description = "Used for a trailing suffix to the name (e.g. Jr)")
  @JsonProperty("suffix")
  String suffix;

  public String suffix() {
    return getSuffix();
  }

  @SuppressWarnings("unchecked")
  public T suffix(String suffix) {
    setSuffix(suffix);
    return (T) this;
  }

  @Schema(
      description = "Value is a valid [ANZCO v1.2](http://www.abs.gov.au/ANZSCO) Standard Occupation classification.")
  @JsonProperty("occupationCode")
  String occupationCode;

  public String occupationCode() {
    return getOccupationCode();
  }

  @SuppressWarnings("unchecked")
  public T occupationCode(String occupationCode) {
    setOccupationCode(occupationCode);
    return (T) this;
  }
}
