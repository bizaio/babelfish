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
