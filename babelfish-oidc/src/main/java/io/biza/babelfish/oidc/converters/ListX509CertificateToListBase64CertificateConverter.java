package io.biza.babelfish.oidc.converters;

import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import com.fasterxml.jackson.databind.util.StdConverter;
import lombok.extern.slf4j.Slf4j;

public class ListX509CertificateToListBase64CertificateConverter
    extends StdConverter<List<X509Certificate>, List<String>> {

  @Override
  public List<String> convert(List<X509Certificate> value) {
    return value != null ? value.stream().map(
        certificate -> Base64.getEncoder().encodeToString(certificate.getPublicKey().getEncoded()))
        .collect(Collectors.toList()) : List.of();
  }
}
