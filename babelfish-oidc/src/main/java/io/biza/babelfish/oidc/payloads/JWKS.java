package io.biza.babelfish.oidc.payloads;

import java.util.List;
import javax.validation.Valid;
import java.util.ArrayList;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@Schema(description = "JSON Web Key Set")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JWKS {

  /**
   * RFC7517 Section 5: https://tools.ietf.org/html/rfc7517#page-10
   */

  /**
   * Keys List
   */
  @JsonProperty("keys")
  @NotNull
  @Builder.Default
  List<JWK> keys = new ArrayList<JWK>();

}
