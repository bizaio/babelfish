/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *******************************************************************************/
package io.biza.babelfish.spring.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.net.URI;
import java.time.OffsetDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Set;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import org.springframework.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import io.biza.babelfish.cdr.support.LabelValueEnumInterface;
import io.biza.babelfish.spring.enumerations.BabelFormFieldType;
import io.biza.babelfish.spring.interfaces.LabelValueDerivedInterface;
import io.biza.babelfish.spring.payloads.BabelFieldLabelValue;
import io.biza.babelfish.spring.payloads.BabelForm;
import io.biza.babelfish.spring.payloads.BabelForm.BabelFormBuilder;
import io.biza.babelfish.spring.payloads.BabelFormField;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class LabelValueOpenApiUtil {
  public static List<BabelFieldLabelValue> getFormLabels(Class<?> typeClass) {
    return getFormLabels(typeClass, "");
  }

  public static BabelForm getForm(Class<?> typeClass) {
    LOG.debug("Processing form with class name {}", typeClass.getName());

    BabelFormBuilder form = BabelForm.builder().title(typeClass.getSimpleName());

    if(!typeClass.isAnnotationPresent(Schema.class)) { return null; }    
    Schema typeSchema = typeClass.getAnnotation(Schema.class);

    /**
     * Setup Title and Description
     */
    if (typeSchema != null) {
      if (typeSchema.description() != null) {
        form.description(typeSchema.description());
      }

      if (typeSchema.title() != null) {
        form.title(typeSchema.title());
      }
    }

    /**
     * Setup fields
     */
    Field[] allFields = typeClass.getDeclaredFields();
    List<BabelFormField> formFields = new ArrayList<BabelFormField>();
    for (Field field : allFields) {

      Class<?> fieldType = field.getType();

      if (field.getGenericType() instanceof ParameterizedType) {
        ParameterizedType parameterType = (ParameterizedType) field.getGenericType();
        fieldType = (Class<?>) parameterType.getActualTypeArguments()[0];
      }

      if (field.isAnnotationPresent(JsonUnwrapped.class)) {
        form.formFields(getForm(field.getType()).formFields());
      } else if (field.isAnnotationPresent(Schema.class)) {
        Schema modelProperty = field.getAnnotation(Schema.class);
        if (modelProperty.hidden()) {
          continue;
        }

        /**
         * Initialise attribute name
         */
        BabelFormField formField = BabelFormField.builder().attribute(field.getName())
            .fieldType(BabelFormFieldType.INPUT).build();

        /**
         * Initialise readonly
         */
        if (modelProperty.accessMode() != null
            && modelProperty.accessMode().equals(AccessMode.READ_ONLY)) {
          formField.readOnly(true);
        }
        
        /**
         * Initialise mandatory
         */
        if(modelProperty.required() || field.isAnnotationPresent(NotNull.class)) {
          formField.mandatory(true);
        }
        
        /**
         * Initialise text area
         */
        if(field.isAnnotationPresent(Lob.class)) {
          formField.fieldType(BabelFormFieldType.TEXTAREA);
        }

        /**
         * Initialise Tooltip
         */
        if (modelProperty.description() != null) {
          formField.tooltip(modelProperty.description());
        }
        
        /**
         * Initialise title
         */
        if (modelProperty.title() != null) {
          formField.title(modelProperty.title());
        }

        /**
         * Populate Enum values
         */
        if (fieldType.isEnum()) {
          if (LabelValueEnumInterface.class.isAssignableFrom(fieldType)) {
            List<BabelFieldLabelValue> values = new ArrayList<BabelFieldLabelValue>();
            for (Object b : fieldType.getEnumConstants()) {
              LabelValueEnumInterface value = (LabelValueEnumInterface) b;
              values.add(BabelFieldLabelValue.builder().label(value.label()).value(value.toString())
                  .build());
            }
            formField.values(values);
          } else {
            List<BabelFieldLabelValue> values = new ArrayList<BabelFieldLabelValue>();
            for (Object b : fieldType.getEnumConstants()) {
              values.add(
                  BabelFieldLabelValue.builder().label(b.toString()).value(b.toString()).build());
            }
            formField.values(values);
          }
          if(Set.class.isAssignableFrom(field.getType())) {
            formField.fieldType(BabelFormFieldType.SELECT_MULTI);
          } else {
            formField.fieldType(BabelFormFieldType.SELECT);
          }
        }

        /**
         * Complex Type Implementing LabelValueDerivedInterface
         */
        if (LabelValueDerivedInterface.class.isAssignableFrom(fieldType)) {
          try {
            LabelValueDerivedInterface value = LabelValueDerivedInterface.class
                .cast(fieldType.getDeclaredConstructor().newInstance());
            formField.values().addAll(value.getFormLabels());
            formField.fieldType(BabelFormFieldType.SELECT);
          } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
              | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            LOG.error(
                "Encountered an error when attempting to cast {} to a LabelValueDerivedInterface class with error: {}",
                fieldType.getSimpleName(), e.getMessage());
          }
        }
        /**
         * DateTime
         */
        LOG.debug("Processing class {}", fieldType.toString());
        if (OffsetDateTime.class.isAssignableFrom(fieldType)) {
          formField.fieldType(BabelFormFieldType.DATETIME);
        }

        /**
         * Currency
         */
        if (Currency.class.isAssignableFrom(fieldType)) {
          formField.fieldType(BabelFormFieldType.SELECT);

          List<BabelFieldLabelValue> values = new ArrayList<BabelFieldLabelValue>();
          for (Currency b : Currency.getAvailableCurrencies()) {
            values.add(BabelFieldLabelValue.builder().label(b.getDisplayName())
                .value(b.getCurrencyCode()).build());
          }
          formField.values(values);
        }

        /**
         * Boolean
         */
        if (Boolean.class.isAssignableFrom(fieldType)) {
          formField.fieldType(BabelFormFieldType.CHECKBOX);
        }
        /**
         * Duration/Period
         */
        if (Period.class.isAssignableFrom(fieldType)) {
          formField.fieldType(BabelFormFieldType.DURATION);
        }
        
        /**
         * Multi Input
         */
        if(Set.class.isAssignableFrom(field.getType()) ) {
          if(String.class.isAssignableFrom(fieldType) || URI.class.isAssignableFrom(fieldType)) {
            formField.fieldType(BabelFormFieldType.INPUT_MULTI);
          }
        }
        
        /**
         * In same package?
         */
        if(fieldType.getPackageName().equals(typeClass.getPackageName())) {
          formField.form(getForm(fieldType));
          formField.fieldType(BabelFormFieldType.FORM);
        }
        
        /**
         * Initialise default value
         */
        if (modelProperty.defaultValue() != null) {
          if (formField.values() != null && formField.values().size() > 0) {
            formField.values().forEach(fieldValue -> {
              if (fieldValue.value().equals(modelProperty.defaultValue())) {
                formField.defaultValue(BabelFieldLabelValue.builder().label(fieldValue.label())
                    .value(fieldValue.value()).build());
              }
            });
          } else {
            formField.defaultValue(
                BabelFieldLabelValue.builder().value(modelProperty.defaultValue()).build());
          }
        }


        formFields.add(formField);
      }

      form.formFields(formFields);
    }
    return form.build();
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
