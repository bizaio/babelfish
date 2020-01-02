package io.biza.cdr.babelfish.converters;

import java.time.OffsetDateTime;
import com.fasterxml.jackson.databind.util.StdConverter;

/**
 * A Jackson StdConverter implementation for converting between CDR DateTimeString and Java
 * OffsetDateTime
 * 
 * @since 0.9.6
 */
public class DateTimeStringToOffsetDateTimeConverter extends StdConverter<String, OffsetDateTime> {

  @Override
  public OffsetDateTime convert(String value) {
    if (value == null)
      return null;
    return OffsetDateTime.parse(value);
  }

}
