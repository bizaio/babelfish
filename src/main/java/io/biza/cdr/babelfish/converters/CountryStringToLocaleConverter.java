package io.biza.cdr.babelfish.converters;

import java.util.Locale;
import com.fasterxml.jackson.databind.util.StdConverter;
import io.biza.cdr.babelfish.Constants;

/**
 * A Jackson StdConverter implementation for converting between CDR Country String and Java Locale
 * 
 * @since 0.9.6
 */
public class CountryStringToLocaleConverter extends StdConverter<String, Locale> {

  @Override
  public Locale convert(String value) {
    if (value == null)
      return null;
    for (String twoLetterCode : Locale.getISOCountries()) {
      Locale testLocale = new Locale(Constants.DEFAULT_LOCALE, twoLetterCode);
      if (testLocale.getISO3Country().equals(value)) {
        return testLocale;
      }
    }
    return null;
  }

}
