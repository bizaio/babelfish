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
package io.biza.babelfish.cdr.response.container;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.biza.babelfish.cdr.v1.model.common.CommonDiscoveryOutage;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Valid
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode


@Schema(description = "Object containing a list of CommonDiscoveryOutage objects")
public abstract class ResponseDiscoveryOutagesListData<T> {
  @Schema(
      description = "List of scheduled outages. Property is mandatory but may contain and empty list if no outages are scheduled",
      required = true)
  @JsonProperty("outages")
  @NotNull
  @NonNull
  public List<CommonDiscoveryOutage> outages;

  public List<CommonDiscoveryOutage> outages() {
    return getOutages();
  }

  @SuppressWarnings("unchecked")
  public T outages(List<CommonDiscoveryOutage> outages) {
    setOutages(outages);
    return (T) this;
  }
}
