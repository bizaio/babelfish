package io.biza.babelfish.oidc.payloads.oidc;

import java.util.List;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Valid
@Schema(description = "Individual Claim Request")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OIDCClaimRequest {
  
	@JsonProperty("essential")
	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	@Builder.Default
	Boolean essential = false;
	
	@JsonProperty("value")
	String value;
	
	@JsonProperty("values")
	List<String> values;
	
}
