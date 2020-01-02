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
@BabelFishModel(description =  "Banking Transaction Detail X2P101 Payload")
public class BankingTransactionDetailExtendedDataX2p101Payload {

    @BabelFishModelProperty(
        description =  "An extended string description. Only present if specified by the extensionUType field",
        required = true
    )
    @NonNull
    @NotNull
    String extendedDescription;

    @BabelFishModelProperty(
        description =  "An end to end ID for the payment created at initiation"
    )
    String endToEndId;

    @BabelFishModelProperty(
        description =  "Purpose of the payment.  Format is defined by NPP standards for the x2p1.01 overlay service"
    )
    String purposeCode;
}
