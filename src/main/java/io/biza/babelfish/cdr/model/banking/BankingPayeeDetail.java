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
package io.biza.babelfish.cdr.model.banking;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.biza.babelfish.cdr.v1.enumerations.PayloadTypeBankingPayee;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Valid
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Banking Payee Detailed Information")
public abstract class BankingPayeeDetail<T> extends BankingPayee<T> {

  @Schema(description = "Type of object included that describes the payee in detail",
      required = true)
  @JsonProperty("payeeUType")
  @NotNull
  PayloadTypeBankingPayee type;

  public PayloadTypeBankingPayee type() {
    return getType();
  }

  @SuppressWarnings("unchecked")
  public T type(PayloadTypeBankingPayee type) {
    setType(type);
    return (T) this;
  }

  @JsonProperty("domestic")
  @Valid
  BankingDomesticPayee<?> domestic;

  public BankingDomesticPayee<?> domestic() {
    return getDomestic();
  }

  @SuppressWarnings("unchecked")
  public T domestic(BankingDomesticPayee<?> domestic) {
    setDomestic(domestic);
    return (T) this;
  }

  @JsonProperty("biller")
  @Valid
  BankingBillerPayee<?> biller;

  public BankingBillerPayee<?> biller() {
    return getBiller();
  }

  @SuppressWarnings("unchecked")
  public T biller(BankingBillerPayee<?> biller) {
    setBiller(biller);
    return (T) this;
  }

  @JsonProperty("international")
  @Valid
  BankingInternationalPayee<?> international;

  public BankingInternationalPayee<?> international() {
    return getInternational();
  }

  @SuppressWarnings("unchecked")
  public T international(BankingInternationalPayee<?> international) {
    setInternational(international);
    return (T) this;
  }

}
