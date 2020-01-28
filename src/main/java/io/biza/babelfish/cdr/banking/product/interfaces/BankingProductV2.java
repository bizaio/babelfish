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
package io.biza.babelfish.cdr.banking.product.interfaces;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.cdr.converters.DateTimeStringToOffsetDateTimeConverter;
import io.biza.babelfish.cdr.converters.OffsetDateTimeToDateTimeStringConverter;
import io.biza.babelfish.cdr.converters.UriStringToUriConverter;
import io.biza.babelfish.cdr.converters.UriToUriStringConverter;
import io.biza.babelfish.cdr.v1.enumerations.BankingProductCategory;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * BankingProductV2 
 * This object provides a basic representation of an Australian Banking Product
 *
 */
@Valid
@Schema(description = "An Australian Banking Product", name = "BankingProduct")
public interface BankingProductV2 {
  /**
   * Get a data holder specific unique identifier for this product. This identifier must be unique to a
   * product but does not otherwise need to adhere to ID permanence guidelines.
   * 
   * @return String containing Product Identifier
   */
  @Schema(
      description = "A data holder specific unique identifier for this product. This identifier must be unique to a product but does not otherwise need to adhere to ID permanence guidelines.",
      required = true)
  @NotNull
  @JsonGetter("productId")
  public String getProductId();

  public default String productId() {
    return getProductId();
  }

  /**
   * Set the data holder specific unique identifier for this product. This identifier must be unique to a
   * product but does not otherwise need to adhere to ID permanence guidelines.
   * 
   * @param productId containing the Product ID
   */
  @JsonSetter("productId")
  public void setProductId(String productId);

  public default BankingProductV2 productId(String productId) {
    setProductId(productId);
    return this;
  }

  /**
   * Get the date and time from which this product is effective (ie. is available for origination). Used
   * to enable the articulation of products to the regime before they are available for customers to
   * originate
   * 
   * @return an OffsetDateTime containing when the product is effective from
   */
  @Schema(
      description = "The date and time from which this product is effective (ie. is available for origination).  Used to enable the articulation of products to the regime before they are available for customers to originate",
      type = "string", format = "date-time")
  @JsonSerialize(converter = OffsetDateTimeToDateTimeStringConverter.class)
  @JsonDeserialize(converter = DateTimeStringToOffsetDateTimeConverter.class)
  @JsonGetter("effectiveFrom")
  public OffsetDateTime getEffectiveFrom();

  public default OffsetDateTime effectiveFrom() {
    return getEffectiveFrom();
  }

  /**
   * Set the date and time from which this product is effective (ie. is available for origination). Used
   * to enable the articulation of products to the regime before they are available for customers to
   * originate
   * 
   * @param effectiveFrom as an OffsetDateTime
   */
  @JsonSetter("effectiveFrom")
  public void setEffectiveFrom(OffsetDateTime effectiveFrom);

  public default BankingProductV2 effectiveFrom(OffsetDateTime effectiveFrom) {
    setEffectiveFrom(effectiveFrom);
    return this;
  }

  /**
   * Get the date and time at which this product will be retired and will no longer be offered.  Used to enable the managed deprecation of products
   * @return an OffsetDateTime of when the product is effective until
   */
  @Schema(
      description = "The date and time at which this product will be retired and will no longer be offered.  Used to enable the managed deprecation of products",
      type = "string", format = "date-time")
  @JsonSerialize(converter = OffsetDateTimeToDateTimeStringConverter.class)
  @JsonDeserialize(converter = DateTimeStringToOffsetDateTimeConverter.class)
  @JsonGetter("effectiveTo")
  public OffsetDateTime getEffectiveTo();
  
  /**
   * Set the date and time at which this product will be retired and will no longer be offered.  Used to enable the managed deprecation of products
   * @param effectiveTo containing an offset date time to set when the product will no longer be offered
   */
  @JsonSetter("effectiveTo")
  public void setEffectiveTo(OffsetDateTime effectiveTo);

