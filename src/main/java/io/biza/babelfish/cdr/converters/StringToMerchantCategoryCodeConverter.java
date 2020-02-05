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
package io.biza.babelfish.cdr.converters;

import java.io.IOException;
import com.fasterxml.jackson.databind.util.StdConverter;
import com.opencsv.exceptions.CsvValidationException;
import io.biza.babelfish.cdr.support.customtypes.MerchantCategoryCodeType;
import lombok.extern.slf4j.Slf4j;

/**
 * A Jackson StdConverter implementation for converting from String to MerchantCategoryCodeType
 * 
 * @since 0.9.6
 */
@Slf4j
public class StringToMerchantCategoryCodeConverter
    extends StdConverter<String, MerchantCategoryCodeType> {
  @Override
  public MerchantCategoryCodeType convert(String value) {
    if (null == value || value == "")
      return null;
    try {
      return MerchantCategoryCodeType.fromValue(value);
    } catch (CsvValidationException | IOException e) {
      LOG.error(
          "Encountered error while attempting to convert MCC, silently accepting and returning null");
      return null;
    }
  }
}
