/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * 
 * public ANY WARRANTY() { return getWARRANTY(); }
 * 
 * @SuppressWarnings("unchecked") public T WARRANTY(ANY WARRANTY) { setWARRANTY(WARRANTY); return
 * (T) this; } even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See
 * the GNU General Public License for more details.
 *******************************************************************************/
package io.biza.cdr.babelfish.model.banking;

import java.net.URI;
import java.util.Arrays;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.support.FormatChecker;
import io.biza.cdr.babelfish.v1.enumerations.BankingProductConstraintType;
import io.biza.cdr.babelfish.converters.UriToUriStringConverter;
import io.biza.cdr.babelfish.support.BabelFishModel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Valid
@BabelFishModel(description = "Banking Product Constraint Definition")
public abstract class BankingProductConstraint<T extends BankingProductConstraint<T>> {
  @BabelFishModelProperty(
      description = "The type of constraint described.  See the next section for an overview of valid values and their meaning",
      required = true)
  @NonNull
  @NotNull
  BankingProductConstraintType constraintType;

  public BankingProductConstraintType constraintType() {
    return getConstraintType();
  }

  @SuppressWarnings("unchecked")
  public T constraintType(BankingProductConstraintType constraintType) {
    setConstraintType(constraintType);
    return (T) this;
  }

  @BabelFishModelProperty(
      description = "Generic field containing additional information relevant to the [constraintType](#tocSproductconstrainttypedoc) specified.  Whether mandatory or not is dependent on the value of [constraintType](#tocSproductconstrainttypedoc)")
  String additionalValue;

  public String additionalValue() {
    return getAdditionalValue();
  }

  @SuppressWarnings("unchecked")
  public T additionalValue(String additionalValue) {
    setAdditionalValue(additionalValue);
    return (T) this;
  }

  @BabelFishModelProperty(description = "Display text providing more information the constraint")
  String additionalInfo;

  public String additionalInfo() {
    return getAdditionalInfo();
  }

  @SuppressWarnings("unchecked")
  public T additionalInfo(String additionalInfo) {
    setAdditionalInfo(additionalInfo);
    return (T) this;
  }

  @BabelFishModelProperty(
      description = "Link to a web page with more information on the constraint",
      dataType = "java.lang.String")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  URI additionalInfoUri;

  public URI additionalInfoUri() {
    return getAdditionalInfoUri();
  }

  @SuppressWarnings("unchecked")
  public T additionalInfoUri(URI additionalInfoUri) {
    setAdditionalInfoUri(additionalInfoUri);
    return (T) this;
  }

  @AssertTrue(
      message = "Additional Value must be an Amount String when Eligibility type is MIN_BALANCE, MAX_BALANCE, OPENING_BALANCE, MAX_LIMIT or MIN_LIMIT")
  private boolean isValueAmount() {
    return Arrays
        .asList(new BankingProductConstraintType[] {BankingProductConstraintType.MIN_BALANCE,
            BankingProductConstraintType.MAX_BALANCE, BankingProductConstraintType.OPENING_BALANCE,
            BankingProductConstraintType.MIN_LIMIT, BankingProductConstraintType.MAX_LIMIT})
        .contains(constraintType) ? FormatChecker.isDecimal(additionalValue) : true;
  }
}
