package io.biza.babelfish.util;

import io.biza.babelfish.cdr.models.VersionerConstants;
import io.biza.babelfish.cdr.orika.OrikaFactoryConfigurer;
import io.biza.babelfish.cdr.orika.OrikaFactoryConfigurerInterface;
import io.biza.babelfish.cdr.orika.converter.UUIDConverter;
import io.biza.babelfish.cdr.support.BabelfishVersionedModel;
import io.swagger.v3.oas.annotations.media.Schema;
import ma.glasnost.orika.converter.BidirectionalConverter;

public class Constants {
	/**
	 * Orika implementer
	 */
	public static final String ORIKA_PACKAGE_BASE = OrikaFactoryConfigurer.class.getPackageName();
	public static final String ORIKA_IMPLEMENTING_CLASS = OrikaFactoryConfigurerInterface.class.getName();
	public static final String ORIKA_BIDIRECTIONAL_CLASS = BidirectionalConverter.class.getName();

	public static final String BASE_MODELS_PACKAGE = VersionerConstants.class.getPackageName();
	public static final String BASE_MODELS_ANNOTATION = Schema.class.getName();
	public static final String BASE_CONVERTER_PACKAGE = UUIDConverter.class.getPackageName();
	public static final String VERSIONED_MODEL = BabelfishVersionedModel.class.getName();
}
