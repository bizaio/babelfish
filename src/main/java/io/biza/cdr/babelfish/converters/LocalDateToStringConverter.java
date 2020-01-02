package io.biza.cdr.babelfish.converters;

import java.time.LocalDate;
import com.fasterxml.jackson.databind.util.StdConverter;
import io.biza.cdr.babelfish.Constants;

/**
 * A Jackson StdConverter implementation for converting between Java LocalDate and CDR DateString
 * 
 * @since 0.9.6
 */
public class LocalDateToStringConverter extends StdConverter<LocalDate, String> {
  @Override
  public String convert(LocalDate value) {
    if (value == null)
      return null;
    return value.format(Constants.CDR_DATESTRING_FORMATTER);
  }
}
