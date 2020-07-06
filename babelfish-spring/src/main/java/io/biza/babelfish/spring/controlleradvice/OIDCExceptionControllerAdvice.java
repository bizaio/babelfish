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

import io.biza.babelfish.common.exceptions.NotFoundException;
import io.biza.babelfish.oidc.Constants;
import io.biza.babelfish.oidc.OIDCErrorMessages;
import io.biza.babelfish.oidc.enumerations.OAuth2ErrorCode;
import io.biza.babelfish.oidc.exceptions.oauth2.AccessDeniedException;
import io.biza.babelfish.oidc.exceptions.oauth2.InvalidClientException;
import io.biza.babelfish.oidc.exceptions.oauth2.InvalidGrantException;
import io.biza.babelfish.oidc.exceptions.oauth2.InvalidRequestException;
import io.biza.babelfish.oidc.exceptions.oauth2.InvalidScopeException;
import io.biza.babelfish.oidc.exceptions.oauth2.UnauthorisedClientException;
import io.biza.babelfish.oidc.exceptions.oauth2.UnsupportedResponseTypeException;
import io.biza.babelfish.oidc.exceptions.openid.core.InvalidRequestObjectException;
import io.biza.babelfish.oidc.exceptions.openid.core.RequestNotSupportedException;
import io.biza.babelfish.oidc.requests.OAuth2ErrorResponse;
import io.biza.babelfish.util.MessageUtil;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class OIDCExceptionControllerAdvice {

	private OAuth2ErrorResponse toOAuth2ErrorResponse(OAuth2ErrorCode code, String description, URI uri, String state) {
		return OAuth2ErrorResponse.builder().error(code).errorDescription(description).errorUri(uri).state(state)
				.build();
	}
	
	@ExceptionHandler(InvalidRequestObjectException.class)
	public ResponseEntity<Object> handleInvalidRequestObjectException(HttpServletRequest req, InvalidRequestObjectException ex) {
		LOG.error("Encountered a request related to an invalid request object: {}", ex.getMessage());
		LOG.debug(MessageUtil.exceptionString(ex));
		return ResponseEntity.badRequest()
				.body(toOAuth2ErrorResponse(OAuth2ErrorCode.INVALID_REQUEST,
						Optional.ofNullable(ex.errorDescription()).orElse(OIDCErrorMessages.OPENID_CONNECT_INVALID_REQUEST_OBJECT),
						Optional.ofNullable(ex.errorUri()).orElse(OIDCErrorMessages.OPENID_CONNECT_ERROR_RESPONSE_URI),
						req.getParameter(Constants.STATE)));
	}
	
	@ExceptionHandler(RequestNotSupportedException.class)
	public ResponseEntity<Object> handleRequestNotSupportedException(HttpServletRequest req, RequestNotSupportedException ex) {
		LOG.error("Encountered a request when Request Object is not supported: {}", ex.getMessage());
		LOG.debug(MessageUtil.exceptionString(ex));
		return ResponseEntity.badRequest()
				.body(toOAuth2ErrorResponse(OAuth2ErrorCode.INVALID_REQUEST,
						Optional.ofNullable(ex.errorDescription()).orElse(OIDCErrorMessages.OAUTH2_REQUEST_NOT_SUPPORTED),
						Optional.ofNullable(ex.errorUri()).orElse(OIDCErrorMessages.OPENID_CONNECT_ERROR_RESPONSE_URI),
						req.getParameter(Constants.STATE)));
	}

	@ExceptionHandler(InvalidRequestException.class)
	public ResponseEntity<Object> handleInvalidRequestException(HttpServletRequest req, InvalidRequestException ex) {
		LOG.error("Encountered an invalid request exception while performing OIDC operations: {}", ex.getMessage());
		LOG.debug(MessageUtil.exceptionString(ex));
		return ResponseEntity.badRequest()
				.body(toOAuth2ErrorResponse(OAuth2ErrorCode.INVALID_REQUEST,
						Optional.ofNullable(ex.errorDescription()).orElse(OIDCErrorMessages.OAUTH2_INVALID_REQUEST),
						Optional.ofNullable(ex.errorUri()).orElse(OIDCErrorMessages.OAUTH2_ERROR_RESPONSE_URI),
						req.getParameter(Constants.STATE)));
	}

	@ExceptionHandler(UnauthorisedClientException.class)
	public ResponseEntity<Object> handleUnauthorisedClientException(HttpServletRequest req,
			UnauthorisedClientException ex) {
		LOG.error("Encountered an unauthorised client exception while performing OIDC operations: {}", ex.getMessage());
		LOG.debug(MessageUtil.exceptionString(ex));

		return ResponseEntity.badRequest()
				.body(toOAuth2ErrorResponse(OAuth2ErrorCode.UNAUTHORISED_CLIENT,
						Optional.ofNullable(ex.errorDescription()).orElse(OIDCErrorMessages.OAUTH2_UNAUTHORIZED_CLIENT),
						Optional.ofNullable(ex.errorUri()).orElse(OIDCErrorMessages.OAUTH2_ERROR_RESPONSE_URI),
						req.getParameter(Constants.STATE)));

	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<Object> handleAccessDeniedException(HttpServletRequest req, AccessDeniedException ex) {
		LOG.error("Encountered an access denied exception while performing OIDC operations: {}", ex.getMessage());
		LOG.debug(MessageUtil.exceptionString(ex));
		return ResponseEntity.badRequest()
				.body(toOAuth2ErrorResponse(OAuth2ErrorCode.ACCESS_DENIED, OIDCErrorMessages.OAUTH2_ACCESS_DENIED,
						OIDCErrorMessages.OAUTH2_ERROR_RESPONSE_URI, req.getParameter(Constants.STATE)));
	}

	@ExceptionHandler(UnsupportedResponseTypeException.class)
	public ResponseEntity<Object> handleUnsupportedResponseTypeException(HttpServletRequest req,
			UnsupportedResponseTypeException ex) {
		LOG.error("Encountered an unsupported response type exception while performing OIDC operations: {}",
				ex.getMessage());
		LOG.debug(MessageUtil.exceptionString(ex));

		return ResponseEntity.badRequest()
				.body(toOAuth2ErrorResponse(OAuth2ErrorCode.UNSUPPORTED_RESPONSE_TYPE,
						Optional.ofNullable(ex.errorDescription())
								.orElse(OIDCErrorMessages.OAUTH2_UNSUPPORTED_RESPONSE_TYPE),
						Optional.ofNullable(ex.errorUri()).orElse(OIDCErrorMessages.OAUTH2_ERROR_RESPONSE_URI),
						req.getParameter(Constants.STATE)));

	}

	@ExceptionHandler(InvalidScopeException.class)
	public ResponseEntity<Object> handleInvalidScopeException(HttpServletRequest req, InvalidScopeException ex) {
		LOG.error("Encountered an invalid scope exception while performing OIDC operations: {}", ex.getMessage());
		LOG.debug(MessageUtil.exceptionString(ex));

		return ResponseEntity.badRequest()
				.body(toOAuth2ErrorResponse(OAuth2ErrorCode.INVALID_SCOPE, OIDCErrorMessages.OAUTH2_INVALID_SCOPE,
						OIDCErrorMessages.OAUTH2_ERROR_RESPONSE_URI, req.getParameter(Constants.STATE)));

	}

	@ExceptionHandler(InvalidClientException.class)
	public ResponseEntity<Object> handleInvalidClientException(HttpServletRequest req, InvalidClientException ex) {
		LOG.error("Encountered an invalid client exception while performing OIDC operations: {}",
				ex.errorDescription());
		LOG.debug(MessageUtil.exceptionString(ex));

		return ResponseEntity.badRequest()
				.body(toOAuth2ErrorResponse(OAuth2ErrorCode.INVALID_CLIENT,
						Optional.ofNullable(ex.errorDescription()).orElse(OIDCErrorMessages.OAUTH2_INVALID_CLIENT),
						OIDCErrorMessages.OAUTH2_ERROR_RESPONSE_URI, req.getParameter(Constants.STATE)));

	}

	@ExceptionHandler(InvalidGrantException.class)
	public ResponseEntity<Object> handleInvalidGrantException(HttpServletRequest req, InvalidGrantException ex) {
		LOG.error("Encountered an invalid grant exception while performing OIDC operations: {}", ex.getMessage());
		LOG.debug(MessageUtil.exceptionString(ex));

		return ResponseEntity.badRequest()
				.body(toOAuth2ErrorResponse(OAuth2ErrorCode.INVALID_GRANT,
						Optional.ofNullable(ex.errorDescription()).orElse(OIDCErrorMessages.OAUTH2_INVALID_CLIENT),
						OIDCErrorMessages.OAUTH2_ERROR_RESPONSE_URI, req.getParameter(Constants.STATE)));

	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Object> handleNotFoundException(HttpServletRequest req, NotFoundException ex) {
		return ResponseEntity.notFound().build();
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleUnhandledError(HttpServletRequest req, Exception ex) {
		LOG.error("Encountered an unhandled error while performing OIDC operations: {}", ex.getMessage());
		LOG.debug(MessageUtil.exceptionString(ex));

		return ResponseEntity.badRequest()
				.body(toOAuth2ErrorResponse(OAuth2ErrorCode.SERVER_ERROR,
						Optional.ofNullable(ex.getMessage()).orElse(OIDCErrorMessages.OAUTH2_SERVER_ERROR), null,
						req.getParameter(Constants.STATE)));

	}

}
