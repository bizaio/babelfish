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
package io.biza.cdr.babelfish.v1.model.common;

import java.util.Arrays;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
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

@Valid
public class CommonPhoneNumber extends io.biza.cdr.babelfish.model.common.CommonPhoneNumber {

  public CommonPhoneNumber(@NonNull CommonPhoneNumberPurpose purpose, @NonNull String number,
      @NonNull String fullNumber) {
    super(purpose, number, fullNumber);
  }

  @AssertTrue(message = "Country Code, when supplied, should be in +## format")
  private boolean isCountryCodeValid() {
    return countryCode == null ? true : FormatChecker.phoneNumberCountryCodeValid(countryCode);
  }

  @AssertTrue(message = "Full Phone Number could not be passed as possibly valid")
  private boolean isFullPhoneNumberValid() {
    return Arrays
        .asList(new PhoneNumberValidationResult[] {PhoneNumberValidationResult.VALID,
            PhoneNumberValidationResult.INCORRECT_FORMAT})
        .contains(FormatChecker.phoneNumberValidity(fullNumber, PhoneNumberFormat.RFC3966));
  }

  @AssertTrue(
      message = "Full Phone Number must be formatted according to section 5.1.4. of RFC 3966")
  private boolean isFullPhoneNumberCorrectlyFormatted() {
    return Arrays
        .asList(new PhoneNumberValidationResult[] {PhoneNumberValidationResult.INCORRECT_FORMAT})
        .contains(FormatChecker.phoneNumberValidity(fullNumber, PhoneNumberFormat.RFC3966));
  }
}
