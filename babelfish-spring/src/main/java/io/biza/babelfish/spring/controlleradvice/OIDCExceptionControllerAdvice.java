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

import io.biza.babelfish.oidc.OIDCErrorMessages;
import io.biza.babelfish.oidc.enumerations.OAuth2ErrorCode;
import io.biza.babelfish.oidc.exceptions.oauth2.InvalidClientException;
import io.biza.babelfish.oidc.exceptions.oauth2.InvalidGrantException;
import io.biza.babelfish.oidc.exceptions.oauth2.InvalidRequestException;
import io.biza.babelfish.oidc.exceptions.oauth2.InvalidScopeException;
import io.biza.babelfish.oidc.exceptions.oauth2.UnauthorisedClientException;
import io.biza.babelfish.oidc.exceptions.oauth2.UnsupportedGrantTypeException;
import io.biza.babelfish.oidc.requests.OAuth2ErrorResponse;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class OIDCExceptionControllerAdvice {
  
  @ExceptionHandler(InvalidClientException.class)
  public ResponseEntity<Object> handleInvalidClientException(HttpServletRequest req,
      InvalidClientException ex) {

    return ResponseEntity.badRequest()
        .body(OAuth2ErrorResponse.builder().error(OAuth2ErrorCode.INVALID_CLIENT)
            .errorDescription(OIDCErrorMessages.OAUTH2_INVALID_CLIENT)
            .errorUri(OIDCErrorMessages.OAUTH2_ERROR_RESPONSE_URI).build());
  }
  
  @ExceptionHandler(InvalidGrantException.class)
  public ResponseEntity<Object> handleInvalidGrantException(HttpServletRequest req,
      InvalidGrantException ex) {

    return ResponseEntity.badRequest()
        .body(OAuth2ErrorResponse.builder().error(OAuth2ErrorCode.INVALID_GRANT)
            .errorDescription(OIDCErrorMessages.OAUTH2_INVALID_GRANT)
            .errorUri(OIDCErrorMessages.OAUTH2_ERROR_RESPONSE_URI).build());
  }

  @ExceptionHandler(InvalidRequestException.class)
  public ResponseEntity<Object> handleInvalidRequestException(HttpServletRequest req,
      InvalidRequestException ex) {

    return ResponseEntity.badRequest()
        .body(OAuth2ErrorResponse.builder().error(OAuth2ErrorCode.INVALID_REQUEST)
            .errorDescription(OIDCErrorMessages.OAUTH2_INVALID_REQUEST)
            .errorUri(OIDCErrorMessages.OAUTH2_ERROR_RESPONSE_URI).build());
  }

  @ExceptionHandler(InvalidScopeException.class)
  public ResponseEntity<Object> handleInvalidScopeException(HttpServletRequest req,
      InvalidScopeException ex) {

    return ResponseEntity.badRequest()
        .body(OAuth2ErrorResponse.builder().error(OAuth2ErrorCode.INVALID_CLIENT)
            .errorDescription(OIDCErrorMessages.OAUTH2_INVALID_SCOPE)
            .errorUri(OIDCErrorMessages.OAUTH2_ERROR_RESPONSE_URI).build());
  }

  @ExceptionHandler(UnauthorisedClientException.class)
  public ResponseEntity<Object> handleUnauthorisedClientException(HttpServletRequest req,
      UnauthorisedClientException ex) {

    return ResponseEntity.badRequest()
        .body(OAuth2ErrorResponse.builder().error(OAuth2ErrorCode.UNAUTHORISED_CLIENT)
            .errorDescription(OIDCErrorMessages.OAUTH2_UNAUTHORIZED_CLIENT)
            .errorUri(OIDCErrorMessages.OAUTH2_ERROR_RESPONSE_URI).build());
  }


  @ExceptionHandler(UnsupportedGrantTypeException.class)
  public ResponseEntity<Object> handleUnsupportedGrantTypeException(HttpServletRequest req,
      UnsupportedGrantTypeException ex) {

    return ResponseEntity.badRequest()
        .body(OAuth2ErrorResponse.builder().error(OAuth2ErrorCode.UNSUPPORTED_GRANT_TYPE)
            .errorDescription(OIDCErrorMessages.OAUTH2_UNSUPPORTED_GRANT_TYPE)
            .errorUri(OIDCErrorMessages.OAUTH2_ERROR_RESPONSE_URI).build());
  }


}
