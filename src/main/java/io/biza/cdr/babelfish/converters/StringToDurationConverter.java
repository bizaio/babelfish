package io.biza.cdr.babelfish.converters;

import java.time.Duration;
import com.fasterxml.jackson.databind.util.StdConverter;

/**
 * A Jackson StdConverter implementation for converting between CDR Duration String Java Duration
 * 
 * @since 0.9.6
 */
public class StringToDurationConverter extends StdConverter<String, Duration> {
  @Override
  public Duration convert(String value) {
    if (null == value)
      return null;
    return Duration.parse(value);
  }
}
