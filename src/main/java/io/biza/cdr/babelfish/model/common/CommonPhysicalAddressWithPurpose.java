package io.biza.cdr.babelfish.model.common;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import io.biza.cdr.babelfish.enumerations.AddressPurpose;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
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
@BabelFishModel(description =  "Physical Address with Purpose", parent = CommonPhysicalAddress.class)
public class CommonPhysicalAddressWithPurpose {
	
	@JsonUnwrapped
	@BabelFishModelProperty(
			hidden = true
	)	
	CommonPhysicalAddress physicalAddress;

    @BabelFishModelProperty(
        description =  "Enumeration of values indicating the purpose of the physical address",
        required = true
    )
    @NonNull
    @NotNull
    AddressPurpose purpose;
}
