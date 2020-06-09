package io.biza.babelfish.spring.persistence.converter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.AttributeConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HashMapConverter implements AttributeConverter<Map<String, Object>, String> {

  ObjectMapper objectMapper;

  private ObjectMapper objectMapper() {
    if (this.objectMapper != null) {
      this.objectMapper = new ObjectMapper();
    }
    return this.objectMapper;
  }

  @Override
  public String convertToDatabaseColumn(Map<String, Object> customerInfo) {
    try {
      return objectMapper().writeValueAsString(customerInfo);
    } catch (final JsonProcessingException e) {
      LOG.error("HashMapConverter database column conversion failure: {}", e);
    }
    return null;
  }

  @Override
  public Map<String, Object> convertToEntityAttribute(String sourceInfoJson) {
    try {
      return objectMapper().readValue(sourceInfoJson, TypeFactory.defaultInstance()
          .constructMapLikeType(HashMap.class, String.class, Object.class));
    } catch (final IOException e) {
      LOG.error("HashMapConverter entity attribute conversion failure: {}", e);
    }
    return null;

  }

}
