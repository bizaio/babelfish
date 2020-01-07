/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *******************************************************************************/
package io.biza.cdr.babelfish.response;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import io.biza.cdr.babelfish.model.common.Error;

@Getter
@Setter
@Valid
@BabelFishModel(description = "CDS Error Response")
public abstract class ResponseErrorList<T> {
  @BabelFishModelProperty(required = true)
  @NonNull
  @NotNull
  @JsonProperty("errors")
  List<Error<?>> errors;
  

  public List<Error<?>> errors() {
    return getErrors();
  }

  @SuppressWarnings("unchecked")
  public T errors(List<Error<?>> errors) {
    setErrors(errors);
    return (T) this;
  }
}
