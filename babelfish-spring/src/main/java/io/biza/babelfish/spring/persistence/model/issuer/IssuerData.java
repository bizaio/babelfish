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

import java.util.Set;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Type;
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
@Table(name = "BABELFISH_ISSUER", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "issuer" }, name = "babelfish_issuer_issuer_uk") })
public class IssuerData {

	@Id
	@Column(name = "ID", insertable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Type(type = "uuid-char")
	UUID id;

	@Column(name = "ISSUER")
	@NotNull
	String issuer;

	@OneToMany(mappedBy = "issuer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Builder.Default
	Set<IssuerKeyData> keys = Set.of();

	@PrePersist
	public void prePersist() {
		if (keys() != null) {
			for (IssuerKeyData one : keys) {
				one.issuer(this);
			}
		}
	}
}
