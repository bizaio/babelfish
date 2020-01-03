/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *******************************************************************************/
package io.biza.cdr.babelfish.model.banking;

import java.util.Locale;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
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

@Valid
@BabelFishModel(description = "International Payee Bank Details")
public interface BankingInternationalPayeeBankDetails {

  @BabelFishModelProperty(
      description = "Country of the recipient institution. A valid [ISO 3166 Alpha-3](https://www.iso.org/iso-3166-country-codes.html) country code",
      required = true)
  @JsonSerialize(converter = LocaleToCountryStringConverter.class)
  @JsonGetter("country")
  public Locale getCountry();

  @JsonDeserialize(converter = CountryStringToLocaleConverter.class)
  @JsonSetter("country")
  public void setCountry(@NotNull Locale country);

  public default BankingInternationalPayeeBankDetails country(@NotNull Locale country) {
    setCountry(country);
    return this;
  }

  @BabelFishModelProperty(description = "Account Targeted for payment", required = true)
  @JsonGetter("accountNumber")
  public String getAccountNumber();

  @JsonSetter("accountNumber")
  public String setAccountNumber(@NotNull String accountNumber);

  public default BankingInternationalPayeeBankDetails accountNumber(@NotNull String accountNumber) {
    setAccountNumber(accountNumber);
    return this;
  }

  @BabelFishModelProperty
  @JsonGetter("bankAddress")
  public BankingInternationalPayeeBankDetailsBankAddress getBankAddress();

  @JsonSetter("bankAddress")
  public void setBankAddress(BankingInternationalPayeeBankDetailsBankAddress bankAddress);

  public default BankingInternationalPayeeBankDetails bankAddress(
      BankingInternationalPayeeBankDetailsBankAddress bankAddress) {
    setBankAddress(bankAddress);
    return this;
  }

  @BabelFishModelProperty(
      description = "Swift bank code.  Aligns with standard [ISO 9362](https://www.iso.org/standard/60390.html)")
  // TODO: BIC Code Validation RFC9362
  @JsonGetter("beneficiaryBankBIC")
  public String getBeneficiaryBankBIC();

  @JsonSetter("beneficiaryBankBIC")
  public void setBeneficiaryBankBIC(String beneficiaryBankBIC);

  public default BankingInternationalPayeeBankDetails beneficiaryBankBIC(
      String beneficiaryBankBIC) {
    setBeneficiaryBankBIC(beneficiaryBankBIC);
    return this;
  }

  @BabelFishModelProperty(description = "Number for Fedwire payment (Federal Reserve Wire Network)")
  @JsonGetter("fedWireNumber")
  public String getFedWireNumber();

  @JsonSetter("fedWireNumber")
  public void setFedWireNumber(String fedWireNumber);

  public default BankingInternationalPayeeBankDetails fedWireNumber(String fedWireNumber) {
    setFedWireNumber(fedWireNumber);
    return this;
  }

  @BabelFishModelProperty(
      description = "Sort code used for account identification in some jurisdictions")
  @JsonGetter("sortCode")
  public String getSortCode();

  @JsonSetter("sortCode")
  public void setSortCode(String sortCode);

  public default BankingInternationalPayeeBankDetails sortCode(String sortCode) {
    setSortCode(sortCode);
    return this;
  }

  @BabelFishModelProperty(description = "Number for the Clearing House Interbank Payments System")
  @JsonGetter("chipNumber")
  public String getChipNumber();

  @JsonSetter("chipNumber")
  public void setChipNumber(String chipNumber);

  public default BankingInternationalPayeeBankDetails chipNumber(String chipNumber) {
    setChipNumber(chipNumber);
    return this;
  }

  @BabelFishModelProperty(description = "International bank routing number")
  @JsonGetter("routingNumber")
  public String getRoutingNumber();

  @JsonSetter("routingNumber")
  public void setRoutingNumber(String routingNumber);

  public default BankingInternationalPayeeBankDetails routingNumber(String routingNumber) {
    setRoutingNumber(routingNumber);
    return this;
  }

  @BabelFishModelProperty(
      description = "The legal entity identifier (LEI) for the beneficiary.  Aligns with [ISO 17442](https://www.iso.org/standard/59771.html)")
  @Pattern(regexp = TypeConstants.ISO17442_PATTERN)
  @JsonGetter("legalEntityIdentifier")
  public String getLegalEntityIdentifier();

  @JsonSetter("legalEntityIdentifier")
  public void setLegalEntityIdentifier(String legalEntityIdentifier);

  public default BankingInternationalPayeeBankDetails legalEntityIdentifier(
      String legalEntityIdentifier) {
    setLegalEntityIdentifier(legalEntityIdentifier);
    return this;
  }
}
