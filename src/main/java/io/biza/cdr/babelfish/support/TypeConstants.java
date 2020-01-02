package io.biza.cdr.babelfish.support;

public final class TypeConstants {
	public static final String BASE_PAYLOAD_VERSION = "1";
	public static final String PAN_NUMBER_PATTERN = "(\\d{4} *){3}(\\d{4})";
	public static final String ISO17442_PATTERN = "^[0-9A-Z]{18}[0-9]{2}$";
	public static final String MASKED_ACCOUNT_PATTERN = "(xxx\\\\-xxx xxxxx|(xxxx ){3})?\\\\d{4}";
	public static final String AUSTRALIA_PHONE_CODE = "+61";
	public static final String AUSTRALIA_ALPHA2 = "AU";
}
