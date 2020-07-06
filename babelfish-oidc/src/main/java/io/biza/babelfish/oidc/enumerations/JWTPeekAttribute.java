package io.biza.babelfish.oidc.enumerations;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "JWT Claims can peek into", name = "JWTPeekAttribute", enumAsRef = true)
public enum JWTPeekAttribute {
	  ISSUER("issuer"),
	  SOFTWARE_STATEMENT("software_statement"),
	  CLIENT_ID("client_id"), REDIRECT_URI("redirect_uri");
	  
	  String value;
	
	  private JWTPeekAttribute(String value) {
		  this.value = value;
	  }
	  
	  public String value() {
		  return value;
	  }

}
