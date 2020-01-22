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
package io.biza.babelfish.cdr.model.common;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Valid
@ToString
@EqualsAndHashCode

@Schema(description = "Error Information")
public abstract class Error<T> {
  @Schema(description = "Must be one of the following: 0001 – Account not able to be found",
      required = true)
  @JsonProperty("code")
  @NotNull
  String code;

  public String code() {
    return getCode();
  }

  @SuppressWarnings("unchecked")
  public T code(String code) {
    setCode(code);
    return (T) this;
  }

  @Schema(description = "Must be one of the following: Invalid account", required = true)
  @JsonProperty("title")
  @NotNull
  String title;

  public String title() {
    return getTitle();
  }

  @SuppressWarnings("unchecked")
  public T title(String title) {
    setTitle(title);
    return (T) this;
  }

  @Schema(description = "ID of the account not found", required = true)
  @JsonProperty("detail")
  @NotNull
  String detail;

  public String detail() {
    return getDetail();
  }

  @SuppressWarnings("unchecked")
  public T detail(String detail) {
    setDetail(detail);
    return (T) this;
  }

  @Schema(description = "Optional additional data for specific error types")
  @JsonProperty("meta")
  Object meta;

  public Object meta() {
    return getMeta();
  }

  @SuppressWarnings("unchecked")
  public T meta(Object meta) {
    setMeta(meta);
    return (T) this;
  }

}
