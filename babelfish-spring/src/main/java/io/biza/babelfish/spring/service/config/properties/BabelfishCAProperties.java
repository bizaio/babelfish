package io.biza.babelfish.spring.service.config.properties;

import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Configuration
@Getter
@ToString
@NoArgsConstructor
public class BabelfishCAProperties {

	@Schema(title = "CA Key Store Password")
	String keyStorePassword = "solongandthanksforallthefish";

	@Schema(title = "CA Key Store")
	String keystorePath = "babelfish-ca.jks";

	@Schema(title = "CA Key Store Key ID")
	String keystoreCaKeyId = "babelfish-ca-key";

	@Schema(title = "CA Certificate ID")
	String keystoreCaCertId = "babelfish-ca";

	@Schema(title = "CA Certificate ID")
	Boolean showPrivateKeys = false;

	/**
	 * Required for @Configuration compatibility
	 */
	public void setKeyStorePassword(String keyStorePassword) {
		this.keyStorePassword = keyStorePassword;
	}

	public void setKeystorePath(String keystorePath) {
		this.keystorePath = keystorePath;
	}

	public void setKeystoreCaKeyId(String keystoreCaKeyId) {
		this.keystoreCaKeyId = keystoreCaKeyId;
	}

	public void setKeystoreCaCertId(String keystoreCaCertId) {
		this.keystoreCaCertId = keystoreCaCertId;
	}

	public void setShowPrivateKeys(Boolean showPrivateKeys) {
		this.showPrivateKeys = showPrivateKeys;
	}

}
