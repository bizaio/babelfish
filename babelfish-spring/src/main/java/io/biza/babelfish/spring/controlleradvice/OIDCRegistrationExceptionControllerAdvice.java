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

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.biza.babelfish.oidc.Constants;
import io.biza.babelfish.oidc.OIDCErrorMessages;
import io.biza.babelfish.oidc.enumerations.RegistrationErrorCode;
import io.biza.babelfish.oidc.exceptions.oauth2.registration.InvalidClientMetadataException;
import io.biza.babelfish.oidc.exceptions.oauth2.registration.InvalidRedirectUriException;
import io.biza.babelfish.oidc.exceptions.oauth2.registration.InvalidSoftwareStatementException;
import io.biza.babelfish.oidc.exceptions.oauth2.registration.UnapprovedSoftwareStatementException;
import io.biza.babelfish.oidc.requests.DynamicRegistrationErrorResponse;
import io.biza.babelfish.util.MessageUtil;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class OIDCRegistrationExceptionControllerAdvice {

	private DynamicRegistrationErrorResponse toDynamicRegistrationErrorResponse(RegistrationErrorCode code,
			String description, URI uri, String state) {
		return DynamicRegistrationErrorResponse.builder().error(code).errorDescription(description).errorUri(uri)
				.state(state).build();
	}
	
	@ExceptionHandler(InvalidClientMetadataException.class)
	public ResponseEntity<Object> handleInvalidClientMetadataException(HttpServletRequest req,
			InvalidClientMetadataException ex) {
		LOG.error("Encountered an invalid client metadata while performing OIDC Registration operations: {}",
				ex.getMessage());
		LOG.debug(MessageUtil.exceptionString(ex));
		return ResponseEntity.badRequest()
				.body(toDynamicRegistrationErrorResponse(RegistrationErrorCode.INVALID_CLIENT_METADATA,
						Optional.ofNullable(ex.errorDescription())
								.orElse(OIDCErrorMessages.OAUTH2_REGISTRATION_INVALID_CLIENT_METADATA),
						Optional.ofNullable(ex.errorUri()).orElse(OIDCErrorMessages.OAUTH2_ERROR_RESPONSE_URI),
						req.getParameter(Constants.STATE)));
	}

	@ExceptionHandler(InvalidRedirectUriException.class)
	public ResponseEntity<Object> handleInvalidClientMetadataException(HttpServletRequest req,
			InvalidRedirectUriException ex) {
		LOG.error("Encountered invalid redirect uris while performing OIDC Registration operations: {}",
				ex.getMessage());
		LOG.debug(MessageUtil.exceptionString(ex));
		return ResponseEntity.badRequest().body(toDynamicRegistrationErrorResponse(
				RegistrationErrorCode.INVALID_REDIRECT_URI,
				Optional.ofNullable(ex.errorDescription())
						.orElse(OIDCErrorMessages.OAUTH2_REGISTRATION_INVALID_REDIRECT_URI),
				Optional.ofNullable(ex.errorUri()).orElse(OIDCErrorMessages.OAUTH2_REGISTRATION_ERROR_RESPONSE_URI),
				req.getParameter(Constants.STATE)));
	}

	@ExceptionHandler(InvalidSoftwareStatementException.class)
	public ResponseEntity<Object> handleInvalidSoftwareStatementException(HttpServletRequest req,
			InvalidSoftwareStatementException ex) {
		LOG.error("Encountered invalid software statement while performing OIDC Registration operations: {}",
				ex.getMessage());
		LOG.debug(MessageUtil.exceptionString(ex));
		return ResponseEntity.badRequest().body(toDynamicRegistrationErrorResponse(
				RegistrationErrorCode.INVALID_SOFTWARE_STATEMENT,
				Optional.ofNullable(ex.errorDescription())
						.orElse(OIDCErrorMessages.OAUTH2_REGISTRATION_INVALID_SOFTWARE_STATEMENT),
				Optional.ofNullable(ex.errorUri()).orElse(OIDCErrorMessages.OAUTH2_REGISTRATION_ERROR_RESPONSE_URI),
				req.getParameter(Constants.STATE)));
	}

	@ExceptionHandler(UnapprovedSoftwareStatementException.class)
	public ResponseEntity<Object> handleUnapprovedSoftwareStatementException(HttpServletRequest req,
			UnapprovedSoftwareStatementException ex) {
		LOG.error("Encountered unapproved software statement while performing OIDC Registration operations: {}",
				ex.getMessage());
		LOG.debug(MessageUtil.exceptionString(ex));
		return ResponseEntity.badRequest().body(toDynamicRegistrationErrorResponse(
				RegistrationErrorCode.UNAPPROVED_SOFTWARE_STATEMENT,
				Optional.ofNullable(ex.errorDescription())
						.orElse(OIDCErrorMessages.OAUTH2_REGISTRATION_UNAPPROVED_SOFTWARE_STATEMENT),
				Optional.ofNullable(ex.errorUri()).orElse(OIDCErrorMessages.OAUTH2_REGISTRATION_ERROR_RESPONSE_URI),
				req.getParameter(Constants.STATE)));
	}
}
