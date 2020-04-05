package io.biza.babelfish.spring.payloads;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Valid
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "ResponseGetTypes",
    description = "Lists of Common Value types for use in Form select fields")
public class ResponseGetTypes {

  @JsonProperty("fieldNames")
  @Schema(description = "Fields included in this payload")
  List<String> fieldNames;
  
  @JsonProperty("fieldTypes")
  @Schema(description = "Field Type Values")
  Map<String, List<FormLabelValue>> fieldTypes;
  
  public ResponseGetTypes populateFieldNames() {
    this.fieldNames = fieldTypes.keySet().stream().collect(Collectors.toList());
    return this;
  }
}
