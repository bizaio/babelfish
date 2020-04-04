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

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.ValueInstantiationException;
import io.biza.babelfish.cdr.exceptions.LabelValueEnumValueNotSupportedException;
import io.biza.babelfish.cdr.support.LabelValueEnumInterface;
import io.biza.babelfish.spring.Messages;
import io.biza.babelfish.spring.enumerations.BabelfishExceptionType;
import io.biza.babelfish.spring.enumerations.BabelfishValidationErrorType;
import io.biza.babelfish.spring.exceptions.ValidationListException;
import io.biza.babelfish.spring.payloads.ResponseValidationError;
import io.biza.babelfish.spring.payloads.ValidationError;
import io.biza.babelfish.spring.util.MessageUtil;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.RollbackException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@Slf4j
@ControllerAdvice
public class BabelfishExceptionAdvice {

  @ExceptionHandler(IllegalStateException.class)
  public ResponseEntity<Object> handleIllegalStateException(HttpServletRequest req,
      IllegalStateException ex) {

    return ResponseEntity.unprocessableEntity()
        .body(ResponseValidationError.builder().type(BabelfishExceptionType.VALIDATION_ERROR)
            .explanation(
                MessageUtil.format(Messages.ILLEGAL_STATE_EXCEPTION_MESSAGE, ex.getMessage()))
            .build());
  }

