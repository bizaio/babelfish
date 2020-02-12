package io.biza.babelfish.cdr.orika;

import java.lang.reflect.InvocationTargetException;
import io.biza.babelfish.cdr.Constants;
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductFeatureV1;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFactory;
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
   * Reflect the mappers and set them up
   * 
   * @param mapper with mappers configured
   */
  public void configureMapperFactory(MapperFactory mapper) {

    LOG.info(
        "Configuring Babelfish CDR Mapper Factory with package of {} and implementing class of {}",
        Constants.ORIKA_PACKAGE_BASE, Constants.ORIKA_IMPLEMENTING_CLASS);

    /**
     * Register 1:1 mappings to allow nested in line remapping
     */
    try (ScanResult mapperResult =
        new ClassGraph().enableAllInfo().whitelistPackages(Constants.BASE_MODELS_PACKAGE).scan()) {
      ClassInfoList configurerClasses =
          mapperResult.getClassesWithAnnotation(Constants.BASE_MODELS_ANNOTATION);

      for (Class<?> clazz : configurerClasses.loadClasses()) {
        mapper.classMap(clazz, clazz).byDefault().register();

        LOG.debug("Registered 1:1 mapper for {}", clazz.getName());
      }
    }

    /**
     * Configure bidirectional configurers
     */
    try (ScanResult mapperResult =
        new ClassGraph().enableAllInfo().whitelistPackages(Constants.ORIKA_PACKAGE_BASE).scan()) {
      ClassInfoList configurerClasses =
          mapperResult.getClassesImplementing(Constants.ORIKA_IMPLEMENTING_CLASS);

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
