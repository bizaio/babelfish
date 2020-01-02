package io.biza.cdr.babelfish.model.common;

import java.util.Arrays;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import io.biza.cdr.babelfish.enumerations.CommonPhoneNumberPurpose;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.support.FormatChecker;
import io.biza.cdr.babelfish.support.PhoneNumberValidationResult;
import io.biza.cdr.babelfish.support.TypeConstants;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@Builder
@Data
@Valid
@BabelFishModel(description = "Phone Number Detail")
public class CommonPhoneNumber {

	@BabelFishModelProperty(description = "May be true for one and only one entry to indicate the preferred phone number. Assumed to be 'false' if not present")
	Boolean isPreferred;

	@BabelFishModelProperty(description = "The purpose of the number as specified by the customer", required = true)
	@NonNull
	@NotNull
	CommonPhoneNumberPurpose purpose;

	@BabelFishModelProperty(description = "If absent, assumed to be Australia (+61). The + should be included")
	@Builder.Default
	String countryCode = TypeConstants.AUSTRALIA_PHONE_CODE;

	@BabelFishModelProperty(description = "Required for non Mobile Phones, if field is present and refers to Australian code - the leading 0 should be omitted.")
	/** 
	 * AMBIGUOUS: Description from Standards unclear and areaCode is not ISO standardised
	 * setByFullNumber uses a best guess for this
	 */
	String areaCode;

	@BabelFishModelProperty(description = "The actual phone number, with leading zeros as appropriate", required = true)
	@NonNull
	@NotNull
	String number;

	@BabelFishModelProperty(description = "An extension number (if applicable)")
	String extension;

	@BabelFishModelProperty(description = "Fully formatted phone number with country code, area code, number and extension incorporated. Formatted according to section 5.1.4. of [RFC 3966](https://www.ietf.org/rfc/rfc3966.txt)", required = true)
	@NonNull
	@NotNull
	String fullNumber;

	/**
	 * A method to setup a CommonPhoneNumber set of values from a single candidate
	 * input number
	 * 
	 * @param inputNumber Representing a candidate number, will attempt to be parsed
	 * @throws NumberParseException if input number could not be parsed
	 */
	public void setByFullNumber(String inputNumber) throws NumberParseException {
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

	@AssertTrue(message = "Country Code, when supplied, should be in +## format")
	private boolean isCountryCodeValid() {
		return countryCode == null ? true : FormatChecker.phoneNumberCountryCodeValid(countryCode);
	}

	@AssertTrue(message = "Full Phone Number could not be passed as possibly valid")
	private boolean isFullPhoneNumberValid() {
		return Arrays.asList(new PhoneNumberValidationResult[] { PhoneNumberValidationResult.VALID, PhoneNumberValidationResult.INCORRECT_FORMAT })
				.contains(FormatChecker.phoneNumberValidity(fullNumber, PhoneNumberFormat.RFC3966));
	}

	@AssertTrue(message = "Full Phone Number must be formatted according to section 5.1.4. of RFC 3966")
	private boolean isFullPhoneNumberCorrectlyFormatted() {
		return Arrays.asList(new PhoneNumberValidationResult[] { PhoneNumberValidationResult.INCORRECT_FORMAT })
				.contains(FormatChecker.phoneNumberValidity(fullNumber, PhoneNumberFormat.RFC3966));
	}
}
