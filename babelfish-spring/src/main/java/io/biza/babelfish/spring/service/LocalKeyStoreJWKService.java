package io.biza.babelfish.spring.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.security.Security;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.RSAEncrypter;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.crypto.bc.BouncyCastleProviderSingleton;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKMatcher;
import com.nimbusds.jose.jwk.JWKSelector;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import com.nimbusds.jwt.SignedJWT;
import io.biza.babelfish.oidc.enumerations.JWEEncryptionAlgorithmType;
import io.biza.babelfish.oidc.enumerations.JWEEncryptionMethodType;
import io.biza.babelfish.oidc.enumerations.JWSSigningAlgorithmType;
import io.biza.babelfish.oidc.payloads.JWKS;
import io.biza.babelfish.oidc.payloads.JWTClaims;
import io.biza.babelfish.spring.exceptions.EncryptionOperationException;
import io.biza.babelfish.spring.exceptions.KeyRetrievalException;
import io.biza.babelfish.spring.exceptions.NotInitialisedException;
import io.biza.babelfish.spring.exceptions.SigningOperationException;
import io.biza.babelfish.spring.exceptions.SigningVerificationException;
import io.biza.babelfish.spring.interfaces.JWKService;
import io.biza.babelfish.spring.util.NimbusUtil;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONStyle;

@Slf4j
@Service
@ConditionalOnProperty(name = "babelfish.service.JWKService",
    havingValue = "LocalKeyStoreJWKService", matchIfMissing = true)
public class LocalKeyStoreJWKService implements JWKService {

  @Value("${babelfish.jwk-file:babelfish-jwks.json}")
  String KEYSET_PATH;

  @Value("${babelfish.signing-key-size:2048}")
  Integer SIGNING_KEY_SIZE;
  
  JWKSet jwkSet;
  ObjectMapper mapper;

  @Override
  public JWKS getJwks() throws NotInitialisedException {
    try {
      return mapper.readValue(jwkSet.toPublicJWKSet().toJSONObject().toJSONString(), JWKS.class);
    } catch (JsonProcessingException e) {
      LOG.error("Encountered JSON Processing exception processing from smart-json to jackson?", e);
      throw NotInitialisedException.builder().build();
    }
  }

  public static LocalKeyStoreJWKService clientService(String keySetPath, Integer signingKeySize) {
    LocalKeyStoreJWKService local = new LocalKeyStoreJWKService(keySetPath, signingKeySize);
    return local;
  }

  private LocalKeyStoreJWKService(String keyPath, Integer keySize) {
    Security.addProvider(BouncyCastleProviderSingleton.getInstance());
    this.KEYSET_PATH = Optional.of(keyPath).orElse("babelfish-jwks.json");
    this.SIGNING_KEY_SIZE = Optional.of(keySize).orElse(2048);
    this.mapper = new ObjectMapper();
    try {
      setupKeySet();
    } catch (NotInitialisedException e) {
      LOG.error(
          "Something went wrong while setting up a manually established Key Store JWK Service", e);
    }
  }

  public LocalKeyStoreJWKService() throws NotInitialisedException {
    /**
     * Initialise jackson
     */
    this.mapper = new ObjectMapper();
    Security.addProvider(BouncyCastleProviderSingleton.getInstance());
  }

  @PostConstruct
  private void setupKeySet() throws NotInitialisedException {
    /**
     * If the keyset doesn't exist go create it
     */
    if (!new File(KEYSET_PATH).exists()) {
      initialiseKeyset();
    }

    try {
      this.jwkSet = JWKSet.load(new File(KEYSET_PATH));
    } catch (IOException | ParseException e) {
      LOG.error("Encountered error while attempting to load JWKSet from {}", KEYSET_PATH);
      throw NotInitialisedException.builder().build();
    }

  }

