package io.biza.babelfish.oidc.requests;

import java.net.URI;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.biza.babelfish.common.jackson.UriStringToUriConverter;
import io.biza.babelfish.common.jackson.UriToUriStringConverter;
import io.biza.babelfish.oidc.enumerations.RegistrationErrorCode;
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
@Schema(description = "OAuth 2.0 Dynamic Client Registration Error Response")
@JsonInclude(Include.NON_NULL)
public class DynamicRegistrationErrorResponse {

  /**
   * Error Code
   */
  @JsonProperty("error")
  @NotNull
  public RegistrationErrorCode error; 

  /**
   * Error Description
   */
  @JsonProperty("error_description")
  public String errorDescription;

  
  /**
   * Error URI
   */
  @JsonProperty("error_uri")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  @JsonDeserialize(converter = UriStringToUriConverter.class)
  public URI errorUri;
  
  
  /**
   * State
   */
  @JsonProperty("state")
  public String state;
  
}
