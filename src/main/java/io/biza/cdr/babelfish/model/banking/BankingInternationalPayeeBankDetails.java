package io.biza.cdr.babelfish.model.banking;

import java.util.Locale;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.cdr.babelfish.converters.LocaleToCountryStringConverter;
import io.biza.cdr.babelfish.converters.CountryStringToLocaleConverter;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.support.TypeConstants;
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
@BabelFishModel(description =  "International Payee Bank Details")
public class BankingInternationalPayeeBankDetails {

    @BabelFishModelProperty(
        description =  "Country of the recipient institution. A valid [ISO 3166 Alpha-3](https://www.iso.org/iso-3166-country-codes.html) country code",
        required = true
    )
	@JsonSerialize(converter = LocaleToCountryStringConverter.class)
	@JsonDeserialize(converter = CountryStringToLocaleConverter.class)
    Locale country;

    @BabelFishModelProperty(
        description =  "Account Targeted for payment",
        required = true
    )
    @NotNull
    @NonNull
    String accountNumber;

    @BabelFishModelProperty
    BankingInternationalPayeeBankDetailsBankAddress bankAddress;

    @BabelFishModelProperty(
        description =  "Swift bank code.  Aligns with standard [ISO 9362](https://www.iso.org/standard/60390.html)"
    )
    // TODO: BIC Code Validation RFC9362
    String beneficiaryBankBIC;

    @BabelFishModelProperty(
        description =  "Number for Fedwire payment (Federal Reserve Wire Network)"
    )
    String fedWireNumber;

    @BabelFishModelProperty(
        description =  "Sort code used for account identification in some jurisdictions"
    )
    String sortCode;

    @BabelFishModelProperty(
        description =  "Number for the Clearing House Interbank Payments System"
    )
    String chipNumber;

    @BabelFishModelProperty(
        description =  "International bank routing number"
    )
    String routingNumber;

    @BabelFishModelProperty(
        description =  "The legal entity identifier (LEI) for the beneficiary.  Aligns with [ISO 17442](https://www.iso.org/standard/59771.html)"
    )
    @Pattern(regexp = TypeConstants.ISO17442_PATTERN)
    String legalEntityIdentifier;
    
}
