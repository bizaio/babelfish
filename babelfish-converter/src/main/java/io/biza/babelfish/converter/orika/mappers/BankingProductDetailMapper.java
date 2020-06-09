package io.biza.babelfish.converter.orika.mappers;

import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductDetailV1;
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductDetailV2;
import io.biza.babelfish.converter.orika.OrikaFactoryConfigurerInterface;
import ma.glasnost.orika.MapperFactory;

public class BankingProductDetailMapper implements OrikaFactoryConfigurerInterface {

  @Override
  public void configure(MapperFactory orikaMapperFactory) {
    orikaMapperFactory.classMap(BankingProductDetailV1.class, BankingProductDetailV2.class)
        .byDefault().register();
  }
}