  @ExceptionHandler(RollbackException.class)
  public ResponseEntity<Object> handleRollbackException(HttpServletRequest req,
      RollbackException ex) {

    ResponseValidationError errors =
        ResponseValidationError.builder().type(BabelfishExceptionType.VALIDATION_ERROR)
            .explanation(Messages.INVALID_PARAMETERS_SEE_VALIDATION_ERRORS_MESSAGE).build();

    Throwable exThrowable = ex.getCause();

    if (exThrowable != null && exThrowable instanceof ConstraintViolationException) {
      ConstraintViolationException constraintException = (ConstraintViolationException) exThrowable;


      constraintException.getConstraintViolations().forEach(violation -> {
        try {
          errors.validationErrors()
              .add(ValidationError.builder().fields(List.of(violation.getPropertyPath().toString()))
                  .message(StringUtils.capitalize(violation.getMessage()))
                  .type(BabelfishValidationErrorType.ATTRIBUTE_INVALID).build());
        } catch (IllegalArgumentException e) {
          LOG.error(
              MessageUtil.format(Messages.UNSUPPORTED_UNWRAP_WITH_DESCRIPTION_MESSAGE, violation));
          errors.validationErrors()
              .add(ValidationError.builder()
                  .fields(List.of(violation.getConstraintDescriptor().toString()))
                  .message(StringUtils.capitalize(violation.getMessage()))
                  .type(BabelfishValidationErrorType.ATTRIBUTE_INVALID).build());
        }
      });
    } else {
      LOG.error(MessageUtil.format(Messages.RECEIVED_SPECIFIED_ERROR_WITH_DESCRIPTION_MESSAGE,
          "RollbackException", ex.toString()));
    }

    return ResponseEntity.unprocessableEntity().body(errors);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<Object> handleHttpMessageNotReadable(HttpServletRequest req,
      HttpMessageNotReadableException ex) {

    if (ex.getCause() != null && ex.getCause() instanceof ValueInstantiationException) {
      ValueInstantiationException e = (ValueInstantiationException) ex.getCause();

      List<String> fieldNames = new ArrayList<String>();

      for (Reference reference : e.getPath()) {
        fieldNames.add(reference.getFieldName());
      }


      if (e.getCause() instanceof LabelValueEnumValueNotSupportedException) {
        LabelValueEnumValueNotSupportedException labelEx =
            (LabelValueEnumValueNotSupportedException) e.getCause();

        List<String> enumValuesList = new ArrayList<String>();
        for (LabelValueEnumInterface enumValue : labelEx.getSourceEnum()) {
          enumValuesList.add(enumValue.name());
        }

        return ResponseEntity.unprocessableEntity()
            .body(
                ResponseValidationError.builder().type(BabelfishExceptionType.INVALID_JSON)
                    .explanation(Messages.INVALID_PARAMETERS_SEE_VALIDATION_ERRORS_MESSAGE)
                    .validationErrors(List.of(ValidationError.builder()
                        .fields(List.of(String.join(".", fieldNames)))
                        .message(MessageUtil.format(Messages.INVALID_PARAMETER_WITH_OPTIONS_MESSAGE,
                            labelEx.getSuppliedValue(), labelEx.getClassName(),
                            String.join(", ", enumValuesList)))
                        .type(BabelfishValidationErrorType.ATTRIBUTE_INVALID).build()))
                    .build());
      } else {

        return ResponseEntity.unprocessableEntity()
            .body(ResponseValidationError.builder().type(BabelfishExceptionType.INVALID_JSON)
                .explanation(MessageUtil
                    .format(Messages.INVALID_PARAMETERS_WITH_EXPLANATION_MESSAGE, ex.getMessage()))
                .build());
      }
    } else if (ex.getCause() != null && ex.getCause() instanceof InvalidFormatException) {
      InvalidFormatException e = (InvalidFormatException) ex.getCause();

      ResponseValidationError errors =
          ResponseValidationError.builder().type(BabelfishExceptionType.VALIDATION_ERROR)
              .explanation(Messages.INVALID_PARAMETERS_SEE_VALIDATION_ERRORS_MESSAGE).build();

      /**
       * Build path
       */
      List<String> pathList = new ArrayList<String>();
      for (Reference reference : e.getPath()) {
        pathList.add(reference.getFieldName());
      }

      errors.validationErrors()
          .add(ValidationError.builder().fields(List.of(String.join(".", pathList)))
              .message(StringUtils.capitalize(e.getCause().getMessage()))
              .type(BabelfishValidationErrorType.ATTRIBUTE_INVALID).build());

      return ResponseEntity.unprocessableEntity().body(errors);
    } else if (ex.getCause() != null && ex.getCause() instanceof JsonMappingException) {
      JsonMappingException e = (JsonMappingException) ex.getCause();

      ResponseValidationError errors =
          ResponseValidationError.builder().type(BabelfishExceptionType.VALIDATION_ERROR)
              .explanation(Messages.INVALID_PARAMETERS_SEE_VALIDATION_ERRORS_MESSAGE).build();

      /**
       * Build path
       */
      List<String> pathList = new ArrayList<String>();
      for (Reference reference : e.getPath()) {
        pathList.add(reference.getFieldName());
      }

      errors.validationErrors()
          .add(ValidationError.builder().fields(List.of(String.join(".", pathList)))
              .message(StringUtils.capitalize(e.getCause().getMessage()))
              .type(BabelfishValidationErrorType.ATTRIBUTE_INVALID).build());

      return ResponseEntity.unprocessableEntity().body(errors);

    } else {
      return ResponseEntity.unprocessableEntity()
          .body(ResponseValidationError.builder().type(BabelfishExceptionType.INVALID_JSON)
              .explanation(Messages.JSON_PARSE_ERROR_MESSAGE).build());
    }
  }


  @ExceptionHandler(ValidationListException.class)
  public ResponseEntity<Object> handleValidationException(HttpServletRequest req,
      ValidationListException ex) {

    return ResponseEntity.unprocessableEntity()
        .body(ResponseValidationError.builder().type(ex.type()).explanation(ex.explanation())
            .validationErrors(ex.validationErrors()).build());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Object> handleInvalidArguments(HttpServletRequest req,
      MethodArgumentNotValidException ex) {

    ResponseValidationError errors =
        ResponseValidationError.builder().type(BabelfishExceptionType.VALIDATION_ERROR)
            .explanation(Messages.INVALID_PARAMETERS_SEE_VALIDATION_ERRORS_MESSAGE).build();

    ex.getBindingResult().getAllErrors().forEach(error -> {
      ConstraintViolation<?> violation = error.unwrap(ConstraintViolation.class);
      try {
        errors.validationErrors()
            .add(ValidationError.builder().fields(List.of(violation.getPropertyPath().toString()))
                .message(StringUtils.capitalize(violation.getMessage()))
                .type(BabelfishValidationErrorType.ATTRIBUTE_INVALID).build());
      } catch (IllegalArgumentException e) {
        LOG.error(MessageUtil.format(Messages.UNSUPPORTED_UNWRAP_WITH_DESCRIPTION_MESSAGE, error));
        errors.validationErrors()
            .add(ValidationError.builder().fields(List.of(error.getCode()))
                .message(StringUtils.capitalize(error.getDefaultMessage()))
                .type(BabelfishValidationErrorType.ATTRIBUTE_INVALID).build());
      }
    });

    return ResponseEntity.unprocessableEntity().body(errors);
  }

  @ExceptionHandler(JsonParseException.class)
  public ResponseEntity<Object> handleInvalidJsonParseException(HttpServletRequest req,
      JsonParseException ex) {

    ResponseValidationError errors =
        ResponseValidationError.builder().type(BabelfishExceptionType.INVALID_JSON)
            .explanation(Messages.JSON_PARSE_ERROR_MESSAGE).build();

    return ResponseEntity.unprocessableEntity().body(errors);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<Object> handleJdbcIntegrityViolation(HttpServletRequest req,
      DataIntegrityViolationException ex) {

    ResponseValidationError errors =
        ResponseValidationError.builder().type(BabelfishExceptionType.VALIDATION_ERROR)
            .explanation(MessageUtil.format(
                Messages.DATABASE_ATTRIBUTE_FORMAT_ERROR_WITH_DESCRIPTION_MESSAGE,
                ex.getMostSpecificCause().getMessage()))
            .build();

    return ResponseEntity.unprocessableEntity().body(errors);
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<Object> handleInvalidFieldTypeException(HttpServletRequest req,
      MethodArgumentTypeMismatchException ex) {

    ResponseValidationError errors =
        ResponseValidationError.builder().type(BabelfishExceptionType.VALIDATION_ERROR)
            .explanation(MessageUtil.format(
                Messages.PATH_PARAMETER_VALIDATION_ERROR_WITH_NAME_TARGET_MESSAGE,
                ex.getParameter().getParameterName(), ex.getRequiredType().getSimpleName()))
            .build();

    return ResponseEntity.unprocessableEntity().body(errors);
  }
}
