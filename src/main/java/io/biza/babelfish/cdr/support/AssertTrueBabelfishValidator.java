package io.biza.babelfish.cdr.support;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AssertTrueBabelfishValidator
    implements ConstraintValidator<AssertTrueBabelfish, Boolean> {

  @Override
  public boolean isValid(Boolean value, ConstraintValidatorContext context) {
    return value != null && value == true;
  }

}
