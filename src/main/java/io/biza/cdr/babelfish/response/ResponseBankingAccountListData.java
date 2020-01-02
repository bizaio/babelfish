package io.biza.cdr.babelfish.response;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.biza.cdr.babelfish.model.banking.BankingAccount;
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
@BabelFishModel(description =  "Response containing a list of Banking Accounts")
public class ResponseBankingAccountListData {

    @BabelFishModelProperty(
        description =  "The list of accounts returned. If the filter results in an empty set then this array may have no records",
        required = true
    )
    @NonNull
    @NotNull
    List<BankingAccount> accounts;
}
