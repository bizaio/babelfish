package io.biza.babelfish.util;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import io.biza.babelfish.util.exception.ValidationErrorException;

public class ValidatorUtil {

  public static <T> void validateOrThrow(Validator validator, T object) {
    Set<ConstraintViolation<T>> validationResult = validator.validate(object);
    if(validationResult.size() > 0) {
      throw new ConstraintViolationException(validationResult);
    }
    return;
  }
}
