package io.biza.babelfish.spring.service.tls;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.InvalidParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.Security;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.CertIOException;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.DefaultDigestAlgorithmIdentifierFinder;
import org.bouncycastle.operator.DefaultSignatureAlgorithmIdentifierFinder;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.bc.BcRSAContentSignerBuilder;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pqc.crypto.util.PrivateKeyFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import io.biza.babelfish.cdr.exceptions.EncryptionOperationException;
import io.biza.babelfish.cdr.exceptions.NotInitialisedException;
import io.biza.babelfish.spring.Constants;
import io.biza.babelfish.spring.interfaces.CertificateService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@ConditionalOnProperty(name = "babelfish.service.CertificateService",
    havingValue = "LocalKeyStoreCertificateService", matchIfMissing = true)
public class LocalCertificateService implements CertificateService {

  @Value("${babelfish.ca.key-store-password:solongandthanksforallthefish}")
  String KEYSTORE_PASSWORD;

  @Value("${babelfish.ca.key-store:babelfish-ca.jks}")
  String KEYSTORE_PATH;

  @Value("${babelfish.ca.key-id:babelfish-ca-key}")
  String KEYSTORE_CA_KEY_ID;

  @Value("${babelfish.ca.cert-id:babelfish-ca}")
  String KEYSTORE_CA_CERT_ID;

  @Value("${babelfish.ca.show_private_keys:false}")
  Boolean CA_SHOW_KEYS;

  @Override
  public String signRequest(String inputCsr)
      throws NotInitialisedException, EncryptionOperationException {

    KeyPair keyPair = keyPair();

    try (final ByteArrayInputStream byteStream = new ByteArrayInputStream(inputCsr.getBytes());
        final InputStreamReader inputStream =
            new InputStreamReader(byteStream, StandardCharsets.UTF_8);
        final PEMParser pem = new PEMParser(inputStream)) {
      PKCS10CertificationRequest csr = (PKCS10CertificationRequest) pem.readObject();

      Calendar expiryTime = Calendar.getInstance();
      expiryTime.setTime(Calendar.getInstance().getTime());
      expiryTime.add(Calendar.YEAR, Constants.CERT_VALIDITY_YEARS);

      X509v3CertificateBuilder certificateBuilder =
          new X509v3CertificateBuilder(new X500Name(Constants.CA_DN),
              BigInteger.valueOf(System.currentTimeMillis()), Calendar.getInstance().getTime(),
              expiryTime.getTime(), csr.getSubject(), SubjectPublicKeyInfo
                  .getInstance(ASN1Sequence.getInstance(keyPair.getPublic().getEncoded())));

      ContentSigner sigGen = new BcRSAContentSignerBuilder(
          new DefaultSignatureAlgorithmIdentifierFinder().find(Constants.CA_SIGNING_ALGORITHM),
          new DefaultDigestAlgorithmIdentifierFinder().find(Constants.CA_SIGNING_ALGORITHM))
              .build(PrivateKeyFactory.createKey(keyPair.getPrivate().getEncoded()));

      X509CertificateHolder holder = certificateBuilder.build(sigGen);
      CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509", "BC");
      X509Certificate certificate = (X509Certificate) certificateFactory
          .generateCertificate(new ByteArrayInputStream(holder.toASN1Structure().getEncoded()));

      String publicKeyString =
          Base64.getEncoder().encodeToString(certificate.getPublicKey().getEncoded());
      StringBuilder encodedChain = new StringBuilder();
      encodedChain.append("-----BEGIN CERTIFICATE-----\n");
      encodedChain.append(publicKeyString.replaceAll(".{80}(?=.)", "$0\n"));
      encodedChain.append("\n-----END CERTIFICATE-----\n");

      return encodedChain.toString();

    } catch (IOException e) {
      throw EncryptionOperationException.builder().message(e.getMessage()).build();
    } catch (OperatorCreationException e) {
      throw EncryptionOperationException.builder().message(e.getMessage()).build();
    } catch (CertificateException e) {
      throw EncryptionOperationException.builder().message(e.getMessage()).build();
    } catch (NoSuchProviderException e) {
      throw EncryptionOperationException.builder().message(e.getMessage()).build();
    }
  }

