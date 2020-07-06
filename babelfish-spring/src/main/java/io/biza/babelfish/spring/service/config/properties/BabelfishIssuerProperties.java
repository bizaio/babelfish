package io.biza.babelfish.spring.service.config.properties;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import io.biza.babelfish.oidc.enumerations.JWEEncryptionAlgorithmType;
import io.biza.babelfish.oidc.enumerations.JWSSigningAlgorithmType;

@Configuration
@Getter
@ToString
@NoArgsConstructor
public class BabelfishIssuerProperties {
	
	@Schema(title = "Perform Automatic Initialisation")
	@NotNull
	Boolean autoInit = false;
	
	@Schema(title = "Auto Init Realms")
	List<String> autoInitRealms;
	
	@Schema(title = "Auto Init Key Signing Algorithm")
	JWSSigningAlgorithmType signingType = JWSSigningAlgorithmType.PS256;
	
	@Schema(title = "Auto Init Encryption Algorithm")
	JWEEncryptionAlgorithmType encryptionType = JWEEncryptionAlgorithmType.RSA_OAEP;
	
	@Schema(title = "Default JWK Key Size")
	@Min(2048)
	Integer defaultKeySize = 2048;

	/**
	 * Required for @Configuration compatibility
	 */
	public void setAutoInitRealms(List<String> autoInitRealms) {
		this.autoInitRealms = autoInitRealms;
	}

	public void setSigningType(JWSSigningAlgorithmType signingType) {
		this.signingType = signingType;
	}

	public void setEncryptionType(JWEEncryptionAlgorithmType encryptionType) {
		this.encryptionType = encryptionType;
	}

	public void setAutoInit(Boolean autoInit) {
		this.autoInit = autoInit;
	}

	public void setDefaultKeySize(Integer defaultKeySize) {
		this.defaultKeySize = defaultKeySize;
	}


}
