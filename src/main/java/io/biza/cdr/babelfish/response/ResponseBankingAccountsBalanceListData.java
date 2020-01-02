package io.biza.cdr.babelfish.response;

import java.util.List;

import javax.validation.constraints.NotNull;

import io.biza.cdr.babelfish.model.banking.BankingBalance;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import lombok.NonNull;

public class ResponseBankingAccountsBalanceListData {

    @BabelFishModelProperty(
        description =  "The list of balances returned",
        required = true
    )
    @NonNull
    @NotNull
    List<BankingBalance> balances;
}
