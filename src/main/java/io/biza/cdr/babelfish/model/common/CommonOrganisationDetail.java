package io.biza.cdr.babelfish.model.common;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.support.FormatChecker;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@Builder
@Data
@Valid
@BabelFishModel(description = "Organisation Definition in Detail", parent = CommonOrganisation.class)
public class CommonOrganisationDetail {
	
	@JsonUnwrapped
	@BabelFishModelProperty(
			hidden = true
	)
	@Valid
	CommonOrganisation commonOrganisation;

    @BabelFishModelProperty(
        description = "Must contain at least one address. One and only one address may have the purpose of REGISTERED. Zero or one, and no more than one, record may have the purpose of MAIL. If zero then the REGISTERED address is to be used for mail",
            required = true)
    @NonNull
    @NotNull
    List<CommonPhysicalAddressWithPurpose> physicalAddresses;

    @AssertTrue(
            message = "Physical Addresses must contain one and only one address of REGISTERED purpose and zero or one addresses of MAIL purpose")
    private boolean isPhysicalAddressesCorrect() {
        return FormatChecker.isAddressPopulated(physicalAddresses);
    }

}
