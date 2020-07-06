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
public class BabelfishServiceProperties {

	@Schema(description = "Config Service Name")
	String configService = "DatabaseConfigService";
	
	@Schema(description = "Issuer Service Name")
	String issuerService = "DatabaseIssuerService";
	
	@Schema(description = "Verification Service Name")
	String verificationService = "NimbusVerificationService";
	
	@Schema(description = "Secrets Manager Service Name")
	String secretService = "DatabaseSecretService";
	
	@Schema(description = "Certificate Authority Service Name")
	String certificateService = "LocalKeyStoreCertificateService";

	/**
	 * Required for @Configuration compatibility
	 */
	public void setConfigService(String configService) {
		this.configService = configService;
	}

	public void setIssuerService(String issuerService) {
		this.issuerService = issuerService;
	}

	public void setVerificationService(String verificationService) {
		this.verificationService = verificationService;
	}

	public void setSecretService(String secretService) {
		this.secretService = secretService;
	}

	public void setCertificateService(String certificateService) {
		this.certificateService = certificateService;
	}

}
