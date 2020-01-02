package io.biza.cdr.babelfish.response;

import javax.validation.constraints.NotNull;

import io.biza.cdr.babelfish.model.CDRResponse;
import io.biza.cdr.babelfish.model.common.LinksPaginated;
import io.biza.cdr.babelfish.model.common.MetaPaginated;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import lombok.NonNull;

public class ResponseBankingDirectDebitAuthorisationList extends CDRResponse<LinksPaginated, MetaPaginated> {

    @BabelFishModelProperty(
        required = true
    )
    @NonNull
    @NotNull
    ResponseBankingDirectDebitAuthorisationListData data;
}
