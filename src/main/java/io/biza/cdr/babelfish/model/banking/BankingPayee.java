package io.biza.cdr.babelfish.model.banking;

import java.time.LocalDate;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.converters.LocalDateToStringConverter;
import io.biza.cdr.babelfish.converters.StringToLocalDateConverter;
import io.biza.cdr.babelfish.enumerations.BankingPayeeType;
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
@BabelFishModel(description =  "Banking Payee Basic Information")
public class BankingPayee {

    @BabelFishModelProperty(
        description =  "ID of the payee adhering to the rules of ID permanence",
        required = true
    )
    @NonNull
    @NotNull
    String payeeId;

    @BabelFishModelProperty(
        description =  "The short display name of the payee as provided by the customer",
        required = true
    )
    @NonNull
    @NotNull
    String nickname;

    @BabelFishModelProperty(
        description =  "A description of the payee provided by the customer"
    )
    String description;

    @BabelFishModelProperty(
        description =  "The type of payee. DOMESTIC means a registered payee for domestic payments including NPP. INTERNATIONAL means a registered payee for international payments. BILLER means a registered payee for BPAY",
        required = true
    )
    @NonNull
    @NotNull
    BankingPayeeType type;

    @BabelFishModelProperty(
        description =  "The date the payee was created by the customer",
        dataType = "java.lang.String"
    )
    @JsonSerialize(converter = LocalDateToStringConverter.class)
    @JsonDeserialize(converter = StringToLocalDateConverter.class)
    private LocalDate creationDate;
}
