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
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.cdr.converters.UriStringToUriConverter;
import io.biza.babelfish.cdr.converters.UriToUriStringConverter;
import io.swagger.v3.oas.annotations.media.Schema;

@Valid
@Schema(description = "Banking Product Card Art", name = "BankingProductCardArt")
public interface BankingProductCardArtV1 {
  /**
   * Get the display label for the card art
   * @return a String containing the label
   */
  @Schema(description = "Display label for the specific image", required = true)
  @NotNull
  @NotBlank
  @Valid
  @JsonGetter("title")
  public String getTitle();
  
  public default String title() {
    return getTitle();
  }
  
  /**
   * Set the display label on the card
   * @param title to set the display label
   */
  @JsonSetter("title")
  public void setTitle(String title);
  
  public default BankingProductCardArtV1 title(String title) {
    setTitle(title);
    return this;
  }

  @Schema(
      description = "Link to a PNG, JPG or GIF image with proportions defined by ISO 7810 ID-1 and width no greater than 512 pixels",
      type = "string", format = "uri")
  @JsonSerialize(converter = UriToUriStringConverter.class)
  @JsonDeserialize(converter = UriStringToUriConverter.class)
  @JsonGetter("imageUri")
  public URI getImage();
  
  public default URI imageUri() {
    return getImage();
  }
  
  @JsonSetter("imageUri")
  public void setImage(URI imageUri);
  
  public default BankingProductCardArtV1 imageUri(URI imageUri) {
    setImage(imageUri);
    return this;
  }
}
