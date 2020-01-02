package io.biza.cdr.babelfish.model.common;

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
@BabelFishModel(description =  "Currency Amount")
public class CommonCurrencyAmount  {

    @BabelFishModelProperty(
        description =  "The current balance of the account at this time. Should align to the current balance available via other channels such as ATM balance enquiry or Internet Banking",
        required = true,
        dataType = "java.lang.String"
    )
    @NonNull
    @NotNull
    @JsonSerialize(converter = BigDecimalToAmountStringConverter.class)
    @JsonDeserialize(converter = AmountStringToBigDecimalConverter.class)
    private BigDecimal amount;
    
    @BabelFishModelProperty(
        description =  "Currency Amount Currency Code",
        required = false,
        dataType = "java.lang.String"
    )
    @JsonSerialize(converter = CurrencyToStringConverter.class)
    @JsonDeserialize(converter = StringToCurrencyConverter.class)
    @Builder.Default
    Currency currency  = Currency.getInstance("AUD");

}

