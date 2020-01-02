package io.biza.cdr.babelfish.converters;

import java.net.URI;
import com.fasterxml.jackson.databind.util.StdConverter;

/**
 * A Jackson StdConverter implementation for converting from CDR URIString 
 * Java URI
 * @since 0.9.6
 */
public class UriStringToUriConverter  extends StdConverter<String, URI> {
	@Override
	public URI convert(String value) {
	    if(value == null) return null;
		return URI.create(value);
	}
}