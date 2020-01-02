package io.biza.cdr.babelfish.response;


import javax.validation.constraints.NotNull;

import io.biza.cdr.babelfish.model.CDRResponse;
import io.biza.cdr.babelfish.model.banking.BankingPayeeDetail;
import io.biza.cdr.babelfish.model.common.Links;
import io.biza.cdr.babelfish.model.common.Meta;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import lombok.NonNull;

public class ResponseBankingPayeeById  extends CDRResponse<Links, Meta> {

    @BabelFishModelProperty(
        required = true
    )
    @NonNull
    @NotNull
    BankingPayeeDetail data;
}
