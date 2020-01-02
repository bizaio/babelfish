package io.biza.cdr.babelfish.converters;

import java.util.Locale;
import com.fasterxml.jackson.databind.util.StdConverter;

/**
 * A Jackson StdConverter implementation for converting between Java Locale and CDR CountryString
 * 
 * @since 0.9.6
 */
public class LocaleToCountryStringConverter extends StdConverter<Locale, String> {
  @Override
  public String convert(Locale value) {
    if (value == null)
      return null;
    return value.getISO3Country();
  }
}
