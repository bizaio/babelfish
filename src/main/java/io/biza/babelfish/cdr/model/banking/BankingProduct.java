/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU Lesser General Public License as published by the Free Software Foundation, either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *******************************************************************************/
package io.biza.babelfish.cdr.model.banking;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.cdr.converters.DateTimeStringToOffsetDateTimeConverter;
import io.biza.babelfish.cdr.converters.OffsetDateTimeToDateTimeStringConverter;
import io.biza.babelfish.cdr.converters.UriStringToUriConverter;
import io.biza.babelfish.cdr.converters.UriToUriStringConverter;
import io.biza.babelfish.cdr.exception.AttributeNotSupportedException;
import io.biza.babelfish.cdr.v1.enumerations.BankingProductCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Valid
@ToString
@EqualsAndHashCode
@Schema(description = "An Australian Banking Product")
public abstract class BankingProduct<T> {
  @Schema(
      description = "A data holder specific unique identifier for this product. This identifier must be unique to a product but does not otherwise need to adhere to ID permanence guidelines.",
      required = true)
  @NotNull
  @JsonProperty("productId")
  private String productId;

  public String productId() {
    return productId;
  }

  @SuppressWarnings("unchecked")
  public T productId(String productId) {
    this.productId = productId;
    return (T) this;
  }

  @Schema(
      description = "The date and time from which this product is effective (ie. is available for origination).  Used to enable the articulation of products to the regime before they are available for customers to originate",
      type = "string", format = "date-time")
  @JsonSerialize(converter = OffsetDateTimeToDateTimeStringConverter.class)
  @JsonDeserialize(converter = DateTimeStringToOffsetDateTimeConverter.class)
  @JsonProperty("effectiveFrom")
  private OffsetDateTime effectiveFrom;

  public OffsetDateTime effectiveFrom() {
    return effectiveFrom;
  }

  @SuppressWarnings("unchecked")
  public T effectiveFrom(OffsetDateTime effectiveFrom) {
    this.effectiveFrom = effectiveFrom;
    return (T) this;
  }

  @Schema(
      description = "The date and time at which this product will be retired and will no longer be offered.  Used to enable the managed deprecation of products",
      type = "string", format = "date-time")
  @JsonSerialize(converter = OffsetDateTimeToDateTimeStringConverter.class)
  @JsonDeserialize(converter = DateTimeStringToOffsetDateTimeConverter.class)
  @JsonProperty("effectiveTo")
  private OffsetDateTime effectiveTo;

  public OffsetDateTime effectiveTo() {
    return effectiveTo;
  }

  @SuppressWarnings("unchecked")
  public T effectiveTo(OffsetDateTime effectiveTo) {
    this.effectiveTo = effectiveTo;
    return (T) this;
  }

  @Schema(
      description = "The last date and time that the information for this product was changed (or the creation date for the product if it has never been altered)",
      required = true, type = "string", format = "date-time")
  @NotNull
  @JsonSerialize(converter = OffsetDateTimeToDateTimeStringConverter.class)
  @JsonDeserialize(converter = DateTimeStringToOffsetDateTimeConverter.class)
  @JsonProperty("lastUpdated")
  private OffsetDateTime lastUpdated;

  public OffsetDateTime lastUpdated() {
    return lastUpdated;
  }

  @SuppressWarnings("unchecked")
  public T lastUpdated(OffsetDateTime lastUpdated) {
    this.lastUpdated = lastUpdated;
    return (T) this;
  }

  @Schema(required = true)
  @NotNull
  @JsonProperty("productCategory")
  BankingProductCategory productCategory;

  public BankingProductCategory productCategory() {
    return this.productCategory;
  }

  @SuppressWarnings("unchecked")
  public T productCategory(BankingProductCategory productCategory) {
    this.productCategory = productCategory;
    return (T) this;
  }

  @Schema(description = "The display name of the product", required = true)
  @NotNull
  @JsonProperty("name")
  String name;

  public String name() {
    return name;
  }

  @SuppressWarnings("unchecked")
  public T name(String name) {
    this.name = name;
    return (T) this;
  }

  @Schema(description = "A description of the product", required = true)
  @NotNull
  @JsonProperty("description")
  String description;

  public String description() {
    return description;
  }

  @SuppressWarnings("unchecked")
  public T description(String description) {
    this.description = description;
    return (T) this;
  }

  @Schema(
      description = "A label of the brand for the product. Able to be used for filtering. For data holders with single brands this value is still required",
      required = true)
  @NotNull
  @JsonProperty("brand")
  String brand;

  public String brand() {
    return this.brand;
  }

  @SuppressWarnings("unchecked")
  public T brand(String brand) {
    this.brand = brand;
    return (T) this;
  }

  @Schema(description = "An optional display name of the brand")
  @JsonProperty("brandName")
  String brandName;

  public String brandName() {
    return brandName;
  }

  @SuppressWarnings("unchecked")
  public T brandName(String brandName) {
    this.brandName = brandName;
    return (T) this;
  }

  @Schema(description = "A link to an application web page where this product can be applied for.",
      type = "string", format = "uri")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  @JsonDeserialize(converter = UriStringToUriConverter.class)
  @JsonProperty("applicationUri")
  URI applicationUri;

  public URI applicationUri() {
    return this.applicationUri;
  }


  @SuppressWarnings("unchecked")
  public T applicationUri(URI applicationUri) {
    this.applicationUri = applicationUri;
    return (T) this;
  }

  @Schema(
      description = "Indicates whether the product is specifically tailored to a circumstance.  In this case fees and prices are significantly negotiated depending on context. While all products are open to a degree of tailoring this flag indicates that tailoring is expected and thus that the provision of specific fees and rates is not applicable",
      required = true)
  @NotNull
  @JsonProperty("isTailored")
  Boolean tailored;

  public Boolean tailored() {
    return tailored;
  }

  @SuppressWarnings("unchecked")
  public T tailored(Boolean isTailored) {
    this.tailored = isTailored;
    return (T) this;
  }

  @Schema(description = "Additional Information for Banking Product")
  @JsonProperty("additionalInformation")
  @Valid
  BankingProductAdditionalInformation<?> additionalInformation;

  public BankingProductAdditionalInformation<?> additionalInformation() {
    return additionalInformation;
  }

  @SuppressWarnings("unchecked")
  public T additionalInformation(BankingProductAdditionalInformation<?> additionalInformation) {
    this.additionalInformation = additionalInformation;
    return (T) this;
  }

  @Schema(description = "An array of card art images")
  @JsonProperty("cardArt")
  @Valid
  List<BankingProductCardArt<?>> cardArt;

  public List<BankingProductCardArt<?>> cardArt() throws AttributeNotSupportedException {
    return cardArt;
  }

  @SuppressWarnings("unchecked")
  public T cardArt(List<BankingProductCardArt<?>> cardArt) throws AttributeNotSupportedException {
    this.cardArt = cardArt;
    return (T) this;
  }
}
