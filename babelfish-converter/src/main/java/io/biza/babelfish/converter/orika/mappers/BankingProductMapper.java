package io.biza.babelfish.converter.orika.mappers;

import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductV1;
import io.biza.babelfish.cdr.models.payloads.banking.product.BankingProductV2;
import io.biza.babelfish.converter.orika.OrikaFactoryConfigurerInterface;
import ma.glasnost.orika.MapperFactory;

public class BankingProductMapper implements OrikaFactoryConfigurerInterface {

  @Override
  public void configure(MapperFactory orikaMapperFactory) {
    orikaMapperFactory.classMap(BankingProductV1.class, BankingProductV2.class).byDefault()
        .register();
  }
}
