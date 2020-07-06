/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *******************************************************************************/
package io.biza.babelfish.spring.controlleradvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.biza.babelfish.cdr.models.payloads.ErrorV1;
import io.biza.babelfish.cdr.models.responses.ResponseErrorListV1;
import io.biza.babelfish.common.exceptions.AttributeNotSupportedException;
import io.biza.babelfish.common.exceptions.LabelValueEnumValueNotSupportedException;
import io.biza.babelfish.common.exceptions.PayloadConversionException;
import io.biza.babelfish.common.exceptions.UnsupportedVersionException;
import io.biza.babelfish.spring.Messages;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class CdrExceptionControllerAdvice {

	@ExceptionHandler(AttributeNotSupportedException.class)
	public ResponseEntity<Object> handleAttributeNotSupported(HttpServletRequest req,
			AttributeNotSupportedException ex) {
		LOG.debug("Attribute not supported", ex);
		return ResponseEntity.badRequest()
				.body(ResponseErrorListV1.builder().errors(List.of(io.biza.babelfish.cdr.Constants.ERROR_ATTRIBUTE_NOT_SUPPORTED)).build());
	}

	@ExceptionHandler(LabelValueEnumValueNotSupportedException.class)
	public ResponseEntity<Object> handleInvalidEnum(HttpServletRequest req, AttributeNotSupportedException ex) {
		LOG.debug("LabelValueEnumValue not supported", ex);
		return ResponseEntity.badRequest()
				.body(ResponseErrorListV1.builder().errors(List.of(io.biza.babelfish.cdr.Constants.ERROR_INVALID_ENUM_VALUE)).build());
	}

	@ExceptionHandler(PayloadConversionException.class)
	public ResponseEntity<Object> handlePayloadConversionFailure(HttpServletRequest req,
			PayloadConversionException ex) {
		LOG.debug("PayloadConversionException encountered", ex);
		return ResponseEntity.badRequest().body(
				ResponseErrorListV1.builder().errors(List.of(io.biza.babelfish.cdr.Constants.ERROR_PAYLOAD_CONVERSION_FAILURE)).build());
	}

	@ExceptionHandler(UnsupportedVersionException.class)
	public ResponseEntity<Object> handleUnsupportedPayload(HttpServletRequest req, UnsupportedVersionException ex) {
		LOG.debug("UnsupportedVersionException encountered", ex);
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
				.body(ResponseErrorListV1.builder().errors(List.of(io.biza.babelfish.cdr.Constants.ERROR_UNSUPPORTED_VERSION)).build());
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> handleIllegalArgumentException(HttpServletRequest req, IllegalArgumentException ex) {
		LOG.debug("IllegalArgumentException encountered", ex);
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST).body(
						ResponseErrorListV1.builder()
								.errors(List.of(ErrorV1.builder().code(Messages.ILLEGAL_ARGUMENT_CDR_CODE)
										.detail(ex.getMessage()).title(Messages.ILLEGAL_ARGUMENT_TITLE).build()))
								.build());
	}

}
