package io.biza.cdr.babelfish.response;

import java.util.List;
import javax.validation.constraints.NotNull;

import io.biza.cdr.babelfish.model.banking.BankingPayee;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import lombok.NonNull;

public class ResponseBankingPayeeListData {

    @BabelFishModelProperty(
        description =  "The list of payees returned",
        required = true
    )
    @NonNull
    @NotNull
    List<BankingPayee> payees;
}
