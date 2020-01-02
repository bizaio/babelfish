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
package io.biza.cdr.babelfish.model.banking;

import java.time.LocalDate;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.cdr.babelfish.enumerations.BankingAccountStatus;
import io.biza.cdr.babelfish.enumerations.BankingProductCategory;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.converters.LocalDateToStringConverter;
import io.biza.cdr.babelfish.converters.StringToLocalDateConverter;
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
@BabelFishModel(description = "An Australian Bank Account")
public class BankingAccount {

    @BabelFishModelProperty(description = "A unique ID of the account adhering to the standards for ID permanence", required = true)
    @NonNull
    @NotNull
    String accountId;

    @BabelFishModelProperty(description = "Date that the account was created (if known)", dataType = "java.lang.String")
    @JsonSerialize(converter = LocalDateToStringConverter.class)
    @JsonDeserialize(converter = StringToLocalDateConverter.class)
    private LocalDate creationDate;

    @BabelFishModelProperty(
            description = "The display name of the account as defined by the bank. This should not incorporate account numbers or PANs. If it does the values should be masked according to the rules of the MaskedAccountString common type.",
            required = true)
    @NonNull
    @NotNull
    String displayName;

    @BabelFishModelProperty(description = "A customer supplied nick name for the account")
    String nickname;

    @BabelFishModelProperty(description = "Open or closed status for the account. If not present then OPEN is assumed")
    @Builder.Default
    BankingAccountStatus openStatus = BankingAccountStatus.OPEN;

    @BabelFishModelProperty(
            description = "Flag indicating that the customer associated with the authorisation is an owner of the account. Does not indicate sole ownership, however. If not present then 'true' is assumed")
    @Builder.Default
    Boolean isOwned = true;

    @BabelFishModelProperty(
            description = "A masked version of the account. Whether BSB/Account Number, Credit Card PAN or another number",
            required = true)
    @NotNull
    @NonNull
    String maskedNumber;

    @BabelFishModelProperty(
    		description = "The category to which a product or account belongs.",
        required = true
    )
    @NotNull
    @NonNull
    BankingProductCategory productCategory;

    @BabelFishModelProperty(
            description = "The unique identifier of the account as defined by the account holder (akin to model number for the account)",
            required = true)
    @NotNull
    @NonNull
    String productName;
}