  public default OffsetDateTime effectiveTo() {
    return getEffectiveTo();
  }  
  
  public default BankingProductV2 effectiveTo(OffsetDateTime effectiveTo) {
    setEffectiveTo(effectiveTo);
    return this;
  }
  
  /**
   * Get the last date and time that the information for this product was changed (or the creation date for the product if it has never been altered)
   * @return OffsetDateTime containing last updated time
   */
  @Schema(
      description = "The last date and time that the information for this product was changed (or the creation date for the product if it has never been altered)",
      required = true, type = "string", format = "date-time")
  @NotNull
  @JsonSerialize(converter = OffsetDateTimeToDateTimeStringConverter.class)
  @JsonDeserialize(converter = DateTimeStringToOffsetDateTimeConverter.class)
  @JsonGetter("lastUpdated")
  public OffsetDateTime getLastUpdated();
  
  public default OffsetDateTime lastUpdated() {
    return getLastUpdated();
  }  
  
  /**
   * Set the last date and time that the information for this product was changed (or the creation date for the product if it has never been altered)
   * @param lastUpdated containing when the product was last modified or first created
   */
  @JsonSetter("lastUpdated")
  public void setLastUpdated(OffsetDateTime lastUpdated);
  
  public default BankingProductV2 lastUpdated(OffsetDateTime lastUpdated) {
    setLastUpdated(lastUpdated);
    return this;
  }

  /**
   * Get the category to which a product or account belongs. 
   * @return a BankingProductCategory enumeration value
   */
  @Schema(description = "The category to which a product or account belongs.", required = true)
  @NotNull
  @JsonGetter("productCategory")
  public BankingProductCategory getProductCategory();
  
  public default BankingProductCategory productCategory() {
    return getProductCategory();
  }  
  
  /**
   * Set the category to which a product or account belongs.
   * @param productCategory containing a BankingProductCategory enumeration value
   */
  @JsonSetter("productCategory")
  public void setProductCategory(BankingProductCategory productCategory);
  
  public default BankingProductV2 productCategory(BankingProductCategory productCategory) {
    setProductCategory(productCategory);
    return this;
  }

  /**
   * Get the display name for the product
   * @return String containing the display name
   */
  @Schema(description = "The display name of the product", required = true)
  @NotNull
  @JsonGetter("name")
  public String getName();
  
  public default String name() {
    return getName();
  }  

  /**
   * Set the display name for the product
   * @param name string containing the display name
   */
  @JsonSetter("name")
  public void setName(String name);
  
  public default BankingProductV2 name(String name) {
    setName(name);
    return this;
  }

  /**
   * Get a basic description of the product
   * @return a string containing a description of the product
   */
  @Schema(description = "A description of the product", required = true)
  @NotNull
  @JsonGetter("description")
  public String getDescription();
  
  public default String description() {
    return getDescription();
  }  
  
  /**
   * Set a basic description of the product
   * @param String containing the description
   */
  @JsonSetter("description")
  public void setDescription(String description);
    
  public default BankingProductV2 description(String description) {
    setDescription(description);
    return this;
  }

  /**
   * A label of the brand for the product. Able to be used for filtering. For data holders with single brands this value is still required
   * @return a String containing the label of the brand
   */
  @Schema(
      description = "A label of the brand for the product. Able to be used for filtering. For data holders with single brands this value is still required",
      required = true)
  @NotNull
  @JsonGetter("brand")
  public String getBrand();
  
  public default String brand() {
    return getBrand();
  }  
  
  /**
   * A label of the brand for the product. Able to be used for filtering. For data holders with single brands this value is still required
   * @param brand as a string to set
   */
  @JsonSetter("brand")
  public void setBrand(String brand);
  
  public default BankingProductV2 brand(String brand) {
    setBrand(brand);
    return this;
  }

