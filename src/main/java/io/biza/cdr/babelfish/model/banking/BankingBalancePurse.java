package io.biza.cdr.babelfish.model.banking;

import java.math.BigDecimal;
import java.util.Currency;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.biza.cdr.babelfish.converters.AmountStringToBigDecimalConverter;
import io.biza.cdr.babelfish.converters.BigDecimalToAmountStringConverter;
import io.biza.cdr.babelfish.converters.CurrencyToStringConverter;
import io.biza.cdr.babelfish.converters.StringToCurrencyConverter;
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
@BabelFishModel(description =  "An Australian Bank Account Purse Balance Representation")
public class BankingBalancePurse {

    @BabelFishModelProperty(
        description =  "The balance available for this additional currency purse",
        required = true
    )
    @JsonSerialize(converter = BigDecimalToAmountStringConverter.class)
    @JsonDeserialize(converter = AmountStringToBigDecimalConverter.class)
    @NonNull
    @NotNull
    BigDecimal amount;

    @BabelFishModelProperty(
        description =  "The currency for the purse"
    )
    @JsonSerialize(converter = CurrencyToStringConverter.class)
    @JsonDeserialize(converter = StringToCurrencyConverter.class)
    Currency currency;
}
