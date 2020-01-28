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
import io.biza.babelfish.enumerations.cdr.BankingTransactionService;
import io.biza.babelfish.enumerations.cdr.PayloadTypeTransactionExtension;
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

@Schema(description = "Banking Transaction Detailed Extended Data")
public abstract class BankingTransactionDetailExtendedData<T> {
  @Schema(description = "Label of the originating payer. Mandatory for inbound payment")
  @JsonProperty("payer")
  String payer;

  public String payer() {
    return getPayer();
  }

  @SuppressWarnings("unchecked")
  public T payer(String payer) {
    setPayer(payer);
    return (T) this;
  }

  @Schema(
      description = "Label of the target PayID.  Mandatory for an outbound payment. The name assigned to the BSB/Account Number or PayID (by the owner of the PayID)")
  @JsonProperty("payee")
  String payee;

  public String payee() {
    return getPayee();
  }

  @SuppressWarnings("unchecked")
  public T payee(String payee) {
    setPayee(payee);
    return (T) this;
  }

  @Schema(
      description = "Optional extended data provided specific to transaction originated via NPP")
  @JsonProperty("extensionUType")
  PayloadTypeTransactionExtension extensionUType;

  public PayloadTypeTransactionExtension extensionUType() {
    return getExtensionUType();
  }

  @SuppressWarnings("unchecked")
  public T extensionUType(PayloadTypeTransactionExtension extensionUType) {
    setExtensionUType(extensionUType);
    return (T) this;
  }

  @Schema(description = "X2P1.01 Payload Details")
  @JsonProperty("x2p101Payload")
  @Valid
  BankingTransactionDetailExtendedDataX2p101Payload<?> x2p101Payload;

  public BankingTransactionDetailExtendedDataX2p101Payload<?> x2p101Payload() {
    return getX2p101Payload();
  }

  @SuppressWarnings("unchecked")
  public T x2p101Payload(BankingTransactionDetailExtendedDataX2p101Payload<?> x2p101Payload) {
    setX2p101Payload(x2p101Payload);
    return (T) this;
  }

  @Schema(description = "Identifier of the applicable overlay service.", required = true)
  @NotNull
  @JsonProperty("service")
  @Valid
  BankingTransactionService service;

  public BankingTransactionService service() {
    return getService();
  }

  @SuppressWarnings("unchecked")
  public T service(BankingTransactionService service) {
    setService(service);
    return (T) this;
  }
}
