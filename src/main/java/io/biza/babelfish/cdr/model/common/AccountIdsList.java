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

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@NoArgsConstructor
@Data
@Valid
@ToString
@EqualsAndHashCode

@Schema(description = "Account ID List")
public abstract class AccountIdsList<T> {
  @Schema(description = "List of Account Identifiers", required = true)
  @JsonProperty("accountIds")
  @NotNull
  @NotEmpty(message = "At least one account identifier is required")
  @Valid
  List<String> accountIds;

  public List<String> accountIds() {
    return getAccountIds();
  }

  @SuppressWarnings("unchecked")
  public T accountIds(List<String> accountIds) {
    setAccountIds(accountIds);
    return (T) this;
  }

}
