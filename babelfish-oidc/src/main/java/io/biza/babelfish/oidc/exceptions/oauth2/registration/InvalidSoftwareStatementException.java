package io.biza.babelfish.oidc.exceptions.oauth2.registration;

import java.net.URI;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class InvalidSoftwareStatementException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private String errorDescription;
	private URI errorUri;
}