package io.biza.babelfish.oidc.payloads;

import java.net.URI;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.biza.babelfish.common.jackson.EpochToOffsetDateTimeConverter;
import io.biza.babelfish.common.jackson.OffsetDateTimeToEpochConverter;
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
@Schema(description = "OpenID User Info Response")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class OIDCStandardClaims {

	@JsonProperty("sub")
	String sub;

	@JsonProperty("name")
	String name;

	@JsonProperty("given_name")
	String givenName;

	@JsonProperty("family_name")
	String familyName;

	@JsonProperty("middle_name")
	String middleName;

	@JsonProperty("nickname")
	String nickname;

	@JsonProperty("preferred_username")
	String preferredUsername;

	@JsonProperty("profile")
	String profile;

	@JsonProperty("picture")
	String picture;

	@JsonProperty("website")
	URI website;

	@JsonProperty("email")
	String email;

	@JsonProperty("email_verified")
	Boolean emailVerified;

	@JsonProperty("gender")
	String gender;

	@JsonProperty("birthdate")
	String birthdate;

	@JsonProperty("zoneinfo")
	String zoneInfo;
	
	@JsonProperty("locale")
	String locale;
	
	@JsonProperty("phone_number")
	String phoneNumber;
	
	@JsonProperty("phone_number_verified")
	Boolean phoneNumberVerified;
	
	@JsonProperty("address")
	OIDCAddressClaim address;

	@JsonProperty("updated_at")
	@JsonSerialize(converter = EpochToOffsetDateTimeConverter.class)
	@JsonDeserialize(converter = OffsetDateTimeToEpochConverter.class)
	OffsetDateTime updatedAt;

}
