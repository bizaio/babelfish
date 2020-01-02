package io.biza.cdr.babelfish.response;


import io.biza.cdr.babelfish.model.CDRResponse;
import io.biza.cdr.babelfish.model.common.Links;
import io.biza.cdr.babelfish.model.common.Meta;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;

public class ResponseCommonCustomerDetail extends CDRResponse<Links, Meta> {

    @BabelFishModelProperty(
        required = true
    )
    ResponseCommonCustomerDetailData data;
}
