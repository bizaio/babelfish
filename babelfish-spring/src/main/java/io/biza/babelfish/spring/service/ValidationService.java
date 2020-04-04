package io.biza.babelfish.spring.service;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.biza.babelfish.spring.enumerations.BabelfishExceptionType;
import io.biza.babelfish.spring.exceptions.ValidationListException;
import io.biza.babelfish.spring.util.ValidationUtil;

@Service
public class ValidationService {

  Validator validator;

  public <T> void validate(T data, String errorMessage) throws ValidationListException {
    
    if(validator == null) {
      validator = Validation.buildDefaultValidatorFactory().getValidator(); 
    }
    
    Set<ConstraintViolation<T>> validationErrors = validator.validate(data);

    if (!validationErrors.isEmpty()) {
      throw ValidationListException.builder().explanation(errorMessage)
          .type(BabelfishExceptionType.VALIDATION_ERROR)
          .validationErrors(ValidationUtil.toValidationList(validationErrors)).build();
    }
  }

}
