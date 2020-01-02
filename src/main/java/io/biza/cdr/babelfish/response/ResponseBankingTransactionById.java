package io.biza.cdr.babelfish.response;

import io.biza.cdr.babelfish.model.CDRResponse;
import io.biza.cdr.babelfish.model.banking.BankingTransactionDetail;
import io.biza.cdr.babelfish.model.common.Links;
import io.biza.cdr.babelfish.model.common.Meta;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;

public class ResponseBankingTransactionById  extends CDRResponse<Links, Meta> {

    @BabelFishModelProperty(
        required = true
    )
    BankingTransactionDetail data;
}
