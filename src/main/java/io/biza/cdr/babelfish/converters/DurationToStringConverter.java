package io.biza.cdr.babelfish.converters;

import java.time.Duration;
import com.fasterxml.jackson.databind.util.StdConverter;

/**
 * A Jackson StdConverter implementation for converting between Java Duration and CDR Duration
 * String
 * 
 * @since 0.9.6
 */
public class DurationToStringConverter extends StdConverter<Duration, String> {
  @Override
  public String convert(Duration value) {
    if (value == null)
      return null;
    return value.toString();
  }
}
