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
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.support.BabelFishModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Valid
@BabelFishModel(description = "Representation of a BPAY Payee")
public interface BankingBillerPayee {

  @BabelFishModelProperty(description = "BPAY Biller Code of the Biller", required = true)
  @JsonGetter("billerCode")
  public String getBillerCode();

  @JsonSetter("billerCode")
  public void setBillerCode(@NotNull String billerCode);

  public default BankingBillerPayee billerCode(@NotNull String billerCode) {
    setBillerCode(billerCode);
    return this;
  }

  @BabelFishModelProperty(
      description = "BPAY CRN of the Biller. If the contents of the CRN match the format of a Credit Card PAN then it should be masked using the rules applicable for the MaskedPANString common type")
  @JsonGetter("crn")
  public String getCrn();

  @JsonSetter("crn")
  public void setCrn(@NotNull String crn);

  public default BankingBillerPayee crn(@NotNull String crn) {
    setCrn(crn);
    return this;
  }

  @BabelFishModelProperty(description = "Name of the Biller", required = true)
  @JsonGetter("billerName")
  public String getBillerName();

  @JsonSetter("billerName")
  public void setBillerName(@NotNull String billerName);

  public default BankingBillerPayee billerName(@NotNull String billerName) {
    setBillerName(billerName);
    return this;
  }

}
