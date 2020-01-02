package io.biza.cdr.babelfish.model.banking;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.enumerations.PayloadTypeBankingDomesticPayeePayId;
import io.biza.cdr.babelfish.support.BabelFishModel;
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
@BabelFishModel(description =  "Domestic Payee PayID Detail")
public class BankingDomesticPayeePayId {

    @BabelFishModelProperty(
        description =  "The name assigned to the PayID by the owner of the PayID"
    )
    String name;

    @BabelFishModelProperty(
        description =  "The identifier of the PayID (dependent on type)",
        required = true
    )
    @NonNull
    @NotNull
    String identifier;

    @BabelFishModelProperty(
        description =  "The type of the PayID",
        required = true
    )
    @NonNull
    @NotNull
    PayloadTypeBankingDomesticPayeePayId type;
}
