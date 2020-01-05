/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *******************************************************************************/
package io.biza.cdr.babelfish.model.common;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.support.TypeConstants;
import io.biza.cdr.babelfish.v1.enumerations.CommonPhoneNumberPurpose;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Valid
@BabelFishModel(description = "Phone Number Detail")
public abstract class CommonPhoneNumber<T extends CommonPhoneNumber<T>> {
  @BabelFishModelProperty(
      description = "May be true for one and only one entry to indicate the preferred phone number. Assumed to be 'false' if not present")
  @JsonProperty("isPreferred")
  public Boolean isPreferred = false;

  @BabelFishModelProperty(description = "The purpose of the number as specified by the customer",
      required = true)
  @JsonProperty("purpose")
  @NotNull
  @NonNull
  public CommonPhoneNumberPurpose purpose;

  public CommonPhoneNumberPurpose purpose() {
    return getPurpose();
  }

  @SuppressWarnings("unchecked")
  public T purpose(CommonPhoneNumberPurpose purpose) {
    setPurpose(purpose);
    return (T) this;
  }

  @BabelFishModelProperty(
      description = "If absent, assumed to be Australia (+61). The + should be included")
  @JsonProperty("countryCode")
  public String countryCode;

  public String countryCode() {
    return getCountryCode();
  }

  @SuppressWarnings("unchecked")
  public T countryCode(String countryCode) {
    setCountryCode(countryCode);
    return (T) this;
  }

  @BabelFishModelProperty(
      description = "Required for non Mobile Phones, if field is present and refers to Australian code - the leading 0 should be omitted.")
  @JsonProperty("areaCode")
  public String areaCode;

  public String areaCode() {
    return getAreaCode();
  }

  @SuppressWarnings("unchecked")
  public T areaCode(String areaCode) {
    setAreaCode(areaCode);
    return (T) this;
  }

  @BabelFishModelProperty(
      description = "The actual phone number, with leading zeros as appropriate", required = true)
  @JsonProperty("number")
  public String number;

  public String number() {
    return getNumber();
  }

  @SuppressWarnings("unchecked")
  public T number(String number) {
    setNumber(number);
    return (T) this;
  }

  @BabelFishModelProperty(description = "An extension number (if applicable)")
  @JsonProperty("extension")
  public String extension;

  public String extension() {
    return getExtension();
  }

  @SuppressWarnings("unchecked")
  public T extension(String extension) {
    setExtension(extension);
    return (T) this;
  }

  @BabelFishModelProperty(
      description = "Fully formatted phone number with country code, area code, number and extension incorporated. Formatted according to section 5.1.4. of [RFC 3966](https://www.ietf.org/rfc/rfc3966.txt)",
      required = true)
  @JsonProperty("fullNumber")
  public String fullNumber;

  public String fullNumber() {
    return getFullNumber();
  }

  @SuppressWarnings("unchecked")
  public T fullNumber(String fullNumber) {
    setFullNumber(fullNumber);
    return (T) this;
  }

  /**
   * A method to setup a CommonPhoneNumber set of values from a single candidate input number
   * 
   * @param inputNumber Representing a candidate number, will attempt to be parsed
   * @throws NumberParseException if input number could not be parsed
   */
  public void setWithFullNumber(@NotNull String inputNumber) throws NumberParseException {
    PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
    PhoneNumber number = phoneUtil.parse(inputNumber, TypeConstants.AUSTRALIA_ALPHA2);
    countryCode("+" + number.getCountryCode());
    String nationalSignificantNumber = phoneUtil.getNationalSignificantNumber(number);
    int areaCodeLength = phoneUtil.getLengthOfGeographicalAreaCode(number);
    if (areaCodeLength > 0) {
      areaCode(nationalSignificantNumber.substring(0, areaCodeLength));
    }
    fullNumber(phoneUtil.format(number, PhoneNumberFormat.NATIONAL));
    fullNumber(phoneUtil.format(number, PhoneNumberFormat.RFC3966));
    extension(number.getExtension());
  }
}
