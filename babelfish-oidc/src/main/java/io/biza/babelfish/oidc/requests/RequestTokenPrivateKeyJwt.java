package io.biza.babelfish.oidc.requests;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.biza.babelfish.common.jackson.ListStringToSpaceListConverter;
import io.biza.babelfish.common.jackson.SpaceListToListStringConverter;
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
public class RequestTokenPrivateKeyJwt {
    
  /**
   * RFC6749 3.2.1
   */
  @JsonProperty("client_id")
  String clientId;
  
  // Optional for Assertion based setups
  @JsonProperty("client_secret")
  String clientSecret;
  
  /**
   * RFC7521 4.1
   */
  @JsonProperty("scope")
  @JsonDeserialize(converter = SpaceListToListStringConverter.class)
  @JsonSerialize(converter = ListStringToSpaceListConverter.class)  
  List<String> scopes;
  
  /**
   * RFC7523 2.1
   */
  @Setter(AccessLevel.NONE)
  @Getter(AccessLevel.NONE)
  @JsonProperty("grant_type")
  final OAuth2GrantType grantType = OAuth2GrantType.CLIENT_CREDENTIALS;
  
  @Setter(AccessLevel.NONE)
  @Getter(AccessLevel.NONE)
  @JsonProperty("client_assertion_type")
  @NotNull
  final OAuth2ClientAssertionType assertionType = OAuth2ClientAssertionType.JWT_BEARER;
  
  @JsonProperty("client_assertion")
  @NotNull
  String clientAssertion;
  
  @Builder
  public RequestTokenPrivateKeyJwt(@NotNull String clientId, String clientSecret, List<String> scopes, String clientAssertion) {
    this.clientId = clientId;
    this.clientSecret = clientSecret;
    this.scopes = scopes;
    this.clientAssertion = clientAssertion;
  }

  
}
