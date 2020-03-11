package io.biza.babelfish.cdr.models.requests.cdr;

import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.oidc.converters.ListStringToSpaceListConverter;
import io.biza.babelfish.oidc.converters.SpaceListToListStringConverter;
import io.biza.babelfish.oidc.enumerations.OAuth2ResponseType;
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
public class CDRRequestObject {
  
  /**
   * CDR Specified Request Object
   * https://consumerdatastandardsaustralia.github.io/standards/#request-object
   */
  @JsonProperty("aud")
  URI aud;
  
  @JsonProperty("response_type")
  @Builder.Default
  String responseType = "id_token token";
  
  @JsonProperty("client_id")
  @NotNull
  String clientId;
  
  @JsonProperty("redirect_uri")
  @NotNull
  URI redirectUri;
  
  @JsonProperty("scope")
  @JsonSerialize(converter = ListStringToSpaceListConverter.class)
  @JsonDeserialize(converter = SpaceListToListStringConverter.class)
  List<String> scopes;
  
  @JsonProperty("state")
  String state;
  
  @JsonProperty("nonce")
  String nonce;
  
  @JsonProperty("claims")
  CDRRequestClaims claims;
  
}
