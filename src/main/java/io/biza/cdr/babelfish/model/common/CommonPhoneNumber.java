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
package io.biza.cdr.babelfish.model.common;

import java.net.URI;
import java.util.Arrays;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.support.FormatChecker;
import io.biza.cdr.babelfish.support.PhoneNumberValidationResult;
import io.biza.cdr.babelfish.support.TypeConstants;
import io.biza.cdr.babelfish.v1.enumerations.CommonPhoneNumberPurpose;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

@BabelFishModel(description = "Phone Number Detail")
public interface CommonPhoneNumber {

  @BabelFishModelProperty(
      description = "May be true for one and only one entry to indicate the preferred phone number. Assumed to be 'false' if not present")
  @JsonGetter("isPreferred")
  public Boolean getIsPreferred();

  @JsonSetter("isPreferred")
  public void setIsPreferred(Boolean isPreferred);

  public default CommonPhoneNumber isPreferred(Boolean isPreferred) {
    setIsPreferred(isPreferred);
    return this;
  }

  @BabelFishModelProperty(description = "The purpose of the number as specified by the customer",
      required = true)
  @JsonGetter("purpose")
  public CommonPhoneNumberPurpose getPurpose();

  @JsonSetter("purpose")
  public void setPurpose(@NotNull CommonPhoneNumberPurpose purpose);

  public default CommonPhoneNumber purpose(@NotNull CommonPhoneNumberPurpose purpose) {
    setPurpose(purpose);
    return this;
  }

  @BabelFishModelProperty(
      description = "If absent, assumed to be Australia (+61). The + should be included")
  @JsonGetter("countryCode")
  public String getCountryCode();

  @JsonSetter("countryCode")
  public void setCountryCode(@NotNull String countryCode);

  public default CommonPhoneNumber countryCode(@NotNull String countryCode) {
    setCountryCode(countryCode);
    return this;
  }

  @BabelFishModelProperty(
      description = "Required for non Mobile Phones, if field is present and refers to Australian code - the leading 0 should be omitted.")
  @JsonGetter("areaCode")
  public String getAreaCode();

  @JsonSetter("areaCode")
  public void setAreaCode(String areaCode);

  public default CommonPhoneNumber areaCode(String areaCode) {
    setAreaCode(areaCode);
    return this;
  }

  @BabelFishModelProperty(
      description = "The actual phone number, with leading zeros as appropriate", required = true)
  @JsonGetter("number")
  public String getNumber();

  @JsonSetter("number")
  public void setNumber(@NotNull String number);

  public default CommonPhoneNumber number(@NotNull String number) {
    setNumber(number);
    return this;
  }

  @BabelFishModelProperty(description = "An extension number (if applicable)")
  @JsonGetter("extension")
  public String getExtension();

  @JsonSetter("extension")
  public void setExtension(String extension);

  public default CommonPhoneNumber extension(String extension) {
    setExtension(extension);
    return this;
  }

  @BabelFishModelProperty(
      description = "Fully formatted phone number with country code, area code, number and extension incorporated. Formatted according to section 5.1.4. of [RFC 3966](https://www.ietf.org/rfc/rfc3966.txt)",
      required = true)
  @JsonGetter("fullNumber")
  public String getFullNumber();

  @JsonSetter("fullNumber")
  public void setFullNumber(@NotNull String fullNumber);

  public default CommonPhoneNumber fullNumber(@NotNull String fullNumber) {
    setFullNumber(fullNumber);
    return this;
  }

  /**
   * A method to setup a CommonPhoneNumber set of values from a single candidate input number
   * 
   * @param inputNumber Representing a candidate number, will attempt to be parsed
   * @throws NumberParseException if input number could not be parsed
   */
  public default void setWithFullNumber(@NotNull String inputNumber) throws NumberParseException {
    PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
    PhoneNumber number = phoneUtil.parse(inputNumber, TypeConstants.AUSTRALIA_ALPHA2);
    setCountryCode("+" + number.getCountryCode());
    String nationalSignificantNumber = phoneUtil.getNationalSignificantNumber(number);
    int areaCodeLength = phoneUtil.getLengthOfGeographicalAreaCode(number);
    if (areaCodeLength > 0) {
      setAreaCode(nationalSignificantNumber.substring(0, areaCodeLength));
    }
    setNumber(phoneUtil.format(number, PhoneNumberFormat.NATIONAL));
    setFullNumber(phoneUtil.format(number, PhoneNumberFormat.RFC3966));
    setExtension(number.getExtension());
  }

}
