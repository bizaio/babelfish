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
import io.biza.babelfish.oauth2.exceptions.InvalidRequestException;
import io.biza.babelfish.oauth2.exceptions.InvalidScopeException;
import io.biza.babelfish.oauth2.exceptions.UnauthorisedClientException;
import io.biza.babelfish.oidc.enumerations.OAuth2ErrorCode;
import io.biza.babelfish.oidc.exceptions.InvalidClientException;
import io.biza.babelfish.oidc.exceptions.InvalidGrantException;
import io.biza.babelfish.oidc.exceptions.UnsupportedGrantTypeException;
import io.biza.babelfish.oidc.requests.OAuth2ErrorResponse;
import io.biza.babelfish.spring.Constants;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class OIDCExceptionControllerAdvice {
  
  @ExceptionHandler(InvalidClientException.class)
  public ResponseEntity<Object> handleInvalidClientException(HttpServletRequest req,
      InvalidClientException ex) {

    return ResponseEntity.badRequest()
        .body(OAuth2ErrorResponse.builder().error(OAuth2ErrorCode.INVALID_CLIENT)
            .errorDescription(Constants.OAUTH2_INVALID_CLIENT_MESSAGE)
            .errorUri(Constants.OAUTH2_ERROR_RESPONSE_URI).build());
  }
  
  @ExceptionHandler(InvalidGrantException.class)
  public ResponseEntity<Object> handleInvalidGrantException(HttpServletRequest req,
      InvalidGrantException ex) {

    return ResponseEntity.badRequest()
        .body(OAuth2ErrorResponse.builder().error(OAuth2ErrorCode.INVALID_GRANT)
            .errorDescription(Constants.OAUTH2_INVALID_GRANT_MESSAGE)
            .errorUri(Constants.OAUTH2_ERROR_RESPONSE_URI).build());
  }

  @ExceptionHandler(InvalidRequestException.class)
  public ResponseEntity<Object> handleInvalidRequestException(HttpServletRequest req,
      InvalidRequestException ex) {

    return ResponseEntity.badRequest()
        .body(OAuth2ErrorResponse.builder().error(OAuth2ErrorCode.INVALID_REQUEST)
            .errorDescription(Constants.OAUTH2_INVALID_REQUEST_MESSAGE)
            .errorUri(Constants.OAUTH2_ERROR_RESPONSE_URI).build());
  }

  @ExceptionHandler(InvalidScopeException.class)
  public ResponseEntity<Object> handleInvalidScopeException(HttpServletRequest req,
      InvalidScopeException ex) {

    return ResponseEntity.badRequest()
        .body(OAuth2ErrorResponse.builder().error(OAuth2ErrorCode.INVALID_CLIENT)
            .errorDescription(Constants.OAUTH2_INVALID_SCOPE_MESSAGE)
            .errorUri(Constants.OAUTH2_ERROR_RESPONSE_URI).build());
  }

  @ExceptionHandler(UnauthorisedClientException.class)
  public ResponseEntity<Object> handleUnauthorisedClientException(HttpServletRequest req,
      UnauthorisedClientException ex) {

    return ResponseEntity.badRequest()
        .body(OAuth2ErrorResponse.builder().error(OAuth2ErrorCode.UNAUTHORISED_CLIENT)
            .errorDescription(Constants.OAUTH2_UNAUTHORIZED_CLIENT_MESSAGE)
            .errorUri(Constants.OAUTH2_ERROR_RESPONSE_URI).build());
  }


  @ExceptionHandler(UnsupportedGrantTypeException.class)
  public ResponseEntity<Object> handleUnsupportedGrantTypeException(HttpServletRequest req,
      UnsupportedGrantTypeException ex) {

    return ResponseEntity.badRequest()
        .body(OAuth2ErrorResponse.builder().error(OAuth2ErrorCode.UNSUPPORTED_GRANT_TYPE)
            .errorDescription(Constants.OAUTH2_UNSUPPORTED_GRANT_TYPE_MESSAGE)
            .errorUri(Constants.OAUTH2_ERROR_RESPONSE_URI).build());
  }


}
