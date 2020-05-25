package io.biza.babelfish.oidc.exceptions.openid;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
