package io.biza.babelfish.oidc.requests;

import java.net.URI;
import java.util.List;
import java.util.Locale;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.oidc.converters.ListStringToSpaceListConverter;
import io.biza.babelfish.oidc.converters.SpaceListToListStringConverter;
import io.biza.babelfish.oidc.enumerations.OAuth2ResponseType;
import io.biza.babelfish.oidc.enumerations.OIDCDisplayType;
import io.biza.babelfish.oidc.enumerations.OIDCPromptType;
import io.biza.babelfish.oidc.enumerations.OIDCResponseMode;
import io.biza.babelfish.oidc.enumerations.OIDCResponseType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Valid
@ToString
@EqualsAndHashCode
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestAuthenticationCodeIdToken {
  
  /**
   * OpenID Connect Core 1.0 Section 3.1.2.1
   */
  @JsonProperty("scope")
  @JsonDeserialize(converter = SpaceListToListStringConverter.class)
  @JsonSerialize(converter = ListStringToSpaceListConverter.class)  
  List<String> scopes;
  
  @JsonProperty("response_type")
  @Builder.Default
  List<OAuth2ResponseType> responseType = List.of(OAuth2ResponseType.CODE, OAuth2ResponseType.ID_TOKEN);
  
  @JsonProperty("client_id")
  @NotNull
  String clientId;
  
  @JsonProperty("redirect_uri")
  @NotNull
  URI redirectUri;
    
  @JsonProperty("state")
  String state;
  
  @JsonProperty("response_mode")
  OIDCResponseMode responseMode;
  
  @JsonProperty("nonce")
  String nonce;
  
  @JsonProperty("display")
  OIDCDisplayType display;
  
  @JsonProperty("prompt")
  OIDCPromptType prompt;
  
  @JsonProperty("max_age")
  Integer maxAge;
  
  @JsonProperty("ui_locales")
  List<Locale> uiLocales;
  
  @JsonProperty("id_token_hint")
  String idTokenHint;
  
  @JsonProperty("login_hint")
  String loginHint;
  
  @JsonProperty("acr_values")
  @JsonDeserialize(converter = SpaceListToListStringConverter.class)
  @JsonSerialize(converter = ListStringToSpaceListConverter.class)  
  List<String> acrValues;
  
  
  @AssertTrue(message = "Scopes list must contain openid")
  private Boolean isOpenIdRequest() {
    return scopes != null && scopes.contains("openid");
  }
  
}
