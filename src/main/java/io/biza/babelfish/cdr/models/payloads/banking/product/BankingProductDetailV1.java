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
package io.biza.babelfish.cdr.models.payloads.banking.product;

import java.util.List;
import javax.validation.Valid;
import io.biza.babelfish.cdr.abstracts.payloads.banking.product.BankingProductCardArt;
import io.biza.babelfish.cdr.exceptions.AttributeNotSupportedException;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Valid
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BankingProductDetailV1
    extends io.biza.babelfish.cdr.abstracts.payloads.banking.product.BankingProductDetail<BankingProductDetailV1> {

  @Override
  public List<BankingProductCardArt<?>> cardArt() throws AttributeNotSupportedException {
    throw new AttributeNotSupportedException("Card Art is not supported in Version 1 Payloads");
  }

  @Override
  public BankingProductDetailV1 cardArt(List<BankingProductCardArt<?>> cardArt)
      throws AttributeNotSupportedException {
    throw new AttributeNotSupportedException("Card Art is not supported in Version 1 Payloads");
  }
}
