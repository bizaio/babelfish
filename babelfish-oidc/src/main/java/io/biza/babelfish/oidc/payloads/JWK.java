package io.biza.babelfish.oidc.payloads;

import java.net.URI;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.oidc.converters.ListBase64CertificateToListX509CertificateConverter;
import io.biza.babelfish.oidc.converters.ListX509CertificateToListBase64CertificateConverter;
import io.biza.babelfish.oidc.enumerations.JWKAlgorithm;
import io.biza.babelfish.oidc.enumerations.JWKKeyOps;
import io.biza.babelfish.oidc.enumerations.JWKKeyType;
import io.biza.babelfish.oidc.enumerations.JWKPublicKeyUse;
import io.swagger.v3.oas.annotations.media.Schema;

@Valid
@Schema(description = "JSON Web Key")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
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
