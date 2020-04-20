/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *******************************************************************************/
package io.biza.babelfish.spring.persistence.specifications;

import javax.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import io.biza.babelfish.oidc.enumerations.JWEEncryptionAlgorithmType;
import io.biza.babelfish.oidc.enumerations.JWKKeyType;
import io.biza.babelfish.oidc.enumerations.JWKPublicKeyUse;
import io.biza.babelfish.oidc.enumerations.JWSSigningAlgorithmType;
import io.biza.babelfish.spring.persistence.model.issuer.IssuerData;
import io.biza.babelfish.spring.persistence.model.issuer.IssuerData_;
import io.biza.babelfish.spring.persistence.model.issuer.IssuerKeyData;
import io.biza.babelfish.spring.persistence.model.issuer.IssuerKeyData_;

public class IssuerKeySpecifications {

	public static Specification<IssuerKeyData> keyUse(JWKPublicKeyUse keyUse) {
		return (root, query, cb) -> {
			return cb.equal(root.get(IssuerKeyData_.keyUse), keyUse);
		};
	}

	public static Specification<IssuerKeyData> enabled(Boolean enabled) {
		return (root, query, cb) -> {
			return cb.equal(root.get(IssuerKeyData_.enabled), enabled);
		};
	}

	public static Specification<IssuerKeyData> keyType(JWKKeyType keyType) {
		return (root, query, cb) -> {
			return cb.equal(root.get(IssuerKeyData_.keyType), keyType);
		};
	}
	
	public static Specification<IssuerKeyData> signingAlgorithm(JWSSigningAlgorithmType algorithm) {
		return (root, query, cb) -> {
			return cb.equal(root.get(IssuerKeyData_.signingAlgorithm), algorithm);
		};
	}
	
	public static Specification<IssuerKeyData> signingAlgorithm(JWEEncryptionAlgorithmType algorithm) {
		return (root, query, cb) -> {
			return cb.equal(root.get(IssuerKeyData_.encryptionAlgorithm), algorithm);
		};
	}

	public static Specification<IssuerKeyData> issuer(String issuer) {
		return (root, query, cb) -> {
			Join<IssuerKeyData, IssuerData> issuerJoin = root.join(IssuerKeyData_.issuer);
			return cb.equal(issuerJoin.get(IssuerData_.issuer), issuer);
		};
	}
}
