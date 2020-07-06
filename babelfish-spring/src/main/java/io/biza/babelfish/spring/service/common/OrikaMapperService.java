/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *******************************************************************************/
package io.biza.babelfish.spring.service.common;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import io.biza.babelfish.converter.orika.OrikaFactoryConfig;
import io.biza.babelfish.converter.orika.OrikaFactoryConfigurer;
import io.biza.babelfish.spring.service.config.properties.BabelfishProperties;

@Service
public class OrikaMapperService extends ConfigurableMapper implements ApplicationContextAware {

	@Autowired
	BabelfishProperties properties;

	@SuppressWarnings("unused")
	private ApplicationContext applicationContext;
	private OrikaFactoryConfigurer configurer;

	public OrikaMapperService() {
		super(false);
		configurer = new OrikaFactoryConfigurer();
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
		init();
	}

	@Override
	protected void configureFactoryBuilder(DefaultMapperFactory.Builder factoryBuilder) {
		configurer.configureFactoryBuilder(factoryBuilder);
	}

	@Override
	protected void configure(MapperFactory factory) {
		OrikaFactoryConfig config = OrikaFactoryConfig.builder().build();
		config.packageBase(properties.orika().packageBase());
		config.implementingClass(properties.orika().implementingClass());
		config.selfMappingAnnotation(properties.orika().selfMappingAnnotation());
		config.converterPackage(properties.orika().converterPackage());
		config.bidirectionalClass(properties.orika().bidirectionalClass());
		configurer.configureMapperFactory(factory, config);
	}

}
