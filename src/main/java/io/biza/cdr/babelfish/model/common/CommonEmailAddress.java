/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *******************************************************************************/
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
