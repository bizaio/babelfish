package io.biza.babelfish.oauth2.payloads.rar;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Set;

import javax.validation.Valid;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.common.jackson.EpochToOffsetDateTimeConverter;
import io.biza.babelfish.common.jackson.OffsetDateTimeToEpochConverter;
import io.biza.babelfish.oauth2.payloads.rar.enumerations.CDRSharingStatus;
import io.biza.babelfish.oidc.Constants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Valid
@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Schema(description = "OAuth2 Rich Authorisation Request")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonTypeName(Constants.CDR_SHARING_V1)
public class CDRSharingV1Request implements RichAuthorisationRequest, Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("actions")
	@Schema(description = "Actions")
	Set<String> actions;

	@JsonProperty("sharing_duration")
	@Schema(description = "Sharing Duration Requested")
	@Builder.Default
	Integer sharingDuration = 0;

	@JsonProperty("sharing_expires_at")
	@Schema(description = "Sharing Expires At")
	@JsonSerialize(converter = OffsetDateTimeToEpochConverter.class)
	@JsonDeserialize(converter = EpochToOffsetDateTimeConverter.class)
	OffsetDateTime sharingExpiresAt;

	@JsonProperty("sharing_status")
	@Schema(description = "Sharing Status")
	CDRSharingStatus sharingStatus;

}
