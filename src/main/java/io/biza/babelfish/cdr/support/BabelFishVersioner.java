package io.biza.babelfish.cdr.support;

import io.biza.babelfish.cdr.Constants;
import io.biza.babelfish.cdr.exceptions.UnsupportedPayloadException;
import io.biza.babelfish.cdr.orika.OrikaFactoryConfigurer;

public class BabelFishVersioner {
  
  public static Integer getVersion(Class<?> clazz) throws UnsupportedPayloadException {
    if(!clazz.getPackageName().startsWith(Constants.BASE_PACKAGE)) {
      throw new UnsupportedPayloadException("BabelFishVersioner only supports version discovery for classes in " + Constants.BASE_PACKAGE);
    }
    return -1;
  }

  public static <S, D> D convert(S sourceClass, Class<D> destinationClass) {
    OrikaFactoryConfigurer factory = new OrikaFactoryConfigurer();
    return factory.getFactory().getMapperFacade().map(sourceClass, destinationClass);
  }
}
