package io.biza.babelfish.cdr.support;

import java.util.List;
import javax.validation.Payload;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BabelfishValidationPayload implements Payload {

  @JsonProperty("message")
  String message;
  
  @JsonProperty("fields")
  List<String> fields;
}
