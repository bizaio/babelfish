package io.biza.babelfish.util;

import java.io.IOException;
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
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMParser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class X509Util {
  /**
   * Generate base64 url encoded digests from a set of X509 Certificates
   * 
   * @param certificates containing a list of X509Certificate objects
   * @param digestName what to digest the thumbprints with
   * @return
   * @return List<String> containing a list of Base64 URL Encoded Certificate Thumbprints
   */
  public static List<String> makeUriDigests(URI certificateUri, String digestName) {
    List<X509Certificate> x5uCertificates = new ArrayList<X509Certificate>();
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder().uri(certificateUri).build();
    try {
      HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
      Security.addProvider(new BouncyCastleProvider());
      PEMParser pemParser = new PEMParser(new StringReader(response.body()));
      Object object;
      while ((object = pemParser.readObject()) != null) {
        if (object instanceof X509CertificateHolder) {
          try {
            X509Certificate x509Cert = new JcaX509CertificateConverter()
                .getCertificate((X509CertificateHolder) object);
            x5uCertificates.add(x509Cert);
          } catch (CertificateException e) {
            LOG.warn(
                "Encountered certificate exception while parsing a received PEM, moving on but results may vary!",
                e);
          }
        }
      }
      pemParser.close();
    } catch (IOException | InterruptedException e) {
      LOG.warn(
          "Unable to retrieve keychain from x5u specified address of {}, results from here may vary!",
          certificateUri.toString(), e);
      return null;
    }


    return makeDigests(x5uCertificates, digestName);
  }


  /**
   * Generate base64 url encoded digests from a set of X509 Certificates
   * 
   * @param certificates containing a list of X509Certificate objects
   * @param digestName what to digest the thumbprints with
   * @return List<String> containing a list of Base64 URL Encoded Certificate Thumbprints
   */
  public static List<String> makeDigests(List<X509Certificate> certificates, String digestName) {
    return certificates.stream().map(certificate -> {
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
}