  /**
   * An optional display name of the brand
   * @return the display name of the brand
   */
  @Schema(description = "An optional display name of the brand")
  @JsonGetter("brandName")
  public String getBrandName();
  
  public default String brandName() {
    return getBrandName();
  }  
  
  /**
   * Set the display name
   * @param brandName containing a display name
   */
  @JsonSetter("brandName")
  public void setBrandName(String brandName);
  
  public default BankingProductV2 brandName(String brandName) {
    setBrandName(brandName);
    return this;
  }

  /**
   * Get the link to an application web page where this product can be applied for.
   * @return a URI containing the Application web page address
   */
  @Schema(description = "A link to an application web page where this product can be applied for.",
      type = "string", format = "uri")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  @JsonDeserialize(converter = UriStringToUriConverter.class)
  @JsonGetter("applicationUri")
  public URI getApplicationUri();
  
  public default URI applicationUri() {
    return getApplicationUri();
  }  
  
  /**
   * Set the link to an application web page where this product can be applied for.
   * @param applicationUri containing a URI to a web page
   */
  @JsonSetter("applicationUri")
  public void setApplicationUri(URI applicationUri);
  
  public default BankingProductV2 applicationUri(URI applicationUri) {
    setApplicationUri(applicationUri);
    return this;
  }

  /**
   * Get whether the product is specifically tailored to a circumstance.  In this case fees and prices are significantly negotiated depending on context. While all products are open to a degree of tailoring this flag indicates that tailoring is expected and thus that the provision of specific fees and rates is not applicable
   * @return Boolean of whether the product is specifically tailored
   */
  @Schema(
      description = "Indicates whether the product is specifically tailored to a circumstance.  In this case fees and prices are significantly negotiated depending on context. While all products are open to a degree of tailoring this flag indicates that tailoring is expected and thus that the provision of specific fees and rates is not applicable",
      required = true)
  @NotNull
  @JsonGetter("isTailored")
  public Boolean getIsTailored();
  
  public default Boolean isTailored() {
    return getIsTailored();
  }  
  
  /**
   * Set whether the product is specifically tailored
   * @param isTailored containing a boolean of if the product is tailored
   */
  @JsonSetter("isTailored")
  public void setIsTailored(Boolean isTailored);
  
  public default BankingProductV2 tailored(Boolean isTailored) {
    setIsTailored(isTailored);
    return this;
  }

  /**
   * Get additional information for a banking product
   * @return a BankingProductAdditionalInformationV1 sub object
   */
  @Schema(description = "Additional Information for Banking Product")
  @JsonGetter("additionalInformation")
  @Valid
  public BankingProductAdditionalInformationV1 getAdditionalInformation();
  
  public default BankingProductAdditionalInformationV1 additionalInformation() {
    return getAdditionalInformation();
  }  
  
  /**
   * Set additional information for a banking product
   * @param additionalInformation containing a BankingProductAdditionalInformationV1 object
   */
  @JsonSetter("additionalInformation")
  public void setAdditionalInformation(BankingProductAdditionalInformationV1 additionalInformation);
  
  public default BankingProductV2 additionalInformation(BankingProductAdditionalInformationV1 additionalInformation) {
    setAdditionalInformation(additionalInformation);
    return this;
  }

  /**
   * Get an array of card art objects
   * @return a List of Card Art objects
   */
  @Schema(description = "An array of card art images")
  @Valid
  @JsonGetter("cardArt")
  public List<BankingProductCardArtV1> getCardArt();
  
  public default List<BankingProductCardArtV1> cardArt() {
    return getCardArt();
  }  
  
  /**
   * Set an array of card art objects
   * @param cardArt containing a list of possible card art
   */
  @JsonSetter("cardArt")
  public void setCardArt(List<BankingProductCardArtV1> cardArt);
  
  public default BankingProductV2 cardArt(List<BankingProductCardArtV1> cardArt) {
    setCardArt(cardArt);
    return this;
  }
}
