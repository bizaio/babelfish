/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *******************************************************************************/
package io.biza.babelfish.typeservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import io.biza.babelfish.spring.service.TypeService;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class})
@Import(TypeService.class)
public class BabelfishTypeServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(BabelfishTypeServiceApplication.class, args);
  }

  @Bean
  public OpenAPI customOpenAPI(@Value("${babelfish.version}") String appVersion) {
    /**
     * OpenID Connect is available in OAS annotations but not yet in swagger-ui :(
     * https://github.com/swagger-api/swagger-ui/issues/3517
     * 
     * TODO: Contribute OpenID support to swagger-ui We want our swagger definition to be accurate
     * so we leave this as is but it means swagger-ui is a "view spec" only interface
     */
    return new OpenAPI()
        .info(new Info().title("Babelfish Type Service API").version(appVersion).description(
            "This is the Babelfish Type Service API. You can find out more about Babelfish at [https://github.com/bizaio/deepthought](https://github.com/bizaio/babelfish) or on the [DataRight.io Slack, #oss](https://join.slack.com/t/datarightio/shared_invite/enQtNzAyNTI2MjA2MzU1LTU1NGE4MmQ2N2JiZWI2ODA5MTQ2N2Q0NTRmYmM0OWRlM2U5YzA3NzU5NDYyODlhNjRmNzU3ZDZmNTI0MDE3NjY).")
            .license(new License().name("LGPL 3.0")
                .url("https://github.com/bizaio/babelfish/blob/develop/LICENSE")));
  }

}
