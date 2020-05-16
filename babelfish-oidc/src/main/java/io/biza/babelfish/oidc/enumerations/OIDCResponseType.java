package io.biza.babelfish.oidc.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import io.biza.babelfish.common.exceptions.UnsupportedResponseTypeException;
import io.biza.babelfish.oidc.exceptions.oauth2.InvalidRequestException;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "OIDC Response Types", enumAsRef = true)
public enum OIDCResponseType {
	// @formatter:off
	NONE("none"), CODE("code"), ID_TOKEN("id_token"), TOKEN("token");
	// @formatter:on

	String value;

	OIDCResponseType(String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return value;
	}

	@JsonCreator
	public static OIDCResponseType fromValue(String value) throws UnsupportedResponseTypeException {
		for (OIDCResponseType b : OIDCResponseType.values()) {
			if (String.valueOf(b.value).equalsIgnoreCase(value)) {
				return b;
			}
		}

		throw UnsupportedResponseTypeException.builder().build();
	}

}
