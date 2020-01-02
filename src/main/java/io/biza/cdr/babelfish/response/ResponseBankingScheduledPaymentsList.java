package io.biza.cdr.babelfish.response;

import io.biza.cdr.babelfish.model.CDRResponse;
import io.biza.cdr.babelfish.model.common.LinksPaginated;
import io.biza.cdr.babelfish.model.common.MetaPaginated;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;

public class ResponseBankingScheduledPaymentsList extends CDRResponse<LinksPaginated, MetaPaginated> {

    @BabelFishModelProperty(
        required = true
    )
    ResponseBankingScheduledPaymentsListData data;
}
