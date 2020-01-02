package io.biza.cdr.babelfish.response;

import javax.validation.constraints.NotNull;

import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import lombok.NonNull;

// extends PaginatedResponse
public class ResponseBankingPayeeList {

    @BabelFishModelProperty(
        required = true
    )
    @NotNull
    @NonNull
    ResponseBankingPayeeListData data;
}