  private KeyPair keyPair() throws EncryptionOperationException {
    try {
      KeyStore keystore = getKeyStore();
      return new KeyPair(keystore.getCertificate(KEYSTORE_CA_CERT_ID).getPublicKey(),
          (PrivateKey) keystore.getKey(KEYSTORE_CA_KEY_ID, KEYSTORE_PASSWORD.toCharArray()));
    } catch (KeyStoreException e) {
      throw EncryptionOperationException.builder().message(e.getMessage()).build();
    } catch (UnrecoverableKeyException e) {
      throw EncryptionOperationException.builder().message(e.getMessage()).build();
    } catch (NoSuchAlgorithmException e) {
      throw EncryptionOperationException.builder().message(e.getMessage()).build();
    }
  }



  @Override
  public String getCaCertificate() throws NotInitialisedException, EncryptionOperationException {

    if (!isInitialised()) {
      throw NotInitialisedException.builder().build();
    }
    return new String(Base64.getEncoder().encode(keyPair().getPublic().getEncoded()))
        .replaceAll(".{80}(?=.)", "$0\n");


  }

  private KeyStore getKeyStore() throws EncryptionOperationException {
    if (new File(KEYSTORE_PATH).exists()) {
      try {
        LOG.info("Keystore {} appears to already exist, moving on", KEYSTORE_PATH);
        FileInputStream fos = new FileInputStream(KEYSTORE_PATH);
        KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
        keystore.load(fos, KEYSTORE_PASSWORD.toCharArray());
        return keystore;
      } catch (FileNotFoundException e) {
        return null;
      } catch (KeyStoreException e) {
        LOG.error("Key store was found but opening it failed with: {}", e);
        throw EncryptionOperationException.builder().message(e.getMessage()).build();
      } catch (NoSuchAlgorithmException e) {
        LOG.error("Invalid Algorithm specified: {}", e.getMessage());
        throw EncryptionOperationException.builder().message(e.getMessage()).build();
      } catch (CertificateException e) {
        LOG.error("Errors encountered with one or more certificates in the key store: {}",
            e.getMessage());
        throw EncryptionOperationException.builder().message(e.getMessage()).build();
      } catch (IOException e) {
        LOG.error("Key store was found but opening it failed with: {}", e.getCause());
        throw EncryptionOperationException.builder().message(e.getMessage()).build();
      }
    } else {
      return null;
    }
  }

  @Override
  public boolean isInitialised() throws EncryptionOperationException {

    KeyStore keystore = getKeyStore();
    try {
      return keystore.containsAlias(KEYSTORE_CA_KEY_ID)
          && keystore.containsAlias(KEYSTORE_CA_CERT_ID);

    } catch (KeyStoreException e) {
      LOG.error("Key store was found but opening it failed with: {}", e);
      throw EncryptionOperationException.builder().message(e.getMessage()).build();
    }
  }

