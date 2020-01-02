package io.biza.cdr.babelfish.response;

import java.util.List;

import io.biza.cdr.babelfish.model.banking.BankingTransaction;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;

public class ResponseBankingTransactionListData {

    @BabelFishModelProperty(
        required = true
    )
    List<BankingTransaction> transactions;
}
