/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *******************************************************************************/
package io.biza.babelfish.spring.util;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import io.biza.babelfish.spring.payloads.BabelFieldLabelValue;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class LabelValueOpenApiUtil {
  public static List<BabelFieldLabelValue> getFormLabels(Class<?> typeClass) {
    return getFormLabels(typeClass, "");
  }

  public static List<BabelFieldLabelValue> getFormLabels(Class<?> typeClass, String prefix) {
    LOG.debug("Processing for {} with prefix of {}", typeClass.getName(), prefix);
    List<BabelFieldLabelValue> labels = new ArrayList<BabelFieldLabelValue>();
    Field[] allFields = typeClass.getDeclaredFields();
    for (Field field : allFields) {
      if (field.isAnnotationPresent(JsonUnwrapped.class)) {
        labels.addAll(getFormLabels(field.getType()));
      } else if (field.isAnnotationPresent(Schema.class)) {
        Schema modelProperty = field.getAnnotation(Schema.class);
        if (!modelProperty.hidden()) {
          Class<?> fieldType = field.getType();

          if (field.getGenericType() instanceof ParameterizedType) {
            ParameterizedType parameterType = (ParameterizedType) field.getGenericType();
            fieldType = (Class<?>) parameterType.getActualTypeArguments()[0];
            // LOG.debug("Field type has been reset to {} with name of {}", parameterType,
            // (Class<?>) parameterType.getActualTypeArguments()[0]);
          }

          if (fieldType.isAnnotationPresent(Schema.class)) {
            if (fieldType.isEnum()) {
              if (StringUtils.isEmpty(modelProperty.description())) {
                if (!StringUtils.isEmpty(fieldType.getAnnotation(Schema.class).description())) {
                  labels.add(BabelFieldLabelValue.builder()
                      .label(fieldType.getAnnotation(Schema.class).description())
                      .value(field.getName()).build());
                }
              }
            } else {
              labels.addAll(getFormLabels(fieldType, field.getName()));
            }
          } else {
            labels.add(BabelFieldLabelValue.builder().label(modelProperty.description())
                .value((StringUtils.isEmpty(prefix) ? "" : prefix + ".") + field.getName())
                .build());
          }
        }
      }
    }
    return labels;
  }

}
