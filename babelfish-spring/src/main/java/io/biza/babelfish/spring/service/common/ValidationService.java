package io.biza.babelfish.spring.service.common;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import org.springframework.stereotype.Service;
import io.biza.babelfish.spring.enumerations.BabelExceptionType;
import io.biza.babelfish.spring.exceptions.ValidationListException;
import io.biza.babelfish.spring.util.ValidationUtil;

@Service
public class ValidationService {

  Validator validator;

  public <T> Set<ConstraintViolation<T>> doValidate(T data) {
    if (validator == null) {
      validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    return validator.validate(data);
  }

  public <T> void validate(T data) throws ValidationException {
    Set<ConstraintViolation<T>> validationErrors = doValidate(data);

    if (!validationErrors.isEmpty()) {
      throw new ValidationException(ValidationUtil.toValidationMessage(validationErrors));
    }
  }

  public <T> void validate(T data, String errorMessage) throws ValidationListException {

    Set<ConstraintViolation<T>> validationErrors = doValidate(data);

    if (!validationErrors.isEmpty()) {
      throw ValidationListException.builder().explanation(errorMessage)
          .type(BabelExceptionType.VALIDATION_ERROR)
          .validationErrors(ValidationUtil.toValidationList(validationErrors)).build();
    }
  }

}