  @Override
  public void initCa() throws EncryptionOperationException, NotInitialisedException {

    /**
     * Do nothing if initialised
     */
    if (isInitialised()) {
      LOG.warn("Attempt to initialise certificate authority that is already initialised");
      return;
    }

    try {
      /**
       * Private key generation
       */
      KeyPairGenerator keyGen = KeyPairGenerator.getInstance(Constants.CA_ALGORITHM);
      keyGen.initialize(Constants.CA_KEY_SIZE);
      KeyPair keyPair = keyGen.generateKeyPair();

      /**
       * Bouncycastle Registration
       */
      Provider bcProvider = new BouncyCastleProvider();
      Security.addProvider(bcProvider);

      /**
       * Certificate Start time
       */
      long timeNow = System.currentTimeMillis();
      Date startDate = new Date(timeNow);

      /**
       * Expiration time
       */
      Calendar expiryTime = Calendar.getInstance();
      expiryTime.setTime(startDate);
      expiryTime.add(Calendar.YEAR, Constants.CA_VALIDITY_YEARS);

      SubjectPublicKeyInfo subjectPublicKeyInfo =
          SubjectPublicKeyInfo.getInstance(keyPair.getPublic().getEncoded());

      /**
       * Set it all up and declare us a CA
       */

      X509v3CertificateBuilder certificateBuilder = new X509v3CertificateBuilder(
          new X500Name(Constants.CA_DN), new BigInteger(Long.toString(timeNow)), startDate,
          expiryTime.getTime(), new X500Name(Constants.CA_DN), subjectPublicKeyInfo);
      certificateBuilder.addExtension(new ASN1ObjectIdentifier("2.5.29.19"), true,
          new BasicConstraints(true));
      ContentSigner contentSigner = new JcaContentSignerBuilder(Constants.CA_SIGNING_ALGORITHM)
          .setProvider(bcProvider).build(keyPair.getPrivate());

      /**
       * Show time baby!
       */
      X509CertificateHolder certificateHolder = certificateBuilder.build(contentSigner);
      X509Certificate selfSignedCert =
          new JcaX509CertificateConverter().getCertificate(certificateHolder);

      X509Certificate[] certificateChain = new X509Certificate[1];
      certificateChain[0] = (X509Certificate) CertificateFactory.getInstance("X.509")
          .generateCertificate(new ByteArrayInputStream(selfSignedCert.getEncoded()));

      /**
       * Save CA to keystore
       */
      KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
      char[] pwdArray = KEYSTORE_PASSWORD.toCharArray();
      ks.load(null, pwdArray);
      FileOutputStream fos = new FileOutputStream(KEYSTORE_PATH);
      ks.setKeyEntry(KEYSTORE_CA_KEY_ID, keyPair.getPrivate(), KEYSTORE_PASSWORD.toCharArray(),
          certificateChain);
      ks.setCertificateEntry(KEYSTORE_CA_CERT_ID, CertificateFactory.getInstance("X.509")
          .generateCertificate(new ByteArrayInputStream(selfSignedCert.getEncoded())));
      ks.store(fos, pwdArray);
      fos.close();

      LOG.warn("Certificate authority initialisation has been completed!");
      LOG.debug("PEM of Certificate Authority is output as follows");
      if (CA_SHOW_KEYS) {
        LOG.debug("\n\n-----BEGIN RSA PRIVATE KEY-----\n{}\n-----END RSA PRIVATE KEY-----\n",
            new String(Base64.getEncoder().encode(
                ks.getKey(KEYSTORE_CA_KEY_ID, KEYSTORE_PASSWORD.toCharArray()).getEncoded()))
                    .replaceAll(".{80}(?=.)", "$0\n"));
      }

      LOG.debug("\n\n-----BEGIN CERTIFICATE-----\n{}\n-----END CERTIFICATE-----\n",
          new String(Base64.getEncoder()
              .encode(ks.getCertificate(KEYSTORE_CA_CERT_ID).getPublicKey().getEncoded()))
                  .replaceAll(".{80}(?=.)", "$0\n"));

    } catch (NoSuchAlgorithmException e) {
      LOG.error("Invalid algorithm of {} specified, cannot initialise certificate authority!",
          Constants.CA_ALGORITHM);
      throw EncryptionOperationException.builder().message(e.getMessage()).build();
    } catch (InvalidParameterException e) {
      LOG.error("Invalid key size of {} specified, cannot initialise certificate authority!",
          Constants.CA_KEY_SIZE);
      throw EncryptionOperationException.builder().message(e.getMessage()).build();
    } catch (OperatorCreationException e) {
      LOG.error("Operator error detected, spewing out stack trace");
      e.printStackTrace();
      throw EncryptionOperationException.builder().message(e.getMessage()).build();
    } catch (CertIOException e) {
      LOG.error("Encountered certificate io error, cannot initialise certificate authority: {}",
          e.getMessage());
      throw EncryptionOperationException.builder().message(e.getMessage()).build();
    } catch (CertificateException e) {
      LOG.error(
          "Encountered certificate conversion exception, cannot initialise certificate authority: {}",
          e.getMessage());
      throw EncryptionOperationException.builder().message(e.getMessage()).build();
    } catch (UnrecoverableKeyException e) {
      LOG.error(
          "Encountered UnrecoverableKeyException, cannot initialise certificate authority: {}",
          e.getMessage());
      e.printStackTrace();
      throw EncryptionOperationException.builder().message(e.getMessage()).build();
    } catch (KeyStoreException e) {
      LOG.error("Encountered KeyStoreException, cannot initialise certificate authority: {}",
          e.getMessage());
      e.printStackTrace();
      throw EncryptionOperationException.builder().message(e.getMessage()).build();
    } catch (FileNotFoundException e) {
      LOG.error("Encountered FileNotFoundException, cannot initialise certificate authority: {}",
          e.getMessage());
      e.printStackTrace();
      throw NotInitialisedException.builder().build();
    } catch (IOException e) {
      LOG.error("Encountered IOException, cannot initialise certificate authority: {}",
          e.getMessage());
      e.printStackTrace();
      throw NotInitialisedException.builder().build();
    }

  }

}
