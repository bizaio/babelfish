package io.biza.babelfish.oidc.payloads.oidc;

import java.util.Map;
import java.util.HashMap;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Valid
@Schema(description = "OIDC Claim Request Map")
@Data
@Builder
@JsonInclude(JsonInclude.Include.ALWAYS)
@NoArgsConstructor
@AllArgsConstructor
public class OIDCClaimRequestMap {

	@JsonProperty("userinfo")
	Map<String, OIDCClaimRequest> userInfo;

	@JsonProperty("id_token")
	Map<String, OIDCClaimRequest> idToken;

	@JsonAnySetter
	@Getter(onMethod_ = { @JsonAnyGetter })
	@Builder.Default
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	Map<String, Object> customClaims = new HashMap<String, Object>();

}