  private void initialiseKeyset() throws NotInitialisedException {
    /**
     * Create a key for signing
     */
    try {
      LOG.info("Initialising the signing and encryption keys for use during operations at: {}",
          KEYSET_PATH);
      RSAKey signingKey = new RSAKeyGenerator(SIGNING_KEY_SIZE).keyUse(KeyUse.SIGNATURE)
          .keyID(UUID.randomUUID().toString()).algorithm(JWSAlgorithm.PS256).generate();
      RSAKey encryptKey = new RSAKeyGenerator(SIGNING_KEY_SIZE).keyUse(KeyUse.ENCRYPTION)
          .keyID(UUID.randomUUID().toString()).algorithm(JWSAlgorithm.PS256).generate();
      JWKSet localJwk = new JWKSet(List.of(signingKey, encryptKey));

      /**
       * Output the new Keyset
       */
      FileWriter jwkFile = new FileWriter(KEYSET_PATH);
      jwkFile.write(localJwk.toJSONObject(false).toJSONString(JSONStyle.NO_COMPRESS));
      jwkFile.close();

    } catch (JOSEException e) {
      LOG.error("Encountered error while attempting to generate keys for JWKSet {}", KEYSET_PATH);
      throw NotInitialisedException.builder().build();
    } catch (IOException e) {
      LOG.error("Encountered I/O Error while writing new JWKS at: {}", KEYSET_PATH, e);
      throw NotInitialisedException.builder().build();
    }

  }

  @Override
  public JWTClaims verify(String compactSerialisation, URI jwksUri, JWTClaims claimChecks)
      throws SigningVerificationException, KeyRetrievalException {

    try {
      SignedJWT inputJwt = SignedJWT.parse(compactSerialisation);
      JWK remoteJwk = remoteSigningKey(jwksUri, inputJwt.getHeader().getKeyID(),
          JWSSigningAlgorithmType.fromValue(inputJwt.getHeader().getAlgorithm().getName()));
      JWSVerifier verifier = new RSASSAVerifier(remoteJwk.toRSAKey());

      if (inputJwt.verify(verifier)) {
        JWTClaims inputClaims = NimbusUtil.fromClaimsSet(inputJwt.getJWTClaimsSet());
        
        if(claimChecks != null) {
          checkEquals("issuer", claimChecks.issuer(), inputClaims.issuer());
          checkEquals("subject", claimChecks.subject(), inputClaims.subject());
          checkEquals("audience", claimChecks.audience(), inputClaims.audience());
        }
        
        return inputClaims;
      } else {
        LOG.warn("Received signing verification error for kid of {} from {}", remoteJwk.getKeyID(),
            jwksUri);
        throw SigningVerificationException.builder()
            .message("Unable to verify serialisation using kid " + remoteJwk.getKeyID()
                + " retrieved from " + jwksUri)
            .build();
      }

    } catch (ParseException e) {
      LOG.error("Unable to parse supplied JWT: {}", e.getMessage(), e);
      throw SigningVerificationException.builder().message(e.getMessage()).build();
    } catch (JOSEException e) {
      LOG.warn("Encountered a JOSEException while attempting to verify payload {} using jwks {}",
          compactSerialisation, jwksUri, e);
      throw SigningVerificationException.builder().message(e.getMessage()).build();
    }
  }

  private <T> void checkEquals(String name, T classOne, T classTwo)
      throws SigningVerificationException {
    if(classOne == null) return;
    if (!classOne.equals(classTwo)) {
      throw SigningVerificationException.builder().message(
          "Verification of " + name + " required claim failed: " + classOne + " versus " + classTwo)
          .build();
    }
  }

  @Override
  public String sign(JWTClaims claims, JWSSigningAlgorithmType algorithm)
      throws SigningOperationException {
    return signToObject(claims, algorithm).serialize();
  }

  private SignedJWT signToObject(JWTClaims claims, JWSSigningAlgorithmType algorithm)
      throws SigningOperationException {
    JWK senderJWK = localSigningKey(algorithm);
    JWSSigner signer;
    try {
      signer = new RSASSASigner(senderJWK.toRSAKey().toPrivateKey());

    } catch (JOSEException e) {
      LOG.warn("Encountered a JOSEException while attempting to setup RSA signer", e);
      throw SigningOperationException.builder().message(e.getMessage()).build();
    }

    SignedJWT signedJwt =
        new SignedJWT(new JWSHeader.Builder(JWSAlgorithm.parse(algorithm.toString()))
            .keyID(senderJWK.getKeyID()).build(), NimbusUtil.toClaimsSet(claims));
    try {
      signedJwt.sign(signer);
      return signedJwt;
    } catch (JOSEException e) {
      LOG.warn("Encountered a JOSEException while performing signature", e);
      throw SigningOperationException.builder().message(e.getMessage()).build();
    }

  }

