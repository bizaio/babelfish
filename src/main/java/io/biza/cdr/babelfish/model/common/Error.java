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
package io.biza.cdr.babelfish.model.common;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import lombok.Data;
import lombok.NonNull;

@Data
@Valid
@BabelFishModel(description = "Error Information")
public abstract class Error<T extends Error<T>> {
  @BabelFishModelProperty(
      description = "Must be one of the following: 0001 – Account not able to be found",
      required = true)
  @JsonProperty("code")
  @NotNull
  @NonNull
  public String code;

  public String code() {
    return getCode();
  }

  @SuppressWarnings("unchecked")
  public T code(String code) {
    setCode(code);
    return (T) this;
  }

  @BabelFishModelProperty(description = "Must be one of the following: Invalid account",
      required = true)
  @JsonProperty("title")
  @NotNull
  @NonNull
  public String title;

  public String title() {
    return getTitle();
  }

  @SuppressWarnings("unchecked")
  public T title(String title) {
    setTitle(title);
    return (T) this;
  }

  @BabelFishModelProperty(description = "ID of the account not found", required = true)
  @JsonProperty("detail")
  @NotNull
  @NonNull
  public String detail;

  public String detail() {
    return getDetail();
  }

  @SuppressWarnings("unchecked")
  public T detail(String detail) {
    setDetail(detail);
    return (T) this;
  }

  @BabelFishModelProperty(description = "Optional additional data for specific error types")
  @JsonProperty("meta")
  public Object meta;

  public Object meta() {
    return getMeta();
  }

  @SuppressWarnings("unchecked")
  public T meta(Object meta) {
    setMeta(meta);
    return (T) this;
  }

}
