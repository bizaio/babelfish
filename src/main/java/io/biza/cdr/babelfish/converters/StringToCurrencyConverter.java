package io.biza.cdr.babelfish.converters;

import java.util.Currency;
import com.fasterxml.jackson.databind.util.StdConverter;

/**
 * A Jackson StdConverter implementation for converting between CDR CurrencyString and
 * Java Currency
 * @since 0.9.6
 */
public class StringToCurrencyConverter extends StdConverter<String, Currency> {
	@Override
	public Currency convert(String value) {
		if(value == null) return null;
		return Currency.getInstance(value);
	}
}