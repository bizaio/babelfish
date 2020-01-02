package io.biza.cdr.babelfish.model.common;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import io.biza.cdr.babelfish.enumerations.CommonEmailAddressPurpose;
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
@BabelFishModel(description =  "Email Address Definition")
public class CommonEmailAddress {

    @BabelFishModelProperty(
        description =  "May be true for one and only one email record in the collection. Denotes the default email address"
    )
    Boolean isPreferred;

    @BabelFishModelProperty(
        description =  "The purpose for the email, as specified by the customer (Enumeration)",
        required = true
    )
    @NonNull
    @NotNull
    CommonEmailAddressPurpose purpose;

    @BabelFishModelProperty(
        description =  "A correctly formatted email address, as defined by the addr_spec format in [RFC 5322](https://www.ietf.org/rfc/rfc5322.txt)",
        required = true
    )
    @NotNull
    @NonNull
    @Email
    String address;
}
