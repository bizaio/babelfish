/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *******************************************************************************/
package io.biza.babelfish.cdr.response;

import io.biza.babelfish.cdr.model.CDRResponse;
import io.biza.babelfish.cdr.response.container.ResponseDiscoveryOutagesListData;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
@Valid
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)


public abstract class ResponseDiscoveryOutagesList<T> extends CDRResponse<T> {
  @Schema(required = true)
  @JsonProperty("data")
  @NotNull
  @NonNull
  public ResponseDiscoveryOutagesListData<?> data;

  public ResponseDiscoveryOutagesListData<?> data() {
    return getData();
  }

  @SuppressWarnings("unchecked")
  public T data(ResponseDiscoveryOutagesListData<?> data) {
    setData(data);
    return (T) this;
  }
}
