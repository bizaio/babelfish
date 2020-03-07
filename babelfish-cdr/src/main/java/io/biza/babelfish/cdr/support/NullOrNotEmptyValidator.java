package io.biza.babelfish.cdr.support;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NullOrNotEmptyValidator
    implements ConstraintValidator<NullOrNotEmpty, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return value == null || value.isEmpty() != true;
  }

}
