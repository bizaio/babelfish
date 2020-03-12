package io.biza.babelfish.oidc.requests;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.oidc.converters.ListStringToSpaceListConverter;
import io.biza.babelfish.oidc.converters.SpaceListToListStringConverter;
import io.biza.babelfish.oidc.enumerations.OAuth2ClientAssertionType;
import io.biza.babelfish.oidc.enumerations.OAuth2GrantType;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.ToString;

@Valid
@ToString
@EqualsAndHashCode
@Data
public class RequestTokenAuthorizationCodeJwt {
    
  /**
   * RFC6749 3.2.1
   */
  @JsonProperty("client_id")
  String clientId;
  
  @JsonProperty("code")
  String code;
  
  /**
   * RFC7523 2.1
   */
  @Setter(AccessLevel.NONE)
  @Getter(AccessLevel.NONE)
  @JsonProperty("grant_type")
  final OAuth2GrantType grantType = OAuth2GrantType.AUTHORIZATION_CODE;
  
  @Setter(AccessLevel.NONE)
  @Getter(AccessLevel.NONE)
  @JsonProperty("client_assertion_type")
  @NotNull
  final OAuth2ClientAssertionType assertionType = OAuth2ClientAssertionType.JWT_BEARER;
  
  @JsonProperty("client_assertion")
  @NotNull
  String clientAssertion;
  
  @Builder
  public RequestTokenAuthorizationCodeJwt(@NotNull String clientId, String clientAssertion) {
    this.clientId = clientId;
    this.clientAssertion = clientAssertion;
  }

  
}
