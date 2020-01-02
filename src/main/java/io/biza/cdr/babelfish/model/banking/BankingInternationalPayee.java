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
@BabelFishModel(description =  "International Payee Beneficiary and Bank Details")
public class BankingInternationalPayee {

    @BabelFishModelProperty(
        required = true
    )
    @NonNull
    @NotNull
    BankingInternationalPayeeBeneficiaryDetails beneficiaryDetails;

    @BabelFishModelProperty(
        required = true
    )
    @NonNull
    @NotNull
    BankingInternationalPayeeBankDetails bankDetails;
}
