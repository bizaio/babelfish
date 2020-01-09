package io.biza.cdr.babelfish.request;
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


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.biza.cdr.babelfish.model.common.AccountIdsList;
import io.biza.cdr.babelfish.model.common.Meta;
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

@Schema(description = "Request containing a set of Account IDs")
public abstract class RequestAccountIds<T> {
  @Schema(required = true)
  @JsonProperty("data")
  @NotNull
  @NonNull
  @Valid
  public AccountIdsList<?> data;

  public AccountIdsList<?> data() {
    return getData();
  }

  @SuppressWarnings("unchecked")
  public T data(AccountIdsList<?> data) {
    setData(data);
    return (T) this;
  }

  @Schema(
      description = "The meta object is used to provide additional information such as second factor authorisation data, traffic management, pagination counts or other purposes that are complementary to the workings of the API.",
      required = true)
  @JsonProperty("meta")
  @Valid
  Meta<?> meta;

  public Meta<?> meta() {
    return getMeta();
  }

  @SuppressWarnings("unchecked")
  public T meta(Meta<?> meta) {
    setMeta(meta);
    return (T) this;
  }
}