  @Override
  public String encryptAndSign(JWTClaims claims, URI jwksUri,
      JWSSigningAlgorithmType signingAlgorithm, JWEEncryptionAlgorithmType encryptionAlgorithm,
      JWEEncryptionMethodType method)
      throws SigningOperationException, EncryptionOperationException, KeyRetrievalException {

    SignedJWT signedJWT = signToObject(claims, signingAlgorithm);

    JWEObject jweObject = new JWEObject(
        new JWEHeader.Builder(JWEAlgorithm.parse(encryptionAlgorithm.toString()),
            EncryptionMethod.parse(method.toString())).contentType("JWT").build(),
        new Payload(signedJWT));

    try {
      jweObject
          .encrypt(new RSAEncrypter((RSAKey) remoteEncryptionKey(jwksUri, jweObject.getHeader())));
      return jweObject.serialize();
    } catch (JOSEException e) {
      LOG.error("Encountered JOSE Exception during encrypt operation", e);
      throw EncryptionOperationException.builder().message(e.getMessage()).build();
    }

  }

  private JWKSet getRemoteJwks(URI jwksUri) throws KeyRetrievalException {
    try {
      return JWKSet.load(jwksUri.toURL());
    } catch (IOException | ParseException e) {
      LOG.error("Attempt to retrieve JWKS from {} failed", jwksUri, e);
      throw KeyRetrievalException.builder().message(e.getMessage()).build();
    }
  }

  private JWK remoteEncryptionKey(URI jwksUri, JWEHeader header) throws KeyRetrievalException {
    List<JWK> matches =
        new JWKSelector(JWKMatcher.forJWEHeader(header)).select(getRemoteJwks(jwksUri));
    if (matches.size() > 0) {
      return matches.get(0);
    } else {
      throw KeyRetrievalException.builder()
          .message("Unable to match a key for encryption with JWEHeader of " + header.toString())
          .build();
    }
  }

  private JWK remoteSigningKey(URI jwksUri, String keyId, JWSSigningAlgorithmType algorithm)
      throws KeyRetrievalException {

    JWKSet remoteJwks = getRemoteJwks(jwksUri);

    List<JWK> matches = new JWKSelector(new JWKMatcher.Builder().keyUse(KeyUse.SIGNATURE)
        .algorithm(JWSAlgorithm.parse(algorithm.toString())).keyID(keyId).build())
            .select(remoteJwks);
    if (matches.size() > 0) {
      return matches.get(0).toPublicJWK();
    } else {
      throw KeyRetrievalException.builder()
          .message("Unable to match a key for signing with algorithm " + algorithm + " from " + jwksUri).build();
    }
  }

  private JWK localSigningKey(JWSSigningAlgorithmType algorithm) throws SigningOperationException {
    List<JWK> matches = new JWKSelector(new JWKMatcher.Builder().keyUse(KeyUse.SIGNATURE)
        .algorithm(JWSAlgorithm.parse(algorithm.toString())).build()).select(jwkSet);
    if (matches.size() > 0) {
      return matches.get(0);
    } else {
      throw SigningOperationException.builder()
          .message("Unable to match a key for signing with algorithm " + algorithm).build();
    }
  }

  @Override
  public String peekAtIssuer(String compactSerialisation) throws SigningVerificationException {
    try {
      SignedJWT inputJwt = SignedJWT.parse(compactSerialisation);
      return inputJwt.getJWTClaimsSet().getIssuer();
    } catch (ParseException e) {
      LOG.error("Unable to peek at issuer inside supplied JWT: {}", e.getMessage(), e);
      throw SigningVerificationException.builder().message(e.getMessage()).build();
    }
  }

  @Override
  public String peekAtSoftwareStatement(String compactSerialisation)
      throws SigningVerificationException {
    try {
      SignedJWT inputJwt = SignedJWT.parse(compactSerialisation);
      return inputJwt.getJWTClaimsSet().getStringClaim("software_statement");
    } catch (ParseException e) {
      LOG.error("Unable to peek at software statement inside supplied JWT: {}", e.getMessage(), e);
      throw SigningVerificationException.builder().message(e.getMessage()).build();
    }
  }


}
