package io.biza.babelfish.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StaticObjectMapper {

  public static String toJSON(Object inputObject) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      return mapper.writeValueAsString(inputObject);
    } catch (JsonProcessingException e) {
      return "Unable to serialize supplied object: " + e.getMessage();
    }
  }

  public static <T> T fromJSON(String inputJSON, Class<T> inputClass)
      throws JsonMappingException, JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(inputJSON, inputClass);
  }
}
