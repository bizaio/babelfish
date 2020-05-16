package io.biza.babelfish.oidc.exceptions.openid;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.biza.babelfish.common.jackson.EpochToOffsetDateTimeConverter;
import io.biza.babelfish.common.jackson.ListStringToSpaceListConverter;
import io.biza.babelfish.common.jackson.OffsetDateTimeToEpochConverter;
import io.biza.babelfish.common.jackson.SpaceListToListStringConverter;
import io.biza.babelfish.common.jackson.UriStringToUriConverter;
import io.biza.babelfish.common.jackson.UriToUriStringConverter;
import io.biza.babelfish.oidc.enumerations.JWEEncryptionAlgorithmType;
import io.biza.babelfish.oidc.enumerations.JWEEncryptionEncodingType;
import io.biza.babelfish.oidc.enumerations.JWSSigningAlgorithmType;
import io.biza.babelfish.oidc.enumerations.OIDCApplicationType;
import io.biza.babelfish.oidc.enumerations.OIDCAuthMethod;
import io.biza.babelfish.oidc.enumerations.OIDCGrantType;
import io.biza.babelfish.oidc.enumerations.OIDCResponseType;
import io.biza.babelfish.oidc.enumerations.RegistrationErrorCode;
import io.biza.babelfish.oidc.exceptions.oauth2.registration.InvalidClientMetadataException;
import io.biza.babelfish.oidc.exceptions.oauth2.registration.InvalidRedirectUriException;
import io.biza.babelfish.oidc.exceptions.oauth2.registration.InvalidSoftwareStatementException;
import io.biza.babelfish.oidc.exceptions.oauth2.registration.UnapprovedSoftwareStatementException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Valid
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Dynamic Registration Error")
public class DynamicRegistrationErrorException extends Exception {
	private static final long serialVersionUID = 1L;

	@JsonProperty("error")
	@NotNull
	RegistrationErrorCode errorCode;

	@JsonProperty("error_description")
	String errorDescription;

	@JsonIgnore
	public Exception getErrorCause() {
		switch (errorCode) {
		case INVALID_CLIENT_METADATA:
			return InvalidClientMetadataException.builder().build();
		case INVALID_REDIRECT_URI:
			return InvalidRedirectUriException.builder().build();
		case INVALID_SOFTWARE_STATEMENT:
			return InvalidSoftwareStatementException.builder().build();
		case UNAPPROVED_SOFTWARE_STATEMENT:
			return UnapprovedSoftwareStatementException.builder().build();
		default:
			return null;
		}
	}

}
