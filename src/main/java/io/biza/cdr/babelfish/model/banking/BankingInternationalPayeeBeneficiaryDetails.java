package io.biza.cdr.babelfish.model.banking;

import java.util.Locale;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.biza.cdr.babelfish.converters.LocaleToCountryStringConverter;
import io.biza.cdr.babelfish.converters.CountryStringToLocaleConverter;
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
@BabelFishModel(description =  "International Payee Beneficiary Details")
public class BankingInternationalPayeeBeneficiaryDetails {

    @BabelFishModelProperty(
        description =  "Name of the beneficiary"
    )
    String name;

    @BabelFishModelProperty(
        description =  "Country where the beneficiary resides. A valid [ISO 3166 Alpha-3](https://www.iso.org/iso-3166-country-codes.html) country code",
        required = true,
        dataType = "java.lang.String"
    )
    @NonNull
    @NotNull
	@JsonSerialize(converter = LocaleToCountryStringConverter.class)
	@JsonDeserialize(converter = CountryStringToLocaleConverter.class)
    Locale country;

    @BabelFishModelProperty(
        description =  "Response message for the payment"
    )
    String message;
}
