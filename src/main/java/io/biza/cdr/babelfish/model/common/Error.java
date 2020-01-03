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

import java.net.URI;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

@Valid
@BabelFishModel(description =  "Error Information")
public interface Error {

    @BabelFishModelProperty(
        description =  "Must be one of the following: 0001 – Account not able to be found",
        required = true
    )
    @JsonGetter("code")
    public String getCode();
    @JsonSetter("code")
    public void setCode(@NotNull String code);
    public default Error code(@NotNull String code) {
      setCode(code);
      return this;
    }

    @BabelFishModelProperty(
        description =  "Must be one of the following: Invalid account",
        required = true
    )
    @JsonGetter("title")
    public String getTitle();
    @JsonSetter("title")
    public void setTitle(@NotNull String title);
    public default Error title(@NotNull String title) {
      setTitle(title);
      return this;
    }

    @BabelFishModelProperty(
        description =  "ID of the account not found",
        required = true
    )
    @JsonGetter("detail")
    public String getDetail();
    @JsonSetter("detail")
    public void setDetail(@NotNull String detail);
    public default Error detail(@NotNull String detail) {
      setDetail(detail);
      return this;
    }

    @BabelFishModelProperty(
        description =  "Optional additional data for specific error types"
    )
    @JsonGetter("meta")
    public Object getMeta();
    @JsonSetter("meta")
    public void setMeta(Object meta);
    public default Error meta(Object meta) {
      setMeta(meta);
      return this;
    }
}
