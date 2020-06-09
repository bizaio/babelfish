package io.biza.babelfish.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import org.apache.commons.lang3.StringUtils;

import io.biza.babelfish.common.enumerations.BabelValidationErrorType;
import io.biza.babelfish.common.payloads.BabelValidationError;
public class ValidationUtil {

  public static <T> List<BabelValidationError> toValidationList(
      Set<ConstraintViolation<T>> validationErrors) {
    List<BabelValidationError> validationErrorList = new ArrayList<BabelValidationError>();
    validationErrors.stream().forEach(violation -> {
      validationErrorList
          .add(BabelValidationError.builder().fields(List.of(violation.getPropertyPath().toString()))
              .message(StringUtils.capitalize(violation.getMessage()))
              .type(BabelValidationErrorType.ATTRIBUTE_INVALID).build());
    });

    return validationErrorList;
  }
  
  public static <T> String toValidationMessage(Set<ConstraintViolation<T>> validationErrors) {
    StringBuilder value = new StringBuilder();
    for(ConstraintViolation<T> violation : validationErrors) {
      value.append(violation.getMessage());
    }
    return value.toString();
  }
  
  public static <T> void validateOrThrow(Validator validator, T object) {
    Set<ConstraintViolation<T>> validationResult = validator.validate(object);
    if(validationResult.size() > 0) {
      throw new ConstraintViolationException(validationResult);
    }
    return;
  }
}
