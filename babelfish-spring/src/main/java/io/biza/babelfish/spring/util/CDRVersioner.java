/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *******************************************************************************/
package io.biza.babelfish.spring.util;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import io.biza.babelfish.common.exceptions.PayloadConversionException;
import io.biza.babelfish.common.exceptions.UnsupportedVersionException;
import io.biza.babelfish.converter.support.BabelfishVersioner;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CDRVersioner {

  public static String HEADER_VERSION = "x-v";
  public static String HEADER_MIN_VERSION = "x-min-v";

  public static Object convert(Object inputData)
      throws PayloadConversionException, UnsupportedVersionException {
    Class<?> destinationType = BabelfishVersioner.getVersionedClass(inputData.getClass(),
        getRequestedVersion(), getRequestedMinimumVersion());

    return BabelfishVersioner.convert(inputData.getClass().cast(inputData), destinationType);

  }

  public static Integer getRequestedVersion() throws UnsupportedVersionException {
    /**
     * Make sure this is a servlet based request
     */
    RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
    if (!(requestAttributes instanceof ServletRequestAttributes)) {
      LOG.error("Call made to inspect servlet request when this is not a Servlet Request");
      throw new UnsupportedVersionException("Unable to identify HttpServletRequest context");
    }
    HttpServletRequest httpRequest = ((ServletRequestAttributes) requestAttributes).getRequest();

    /**
     * Verify mandatory version header is supplied
     */
    if (httpRequest.getHeader(HEADER_VERSION) == null) {
      LOG.warn("Payload version header {} was null", HEADER_VERSION);
      throw new UnsupportedVersionException(
          HEADER_VERSION + " header is mandatory for CDR endpoint requests");
    }

    try {
      return Integer.parseInt(httpRequest.getHeader(HEADER_VERSION));
    } catch (NumberFormatException e) {
      LOG.warn("Unparseable payload version of {} requested", httpRequest.getHeader(HEADER_VERSION));
      throw new UnsupportedVersionException(
          "Requested an unparseable version of " + httpRequest.getHeader(HEADER_VERSION));
    }
  }

  public static Optional<Integer> getRequestedMinimumVersion() throws UnsupportedVersionException {
    /**
     * Make sure this is a servlet based request
     */
    RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
    if (!(requestAttributes instanceof ServletRequestAttributes)) {
      LOG.error("Call made to inspect servlet request when this is not a Servlet Request");
      throw new UnsupportedVersionException("Unable to identify HttpServletRequest context");
    }
    HttpServletRequest httpRequest = ((ServletRequestAttributes) requestAttributes).getRequest();

    if (httpRequest.getHeader(HEADER_MIN_VERSION) == null) {
      return Optional.empty();
    } else {
      try {
        return Optional.of(Integer.parseInt(httpRequest.getHeader(HEADER_MIN_VERSION)));
      } catch (NumberFormatException e) {
        LOG.warn(
            "Requested an invalid minimum version of " + httpRequest.getHeader(HEADER_MIN_VERSION));
        return Optional.empty();
      }
    }
  }

  public static Boolean needsConversion(Class<?> clazz) throws UnsupportedVersionException {
    return BabelfishVersioner.getVersion(clazz) != getRequestedVersion();
  }
}
