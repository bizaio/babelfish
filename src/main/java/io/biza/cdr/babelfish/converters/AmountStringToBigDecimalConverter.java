package io.biza.cdr.babelfish.converters;

import java.math.BigDecimal;
import com.fasterxml.jackson.databind.util.StdConverter;

/**
 * A Jackson StdConverter implementation for converting between CDR AmountString and
 * Java BigDecimal
 * @since 0.9.6
 */
public class AmountStringToBigDecimalConverter extends StdConverter<String, BigDecimal> {
  @Override
  public BigDecimal convert(String value) {
    if(value == null) return null;
    return new BigDecimal(value);
  }
}
