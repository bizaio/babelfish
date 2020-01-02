package io.biza.cdr.babelfish.converters;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import com.fasterxml.jackson.databind.util.StdConverter;
import io.biza.cdr.babelfish.Constants;

/**
 * A Jackson StdConverter implementation for converting between Java BigDecimal and
 * CDR AmountString
 * @since 0.9.6
 */
public class BigDecimalToAmountStringConverter extends StdConverter<BigDecimal, String> {
	@Override
	public String convert(BigDecimal value) {
		if(value == null) return null;
        final DecimalFormat myFormatter = new DecimalFormat(Constants.CDR_AMOUNTSTRING_PATTERN);
        return myFormatter.format(value);
	}
}