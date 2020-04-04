package io.biza.babelfish.spring.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import org.apache.commons.lang3.StringUtils;
import io.biza.babelfish.spring.enumerations.BabelfishValidationErrorType;
import io.biza.babelfish.spring.payloads.ValidationError;
public class ValidationUtil {

  public static <T> List<ValidationError> toValidationList(
      Set<ConstraintViolation<T>> validationErrors) {
    List<ValidationError> validationErrorList = new ArrayList<ValidationError>();
    validationErrors.stream().forEach(violation -> {
      validationErrorList
          .add(ValidationError.builder().fields(List.of(violation.getPropertyPath().toString()))
              .message(StringUtils.capitalize(violation.getMessage()))
              .type(BabelfishValidationErrorType.ATTRIBUTE_INVALID).build());
    });

    return validationErrorList;
  }
}
