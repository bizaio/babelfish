package io.biza.babelfish.spring.service.config.properties;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import io.biza.babelfish.converter.Constants;

@Configuration
@Getter
@ToString
@NoArgsConstructor
public class BabelfishOrikaProperties {

	@Schema(title = "Orika Package Base")
	@NotEmpty
	List<String> packageBase = List.of(Constants.ORIKA_PACKAGE_BASE);

	@Schema(title = "Orika Implementing Class")
	@NotBlank
	String implementingClass = Constants.ORIKA_IMPLEMENTING_CLASS;

	@Schema(title = "Orika Self Mapping Annotation Trigger")
	@NotBlank
	String selfMappingAnnotation = Constants.BASE_MODELS_ANNOTATION;

	@Schema(title = "Orika Converter Package List")
	@NotEmpty
	List<String> converterPackage = List.of(Constants.BASE_CONVERTER_PACKAGE);

	@Schema(title = "Orika BiDirectional Implementing Class")
	@NotBlank
	String bidirectionalClass = Constants.ORIKA_BIDIRECTIONAL_CLASS;


	/**
	 * Required for @Configuration compatibility
	 */
	public void setPackageBase(List<String> packageBase) {
		this.packageBase = packageBase;
	}

	public void setImplementingClass(String implementingClass) {
		this.implementingClass = implementingClass;
	}

	public void setSelfMappingAnnotation(String selfMappingAnnotation) {
		this.selfMappingAnnotation = selfMappingAnnotation;
	}

	public void setConverterPackage(List<String> converterPackage) {
		this.converterPackage = converterPackage;
	}

	public void setBidirectionalClass(String bidirectionalClass) {
		this.bidirectionalClass = bidirectionalClass;
	}


}
