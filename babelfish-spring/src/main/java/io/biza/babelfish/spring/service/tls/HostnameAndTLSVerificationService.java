package io.biza.babelfish.spring.service.tls;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.biza.babelfish.spring.service.config.properties.BabelfishProperties;
import io.biza.babelfish.util.NimbusUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HostnameAndTLSVerificationService {

	@Autowired
	BabelfishProperties properties;

	public HostnameAndTLSVerificationService(@Autowired BabelfishProperties properties) {

		if (properties.insecureOutboundTls()) {

			LOG.warn("WARNING WARNING WARNING WARNING WARNING WARNING WARNING WARNING WARNING WARNING WARNING WARNING");
			LOG.warn("|||||||||||                     OUTBOUND TLS VERIFICATION DISABLED                  |||||||||||");
			LOG.warn("WARNING WARNING WARNING WARNING WARNING WARNING WARNING WARNING WARNING WARNING WARNING WARNING");
			HttpsURLConnection.setDefaultSSLSocketFactory(NimbusUtil.tlsDisableSocketFactory());

		} else {
			LOG.info("Normal TLS Verification Enabled");
		}

		if (properties.insecureHostnameVerification()) {
			LOG.warn("WARNING WARNING WARNING WARNING WARNING WARNING WARNING WARNING WARNING WARNING WARNING WARNING");
			LOG.warn("|||||||||||               HOSTNAME VERIFICATION FUNCTIONANLITY DISABLED             |||||||||||");
			LOG.warn("WARNING WARNING WARNING WARNING WARNING WARNING WARNING WARNING WARNING WARNING WARNING WARNING");
			HttpsURLConnection.setDefaultHostnameVerifier(NimbusUtil.anyHostnameVerifier());
		} else {
			LOG.info("Normal Hostname Verification Enabled");
		}
	}
}
