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

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Valid
@BabelFishModel(description = "Banking Transaction Detail X2P101 Payload")
public interface BankingTransactionDetailExtendedDataX2p101Payload {

  @BabelFishModelProperty(
      description = "An extended string description. Only present if specified by the extensionUType field",
      required = true)
  @JsonGetter("extendedDescription")
  public String getExtendedDescription();

  @JsonSetter("extendedDescription")
  public void setExtendedDescription(@NotNull String extendedDescription);

  public default BankingTransactionDetailExtendedDataX2p101Payload extendedDescription(
      @NotNull String extendedDescription) {
    setExtendedDescription(extendedDescription);
    return this;
  }

  @BabelFishModelProperty(description = "An end to end ID for the payment created at initiation")
  @JsonGetter("endToEndId")
  public String getEndToEndId();

  @JsonSetter("endToEndId")
  public void setEndToEndId(String endToEndId);

  public default BankingTransactionDetailExtendedDataX2p101Payload endToEndId(String endToEndId) {
    setEndToEndId(endToEndId);
    return this;
  }

  @BabelFishModelProperty(
      description = "Purpose of the payment.  Format is defined by NPP standards for the x2p1.01 overlay service")
  @JsonGetter("purposeCode")
  public String getPurposeCode();

  @JsonSetter("purposeCode")
  public void setPurposeCode(String purposeCode);

  public default BankingTransactionDetailExtendedDataX2p101Payload purposeCode(String purposeCode) {
    setPurposeCode(purposeCode);
    return this;
  }
}
