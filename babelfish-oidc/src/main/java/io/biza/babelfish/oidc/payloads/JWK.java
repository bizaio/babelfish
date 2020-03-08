package io.biza.babelfish.oidc.payloads;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.oidc.converters.Base64UrlEncodedToByteConverter;
import io.biza.babelfish.oidc.converters.ByteToBase64UrlEncodedConverter;
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
import io.biza.babelfish.oidc.support.JWKUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

@Valid
@Schema(description = "JSON Web Key")
@JsonIgnoreProperties(ignoreUnknown = true)
public class JWK {

  /**
   * Common Parameters https://tools.ietf.org/html/rfc7517#page-6
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
  @JsonProperty("x5t")
  List<String> x5t;

  /**
   * X.509 Certificate SHA-256 Thumbprint
   * 
   * @return
   */
  @JsonProperty("x5t#S256")
  List<String> x5t256;
  
  /**
   * Parameters for Public Keys https://tools.ietf.org/html/rfc7518#page-30
   */
  @JsonProperty("n")
  String modulus;

  @JsonProperty("e")
  String exponent;

}
