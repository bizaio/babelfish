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
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
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
      if(Pattern.matches("^\\-?([1-9](\\d){0,15}|0)\\.(\\d){2,}$", decimal)) {
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

  public static boolean isAddressPopulated(
      List<CommonPhysicalAddressWithPurpose<?>> physicalAddresses) {
    if (physicalAddresses == null) {
      return false;
    }
    int registeredCount = 0;
    int mailCount = 0;
    for (CommonPhysicalAddressWithPurpose<?> oneAddress : physicalAddresses) {
      if (oneAddress.purpose().equals(AddressPurpose.REGISTERED)) {
        registeredCount++;
      }
      if (oneAddress.purpose().equals(AddressPurpose.MAIL)) {
        mailCount++;
      }
    }
    if (registeredCount != 1) {
      return false;
    }
    if (mailCount != 0 && mailCount != 1) {
      return false;
    }
    return true;
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
}
