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
package io.biza.babelfish.interfaces.cdr.banking.product;

import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.converter.cdr.UriStringToUriConverter;
import io.biza.babelfish.converter.cdr.UriToUriStringConverter;
import io.swagger.v3.oas.annotations.media.Schema;

@Valid
@Schema(description = "Banking Product Bundle Definition", name = "BankingProductBundle")
public interface BankingProductBundleV1 {
  
  /**
   * Get the name of the product bundle
   * @return String containing the name of the bundle
   */
  @Schema(description = "Name of the bundle", required = true)
  @NotNull
  @NotBlank
  @Valid
  @JsonGetter("name")
  public String getName();

  public default String name() {
    return getName();
  }

  /**
   * Set the name of the product bundle
   * @param name to set the product bundle to
   */
  @JsonSetter("name")
  public void setName(String name);
  
  public default BankingProductBundleV1 name(String name) {
    setName(name);
    return this;
  }

  /**
   * Get the description of the bundle
   * @return a free text description of the bundle
   */
  @Schema(description = "Description of the bundle", required = true)
  @NotNull
  @NotBlank
  @Valid
  @JsonGetter("description")
  public String getDescription();

  public default String description() {
    return getDescription();
  }

  /**
   * Set the bundle description
   * @param description containing a freetext bundle description
   */
  @JsonSetter("description")
  public void setDescription(String description);
  
  public default BankingProductBundleV1 description(String description) {
    setDescription(description);
    return this;
  }

  /**
   * Get additional information about the bundle
   * @return a free text string with additional bundle information
   */
  @Schema(description = "Display text providing more information on the bundle")
  @JsonGetter("additionalInfo")
  public String getAdditionalInfo();
  
  public default String additionalInfo() {
    return getAdditionalInfo();
  }  
  
  /**
   * Set the display text for additional bundle information
   * @param additionalInfo containing additional information
   */
  @JsonSetter("additionalInfo")
  public void setAdditionalInfo(String additionalInfo);
  
  public default BankingProductBundleV1 additionalInfo(String additionalInfo) {
    setAdditionalInfo(additionalInfo);
    return this;
  }

  /**
   * Get additional information address
   * @return a Link to a web page with more information on the bundle criteria and benefits
   */
  @Schema(
      description = "Link to a web page with more information on the bundle criteria and benefits",
      type = "string", format = "uri")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  @JsonDeserialize(converter = UriStringToUriConverter.class)
  @JsonGetter("additionalInfoUri")
  public URI getAdditionalInfoUri();
  
  /**
   * Link to a web page with more information on the bundle criteria and benefits
   * @param additionalInfoUri containing the link to set to
   */
  @JsonSetter("additionalInfoUri")
  public void setAdditionalInfoUri(URI additionalInfoUri);
  
  public default URI additionalInfoUri() {
    return getAdditionalInfoUri();
  }

  public default BankingProductBundleV1 additionalInfoUri(URI additionalInfoUri) {
    setAdditionalInfoUri(additionalInfoUri);
    return this;
  }

  /**
   * An array of product identifiers included within this bundle. May not contain all products in bundle and specified products may not be available at this endpoint
   * @return a List of String's containing productId's
   */
  @Schema(
      description = "Array of product IDs for products included in the bundle that are available via the product end points.  Note that this array is not intended to represent a comprehensive model of the products included in the bundle and some products available for the bundle may not be available via the product reference end points")
  @JsonGetter("productIds")
  public List<String> getProductIdentifiers();
  
  public default List<String> productIdentifiers() {
    return getProductIdentifiers();
  }
  
  /**
   * Set a list of product identifiers in this bundle
   * @param productIds as a list of strings
   */
  @JsonSetter("productIds")
  public void setProductIdentifiers(List<String> productIds);
  
  public default BankingProductBundleV1 productIdentifiers(List<String> productIds) {
    setProductIdentifiers(productIds);
    return this;
  }
}
