/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU Lesser General Public License as published by the Free Software Foundation, either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *******************************************************************************/
package io.biza.babelfish.cdr.converters;

import java.net.URI;
import java.net.URISyntaxException;
import com.fasterxml.jackson.databind.util.StdConverter;

/**
 * A Jackson StdConverter implementation for converting from CDR URIString Java URI
 * 
 * @since 0.9.6
 */
public class UriStringToUriConverter extends StdConverter<String, URI> {
  @Override
  public URI convert(String value) {
    if (null == value || value == "")
      return null;

    try {
      URI uri = new URI(value);
      if (!uri.isAbsolute() || uri.isOpaque()) {
        throw new RuntimeException("Supplied URL must be absolute AND not opaque");
      }
      return uri;
    } catch (URISyntaxException e) {
      throw new RuntimeException("Invalid URL Specified: " + e.getReason());
    }
  }
}
