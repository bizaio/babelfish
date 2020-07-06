package io.biza.babelfish.spring.service.config.properties;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Configuration
@Getter
@ToString
@NoArgsConstructor
public class BabelfishTypeManagerProperties {
	
	@Schema(title = "Enable Type Manager")
	Boolean enabled = false;
	
	@Schema(title = "Type Manager Enumerations Packages")
	List<String> enumPackages = List.of("io.biza.babelfish.cdr.enumerations");

	@Schema(title = "Type Manager Forms Packages")
	List<String> formPackages = List.of("io.biza.babelfish.cdr.models");

	/**
	 * Required for @Configuration compatibility
	 */
	public void setEnumPackages(List<String> enumPackages) {
		this.enumPackages = enumPackages;
	}

	public void setFormPackages(List<String> formPackages) {
		this.formPackages = formPackages;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

}
