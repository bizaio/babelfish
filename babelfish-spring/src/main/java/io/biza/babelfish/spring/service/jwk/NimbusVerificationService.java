package io.biza.babelfish.spring.service.jwk;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import io.biza.babelfish.interfaces.VerificationService;

@Service
@ConditionalOnProperty(name = "babelfish.service.VerificationService", havingValue = "NimbusVerificationService", matchIfMissing = true)
public class NimbusVerificationService implements VerificationService {}
