package io.biza.babelfish.converter.models;

import java.util.HashMap;

import io.biza.babelfish.cdr.support.BabelfishVersionedModel;
import io.biza.babelfish.converter.Constants;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class VersionerConstants {
  
  public static HashMap<Class<?>, Integer> versionMap = getVersionMap();
  public static HashMap<Class<?>, HashMap<Integer, Class<?>>> classMap = getClassMap();

  public static HashMap<Class<?>, Integer> getVersionMap() {
    HashMap<Class<?>, Integer> versionMap = new HashMap<Class<?>, Integer>();

    LOG.debug("Populating version mapper");
    try (ScanResult mapperResult =
        new ClassGraph().enableAllInfo().whitelistPackages(Constants.BASE_MODELS_PACKAGE).scan()) {
      ClassInfoList configurerClasses =
          mapperResult.getClassesWithAnnotation(Constants.VERSIONED_MODEL);

      /**
       * Populate schema map
       */
      for (Class<?> clazz : configurerClasses.loadClasses()) {
        LOG.debug("Processing version mapper class of {}", clazz.getName());
        BabelfishVersionedModel versionModel = clazz.getAnnotation(BabelfishVersionedModel.class);
        LOG.debug("Adding version of {} for class of {}", versionModel.version(), clazz.getName());
        versionMap.put(clazz, versionModel.version());
      }

      LOG.debug("VersionerConstants map version map has been setup as follows: {}", versionMap);
      return versionMap;
    }
  }


  public static HashMap<Class<?>, HashMap<Integer, Class<?>>> getClassMap() {
    HashMap<Class<?>, HashMap<Integer, Class<?>>> classMap =
        new HashMap<Class<?>, HashMap<Integer, Class<?>>>();

    LOG.debug("Populating class mapper");
    try (ScanResult mapperResult =
        new ClassGraph().enableAllInfo().whitelistPackages(Constants.BASE_MODELS_PACKAGE).scan()) {
      ClassInfoList configurerClasses =
          mapperResult.getClassesWithAnnotation(Constants.VERSIONED_MODEL);

      HashMap<String, HashMap<Integer, Class<?>>> schemaMap =
          new HashMap<String, HashMap<Integer, Class<?>>>();

      /**
       * Populate schema map
       */
      for (Class<?> clazz : configurerClasses.loadClasses()) {
        LOG.debug("Processing version mapper of {}", clazz.getName());
        BabelfishVersionedModel versionModel = clazz.getAnnotation(BabelfishVersionedModel.class);
        HashMap<Integer, Class<?>> currentSchemaMap =
            schemaMap.getOrDefault(versionModel.schemaName(), new HashMap<Integer, Class<?>>());
        currentSchemaMap.put(versionModel.version(), clazz);
        schemaMap.put(versionModel.schemaName(), currentSchemaMap);
      }

      /**
       * Now iterate schema map to populate class mappings for faster resolution in future use
       * 
       * @formatter:off
       * Processes hash example of: 
       *   { "ResponseBankingProductByID" -> 
       *     { 
       *       1 -> ResponseBankingProductByIDV1.class, 
       *       2 -> ResponseBankingProductByIDV2.class 
       *     } 
       *   }
       * @formatter:on
       */
      for (String schemaName : schemaMap.keySet()) {
        for (Integer version : schemaMap.get(schemaName).keySet()) {
          for (Integer versionChild : schemaMap.get(schemaName).keySet()) {
            HashMap<Integer, Class<?>> currentMap = classMap.getOrDefault(
                schemaMap.get(schemaName).get(version), new HashMap<Integer, Class<?>>());
            currentMap.put(versionChild, schemaMap.get(schemaName).get(versionChild));
            classMap.put(schemaMap.get(schemaName).get(version), currentMap);
          }
        }
      }

      LOG.debug("VersionerConstants map has been setup as follows: {}", classMap);
      return classMap;
    }
  }
}
