/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *******************************************************************************/
package io.biza.cdr.babelfish.model.banking;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import io.biza.cdr.babelfish.v1.enumerations.PayloadTypeBankingDomesticPayeePayId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Valid
@ToString
@EqualsAndHashCode

@Schema(description = "Domestic Payee PayID Detail")
public abstract class BankingDomesticPayeePayId<T> {
  @Schema(description = "The name assigned to the PayID by the owner of the PayID")
  String name;

  public String name() {
    return getName();
  }

  @SuppressWarnings("unchecked")
  public T name(String name) {
    setName(name);
    return (T) this;
  }

  @Schema(description = "The identifier of the PayID (dependent on type)",
      required = true)
  @NonNull
  @NotNull
  String identifier;

  public String identifier() {
    return getIdentifier();
  }

  @SuppressWarnings("unchecked")
  public T identifier(String identifier) {
    setIdentifier(identifier);
    return (T) this;
  }

  @Schema(description = "The type of the PayID", required = true)
  @NonNull
  @NotNull
  PayloadTypeBankingDomesticPayeePayId type;

  public PayloadTypeBankingDomesticPayeePayId type() {
    return getType();
  }

  @SuppressWarnings("unchecked")
  public T type(PayloadTypeBankingDomesticPayeePayId type) {
    setType(type);
    return (T) this;
  }
}
