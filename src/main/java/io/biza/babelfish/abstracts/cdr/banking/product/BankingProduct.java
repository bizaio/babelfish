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
package io.biza.babelfish.abstracts.cdr.banking.product;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.List;
import io.biza.babelfish.enumerations.cdr.BankingProductCategory;
import io.biza.babelfish.interfaces.cdr.banking.product.BankingProductAdditionalInformationV1;
import io.biza.babelfish.interfaces.cdr.banking.product.BankingProductCardArtV1;
import lombok.Data;

@Data
public abstract class BankingProduct {
  String productId;
  OffsetDateTime effectiveFrom;
  OffsetDateTime effectiveTo;
  OffsetDateTime lastUpdated;
  BankingProductCategory productCategory;
  String name;
  String description;
  String brand;
  String brandName;
  URI applicationUri;
  Boolean isTailored;
  BankingProductAdditionalInformationV1 additionalInformation;
  List<BankingProductCardArtV1> cardArt;
}
