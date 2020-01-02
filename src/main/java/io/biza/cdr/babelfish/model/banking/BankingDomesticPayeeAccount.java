package io.biza.cdr.babelfish.model.banking;

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
@BabelFishModel(description =  "Domestic Payment Payee Details")
public class BankingDomesticPayeeAccount {

    @BabelFishModelProperty(
        description =  "Name of the account to pay to",
        required = true
    )
    @NonNull
    @NotNull
    String accountName;

    @BabelFishModelProperty(
        description =  "BSB of the account to pay to",
        required = true
    )
    @NonNull
    @NotNull
    String bsb;

    @BabelFishModelProperty(
        description =  "Number of the account to pay to",
        required = true
    )
    @NonNull
    @NotNull
    String accountNumber;
}
