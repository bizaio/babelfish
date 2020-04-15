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
package io.biza.babelfish.spring.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import io.biza.babelfish.cdr.support.LabelValueEnumInterface;
import io.biza.babelfish.spring.interfaces.LabelValueDerivedInterface;
import io.biza.babelfish.spring.payloads.BabelFieldLabelValue;
import io.biza.babelfish.spring.payloads.BabelForm;
import io.biza.babelfish.spring.util.LabelValueOpenApiUtil;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TypeService implements ApplicationContextAware {

  @Value("${babelfish.typeManager.enumerations.packages:io.biza.babelfish.cdr.enumerations}")
  List<String> enumPackages;

  @Value("${babelfish.typeManager.forms.packages:io.biza.babelfish.cdr.models}")
  List<String> formPackages;

  private Map<String, Class<?>> enumerationMap = new HashMap<String, Class<?>>();
  private Map<String, Class<?>> formMap = new HashMap<String, Class<?>>();

  @SuppressWarnings("unused")
  private ApplicationContext applicationContext;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
    init();
  }

  public void init() {
    if (enumPackages != null) {
      try (ScanResult mapperResult = new ClassGraph().enableAllInfo()
          .whitelistPackages(enumPackages.toArray(new String[0])).scan()) {

        ClassInfoList configurerClasses = mapperResult.getAllClasses();

        for (Class<?> clazz : configurerClasses.loadClasses()) {
          enumerationMap.put(clazz.getSimpleName(), clazz);
          LOG.debug("Registered Type for {}", clazz.getSimpleName());
        }
      }
    }

    if (formPackages != null) {
      try (ScanResult mapperResult = new ClassGraph().enableAllInfo()
          .whitelistPackages(formPackages.toArray(new String[0])).scan()) {
        
        ClassInfoList configurerClasses = mapperResult.getClassesWithAnnotation("io.swagger.v3.oas.annotations.media.Schema");

        for (Class<?> clazz : configurerClasses.loadClasses()) {
          formMap.put(clazz.getSimpleName(), clazz);
          LOG.debug("Registered Form for {}", clazz.getSimpleName());
        }
      }
    }

  }

  public BabelForm getFormDefinition(String formName) {
    if (formMap.containsKey(formName)) {
      Class<?> targetClass = formMap.get(formName);
      if (targetClass.isAnnotationPresent(Schema.class)) {
        return LabelValueOpenApiUtil.getForm(targetClass);
      } else {
        LOG.error("Retrieving Form Definition requested for {} with unknown type of {}",
            targetClass);
        return null;
      }
    }
    
    LOG.error("Unable to locate requested field name of {} in enumeration map", formName);
    return null;
  }

  public Map<String, List<BabelFieldLabelValue>> getEnumerationTypes(List<String> labelTypes) {
    Map<String, List<BabelFieldLabelValue>> formLabels =
        new HashMap<String, List<BabelFieldLabelValue>>();
    labelTypes.forEach(oneFieldType -> {
      List<BabelFieldLabelValue> fieldList = getEnumerationTypes(oneFieldType);
      if (fieldList != null) {
        formLabels.put(oneFieldType, fieldList);
      }
    });

    return formLabels;
  }

  public List<BabelFieldLabelValue> getEnumerationTypes(String oneFieldType) {
    if (enumerationMap.containsKey(oneFieldType)) {
      Class<?> targetClass = enumerationMap.get(oneFieldType);
      List<BabelFieldLabelValue> fieldValue = new ArrayList<BabelFieldLabelValue>();

      LOG.debug(
          "{} -> isEnum: {} LabelValueEnumInterface: {} LabelValueDerivedInterface: {} ApiModel: {}",
          targetClass, targetClass.isEnum(),
          LabelValueEnumInterface.class.isAssignableFrom(targetClass),
          LabelValueDerivedInterface.class.isAssignableFrom(targetClass),
          targetClass.isAnnotationPresent(Schema.class));
      if (targetClass.isEnum() && LabelValueEnumInterface.class.isAssignableFrom(targetClass)) {
        for (Object b : targetClass.getEnumConstants()) {
          LabelValueEnumInterface value = (LabelValueEnumInterface) b;
          fieldValue.add(
              BabelFieldLabelValue.builder().label(value.label()).value(value.toString()).build());
        }

        return fieldValue;
      } else if (LabelValueDerivedInterface.class.isAssignableFrom(targetClass)) {
        try {
          LabelValueDerivedInterface value = LabelValueDerivedInterface.class
              .cast(targetClass.getDeclaredConstructor().newInstance());
          return value.getFormLabels();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
            | InvocationTargetException | NoSuchMethodException | SecurityException e) {
          LOG.error(
              "Encountered an error when attempting to cast {} to a LabelValueDerivedInterface class with error: {}",
              targetClass.getSimpleName(), e.getMessage());
        }
      } else if (targetClass.isEnum()) {
        for (Object b : targetClass.getEnumConstants()) {
          fieldValue
              .add(BabelFieldLabelValue.builder().label(b.toString()).value(b.toString()).build());
        }
        return fieldValue;

      } else if (targetClass.isAnnotationPresent(Schema.class)) {
        return LabelValueOpenApiUtil.getFormLabels(targetClass);
      } else {
        LOG.error(
            "Unable to create type fields response, found object but unable to determine how to interogate it");
        return null;
      }
    }

    LOG.error("Unable to locate requested field name of {} in enumeration map", oneFieldType);
    return null;
  }
}
