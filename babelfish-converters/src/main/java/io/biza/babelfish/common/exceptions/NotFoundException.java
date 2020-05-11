package io.biza.babelfish.common.exceptions;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Schema(description = "Describes a NotFoundException failure")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class NotFoundException extends Exception {
  private static final long serialVersionUID = 1L;

  public String message;

}
