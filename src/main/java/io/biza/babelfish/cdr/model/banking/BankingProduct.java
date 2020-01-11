/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *******************************************************************************/
package io.biza.babelfish.cdr.model.banking;

import java.net.URI;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.cdr.converters.DateTimeStringToOffsetDateTimeConverter;
import io.biza.babelfish.cdr.converters.OffsetDateTimeToDateTimeStringConverter;
import io.biza.babelfish.cdr.converters.UriToUriStringConverter;
import io.biza.babelfish.cdr.v1.enumerations.BankingProductCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Valid
@ToString
@EqualsAndHashCode

@Schema(description = "An Australian Banking Product")
public abstract class BankingProduct<T> {
  @Schema(
      description = "A data holder specific unique identifier for this product. This identifier must be unique to a product but does not otherwise need to adhere to ID permanence guidelines.",
      required = true)
  @NonNull
  @NotNull
  private String productId;

  public String productId() {
    return getProductId();
  }

  @SuppressWarnings("unchecked")
  public T productId(String productId) {
    setProductId(productId);
    return (T) this;
  }

  @Schema(
      description = "The date and time from which this product is effective (ie. is available for origination).  Used to enable the articulation of products to the regime before they are available for customers to originate",
      type = "string")
  @JsonSerialize(converter = OffsetDateTimeToDateTimeStringConverter.class)
  @JsonDeserialize(converter = DateTimeStringToOffsetDateTimeConverter.class)
  private OffsetDateTime effectiveFrom;

  public OffsetDateTime effectiveFrom() {
    return getEffectiveFrom();
  }

  @SuppressWarnings("unchecked")
  public T effectiveFrom(OffsetDateTime effectiveFrom) {
    setEffectiveFrom(effectiveFrom);
    return (T) this;
  }

  @Schema(
      description = "The date and time at which this product will be retired and will no longer be offered.  Used to enable the managed deprecation of products",
      type = "string")
  @JsonSerialize(converter = OffsetDateTimeToDateTimeStringConverter.class)
  @JsonDeserialize(converter = DateTimeStringToOffsetDateTimeConverter.class)
  private OffsetDateTime effectiveTo;

  public OffsetDateTime effectiveTo() {
    return getEffectiveTo();
  }

  @SuppressWarnings("unchecked")
  public T effectiveTo(OffsetDateTime effectiveTo) {
    setEffectiveTo(effectiveTo);
    return (T) this;
  }

  @Schema(
      description = "The last date and time that the information for this product was changed (or the creation date for the product if it has never been altered)",
      required = true, type = "string")
  @NonNull
  @NotNull
  @JsonSerialize(converter = OffsetDateTimeToDateTimeStringConverter.class)
  @JsonDeserialize(converter = DateTimeStringToOffsetDateTimeConverter.class)
  private OffsetDateTime lastUpdated;

  public OffsetDateTime lastUpdated() {
    return getLastUpdated();
  }

  @SuppressWarnings("unchecked")
  public T lastUpdated(OffsetDateTime lastUpdated) {
    setLastUpdated(lastUpdated);
    return (T) this;
  }

  @Schema(required = true)
  @NonNull
  @NotNull
  BankingProductCategory productCategory;

  public BankingProductCategory productCategory() {
    return getProductCategory();
  }

  @SuppressWarnings("unchecked")
  public T productCategory(BankingProductCategory productCategory) {
    setProductCategory(productCategory);
    return (T) this;
  }

  @Schema(description = "The display name of the product", required = true)
  @NonNull
  @NotNull
  String name;

  public String name() {
    return getName();
  }

  @SuppressWarnings("unchecked")
  public T name(String name) {
    setName(name);
    return (T) this;
  }

  @Schema(description = "A description of the product", required = true)
  @NonNull
  @NotNull
  String description;

  public String description() {
    return getDescription();
  }

  @SuppressWarnings("unchecked")
  public T description(String description) {
    setDescription(description);
    return (T) this;
  }

  @Schema(
      description = "A label of the brand for the product. Able to be used for filtering. For data holders with single brands this value is still required",
      required = true)
  @NonNull
  @NotNull
  String brand;

  public String brand() {
    return getBrand();
  }

  @SuppressWarnings("unchecked")
  public T brand(String brand) {
    setBrand(brand);
    return (T) this;
  }

  @Schema(description = "An optional display name of the brand")
  String brandName;

  public String brandName() {
    return getBrandName();
  }

  @SuppressWarnings("unchecked")
  public T brandName(String brandName) {
    setBrandName(brandName);
    return (T) this;
  }

  @Schema(
      description = "A link to an application web page where this product can be applied for.",
      type = "string")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  URI applicationUri;

  public URI applicationUri() {
    return getApplicationUri();
  }

  @SuppressWarnings("unchecked")
  public T applicationUri(URI applicationUri) {
    setApplicationUri(applicationUri);
    return (T) this;
  }

  @Schema(
      description = "Indicates whether the product is specifically tailored to a circumstance.  In this case fees and prices are significantly negotiated depending on context. While all products are open to a degree of tailoring this flag indicates that tailoring is expected and thus that the provision of specific fees and rates is not applicable",
      required = true)
  @NonNull
  @NotNull
  @JsonProperty("isTailored")
  Boolean tailored;

  public Boolean tailored() {
    return getTailored();
  }

  @SuppressWarnings("unchecked")
  public T tailored(Boolean isTailored) {
    setTailored(isTailored);
    return (T) this;
  }

  @Schema(description = "Additional Information for Banking Product")
  BankingProductAdditionalInformation<?> additionalInformation;

  public BankingProductAdditionalInformation<?> additionalInformation() {
    return getAdditionalInformation();
  }

  @SuppressWarnings("unchecked")
  public T additionalInformation(BankingProductAdditionalInformation<?> additionalInformation) {
    setAdditionalInformation(additionalInformation);
    return (T) this;
  }
}
