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
@BabelFishModel(description =  "International Payee Bank Address Details")
public class BankingInternationalPayeeBankDetailsBankAddress {

    @BabelFishModelProperty(
        description =  "Name of the recipient Bank",
        required = true
    )
    @NonNull
    @NotNull
    String name;

    @BabelFishModelProperty(
        description =  "Address of the recipient Bank",
        required = true
    )
    @NonNull
    @NotNull
    String address;
}
