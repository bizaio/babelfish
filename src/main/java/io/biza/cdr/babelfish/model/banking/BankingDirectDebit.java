package io.biza.cdr.babelfish.model.banking;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.biza.cdr.babelfish.converters.AmountStringToBigDecimalConverter;
import io.biza.cdr.babelfish.converters.BigDecimalToAmountStringConverter;
import io.biza.cdr.babelfish.converters.OffsetDateTimeToDateTimeStringConverter;
import io.biza.cdr.babelfish.converters.DateTimeStringToOffsetDateTimeConverter;
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
@BabelFishModel(description =  "Representation of a Direct Debit Authorisation")
public class BankingDirectDebit {

    @BabelFishModelProperty(
        description =  "A unique ID of the account adhering to the standards for ID permanence.",
        required = true
    )
    @NonNull
    @NotNull
    String accountId;

    @BabelFishModelProperty(
        required = true
    )
    @NonNull
    @NotNull
    BankingAuthorisedEntity authorisedEntity;

    @BabelFishModelProperty(
        description =  "The date and time of the last debit executed under this authorisation",
        dataType = "java.lang.String"
    )
    @JsonSerialize(converter = OffsetDateTimeToDateTimeStringConverter.class)
    @JsonDeserialize(converter = DateTimeStringToOffsetDateTimeConverter.class)
    LocalDateTime lastDebitDateTime;

    @BabelFishModelProperty(
        description =  "The amount of the last debit executed under this authorisation"
    )
    @JsonSerialize(converter = BigDecimalToAmountStringConverter.class)
    @JsonDeserialize(converter = AmountStringToBigDecimalConverter.class)
    BigDecimal lastDebitAmount;
}
