package io.biza.babelfish.cdr.orika;

import java.lang.reflect.InvocationTargetException;
import io.biza.babelfish.cdr.Constants;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Slf4j
public class OrikaFactoryConfigurer {

  protected static MapperFactory localMapper;

  /**
   * Produce a local mapper factory
   * 
   * @return MapperFactory with configuration
   */
  public MapperFactory getFactory() {
    if (localMapper == null) {
      buildFactory();
    }

    return localMapper;
  }

  /**
   * Initialise the mapper, configure it and setup localMapper
   */
  public void buildFactory() {
    /**
     * Initialise the orika mapper
     */
    DefaultMapperFactory.Builder builder = new DefaultMapperFactory.Builder();
    configureFactoryBuilder(builder);
    localMapper = builder.build();
    configureMapperFactory(localMapper);

  }

  /**
   * Setup the lombok resolver
   * 
   * @param builder with lombok compatible resolver
   */
  public void configureFactoryBuilder(DefaultMapperFactory.Builder builder) {
    builder.propertyResolverStrategy(new OrikaFluentLombokResolver());
  }

  /**
   * Reflect the mappers and set them up using default Factory Configuration
   * 
   * @param mapper with mappers configured
   */
  public void configureMapperFactory(MapperFactory mapper) {
    configureMapperFactory(mapper, OrikaFactoryConfig.builder().build());
  }

  public void configureMapperFactory(MapperFactory mapper, OrikaFactoryConfig config) {

    LOG.info("Configuring Mapper Factory with package of {} and implementing class of {}",
        config.packageBase(), config.implementingClass());

    /**
     * Register 1:1 mappings to allow nested in line remapping
     */
    if (config.selfMappingAnnotation() != null) {
      try (ScanResult mapperResult = new ClassGraph().enableAllInfo()
          .whitelistPackages(config.packageBase().toArray(new String[0])).scan()) {

        ClassInfoList configurerClasses =
            mapperResult.getClassesWithAnnotation(config.selfMappingAnnotation());

        for (Class<?> clazz : configurerClasses.loadClasses()) {
          mapper.classMap(clazz, clazz).byDefault().register();

          LOG.debug("Registered 1:1 mapper for {}", clazz.getName());
        }
      }
    }

    /**
     * Configure bidirectional converters
     */
    ConverterFactory converterFactory = mapper.getConverterFactory();

    if (config.converterPackage() != null) {
      try (ScanResult converterResult = new ClassGraph().enableAllInfo()
          .whitelistPackages(config.converterPackage().toArray(new String[0])).scan()) {
        ClassInfoList converterClasses = converterResult.getSubclasses(config.bidirectionalClass());

        for (Class<?> clazz : converterClasses.loadClasses()) {
          try {
            BidirectionalConverter<?, ?> converter =
                (BidirectionalConverter<?, ?>) clazz.getConstructor().newInstance();
            converterFactory.registerConverter(converter);
            LOG.info("Registered converter for {} <-> {}", converter.getAType().getName(),
                converter.getBType().getName());
          } catch (InstantiationException | IllegalAccessException | InvocationTargetException
              | NoSuchMethodException | SecurityException e) {
            LOG.error(
                "Unable to find a declared constructor for class name of {} with exception of {}",
                clazz.getName(), e.toString());
          } catch (IllegalArgumentException e) {
            LOG.error(
                "Encountered issues when configuring downstream mapper for class name of {} with exception of {}",
                clazz.getName(), e.toString());
          }
        }
      }
    }

    /**
     * Configure bidirectional mappers
     */
    try (ScanResult mapperResult = new ClassGraph().enableAllInfo()
        .whitelistPackages(config.packageBase().toArray(new String[0])).scan()) {
      ClassInfoList configurerClasses =
          mapperResult.getClassesImplementing(config.implementingClass());

      for (Class<?> clazz : configurerClasses.loadClasses()) {
        try {
          OrikaFactoryConfigurerInterface configurer =
              (OrikaFactoryConfigurerInterface) clazz.getConstructor().newInstance();
          configurer.configure(mapper);
          LOG.info("Registered mapper named {}", configurer.getClass().getName());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException
            | NoSuchMethodException | SecurityException e) {
          LOG.error(
              "Unable to find a declared constructor for class name of {} with exception of {}",
              clazz.getName(), e.toString());
        } catch (IllegalArgumentException e) {
          LOG.error(
              "Encountered issues when configuring downstream mapper for class name of {} with exception of {}",
              clazz.getName(), e.toString());
        }
      }
    }
  }

}
