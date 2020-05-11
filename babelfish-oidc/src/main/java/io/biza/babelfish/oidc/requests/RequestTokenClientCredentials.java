package io.biza.babelfish.oidc.requests;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.biza.babelfish.common.jackson.ListStringToSpaceListConverter;
import io.biza.babelfish.common.jackson.SpaceListToListStringConverter;
import io.biza.babelfish.oidc.enumerations.OAuth2GrantType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.ToString;

@Valid
@ToString
@EqualsAndHashCode
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestTokenClientCredentials {
  
  /**
   * RFC6749 3.2.1
   */
  @JsonProperty("client_id")
  @NotEmpty
  String clientId;

  @NotEmpty
  @JsonProperty("client_secret")
  String clientSecret;
  
  
  /**
   * RFC6749 4.4.2
   */
  @JsonProperty("grant_type")
  @Setter(AccessLevel.NONE)
  @Getter(AccessLevel.NONE)
  @Builder.Default
  OAuth2GrantType grantType = OAuth2GrantType.CLIENT_CREDENTIALS;
  
  @JsonProperty("scope")
  @JsonSerialize(converter = ListStringToSpaceListConverter.class)
  @JsonDeserialize(converter = SpaceListToListStringConverter.class)
  List<String> scopes;

  
}
