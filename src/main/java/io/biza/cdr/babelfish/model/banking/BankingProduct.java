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

import java.net.URI;
import java.time.OffsetDateTime;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.v1.enumerations.BankingProductCategory;
import io.biza.cdr.babelfish.converters.DateTimeStringToOffsetDateTimeConverter;
import io.biza.cdr.babelfish.converters.OffsetDateTimeToDateTimeStringConverter;
import io.biza.cdr.babelfish.converters.UriToUriStringConverter;
import io.biza.cdr.babelfish.support.BabelFishModel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
@Valid
@BabelFishModel(description =  "An Australian Banking Product")
public abstract class BankingProduct {

    @BabelFishModelProperty(
        description =  "A data holder specific unique identifier for this product. This identifier must be unique to a product but does not otherwise need to adhere to ID permanence guidelines.",
        required = true
    )
    @NonNull
    @NotNull
    private String productId;

    @BabelFishModelProperty(
        description =  "The date and time from which this product is effective (ie. is available for origination).  Used to enable the articulation of products to the regime before they are available for customers to originate",
        dataType = "java.lang.String"
    )
    @JsonSerialize(converter = OffsetDateTimeToDateTimeStringConverter.class)
    @JsonDeserialize(converter = DateTimeStringToOffsetDateTimeConverter.class)
    private OffsetDateTime effectiveFrom;

    @BabelFishModelProperty(
        description =  "The date and time at which this product will be retired and will no longer be offered.  Used to enable the managed deprecation of products",
        dataType = "java.lang.String"
            )
    @JsonSerialize(converter = OffsetDateTimeToDateTimeStringConverter.class)
    @JsonDeserialize(converter = DateTimeStringToOffsetDateTimeConverter.class)
    private OffsetDateTime effectiveTo;

    @BabelFishModelProperty(
        description =  "The last date and time that the information for this product was changed (or the creation date for the product if it has never been altered)",
        required = true,
        dataType = "java.lang.String"
    )
    @NonNull
    @NotNull
    @JsonSerialize(converter = OffsetDateTimeToDateTimeStringConverter.class)
    @JsonDeserialize(converter = DateTimeStringToOffsetDateTimeConverter.class)
    private OffsetDateTime lastUpdated;

    @BabelFishModelProperty(
        required = true
    )
    @NonNull
    @NotNull
    BankingProductCategory productCategory;

    @BabelFishModelProperty(
        description =  "The display name of the product",
        required = true
    )
    @NonNull
    @NotNull
    String name;

    @BabelFishModelProperty(
        description =  "A description of the product",
        required = true
    )
    @NonNull
    @NotNull
    String description;

    @BabelFishModelProperty(
        description =  "A label of the brand for the product. Able to be used for filtering. For data holders with single brands this value is still required",
        required = true
    )
    @NonNull
    @NotNull
    String brand;

    @BabelFishModelProperty(
        description =  "An optional display name of the brand"
    )
    String brandName;

    @BabelFishModelProperty(
        description =  "A link to an application web page where this product can be applied for.",
        dataType = "java.lang.String"
    )
    @JsonSerialize(converter = UriToUriStringConverter.class)
    URI applicationUri;

    @BabelFishModelProperty(
        description =  "Indicates whether the product is specifically tailored to a circumstance.  In this case fees and prices are significantly negotiated depending on context. While all products are open to a degree of tailoring this flag indicates that tailoring is expected and thus that the provision of specific fees and rates is not applicable",
        required = true
    )
    @NonNull
    @NotNull
    Boolean isTailored;

    @BabelFishModelProperty
    BankingProductAdditionalInformation additionalInformation;
}
