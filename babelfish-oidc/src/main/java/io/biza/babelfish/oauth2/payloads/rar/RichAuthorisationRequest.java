package io.biza.babelfish.oauth2.payloads.rar;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import io.swagger.v3.oas.annotations.media.Schema;
import io.biza.babelfish.oidc.Constants;

@Schema(description = "OAuth2 Rich Authorisation Request")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = As.PROPERTY, property = "type")
@JsonSubTypes({ @JsonSubTypes.Type(value = CDRSharingV1Request.class, name = Constants.CDR_SHARING_V1) })
public interface RichAuthorisationRequest {

}
