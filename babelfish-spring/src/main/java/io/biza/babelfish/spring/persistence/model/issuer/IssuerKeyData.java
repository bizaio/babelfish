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
package io.biza.babelfish.spring.persistence.model.issuer;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import io.biza.babelfish.oidc.enumerations.JWEEncryptionAlgorithmType;
import io.biza.babelfish.oidc.enumerations.JWKKeyType;
import io.biza.babelfish.oidc.enumerations.JWKPublicKeyUse;
import io.biza.babelfish.oidc.enumerations.JWSSigningAlgorithmType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Valid
@Table(name = "BABELFISH_ISSUER_KEY")
public class IssuerKeyData {

	@Id
	@Column(name = "ID", insertable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Type(type = "uuid-char")
	UUID id;

	@ManyToOne
	@JoinColumn(name = "BABELFISH_ISSUER_ID", nullable = false, foreignKey = @ForeignKey(name = "BABELFISH_ISSUER_ISSUER_KEY_FK"))
	@ToString.Exclude
	IssuerData issuer;

	@Column(name = "ENABLED")
	@NotNull
	@Builder.Default
	Boolean enabled = true;

	@CreationTimestamp
	@Column(name = "CREATION_TIMESTAMP")
	OffsetDateTime creationTime;

	@Column(name = "KEY_TYPE")
	@Enumerated(EnumType.STRING)
	@NotNull
	JWKKeyType keyType;

	@Column(name = "KEY_USE")
	@Enumerated(EnumType.STRING)
	@NotNull
	JWKPublicKeyUse keyUse;
	
	@Column(name = "SIGNING_ALGORITHM")
	@Enumerated(EnumType.STRING)	
	JWSSigningAlgorithmType signingAlgorithm;
	
	@Column(name = "ENCRYPTION_ALGORITHM")
	@Enumerated(EnumType.STRING)	
	JWEEncryptionAlgorithmType encryptionAlgorithm;
	
	@Column(name = "KEY_CONTENT")
	@Lob
	String keyContent;

	@PrePersist
	public void prePersist() {
		if (issuer() != null) {
			Set<IssuerKeyData> keys = new HashSet<IssuerKeyData>();
			keys.addAll(issuer.keys());
			keys.add(this);
			issuer.keys(keys);
		}
	}

}
