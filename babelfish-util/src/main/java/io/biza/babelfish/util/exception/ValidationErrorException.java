package io.biza.babelfish.util.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Builder
@Getter
@AllArgsConstructor
public class ValidationErrorException extends Exception {
  private static final long serialVersionUID = 1L;
  Set<ConstraintViolation<?>> violations;
  
}
