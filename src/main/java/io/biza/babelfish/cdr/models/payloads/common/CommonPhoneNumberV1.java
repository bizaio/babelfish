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
package io.biza.babelfish.cdr.models.payloads.common;

import java.util.Arrays;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import io.biza.babelfish.cdr.enumerations.CommonPhoneNumberPurpose;
import io.biza.babelfish.cdr.support.FormatChecker;
import io.biza.babelfish.cdr.support.PhoneNumberValidationResult;
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
@Schema(description = "Phone Number Detail", name = "CommonPhoneNumber")
public class CommonPhoneNumberV1 {
  @Schema(
      description = "May be true for one and only one entry to indicate the preferred phone number. Assumed to be 'false' if not present")
  @JsonProperty("isPreferred")
  @Builder.Default
  Boolean isPreferred = false;

  @Schema(description = "The purpose of the number as specified by the customer", required = true)
  @JsonProperty("purpose")
  @NotNull
  CommonPhoneNumberPurpose purpose;

  @Schema(description = "If absent, assumed to be Australia (+61). The + should be included")
  @JsonProperty("countryCode")
  @Builder.Default
  String countryCode = "+61";

  @Schema(
      description = "Required for non Mobile Phones, if field is present and refers to Australian code - the leading 0 should be omitted.")
  @JsonProperty("areaCode")
  String areaCode;

  @Schema(description = "The actual phone number, with leading zeros as appropriate",
      required = true)
  @JsonProperty("number")
  String number;

  @Schema(description = "An extension number (if applicable)")
  @JsonProperty("extension")
  String extension;

  @Schema(
      description = "Fully formatted phone number with country code, area code, number and extension incorporated. Formatted according to section 5.1.4. of [RFC 3966](https://www.ietf.org/rfc/rfc3966.txt)",
      required = true)
  @JsonProperty("fullNumber")
  String fullNumber;

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
  
  @AssertTrue(message = "Country Code, when supplied, should be in +## format")
  private boolean isCountryCodeValid() {
    return countryCode() == null ? true : FormatChecker.phoneNumberCountryCodeValid(countryCode());
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
        .contains(FormatChecker.phoneNumberValidity(fullNumber(), PhoneNumberFormat.RFC3966));
  }

  @AssertTrue(
      message = "Full Phone Number must be formatted according to section 5.1.4. of RFC 3966")
  private boolean isFullPhoneNumberCorrectlyFormatted() {
    return Arrays
        .asList(new PhoneNumberValidationResult[] {PhoneNumberValidationResult.INCORRECT_FORMAT})
        .contains(FormatChecker.phoneNumberValidity(fullNumber(), PhoneNumberFormat.RFC3966));
  }
}
