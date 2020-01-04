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
import io.biza.cdr.babelfish.v1.enumerations.BankingTransactionService;
import io.biza.cdr.babelfish.v1.enumerations.PayloadTypeBankingPayee;
import io.biza.cdr.babelfish.v1.enumerations.PayloadTypeTransactionExtension;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Valid
@BabelFishModel(description = "Banking Transaction Detailed Extended Data")
public interface BankingTransactionDetailExtendedData {

  @BabelFishModelProperty(
      description = "Label of the originating payer. Mandatory for inbound payment")
  @JsonGetter("payer")
  public String getPayer();

  @JsonSetter("payer")
  public void setPayer(String payer);

  public default BankingTransactionDetailExtendedData payer(String payer) {
    setPayer(payer);
    return this;
  }

  @BabelFishModelProperty(
      description = "Label of the target PayID.  Mandatory for an outbound payment. The name assigned to the BSB/Account Number or PayID (by the owner of the PayID)")
  @JsonGetter("payee")
  public String getPayee();

  @JsonSetter("payee")
  public void setPayee(String payee);

  public default BankingTransactionDetailExtendedData payee(String payee) {
    setPayee(payee);
    return this;
  }

  @BabelFishModelProperty(
      description = "Optional extended data provided specific to transaction originated via NPP")
  @JsonGetter("extensionUType")
  public PayloadTypeTransactionExtension getType();

  @JsonSetter("extensionUType")
  public void setType(PayloadTypeTransactionExtension extensionUType);

  public default BankingTransactionDetailExtendedData type(
      PayloadTypeTransactionExtension extensionUType) {
    setType(extensionUType);
    return this;
  }

  @BabelFishModelProperty(description = "X2P1.01 Extension Data")
  @JsonGetter("x2p101Payload")
  public BankingTransactionDetailExtendedDataX2p101Payload getX2P101Payload();

  @JsonSetter("x2p101Payload")
  public void setX2P101Payload(BankingTransactionDetailExtendedDataX2p101Payload x2p101Payload);

  public default BankingTransactionDetailExtendedData x2P101Payload(
      BankingTransactionDetailExtendedDataX2p101Payload x2p101Payload) {
    setX2P101Payload(x2p101Payload);
    return this;
  }

  @BabelFishModelProperty(description = "Identifier of the applicable overlay service.",
      required = true)
  @JsonGetter("service")
  public BankingTransactionService getService();

  @JsonSetter("service")
  public void setService(@NotNull BankingTransactionService service);

  public default BankingTransactionDetailExtendedData service(
      @NotNull BankingTransactionService service) {
    setService(service);
    return this;
  }
}
