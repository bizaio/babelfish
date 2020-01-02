package io.biza.cdr.babelfish.response;

import io.biza.cdr.babelfish.model.CDRResponse;
import io.biza.cdr.babelfish.model.common.Links;
import io.biza.cdr.babelfish.model.common.Meta;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

public class ResponseCommonCustomer  extends CDRResponse<Links, Meta> {

    @BabelFishModelProperty(
        required = true
    )
    @NotNull
    @NonNull
    ResponseCommonCustomerData data;
}
