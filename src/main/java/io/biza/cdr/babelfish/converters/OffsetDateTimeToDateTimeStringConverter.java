package io.biza.cdr.babelfish.converters;

import java.time.OffsetDateTime;
import com.fasterxml.jackson.databind.util.StdConverter;
import io.biza.cdr.babelfish.Constants;

/**
 * A Jackson StdConverter implementation for converting between Java OffsetDateTime and
 * CDR DateTimeString
 * @since 0.9.6
 */
public class OffsetDateTimeToDateTimeStringConverter extends StdConverter<OffsetDateTime, String> {
    @Override
    public String convert(OffsetDateTime value) {
      if(value == null) return null;      
    	return value.format(Constants.CDR_DATETIMESTRING_FORMATTER);
    }
}