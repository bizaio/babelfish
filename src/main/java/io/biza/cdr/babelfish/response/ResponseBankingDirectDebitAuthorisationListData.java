package io.biza.cdr.babelfish.response;

import java.util.List;

import javax.validation.constraints.NotNull;

import io.biza.cdr.babelfish.model.banking.BankingDirectDebit;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import lombok.NonNull;

public class ResponseBankingDirectDebitAuthorisationListData {

    @BabelFishModelProperty(
        description =  "The list of authorisations returned",
        required = true
    )
    @NotNull
    @NonNull
    List<BankingDirectDebit> directDebitAuthorisations;
}
