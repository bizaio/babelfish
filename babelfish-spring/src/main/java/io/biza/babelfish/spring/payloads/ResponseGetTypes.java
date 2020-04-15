package io.biza.babelfish.spring.payloads;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;
import com.fasterxml.jackson.annotation.JsonProperty;
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
  Map<String, List<BabelFieldLabelValue>> fieldTypes;
  
  public ResponseGetTypes populateFieldNames() {
    this.fieldNames = fieldTypes.keySet().stream().collect(Collectors.toList());
    return this;
  }
}
