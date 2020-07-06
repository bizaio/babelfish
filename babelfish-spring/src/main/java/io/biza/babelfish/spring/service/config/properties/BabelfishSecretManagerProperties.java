package io.biza.babelfish.spring.service.config.properties;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Configuration
@Getter
@ToString
@NoArgsConstructor
public class BabelfishSecretManagerProperties {

	@Schema(title = "Secret Manager Default Generation Length")
	@NotNull
	@Min(6)
	Integer defaultSecretLength = 32;

	/**
	 * Required for @Configuration compatibility
	 */
	public void setDefaultSecretLength(Integer defaultSecretLength) {
		this.defaultSecretLength = defaultSecretLength;
	}
}
