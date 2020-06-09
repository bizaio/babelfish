package io.biza.babelfish.converter.support;

import io.biza.babelfish.converter.orika.OrikaFactoryConfigurer;

public class BabelfishConverter {

  public static <S, D> D convert(S sourceClass, Class<D> destinationClass) {
    OrikaFactoryConfigurer factory = new OrikaFactoryConfigurer();
    return factory.getFactory().getMapperFacade().map(sourceClass, destinationClass);
  }
}
