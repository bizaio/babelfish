package io.biza.cdr.babelfish.converters;

import java.math.BigDecimal;
import com.fasterxml.jackson.databind.util.StdConverter;
import io.biza.cdr.babelfish.Constants;

/**
 * A Jackson StdConverter implementation for converting between CDR RateString and
 * Java BigDecimal
 * @since 0.9.6
 */
public class RateStringToBigDecimalConverter extends StdConverter<String, BigDecimal> {
	@Override
	public BigDecimal convert(String value) {
		if(value == null) return null;
		return new BigDecimal(value).setScale(Constants.CDR_RATESTRING_SCALE, Constants.CDR_RATESTRING_ROUNDING_MODE);
	}
}