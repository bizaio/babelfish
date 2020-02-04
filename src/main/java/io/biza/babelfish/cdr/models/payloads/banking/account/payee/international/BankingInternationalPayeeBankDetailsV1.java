/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU Lesser General Public License as published by the Free Software Foundation, either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *******************************************************************************/
package io.biza.babelfish.cdr.models.payloads.banking.account.payee.international;

import java.util.Locale;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.cdr.converters.CountryStringToLocaleConverter;
import io.biza.babelfish.cdr.converters.LocaleToCountryStringConverter;
import io.biza.babelfish.cdr.support.TypeConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Valid
@ToString
@EqualsAndHashCode
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "International Payee Bank Details", name = "BankingInternationalPayeeBankDetailsV1")
public class BankingInternationalPayeeBankDetailsV1 {
  @Schema(
      description = "Country of the recipient institution. A valid [ISO 3166 Alpha-3](https://www.iso.org/iso-3166-country-codes.html) country code",
      required = true)
  @JsonSerialize(converter = LocaleToCountryStringConverter.class)
  @JsonDeserialize(converter = CountryStringToLocaleConverter.class)
  @JsonProperty("country")
  Locale country;

  @Schema(description = "Account Targeted for payment", required = true)
  @NotNull
  @JsonProperty("accountNumber")
  String accountNumber;

  @Schema(description = "International Payee Bank Address Details")
  @JsonProperty("bankAddress")
  @Valid
  BankingInternationalPayeeBankDetailsBankAddressV1 bankAddress;

  @Schema(
      description = "Swift bank code.  Aligns with standard [ISO 9362](https://www.iso.org/standard/60390.html)")
  // TODO: BIC Code Validation RFC9362
  @JsonProperty("beneficiaryBankBIC")
  String beneficiaryBankBIC;

  @Schema(description = "Number for Fedwire payment (Federal Reserve Wire Network)")
  @JsonProperty("fedWireNumber")
  String fedWireNumber;

  @Schema(description = "Sort code used for account identification in some jurisdictions")
  @JsonProperty("sortCode")
  String sortCode;

  @Schema(description = "Number for the Clearing House Interbank Payments System")
  @JsonProperty("chipNumber")
  String chipNumber;

  @Schema(description = "International bank routing number")
  @JsonProperty("routingNumber")
  String routingNumber;

  @Schema(
      description = "The legal entity identifier (LEI) for the beneficiary.  Aligns with [ISO 17442](https://www.iso.org/standard/59771.html)")
  @Pattern(regexp = TypeConstants.ISO17442_PATTERN)
  @JsonProperty("legalEntityIdentifier")
  String legalEntityIdentifier;
}
