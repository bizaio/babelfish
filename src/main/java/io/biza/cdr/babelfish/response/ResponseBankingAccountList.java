package io.biza.cdr.babelfish.response;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.biza.cdr.babelfish.model.CDRResponse;
import io.biza.cdr.babelfish.model.common.LinksPaginated;
import io.biza.cdr.babelfish.model.common.MetaPaginated;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@Builder
@Data
@EqualsAndHashCode(callSuper=true)
@Valid
@BabelFishModel(description =  "Response containing a list of Banking Accounts")
public class ResponseBankingAccountList extends CDRResponse<LinksPaginated, MetaPaginated> {

    @BabelFishModelProperty(
        required = true
    )
    @NonNull
    @NotNull
    ResponseBankingAccountListData data;
}
