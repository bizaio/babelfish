package io.biza.babelfish.oidc.requests;

import java.net.URI;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.biza.babelfish.oidc.enumerations.OIDCErrorCode;
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
@Schema(description = "OpenID Connect Core Error Response")
public class OIDCErrorResponse {

  /**
   * Error Code
   */
  @JsonProperty("error")
  @NotNull
  public OIDCErrorCode error; 

  /**
   * Error Description
   */
  @JsonProperty("error_description")
  public String errorDescription;

  
  /**
   * Error URI
   */
  @JsonProperty("error_uri")
  public URI errorUri;
  
  
  /**
   * State
   */
  @JsonProperty("state")
  public String state;
  
}
