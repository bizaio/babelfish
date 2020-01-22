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
package io.biza.babelfish.cdr.model.banking;

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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Valid
@ToString
@EqualsAndHashCode
@Schema(description = "International Payee Bank Details")
public abstract class BankingInternationalPayeeBankDetails<T> {
  @Schema(
      description = "Country of the recipient institution. A valid [ISO 3166 Alpha-3](https://www.iso.org/iso-3166-country-codes.html) country code",
      required = true)
  @JsonSerialize(converter = LocaleToCountryStringConverter.class)
  @JsonDeserialize(converter = CountryStringToLocaleConverter.class)
  @JsonProperty("country")
  Locale country;

  public Locale country() {
    return getCountry();
  }

  @SuppressWarnings("unchecked")
  public T country(Locale country) {
    setCountry(country);
    return (T) this;
  }

  @Schema(description = "Account Targeted for payment", required = true)
  @NotNull
  @JsonProperty("accountNumber")
  String accountNumber;

  public String accountNumber() {
    return getAccountNumber();
  }

  @SuppressWarnings("unchecked")
  public T accountNumber(String accountNumber) {
    setAccountNumber(accountNumber);
    return (T) this;
  }

  @Schema(description = "International Payee Bank Address Details")
  @JsonProperty("bankAddress")
  @Valid
  BankingInternationalPayeeBankDetailsBankAddress<?> bankAddress;

  public BankingInternationalPayeeBankDetailsBankAddress<?> bankAddress() {
    return getBankAddress();
  }

  @SuppressWarnings("unchecked")
  public T bankAddress(BankingInternationalPayeeBankDetailsBankAddress<?> bankAddress) {
    setBankAddress(bankAddress);
    return (T) this;
  }

  @Schema(
      description = "Swift bank code.  Aligns with standard [ISO 9362](https://www.iso.org/standard/60390.html)")
  // TODO: BIC Code Validation RFC9362
  @JsonProperty("beneficiaryBankBIC")
  String beneficiaryBankBIC;

  public String beneficiaryBankBIC() {
    return getBeneficiaryBankBIC();
  }

  @SuppressWarnings("unchecked")
  public T beneficiaryBankBIC(String beneficiaryBankBIC) {
    setBeneficiaryBankBIC(beneficiaryBankBIC);
    return (T) this;
  }

  @Schema(description = "Number for Fedwire payment (Federal Reserve Wire Network)")
  @JsonProperty("fedWireNumber")
  String fedWireNumber;

  public String fedWireNumber() {
    return getFedWireNumber();
  }

  @SuppressWarnings("unchecked")
  public T fedWireNumber(String fedWireNumber) {
    setFedWireNumber(fedWireNumber);
    return (T) this;
  }

  @Schema(description = "Sort code used for account identification in some jurisdictions")
  @JsonProperty("sortCode")
  String sortCode;

  public String sortCode() {
    return getSortCode();
  }

  @SuppressWarnings("unchecked")
  public T sortCode(String sortCode) {
    setSortCode(sortCode);
    return (T) this;
  }

  @Schema(description = "Number for the Clearing House Interbank Payments System")
  @JsonProperty("chipNumber")
  String chipNumber;

  public String chipNumber() {
    return getChipNumber();
  }

  @SuppressWarnings("unchecked")
  public T chipNumber(String chipNumber) {
    setChipNumber(chipNumber);
    return (T) this;
  }

  @Schema(description = "International bank routing number")
  @JsonProperty("routingNumber")
  String routingNumber;

  public String routingNumber() {
    return getRoutingNumber();
  }

  @SuppressWarnings("unchecked")
  public T routingNumber(String routingNumber) {
    setRoutingNumber(routingNumber);
    return (T) this;
  }

  @Schema(
      description = "The legal entity identifier (LEI) for the beneficiary.  Aligns with [ISO 17442](https://www.iso.org/standard/59771.html)")
  @Pattern(regexp = TypeConstants.ISO17442_PATTERN)
  @JsonProperty("legalEntityIdentifier")
  String legalEntityIdentifier;

  public String legalEntityIdentifier() {
    return getLegalEntityIdentifier();
  }

  @SuppressWarnings("unchecked")
  public T legalEntityIdentifier(String legalEntityIdentifier) {
    setLegalEntityIdentifier(legalEntityIdentifier);
    return (T) this;
  }
}
