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
package io.biza.babelfish.cdr.response.container;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.biza.babelfish.cdr.v1.model.banking.BankingScheduledPayment;
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
@EqualsAndHashCode


@Schema(description = "An object containing a list of BankingScheduledPayment objects")
public abstract class ResponseBankingScheduledPaymentsListData<T> {
  @Schema(description = "The list of scheduled payments to return", required = true)
  @JsonProperty("scheduledPayments")
  @NotNull
  @NonNull
  public List<BankingScheduledPayment> scheduledPayments;

  public List<BankingScheduledPayment> scheduledPayments() {
    return getScheduledPayments();
  }

  @SuppressWarnings("unchecked")
  public T scheduledPayments(List<BankingScheduledPayment> scheduledPayments) {
    setScheduledPayments(scheduledPayments);
    return (T) this;
  }
}
