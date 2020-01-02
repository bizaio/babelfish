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
package io.biza.cdr.babelfish.response;

import java.util.List;

import io.biza.cdr.babelfish.model.banking.BankingScheduledPayment;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;

public class ResponseBankingScheduledPaymentsListData {

    @BabelFishModelProperty(
        description =  "The list of scheduled payments to return",
        required = true
    )
    List<BankingScheduledPayment> scheduledPayments;
}
