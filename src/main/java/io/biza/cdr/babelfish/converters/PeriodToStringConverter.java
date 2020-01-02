package io.biza.cdr.babelfish.converters;

import java.time.Period;
import com.fasterxml.jackson.databind.util.StdConverter;

/**
 * A Jackson StdConverter implementation for converting between Java Period and CDR DurationString
 * 
 * @since 0.9.6
 */
public class PeriodToStringConverter extends StdConverter<Period, String> {
  @Override
  public String convert(Period value) {
    if (null == value)
      return null;
    return value.toString();
  }
}
