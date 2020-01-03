/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *******************************************************************************/
package io.biza.cdr.babelfish.v1.response;

import io.biza.cdr.babelfish.v1.response.ResponseDiscoveryOutagesListData;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.v1.model.CDRResponse;
import io.biza.cdr.babelfish.v1.model.common.Links;
import io.biza.cdr.babelfish.v1.model.common.Meta;
import lombok.NonNull;

import javax.validation.constraints.NotNull;


public class ResponseDiscoveryOutagesList extends io.biza.cdr.babelfish.response.ResponseDiscoveryOutagesList {

  public ResponseDiscoveryOutagesList(@NonNull ResponseDiscoveryOutagesListData data,
      @NonNull CDRResponse<Links, Meta> metadata) {
    super(data, metadata);
    // TODO Auto-generated constructor stub
  }

}
