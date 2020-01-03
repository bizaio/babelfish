/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *******************************************************************************/
package io.biza.cdr.babelfish.model.banking;

import java.net.URI;
import java.time.OffsetDateTime;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.v1.enumerations.BankingProductCategory;
import io.biza.cdr.babelfish.converters.DateTimeStringToOffsetDateTimeConverter;
import io.biza.cdr.babelfish.converters.OffsetDateTimeToDateTimeStringConverter;
import io.biza.cdr.babelfish.converters.UriStringToUriConverter;
import io.biza.cdr.babelfish.converters.UriToUriStringConverter;
import io.biza.cdr.babelfish.support.BabelFishModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Valid
@BabelFishModel(description = "An Australian Banking Product")
public interface BankingProduct {

  @BabelFishModelProperty(
      description = "A data holder specific unique identifier for this product. This identifier must be unique to a product but does not otherwise need to adhere to ID permanence guidelines.",
      required = true)
  @JsonGetter("productId")
  public String getProductId();

  @JsonSetter("productId")
  public void setProductId(@NotNull String productId);

  public default BankingProduct productId(@NotNull String productId) {
    setProductId(productId);
    return this;
  }

  @BabelFishModelProperty(
      description = "The date and time from which this product is effective (ie. is available for origination).  Used to enable the articulation of products to the regime before they are available for customers to originate",
      dataType = "java.lang.String")
  @JsonSerialize(converter = OffsetDateTimeToDateTimeStringConverter.class)
  @JsonGetter("effectiveFrom")
  public OffsetDateTime getEffectiveFrom();

  @JsonSetter("effectiveFrom")
  @JsonDeserialize(converter = DateTimeStringToOffsetDateTimeConverter.class)
  public void setEffectiveFrom(OffsetDateTime effectiveFrom);

  public default BankingProduct effectiveFrom(OffsetDateTime effectiveFrom) {
    setEffectiveFrom(effectiveFrom);
    return this;
  }

  @BabelFishModelProperty(
      description = "The date and time at which this product will be retired and will no longer be offered.  Used to enable the managed deprecation of products",
      dataType = "java.lang.String")
  @JsonSerialize(converter = OffsetDateTimeToDateTimeStringConverter.class)
  @JsonGetter("effectiveTo")
  public OffsetDateTime getEffectiveTo();

  @JsonSetter("effectiveTo")
  @JsonDeserialize(converter = DateTimeStringToOffsetDateTimeConverter.class)
  public void setEffectiveTo(OffsetDateTime effectiveTo);

  public default BankingProduct effectiveTo(OffsetDateTime effectiveTo) {
    setEffectiveTo(effectiveTo);
    return this;
  }

  @BabelFishModelProperty(
      description = "The last date and time that the information for this product was changed (or the creation date for the product if it has never been altered)",
      required = true, dataType = "java.lang.String")
  @JsonSerialize(converter = OffsetDateTimeToDateTimeStringConverter.class)
  @JsonGetter("lastUpdated")
  public OffsetDateTime getLastUpdated();

  @JsonSetter("lastUpdated")
  @JsonDeserialize(converter = DateTimeStringToOffsetDateTimeConverter.class)
  public void setLastUpdated(OffsetDateTime lastUpdated);

  public default BankingProduct lastUpdated(@NotNull OffsetDateTime lastUpdated) {
    setLastUpdated(lastUpdated);
    return this;
  }

  @BabelFishModelProperty(required = true)
  @JsonGetter("productCategory")
  public BankingProductCategory getProductCategory();

  @JsonSetter("productCategory")
  public void setProductCategory(@NotNull BankingProductCategory productCategory);

  public default BankingProduct productCategory(@NotNull BankingProductCategory productCategory) {
    setProductCategory(productCategory);
    return this;
  }

  @BabelFishModelProperty(description = "The display name of the product", required = true)
  @JsonGetter("name")
  public String getName();

  @JsonSetter("name")
  public void setName(@NotNull String name);

  public default BankingProduct name(@NotNull String name) {
    setName(name);
    return this;
  }

  @BabelFishModelProperty(description = "A description of the product", required = true)
  @JsonGetter("description")
  public String getDescription();

  @JsonSetter("description")
  public void setDescription(@NotNull String description);

  public default BankingProduct description(@NotNull String description) {
    setDescription(description);
    return this;
  }

  @BabelFishModelProperty(
      description = "A label of the brand for the product. Able to be used for filtering. For data holders with single brands this value is still required",
      required = true)
  @JsonGetter("brand")
  public String getBrand();

  @JsonSetter("brand")
  public void setBrand(@NotNull String brand);

  public default BankingProduct brand(@NotNull String brand) {
    setBrand(brand);
    return this;
  }

  @BabelFishModelProperty(description = "An optional display name of the brand")
  @JsonGetter("brandName")
  public String getBrandName();

  @JsonSetter("brandName")
  public void setBrandName(String brandName);

  public default BankingProduct brandName(@NotNull String brandName) {
    setBrandName(brandName);
    return this;
  }

  @BabelFishModelProperty(
      description = "A link to an application web page where this product can be applied for.",
      dataType = "java.lang.String")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  @JsonGetter("applicationUri")
  public URI getApplicationUri();

  @JsonSetter("applicationUri")
  @JsonDeserialize(converter = UriStringToUriConverter.class)
  public void setApplicationUri(URI applicationUri);

  public default BankingProduct applicationUri(URI applicationUri) {
    setApplicationUri(applicationUri);
    return this;
  }

  @BabelFishModelProperty(
      description = "Indicates whether the product is specifically tailored to a circumstance.  In this case fees and prices are significantly negotiated depending on context. While all products are open to a degree of tailoring this flag indicates that tailoring is expected and thus that the provision of specific fees and rates is not applicable",
      required = true)
  @JsonGetter("isTailored")
  public Boolean isTailored();

  @JsonSetter("isTailored")
  public void setIsTailored(@NotNull Boolean isTailored);

  public default BankingProduct isTailored(@NotNull Boolean isTailored) {
    setIsTailored(isTailored);
    return this;
  }

  @BabelFishModelProperty
  @JsonGetter("additionalInformation")
  public BankingProductAdditionalInformation getAdditionalInformation();

  @JsonSetter("additionalInformation")
  public void setAdditionalInformation(BankingProductAdditionalInformation additionalInformation);

  public default BankingProduct additionalInformation(
      BankingProductAdditionalInformation additionalInformation) {
    setAdditionalInformation(additionalInformation);
    return this;
  }


}
