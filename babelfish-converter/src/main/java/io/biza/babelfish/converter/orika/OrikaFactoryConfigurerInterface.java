package io.biza.babelfish.converter.orika;

import ma.glasnost.orika.MapperFactory;

/**
 * An interface for Orika Mapper Factory setup
 */
public interface OrikaFactoryConfigurerInterface {
  void configure(MapperFactory orikaMapperFactory);
}
