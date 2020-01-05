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

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.support.BabelFishModel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Valid
@BabelFishModel(description = "Representation of a BPAY Payee")
public abstract class BankingBillerPayee<T extends BankingBillerPayee<T>> {
  @BabelFishModelProperty(description = "BPAY Biller Code of the Biller", required = true)
  @NotNull
  @NonNull
  String billerCode;

  public String billerCode() {
    return getBillerCode();
  }

  @SuppressWarnings("unchecked")
  public T billerCode(String billerCode) {
    setBillerCode(billerCode);
    return (T) this;
  }

  @BabelFishModelProperty(
      description = "BPAY CRN of the Biller. If the contents of the CRN match the format of a Credit Card PAN then it should be masked using the rules applicable for the MaskedPANString common type")
  @NotNull
  @NonNull
  String crn;

  public String crn() {
    return getCrn();
  }

  @SuppressWarnings("unchecked")
  public T crn(String crn) {
    setCrn(crn);
    return (T) this;
  }

  @BabelFishModelProperty(description = "Name of the Biller", required = true)
  @NotNull
  @NonNull
  String billerName;

  public String billerName() {
    return getBillerName();
  }

  @SuppressWarnings("unchecked")
  public T billerName(String billerName) {
    setBillerName(billerName);
    return (T) this;
  }
}
