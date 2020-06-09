package io.biza.babelfish.converter.orika.mappers;

import io.biza.babelfish.cdr.models.responses.ResponseBankingProductListV1;
import io.biza.babelfish.cdr.models.responses.ResponseBankingProductListV2;
import io.biza.babelfish.converter.orika.OrikaFactoryConfigurerInterface;
import ma.glasnost.orika.MapperFactory;

public class ResponseBankingProductListMapper implements OrikaFactoryConfigurerInterface {

  @Override
  public void configure(MapperFactory orikaMapperFactory) {
    orikaMapperFactory.classMap(ResponseBankingProductListV1.class, ResponseBankingProductListV2.class).byDefault()
        .register();
  }
}
