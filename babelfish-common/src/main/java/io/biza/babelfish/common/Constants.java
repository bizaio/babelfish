package io.biza.babelfish.common;

import java.math.RoundingMode;
import java.time.format.DateTimeFormatter;

public class Constants {
	
	/**
	 * Generic Constants
	 */
	public static final String DEFAULT_LOCALE = "en";
	
	/**
	 * CDR AmountString
	 */
	public static final String CDR_AMOUNTSTRING_PATTERN = "##############0.00###;-##############0.00###";
	
	/**
	 * CDR RateString
	 */
	public static final String CDR_RATESTRING_PATTERN = "0.00##############;0.00##############";

	public static final int CDR_RATESTRING_SCALE = 16;

	public static final RoundingMode CDR_RATESTRING_ROUNDING_MODE = RoundingMode.HALF_EVEN;

	/**
	 * CDR DateTimeString
	 */
	public static final DateTimeFormatter CDR_DATETIMESTRING_FORMATTER = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

	/**
	 * CDR DateString
	 */
	public static final DateTimeFormatter CDR_DATESTRING_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;
}
