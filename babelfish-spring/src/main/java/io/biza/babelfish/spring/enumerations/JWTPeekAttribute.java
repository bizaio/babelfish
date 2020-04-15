package io.biza.babelfish.spring.enumerations;

import io.biza.babelfish.cdr.support.LabelValueEnumInterface;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "JWT Claims can peek into", name = "JWTPeekAttribute", enumAsRef = true)
public enum JWTPeekAttribute {
	  ISSUER("issuer"),
	  SOFTWARE_STATEMENT("software_statement"),
	  CLIENT_ID("client_id");
	  
	  String value;
	
	  private JWTPeekAttribute(String value) {
		  this.value = value;
	  }

}
