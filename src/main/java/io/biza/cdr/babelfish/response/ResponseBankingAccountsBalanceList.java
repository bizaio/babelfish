package io.biza.cdr.babelfish.response;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@Builder
@Data
@Valid
@BabelFishModel(description =  "Response containing a Banking Accounts Balances List")
//  Implement extends PaginatedResponse
public class ResponseBankingAccountsBalanceList {

    @BabelFishModelProperty(
        required = true
    )
    @NonNull
    @NotNull
    ResponseBankingAccountsBalanceListData data;
}
