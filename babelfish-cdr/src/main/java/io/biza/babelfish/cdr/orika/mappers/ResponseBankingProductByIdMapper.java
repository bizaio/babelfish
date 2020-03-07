package io.biza.babelfish.cdr.orika.mappers;

import io.biza.babelfish.cdr.models.responses.ResponseBankingProductByIdV1;
import io.biza.babelfish.cdr.models.responses.ResponseBankingProductByIdV2;
import io.biza.babelfish.cdr.orika.OrikaFactoryConfigurerInterface;
import ma.glasnost.orika.MapperFactory;

public class ResponseBankingProductByIdMapper implements OrikaFactoryConfigurerInterface {

  @Override
  public void configure(MapperFactory orikaMapperFactory) {
    orikaMapperFactory.classMap(ResponseBankingProductByIdV1.class, ResponseBankingProductByIdV2.class).byDefault()
        .register();
  }
}
