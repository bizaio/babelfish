package io.biza.cdr.babelfish.converters;

import java.util.Base64;
import com.fasterxml.jackson.databind.util.StdConverter;


/**
 * A Jackson StdConverter implementation for converting between Java String and a Base64 String
 * 
 * @since 0.9.6
 */
public class StringToBase64StringConverter extends StdConverter<String, String> {

  @Override
  public String convert(String value) {
    if(value == null) return null;
    return Base64.getEncoder().encodeToString(value.getBytes());
  }

}
