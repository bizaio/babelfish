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
package io.biza.cdr.babelfish.support;

import java.math.BigDecimal;
import java.net.URI;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.validator.routines.EmailValidator;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import io.biza.cdr.babelfish.Constants;
import io.biza.cdr.babelfish.model.common.CommonPhysicalAddressWithPurpose;
import io.biza.cdr.babelfish.v1.enumerations.AddressPurpose;

public class FormatChecker {
  public static boolean phoneNumberCountryCodeValid(String countryCode) {
    if (countryCode == null)
      return false;
    if (!countryCode.startsWith("+"))
      return false;
    try {
      Integer countryCodeNumber = Integer.parseInt(countryCode.replaceFirst("^\\+", ""));
      PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
      // returns ZZ when no match which we invert (ie. country code is valid when result is not ZZ)
      return !phoneUtil.getRegionCodeForCountryCode(countryCodeNumber).equals("ZZ");
    } catch (NumberFormatException e) {
      return false;
    }
  }

  public static boolean isASCIIString(String inputString) {
    return StringUtils.isAsciiPrintable(inputString);
  }

  public static boolean isAbn(String inputAbn) {
    // Strip spacing
    inputAbn = inputAbn.replaceAll(" ", "");
    // Check if it is the right length and is all digits
    if (NumberUtils.isDigits(inputAbn) && inputAbn.length() == 11) {
      System.out.println("Processing input abn of " + inputAbn);
      // "Magical" weighting factors from https://abr.business.gov.au/Help/AbnFormat
      final int[] weightingFactors = {10, 1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
      // Use this to add up the checksums
      int abnChecksum = 0;
      // Iterate over each digit in the abn
      for (int i = 0; i < inputAbn.length(); i++) {
        int valueAtIterator = Character.digit(inputAbn.charAt(i), 10);
        if (i == 0) {
          valueAtIterator--;
        }
        abnChecksum += valueAtIterator * weightingFactors[i];
      }
      // Modulus 89 check 0 remainder. Why did they choose 89? 1989 perhaps? I would have preferred
      // 42...
      return abnChecksum % 89 == 0;
    } else {
      return false;
    }
  }

  // TODO: ACN Check
  public static boolean isAcn(String inputAcn) {
    return true;
  }

  public static boolean isEmail(String inputEmail) {
    return EmailValidator.getInstance().isValid(inputEmail);
  }

  public static PhoneNumberValidationResult phoneNumberValidity(String fullNumber,
      PhoneNumberFormat phoneFormat) {
    if (fullNumber == null) {
      return PhoneNumberValidationResult.INVALID;
    }
    try {
      PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
      PhoneNumber currentNumber = phoneUtil.parse(fullNumber, TypeConstants.AUSTRALIA_ALPHA2);
      if (phoneUtil.isValidNumber(currentNumber)) {
        if (phoneUtil.format(currentNumber, phoneFormat).equals(fullNumber)) {
          return PhoneNumberValidationResult.VALID;
        } else {
          return PhoneNumberValidationResult.INCORRECT_FORMAT;
        }
      } else {
        return PhoneNumberValidationResult.INVALID;
      }
    } catch (NumberParseException e) {
      return PhoneNumberValidationResult.INVALID;
    }
  }

  public static Boolean isPhoneNumber(String fullNumber, PhoneNumberFormat phoneFormat) {
    if (phoneNumberValidity(fullNumber, phoneFormat).equals(PhoneNumberValidationResult.VALID)) {
      return true;
    } else {
      return false;
    }
  }

  public static Boolean isPhoneNumber(String fullNumber) {
    return FormatChecker.isPhoneNumber(fullNumber, PhoneNumberUtil.PhoneNumberFormat.NATIONAL)
        || FormatChecker.isPhoneNumber(fullNumber, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL)
        || FormatChecker.isPhoneNumber(fullNumber, PhoneNumberUtil.PhoneNumberFormat.RFC3966);
  }

  public static Boolean isPanNumber(String pan) {
    return pan != null && pan.matches(TypeConstants.PAN_NUMBER_PATTERN);
  }

  public static Boolean isMaskedPanNumber(String pan) {
    return pan != null && pan.matches(TypeConstants.MASKED_ACCOUNT_PATTERN);
  }

  public static Boolean isPositiveInteger(String integer) {
    try {
      if (Integer.parseInt(integer) > 0) {
        return true;
      }
      return false;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  public static Boolean isDuration(String duration) {
    try {
      Duration.parse(duration);
      return true;
    } catch (DateTimeParseException e) {
      return false;
    }
  }

  public static Boolean isPeriod(String period) {
    try {
      Period.parse(period);
      return true;
    } catch (DateTimeParseException e) {
      return false;
    }
  }

  public static Boolean isAmountString(String decimal) {
    try {
      if (Pattern.matches("^\\-?([1-9](\\d){0,15}|0)\\.(\\d){2,}$", decimal)) {
        // Check it parses to BigDecimal too
        new BigDecimal(decimal);
        return true;
      } else {
        return false;
      }
    } catch (NumberFormatException e) {
      return false;
    }
  }

  public static boolean isDefined(Enum<?> inputEnum) {
    return inputEnum != null;
  }

  public static boolean isDefined(BigDecimal inputDecimal) {
    return inputDecimal != null;
  }

  public static boolean isDefined(String additionalValue) {
    return additionalValue != null;
  }

  public static boolean isNotEmpty(String additionalValue) {
    return isDefined(additionalValue) && additionalValue != "";
  }

  public static boolean isDefined(LocalDateTime dateTime) {
    return dateTime != null;
  }

  public static HashMap<String, String> mapifyQueryString(URI uri) {
    HashMap<String, String> urlMap = new HashMap<String, String>();
    if (uri.getQuery() == null) {
      return urlMap;
    }
    for (String param : uri.getQuery().split("&")) {
      urlMap.put(StringUtils.substringBefore(param, "="), StringUtils.substringAfter(param, "="));
    }
    return urlMap;
  }

  public static Boolean isNaturalNumber(int i) {
    return i >= 0;
  }

  public static Boolean isPositiveInteger(int i) {
    return i > 0;
  }

  public static Boolean isNegativeInteger(int i) {
    return i <= 0;
  }

  public static Boolean isDateTimeString(String input) {
    try {
      LocalDateTime localDateTime =
          LocalDateTime.parse(input, Constants.CDR_DATETIMESTRING_FORMATTER);
      return input.equals(localDateTime.format(Constants.CDR_DATETIMESTRING_FORMATTER));
    } catch (Exception e) {
      return false;
    }
  }

  public static Boolean isDateString(String input) {
    try {
      LocalDate localDate = LocalDate.parse(input);
      return input.equals(localDate.format(Constants.CDR_DATESTRING_FORMATTER));
    } catch (Exception e) {
      return false;
    }
  }

  public static Boolean isCurrencyString(String input) {
    try {
      Currency.getInstance(input);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public static Boolean isRateString(String input) {
    try {
      if (Pattern.matches("^\\-?(0|1){1}\\.(\\d){2,16}$", input)) {
        // Check it parses to BigDecimal too
        new BigDecimal(input);
        return true;
      } else {
        return false;
      }
    } catch (NumberFormatException e) {
      return false;
    }
  }

  public static Boolean isUriString(String input) {
    try {
      URI.create(input);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

}
