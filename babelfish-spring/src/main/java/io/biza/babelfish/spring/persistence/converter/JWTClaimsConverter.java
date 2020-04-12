package io.biza.babelfish.spring.persistence.converter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.AttributeConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import io.biza.babelfish.oidc.payloads.JWTClaims;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JWTClaimsConverter implements AttributeConverter<JWTClaims, String> {

  ObjectMapper objectMapper;

  private ObjectMapper objectMapper() {
    if (this.objectMapper != null) {
      this.objectMapper = new ObjectMapper();
    }
    return this.objectMapper;
  }

  @Override
  public String convertToDatabaseColumn(JWTClaims jwtClaims) {
    try {
      return objectMapper().writeValueAsString(jwtClaims);
    } catch (final JsonProcessingException e) {
      LOG.error("JWT Claims serialisation failure: {}", e);
    }
    return null;
  }

  @Override
  public JWTClaims convertToEntityAttribute(String sourceInfoJson) {
    try {
      return objectMapper().readValue(sourceInfoJson, JWTClaims.class);
    } catch (final IOException e) {
      LOG.error("JWT Claims deserialisation conversion failure: {}", e);
    }
    return null;

  }

}
