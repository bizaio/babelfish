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
@Schema(description = "JSON Web Key")
@JsonIgnoreProperties(ignoreUnknown = true)
@Slf4j
public class JWK {

  /**
   * Common Parameters https://tools.ietf.org/html/rfc7517#page-5
   */

  /**
   * Key Type
   */
  @JsonProperty("kty")
  @NotNull
  JWKKeyType kty;

  /**
   * Public Key Use
   */
  @JsonProperty("use")
  JWKPublicKeyUse use;

  /**
   * Key Operations
   */
  @JsonProperty("keyOps")
  List<JWKKeyOps> keyOps;

  /**
   * Algorithm
   */
  @JsonProperty("alg")
  JWKAlgorithm alg;

  /**
   * Key Identifier
   */
  @JsonProperty("kid")
  String kid;

  /**
   * X.509 URL Parameter
   */
  @JsonProperty("x5u")
  URI x5u;

  /**
   * X.509 Certificate Chain
   */
  @JsonProperty("x5c")
  @JsonDeserialize(converter = ListBase64CertificateToListX509CertificateConverter.class)
  @JsonSerialize(converter = ListX509CertificateToListBase64CertificateConverter.class)
  List<X509Certificate> x5c;

  /**
   * X.509 Certificate Thumbprints
   */
  @JsonSetter("x5t")
  List<String> x5t;

  @JsonGetter("x5t")
  List<String> x5tGenerator() {
    if (x5c != null && x5c.size() > 0) {
      return makeDigests(x5c, "SHA-1");
    } else {
      return x5t;
    }
  }

  /**
   * X.509 Certificate SHA-256 Thumbprint
   * 
   * @return
   */
  @JsonSetter("x5t#S256")
  List<String> x5t256;

  @JsonGetter("x5t#S256")
  List<String> x5t256Generator() {
    if (x5c != null && x5c.size() > 0) {
      return makeDigests(x5c, "SHA-256");
    } else {
      return x5t256;
    }
  }

  /**
   * Generate base64 url encoded digests from a set of X509 Certificates
   * 
   * @param certificates containing a list of X509Certificate objects
   * @param digestName what to digest the thumbprints with
   * @return List<String> containing a list of Base64 URL Encoded Certificate Thumbprints
   */

  private List<String> makeDigests(List<X509Certificate> certificates, String digestName) {
    return x5c.stream().map(certificate -> {
      try {
        MessageDigest md = MessageDigest.getInstance(digestName);
        md.update(certificate.getEncoded());
        return Base64.getUrlEncoder().encodeToString(md.digest());
      } catch (CertificateEncodingException | NoSuchAlgorithmException e) {
        LOG.warn(
            "Unable to calculate {} digest on certificate, ignoring for now but results may vary!",
            digestName, e);
        return null;
      }
    }).filter(Objects::nonNull).collect(Collectors.toList());

  }

  @AssertFalse(message = "Only one of use or keyOps should be used, see RFC7517 4.3")
  private Boolean isUseAndKeyOpsExclusive() {
    return use != null && (keyOps != null && keyOps.size() > 0);
  }

  @AssertTrue(message = "Sign should only be paired with Verify, see RFC7517 4.3")
  private Boolean isSigningOnly() {
    return keyOps != null
        ? keyOps.stream().allMatch(op -> Set.of(JWKKeyOps.SIGN, JWKKeyOps.VERIFY).contains(op))
        : true;
  }

  @AssertTrue(message = "Encrypt should only be paired with Decrypt, see RFC7517 4.3")
  private Boolean isEncryptionOnly() {
    return keyOps != null
        ? keyOps.stream().allMatch(op -> Set.of(JWKKeyOps.ENCRYPT, JWKKeyOps.DECRYPT).contains(op))
        : true;
  }

  @AssertTrue(message = "Wrap should only be paired with Unwrap, see RFC7517 4.3")
  private Boolean isWrappedOnly() {
    return keyOps != null
        ? keyOps.stream()
            .allMatch(op -> Set.of(JWKKeyOps.WRAP_KEY, JWKKeyOps.UNWRAP_KEY).contains(op))
        : true;
  }

}
