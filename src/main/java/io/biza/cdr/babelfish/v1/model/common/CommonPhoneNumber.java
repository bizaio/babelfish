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
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat;
import io.biza.cdr.babelfish.support.FormatChecker;
import io.biza.cdr.babelfish.support.PhoneNumberValidationResult;
import io.biza.cdr.babelfish.v1.enumerations.CommonPhoneNumberPurpose;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Valid
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)


public class CommonPhoneNumber
    extends io.biza.cdr.babelfish.model.common.CommonPhoneNumber<CommonPhoneNumber> {
  @AssertTrue(message = "Country Code, when supplied, should be in +## format")
  private boolean isCountryCodeValid() {
    return countryCode == null ? true : FormatChecker.phoneNumberCountryCodeValid(countryCode);
  }

  @AssertTrue(message = "Area Code must be supplied when purpose is not MOBILE")
  private boolean isAreaCodeValid() {
    if (purpose() == null)
      return true;
    if (purpose().equals(CommonPhoneNumberPurpose.MOBILE))
      return true;
    return FormatChecker.isNotEmpty(areaCode());
  }

  @AssertTrue(message = "Area Code should not have a leading zero when Country Code is set to +61")
  private boolean isAreaCodeAustralianPrefix() {
    if (countryCode() == null)
      return true;
    if (areaCode() == null)
      return true;
    if (!countryCode().equals("+61"))
      return true;
    return !areaCode().startsWith("0");
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
