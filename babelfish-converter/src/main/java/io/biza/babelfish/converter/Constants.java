package io.biza.babelfish.converter;

import io.biza.babelfish.cdr.support.BabelfishVersionedModel;
import io.biza.babelfish.converter.models.VersionerConstants;
import io.biza.babelfish.converter.orika.OrikaFactoryConfigurer;
import io.biza.babelfish.converter.orika.OrikaFactoryConfigurerInterface;
import io.biza.babelfish.converter.orika.converter.UUIDConverter;
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
