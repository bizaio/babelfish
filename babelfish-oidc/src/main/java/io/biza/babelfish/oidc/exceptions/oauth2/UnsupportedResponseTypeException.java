package io.biza.babelfish.oidc.exceptions.oauth2;

import java.net.URI;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UnsupportedResponseTypeException extends Exception {
	private static final long serialVersionUID = 1L;
	private String errorDescription;
	private URI errorUri;
}
