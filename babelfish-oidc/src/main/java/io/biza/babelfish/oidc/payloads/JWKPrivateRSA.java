package io.biza.babelfish.oidc.payloads;

import java.net.URI;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.oidc.converters.Base64UrlEncodedToByteConverter;
import io.biza.babelfish.oidc.converters.ByteToBase64UrlEncodedConverter;
import io.biza.babelfish.oidc.enumerations.JWKAlgorithm;
import io.biza.babelfish.oidc.enumerations.JWKKeyOps;
import io.biza.babelfish.oidc.enumerations.JWKKeyType;
import io.biza.babelfish.oidc.enumerations.JWKPublicKeyUse;
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
@Schema(description = "JSON Web Key for Private RSA Keys")
public class JWKPrivateRSA implements JWK {

  /**
   * Common Parameters https://tools.ietf.org/html/rfc7517#page-6
   *
   */
  JWKKeyType kty;
  JWKPublicKeyUse use;
  List<JWKKeyOps> keyOps;
  JWKAlgorithm alg;
  String kid;
  URI x5u;
  List<X509Certificate> x5c;
  List<String> x5t;
  List<String> x5t256;

  /**
   * Parameters for Public Keys https://tools.ietf.org/html/rfc7518#page-30
   */
  @JsonProperty("n")
  @NotNull
  @JsonDeserialize(converter = Base64UrlEncodedToByteConverter.class)
  @JsonSerialize(converter = ByteToBase64UrlEncodedConverter.class)
  byte[] modulus;

  @JsonProperty("e")
  @NotNull
  @JsonDeserialize(converter = Base64UrlEncodedToByteConverter.class)
  @JsonSerialize(converter = ByteToBase64UrlEncodedConverter.class)
  byte[] exponent;
  
  /**
   * Parameters for Private Keys https://tools.ietf.org/html/rfc7518#page-30
   */
  @JsonProperty("d")
  @NotNull
  @JsonDeserialize(converter = Base64UrlEncodedToByteConverter.class)
  @JsonSerialize(converter = ByteToBase64UrlEncodedConverter.class)
  byte[] privateExponent;
  
  @JsonProperty("p")
  @JsonDeserialize(converter = Base64UrlEncodedToByteConverter.class)
  @JsonSerialize(converter = ByteToBase64UrlEncodedConverter.class)
  byte[] firstPrimeFactor;

  @JsonProperty("q")
  @JsonDeserialize(converter = Base64UrlEncodedToByteConverter.class)
  @JsonSerialize(converter = ByteToBase64UrlEncodedConverter.class)
  byte[] secondPrimeFactor;

  @JsonProperty("dp")
  @JsonDeserialize(converter = Base64UrlEncodedToByteConverter.class)
  @JsonSerialize(converter = ByteToBase64UrlEncodedConverter.class)
  byte[] firstFactorCrtExponent;

  @JsonProperty("dq")
  @JsonDeserialize(converter = Base64UrlEncodedToByteConverter.class)
  @JsonSerialize(converter = ByteToBase64UrlEncodedConverter.class)
  byte[] secondFactorCrtExponent;
  
  @JsonProperty("qi")
  @JsonDeserialize(converter = Base64UrlEncodedToByteConverter.class)
  @JsonSerialize(converter = ByteToBase64UrlEncodedConverter.class)
  byte[] firstCrtCoefficient;

  @JsonProperty("oth")
  List<JWKPrivateRSAOtherPrime> oth;

  /**
   * Utility variables
   */
  @JsonIgnore
  @Builder.Default
  Boolean enableCertRetrieval = false;
}
