package io.biza.cdr.babelfish.converters;

import java.time.Period;
import com.fasterxml.jackson.databind.util.StdConverter;

/**
 * A Jackson StdConverter implementation for converting between CDR DurationString and Java Period
 * 
 * @since 0.9.6
 */
public class StringToPeriodConverter extends StdConverter<String, Period> {
  @Override
  public Period convert(String value) {
    if (value == null)
      return null;
    return Period.parse(value);
  }
}
