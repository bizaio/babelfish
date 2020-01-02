package io.biza.cdr.babelfish.converters;

import java.time.LocalDate;
import com.fasterxml.jackson.databind.util.StdConverter;
import io.biza.cdr.babelfish.Constants;

/**
 * A Jackson StdConverter implementation for converting between CDR DateString and
 * Java LocalDate
 * @since 0.9.6
 */
public class StringToLocalDateConverter extends StdConverter<String, LocalDate> {
    
    @Override
    public LocalDate convert(String value) {
        return LocalDate.parse(value, Constants.CDR_DATESTRING_FORMATTER);
    }
    
}