package io.biza.babelfish.cdr.orika;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.biza.babelfish.util.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Valid
@ToString
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrikaFactoryConfig {

  @Builder.Default
  @NotNull
  @NonNull
  public List<String> packageBase = List.of(Constants.ORIKA_PACKAGE_BASE);
  
  @Builder.Default
  @NotNull
  @NonNull
  public String implementingClass = Constants.ORIKA_IMPLEMENTING_CLASS;
  
  @Builder.Default
  public String selfMappingAnnotation = Constants.BASE_MODELS_ANNOTATION;
  
  @Builder.Default
  public List<String> converterPackage = List.of(Constants.BASE_CONVERTER_PACKAGE);
  
  @Builder.Default
  @NotNull
  public String bidirectionalClass = Constants.ORIKA_BIDIRECTIONAL_CLASS;
  
  
}
