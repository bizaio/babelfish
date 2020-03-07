package io.biza.babelfish.oidc.payloads;

import java.net.URI;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.time.OffsetDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.oidc.converters.FutureSecondsToOffsetDateTimeConverter;
import io.biza.babelfish.oidc.converters.ListBase64CertificateToListX509CertificateConverter;
import io.biza.babelfish.oidc.converters.ListStringToSpaceListConverter;
import io.biza.babelfish.oidc.converters.ListX509CertificateToListBase64CertificateConverter;
import io.biza.babelfish.oidc.converters.OffsetDateTimeToFutureSecondsConverter;
import io.biza.babelfish.oidc.converters.SpaceListToListStringConverter;
import io.biza.babelfish.oidc.enumerations.JWKAlgorithm;
import io.biza.babelfish.oidc.enumerations.JWKKeyOps;
import io.biza.babelfish.oidc.enumerations.JWKKeyType;
import io.biza.babelfish.oidc.enumerations.JWKPublicKeyUse;
import io.biza.babelfish.oidc.enumerations.OAuth2TokenType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Valid
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "JSON Web Key Set")
@JsonIgnoreProperties(ignoreUnknown = true)
public class JWKS {

  /**
   * RFC7517 Section 5: https://tools.ietf.org/html/rfc7517#page-10
   */

  /**
   * Keys List
   */
  @JsonProperty("keys")
  @NotNull
  List<JWK> keys;

}
