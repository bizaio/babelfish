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
package io.biza.babelfish.cdr.banking.product.model.abstracts;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.biza.babelfish.cdr.banking.product.interfaces.BankingProductAdditionalInformationV1;
import io.biza.babelfish.cdr.banking.product.interfaces.BankingProductCardArtV1;
import io.biza.babelfish.cdr.banking.product.interfaces.BankingProductV2;
import io.biza.babelfish.cdr.converters.DateTimeStringToOffsetDateTimeConverter;
import io.biza.babelfish.cdr.converters.OffsetDateTimeToDateTimeStringConverter;
import io.biza.babelfish.cdr.converters.UriStringToUriConverter;
import io.biza.babelfish.cdr.converters.UriToUriStringConverter;
import io.biza.babelfish.cdr.exception.AttributeNotSupportedException;
import io.biza.babelfish.cdr.v1.enumerations.BankingProductCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
public abstract class BankingProduct {
  public String productId;
  public OffsetDateTime effectiveFrom;
  public OffsetDateTime effectiveTo;
  public OffsetDateTime lastUpdated;
  public BankingProductCategory productCategory;
  public String name;
  public String description;
  public String brand;
  public String brandName;
  public URI applicationUri;
  public Boolean isTailored;
  public BankingProductAdditionalInformationV1 additionalInformation;
  public List<BankingProductCardArtV1> cardArt;
}
