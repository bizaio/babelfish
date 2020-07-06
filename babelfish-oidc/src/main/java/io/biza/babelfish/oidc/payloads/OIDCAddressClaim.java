package io.biza.babelfish.oidc.payloads;

import javax.validation.Valid;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Valid
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "OpenID Address Claim")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class OIDCAddressClaim {

	@JsonProperty("formatted")
	String formatted;
	@JsonProperty("street_address")
	String streetAddress;
	@JsonProperty("locality")
	String locality;
	@JsonProperty("region")
	String region;
	@JsonProperty("postal_code")
	String postalCode;
	@JsonProperty("country")
	String country;
}
