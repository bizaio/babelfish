package io.biza.babelfish.spring.service;

import javax.net.ssl.HttpsURLConnection;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import io.biza.babelfish.spring.util.NimbusUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@ConditionalOnProperty(name = "babelfish.tls-verify", havingValue = "false")
public class DisableTLSVerificationService {

  public DisableTLSVerificationService() {
    LOG.warn(
        "WARNING WARNING WARNING WARNING WARNING WARNING WARNING WARNING WARNING WARNING WARNING WARNING");
    LOG.warn(
        "|||||||||||                     OUTBOUND TLS VERIFICATION DISABLED                  |||||||||||");
    LOG.warn(
        "WARNING WARNING WARNING WARNING WARNING WARNING WARNING WARNING WARNING WARNING WARNING WARNING");
    HttpsURLConnection.setDefaultSSLSocketFactory(NimbusUtil.tlsDisableSocketFactory());
  }
}
