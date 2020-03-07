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
package io.biza.babelfish.cdr.models.payloads.banking.account.payee.scheduled;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.biza.babelfish.cdr.enumerations.PayloadTypeBankingScheduledPaymentTo;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.bpay.BankingBillerPayeeV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.domestic.BankingDomesticPayeeV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.international.BankingInternationalPayeeV1;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Valid
@ToString
@EqualsAndHashCode(callSuper = false)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(
    description = "Object containing details of the destination of the payment. Used to specify a variety of payment destination types",
    name = "BankingScheduledPaymentToV1")
public class BankingScheduledPaymentToV1 extends io.biza.babelfish.cdr.abstracts.payloads.banking.account.payee.scheduled.BankingScheduledPaymentToV1 {
  @Schema(
      description = "The type of object provided that specifies the destination of the funds for the payment.",
      required = true)
  @NotNull
  @JsonProperty("toUType")
  PayloadTypeBankingScheduledPaymentTo type;

  @Schema(
      description = "Present if toUType is set to accountId. Indicates that the payment is to another account that is accessible under the current consent")
  @JsonProperty("accountId")
  String accountId;

  @Schema(
      description = "Present if toUType is set to payeeId. Indicates that the payment is to registered payee that can be accessed using the payee end point. If the Bank Payees scope has not been consented to then a payeeId should not be provided and the full payee details should be provided instead")
  @JsonProperty("payeeId")
  String payeeId;

  @Schema(description = "Domestic Payee Details")
  @JsonProperty("domestic")
  @Valid
  BankingDomesticPayeeV1 domestic;

  @Schema(description = "BPAY Biller Payee Details")
  @JsonProperty("biller")
  @Valid
  BankingBillerPayeeV1 biller;

  @Schema(description = "International Payee Details")
  @JsonProperty("international")
  @Valid
  BankingInternationalPayeeV1 international;

}
