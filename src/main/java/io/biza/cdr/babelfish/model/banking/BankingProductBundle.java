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
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.biza.cdr.babelfish.support.BabelFishModelProperty;
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
@BabelFishModel(description = "Banking Product Bundle Definition")
public interface BankingProductBundle {

  @BabelFishModelProperty(description = "Name of the bundle", required = true)
  @JsonGetter("name")
  public String getName();

  @JsonSetter("name")
  public void setName(@NotNull String name);

  public default BankingProductBundle name(@NotNull String name) {
    setName(name);
    return this;
  }

  @BabelFishModelProperty(description = "Description of the bundle", required = true)
  @JsonGetter("description")
  public String getDescription();

  @JsonSetter("description")
  public void setDescription(@NotNull String description);

  public default BankingProductBundle description(@NotNull String description) {
    setDescription(description);
    return this;
  }

  @BabelFishModelProperty(description = "Display text providing more information on the bundle")
  @JsonGetter("additionalInformation")
  public String getAdditionalInformation();

  @JsonSetter("additionalInformation")
  public void setAdditionalInformation(String additionalInformation);

  public default BankingProductBundle additionalInformation(String additionalInformation) {
    setAdditionalInformation(additionalInformation);
    return this;
  }

  @BabelFishModelProperty(
      description = "Link to a web page with more information on the bundle criteria and benefits",
      dataType = "java.lang.String")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  @JsonGetter("additionalInfoUri")
  public URI getAdditionalInfoUri();

  @JsonDeserialize(converter = UriStringToUriConverter.class)
  @JsonSetter("additionalInfoUri")
  public void setAdditionalInfoUri(URI additionalInfoUri);

  public default BankingProductBundle additionalInfoUri(URI additionalInfoUri) {
    setAdditionalInfoUri(additionalInfoUri);
    return this;
  }

  @BabelFishModelProperty(
      description = "Array of product IDs for products included in the bundle that are available via the product end points.  Note that this array is not intended to represent a comprehensive model of the products included in the bundle and some products available for the bundle may not be available via the product reference end points")
  @JsonGetter("productIds")
  public List<String> getProductIds();

  @JsonSetter("productIds")
  public void setProductIds(List<String> productIds);

  public default BankingProductBundle productIds(List<String> productIds) {
    setProductIds(productIds);
    return this;
  }
}
