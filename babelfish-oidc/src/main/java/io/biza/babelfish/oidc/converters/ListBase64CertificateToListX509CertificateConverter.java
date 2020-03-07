package io.biza.babelfish.oidc.converters;

import java.io.ByteArrayInputStream;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import com.fasterxml.jackson.databind.util.StdConverter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ListBase64CertificateToListX509CertificateConverter
    extends StdConverter<List<String>, List<X509Certificate>> {

  @Override
  public List<X509Certificate> convert(List<String> value) {
    return value != null ? value.stream().map(certificate -> {
      try {
        return (X509Certificate) CertificateFactory.getInstance("X.509")
            .generateCertificate(new ByteArrayInputStream(Base64.getDecoder().decode(certificate)));
      } catch (CertificateException e) {
        LOG.error(
            "Encountered error while attempting to deserialise String list to X509Certificates, proceeding without adding to list but results may vary!",
            e);
        return null;
      }
    }).filter(x -> x != null).collect(Collectors.toList()) : List.of();
  }
}
