package io.biza.babelfish.spring.util;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKMatcher;
import com.nimbusds.jose.jwk.JWKSelector;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.KeyType;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import io.biza.babelfish.spring.exceptions.SigningVerificationException;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONStyle;

@Slf4j
public class KeyUtil {

	JWKSet jwkSet;

	/**
	 * Accepts a String containing the JSON representation of a JWK Set
	 * 
	 * @param jwks containing the JWKSet to load
	 */
	public KeyUtil(String jwks) {
		try {
			jwkSet = JWKSet.load(new ByteArrayInputStream(jwks.getBytes(Charset.forName("UTF-8"))));
		} catch (IOException | ParseException e) {
			LOG.error("Unable to parse supplied jwks", e);
		}
	}

	/**
	 * Create an empty KeyUtil
	 */
	private KeyUtil() {
		jwkSet = new JWKSet();
	}

	/**
	 * Given a key size and list of key uses produce a JWKSet for future use and
	 * return it as a KeyUtil
	 * 
	 * @param keySize Key Size to use, defaults to 2048 if not specified
	 * @param keyUses A list of key uses to generate for
	 * @return A KeyUtil object with the initialised keys
	 * @throws JOSEException if unable to initialise
	 */
	public static KeyUtil generate(int keySize, KeyUse... keyUses) throws JOSEException {
		KeyUtil util = new KeyUtil();
		for (KeyUse keyUse : keyUses) {
			util.addPrivateKey(keySize, keyUse);
		}
		return util;
	}

	/**
	 * Add a Private Key to the existing JWKSet
	 * 
	 * @param keySize containing the key size to use
	 * @param keyUse  containing the key use to assign
	 * @throws JOSEException if initialisation failed
	 */
	public void addPrivateKey(int keySize, KeyUse keyUse) throws JOSEException {
		jwkSet.getKeys().add(new RSAKeyGenerator(keySize).keyIDFromThumbprint(true).keyUse(keyUse).generate());
	}

	/**
	 * Retrieve the public jwks
	 * 
	 * @return JSON containing the public jwks
	 */
	public String getPublicJWKS() {
		return jwkSet.toJSONObject(true).toJSONString(JSONStyle.NO_COMPRESS);
	}
	

	/**
	 * Sign a set of claims using a supplied algorithm
	 * 
	 * @param algorithm to sign the claims with
	 * @param claimsSet containing the set of claims to sign
	 * @return A signed JWT with compact serialisation
	 * @throws JOSEException if signing failed
	 */
	public String sign(JWSAlgorithm algorithm, JWTClaimsSet claimsSet) throws JOSEException {
		List<JWK> matches = new JWKSelector(
				new JWKMatcher.Builder().privateOnly(true).keyType(KeyType.RSA).keyUse(KeyUse.SIGNATURE).build())
						.select(jwkSet);

		JWK rsaJWK = matches.get(0);
		JWSSigner signer = new RSASSASigner(rsaJWK.toRSAKey());
		SignedJWT signedJWT = new SignedJWT(new JWSHeader.Builder(algorithm).keyID(rsaJWK.getKeyID()).build(),
				claimsSet);
		signedJWT.sign(signer);
		return signedJWT.serialize();
	}

	/**
	 * Parse a compact serialisation and return it
	 * @param compactSerialisation containing a JWT serialisation
	 * @return SignedJWT content
	 * @throws ParseException if parsing failed
	 */
	  public static SignedJWT peekAtClientId(String compactSerialisation) throws ParseException {
	    return SignedJWT.parse(compactSerialisation);
	  }


	/**
	 * public KeyUtil(File jwks) { try { jwkSet = JWKSet.load(jwks); } catch
	 * (IOException | ParseException e) { LOG.error("Unable to parse supplied jwks
	 * from {}", jwks.getAbsolutePath(), e); } }
	 * 
	 * public void writeToFile(String filename) throws IOException { File output =
	 * new File(filename); Writer os = null; try { os = new BufferedWriter(new
	 * FileWriter(output)); os.write(jwkSet.toJSONObject(false).toJSONString()); }
	 * finally { os.close(); } }
	 */

}
