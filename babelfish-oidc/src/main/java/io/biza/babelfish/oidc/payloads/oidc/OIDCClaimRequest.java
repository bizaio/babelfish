package io.biza.babelfish.oidc.payloads.oidc;

import java.net.URI;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.biza.babelfish.common.jackson.ListBase64CertificateToListX509CertificateConverter;
import io.biza.babelfish.common.jackson.ListX509CertificateToListBase64CertificateConverter;
import io.biza.babelfish.oidc.enumerations.JWKAlgorithm;
import io.biza.babelfish.oidc.enumerations.JWKKeyOps;
import io.biza.babelfish.oidc.enumerations.JWKKeyType;
import io.biza.babelfish.oidc.enumerations.JWKPublicKeyUse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Valid
@Schema(description = "Individual Claim Request")
@Builder
@Data
public class OIDCClaimRequest {
  
	
	@JsonProperty("essential")
	@Builder.Default
	Boolean essential = false;
	
	@JsonProperty("value")
	String value;
	
	@JsonProperty("values")
	List<String> values;
	
}
