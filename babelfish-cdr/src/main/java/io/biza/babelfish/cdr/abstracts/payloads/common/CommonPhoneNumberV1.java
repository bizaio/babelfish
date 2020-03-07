package io.biza.babelfish.cdr.abstracts.payloads.common;

import java.util.Arrays;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import io.biza.babelfish.cdr.enumerations.CommonPhoneNumberPurpose;
import io.biza.babelfish.cdr.support.FormatChecker;
import io.biza.babelfish.cdr.support.PhoneNumberValidationResult;
import io.biza.babelfish.cdr.support.TypeConstants;

public abstract class CommonPhoneNumberV1<T> {

  public abstract String countryCode();

  public abstract T countryCode(String countryCode);

  public abstract CommonPhoneNumberPurpose purpose();

  public abstract String areaCode();

  public abstract String fullNumber();

  public abstract T fullNumber(String fullNumber);

  public abstract T areaCode(String areaCode);

  public abstract T extension(String extension);

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
