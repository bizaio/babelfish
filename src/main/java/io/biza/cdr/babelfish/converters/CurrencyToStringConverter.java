package io.biza.cdr.babelfish.converters;

import java.util.Currency;
import com.fasterxml.jackson.databind.util.StdConverter;

/**
 * A Jackson StdConverter implementation for converting between Java Currency and
 * CDR CurrencyString
 * @since 0.9.6
 */
public class CurrencyToStringConverter extends StdConverter<Currency, String> {
	@Override
	public String convert(Currency value) {
		if(value == null) return null;
		return value.getCurrencyCode();
	}
}
