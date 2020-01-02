package io.biza.cdr.babelfish.converters;

import java.util.Base64;
import com.fasterxml.jackson.databind.util.StdConverter;

/**
 * A Jackson StdConverter implementation for converting between Java Base64 String and
 * Java String
 * @since 0.9.6
 */
public class Base64StringToStringConverter extends StdConverter<String, String> {
    
    @Override
    public String convert(String value) {
      if(value == null) return null;
      return Base64.getDecoder().decode(value).toString();
    }
    
}