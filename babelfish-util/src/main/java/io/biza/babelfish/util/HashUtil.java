package io.biza.babelfish.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.nimbusds.jose.util.Base64URL;

import io.biza.babelfish.common.exceptions.SigningVerificationException;
import io.biza.babelfish.oidc.Messages;
import io.biza.babelfish.oidc.enumerations.JWSSigningAlgorithmType;

public class HashUtil {

	public static String oidcHash(String state, JWSSigningAlgorithmType algorithm)
			throws SigningVerificationException {
		// This is pulled straight out of the OpenID Conformance Suite CalculateSHash
		// Thanks to Joseph Heenan who I think is the original author.
		MessageDigest digester;

		try {
			Matcher matcher = Pattern.compile("^(HS|RS|ES|PS)(256|384|512)$").matcher(algorithm.toString());
			if (!matcher.matches()) {
				throw SigningVerificationException.builder().errorDescription(Messages.UNABLE_TO_IDENTIFY_MESSAGE_DIGESTER)
						.build();
			}

			String digestAlgorithm = "SHA-" + matcher.group(2);
			digester = MessageDigest.getInstance(digestAlgorithm);

		} catch (NoSuchAlgorithmException e) {
			throw SigningVerificationException.builder()
					.errorDescription(MessageUtil.format(Messages.UNSUPPORTED_DIGEST_FOR_ALGORITHM, algorithm.toString()))
					.build();
		}

		byte[] stateDigest = digester.digest(state.getBytes(StandardCharsets.US_ASCII));

		byte[] halfDigest = new byte[stateDigest.length / 2];
		System.arraycopy(stateDigest, 0, halfDigest, 0, halfDigest.length);

		return Base64URL.encode(halfDigest).toString();

	}
}
