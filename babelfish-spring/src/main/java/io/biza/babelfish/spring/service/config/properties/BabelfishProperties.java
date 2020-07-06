package io.biza.babelfish.spring.service.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import io.biza.babelfish.spring.Variables;

@Configuration
@ConfigurationProperties(prefix = Variables.BABELFISH, ignoreUnknownFields = false)
@Getter
@ToString
@NoArgsConstructor
public class BabelfishProperties {

	@Schema(description = "Babelfish advertised version")
	String version = "Biza.io Babelfish 1.2.8-SNAPSHOT";
	
	@Schema(description = "Outbound TLS Disabled")
	Boolean insecureOutboundTls = false;
	
	@Schema(description = "Outbound Hostname Verification Disabled")
	Boolean insecureHostnameVerification = false;

	@Schema(description = "Babelfish Orika Service Properties")
	@NestedConfigurationProperty
	BabelfishOrikaProperties orika = new BabelfishOrikaProperties();

	@Schema(description = "Babelfish Issuer Properties")
	@NestedConfigurationProperty
	BabelfishIssuerProperties issuer = new BabelfishIssuerProperties();

	@Schema(description = "Babelfish Type Service Properties")
	@NestedConfigurationProperty
	BabelfishTypeManagerProperties typeService = new BabelfishTypeManagerProperties();

	@Schema(description = "Babelfish CA Service Properties")
	@NestedConfigurationProperty
	BabelfishCAProperties ca = new BabelfishCAProperties();

	@Schema(description = "Babelfish Secret Manager Properties")
	@NestedConfigurationProperty
	BabelfishSecretManagerProperties secretManager = new BabelfishSecretManagerProperties();
	
	@Schema(description = "Babelfish Service Definitions")
	@NestedConfigurationProperty
	BabelfishServiceProperties service = new BabelfishServiceProperties();

	/**
	 * Required for @Configuration compatibility
	 */
	public void setOrika(BabelfishOrikaProperties orika) {
		this.orika = orika;
	}

	public void setIssuer(BabelfishIssuerProperties issuer) {
		this.issuer = issuer;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public void setTypeService(BabelfishTypeManagerProperties typeService) {
		this.typeService = typeService;
	}

	public void setCa(BabelfishCAProperties ca) {
		this.ca = ca;
	}

	public void setSecretManager(BabelfishSecretManagerProperties secretManager) {
		this.secretManager = secretManager;
	}

	public void setInsecureOutboundTls(Boolean insecureOutboundTls) {
		this.insecureOutboundTls = insecureOutboundTls;
	}

	public void setService(BabelfishServiceProperties service) {
		this.service = service;
	}

	public void setInsecureHostnameVerification(Boolean insecureHostnameVerification) {
		this.insecureHostnameVerification = insecureHostnameVerification;
	}

}
