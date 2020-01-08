/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *******************************************************************************/
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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Valid
@ToString
@EqualsAndHashCode
@BabelFishModel(description = "International Payee Bank Details")
public abstract class BankingInternationalPayeeBankDetails<T> {
  @BabelFishModelProperty(
      description = "Country of the recipient institution. A valid [ISO 3166 Alpha-3](https://www.iso.org/iso-3166-country-codes.html) country code",
      required = true)
  @JsonSerialize(converter = LocaleToCountryStringConverter.class)
  @JsonDeserialize(converter = CountryStringToLocaleConverter.class)
  Locale country;

  public Locale country() {
    return getCountry();
  }

  @SuppressWarnings("unchecked")
  public T country(Locale country) {
    setCountry(country);
    return (T) this;
  }

  @BabelFishModelProperty(description = "Account Targeted for payment", required = true)
  @NotNull
  @NonNull
  String accountNumber;

  public String accountNumber() {
    return getAccountNumber();
  }

  @SuppressWarnings("unchecked")
  public T accountNumber(String accountNumber) {
    setAccountNumber(accountNumber);
    return (T) this;
  }

  @BabelFishModelProperty
  BankingInternationalPayeeBankDetailsBankAddress<?> bankAddress;

  public BankingInternationalPayeeBankDetailsBankAddress<?> bankAddress() {
    return getBankAddress();
  }

  @SuppressWarnings("unchecked")
  public T bankAddress(BankingInternationalPayeeBankDetailsBankAddress<?> bankAddress) {
    setBankAddress(bankAddress);
    return (T) this;
  }

  @BabelFishModelProperty(
      description = "Swift bank code.  Aligns with standard [ISO 9362](https://www.iso.org/standard/60390.html)")
  // TODO: BIC Code Validation RFC9362
  String beneficiaryBankBIC;

  public String beneficiaryBankBIC() {
    return getBeneficiaryBankBIC();
  }

  @SuppressWarnings("unchecked")
  public T beneficiaryBankBIC(String beneficiaryBankBIC) {
    setBeneficiaryBankBIC(beneficiaryBankBIC);
    return (T) this;
  }

  @BabelFishModelProperty(description = "Number for Fedwire payment (Federal Reserve Wire Network)")
  String fedWireNumber;

  public String fedWireNumber() {
    return getFedWireNumber();
  }

  @SuppressWarnings("unchecked")
  public T fedWireNumber(String fedWireNumber) {
    setFedWireNumber(fedWireNumber);
    return (T) this;
  }

  @BabelFishModelProperty(
      description = "Sort code used for account identification in some jurisdictions")
  String sortCode;

  public String sortCode() {
    return getSortCode();
  }

  @SuppressWarnings("unchecked")
  public T sortCode(String sortCode) {
    setSortCode(sortCode);
    return (T) this;
  }

  @BabelFishModelProperty(description = "Number for the Clearing House Interbank Payments System")
  String chipNumber;

  public String chipNumber() {
    return getChipNumber();
  }

  @SuppressWarnings("unchecked")
  public T chipNumber(String chipNumber) {
    setChipNumber(chipNumber);
    return (T) this;
  }

  @BabelFishModelProperty(description = "International bank routing number")
  String routingNumber;

  public String routingNumber() {
    return getRoutingNumber();
  }

  @SuppressWarnings("unchecked")
  public T routingNumber(String routingNumber) {
    setRoutingNumber(routingNumber);
    return (T) this;
  }

  @BabelFishModelProperty(
      description = "The legal entity identifier (LEI) for the beneficiary.  Aligns with [ISO 17442](https://www.iso.org/standard/59771.html)")
  @Pattern(regexp = TypeConstants.ISO17442_PATTERN)
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
