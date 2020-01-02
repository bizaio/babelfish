package io.biza.cdr.babelfish.converters;

import java.net.URI;
import com.fasterxml.jackson.databind.util.StdConverter;

/**
 * A Jackson StdConverter implementation for converting from Java URI to
 * CDR URIString format
 * @since 0.9.6
 */
public class UriToUriStringConverter  extends StdConverter<URI, String> {
	@Override
	public String convert(URI value) {
	    if(value == null) return null;
		return value.toString();
	}
}