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

import java.net.URI;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import io.biza.cdr.babelfish.model.CDRResponse;
import io.biza.cdr.babelfish.model.common.CommonDiscoveryStatusData;
import io.biza.cdr.babelfish.model.common.Links;
import io.biza.cdr.babelfish.model.common.LinksPaginated;
import io.biza.cdr.babelfish.model.common.Meta;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.v1.model.banking.BankingAccountDetail;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

@Data
@Accessors
@Valid
@BabelFishModel(modelName = "ResponseCommonDiscoveryStatus", description = "Common Discovery Status")
public abstract class ResponseCommonDiscoveryStatus {

  @BabelFishModelProperty(required = true)
  @NonNull
  @NotNull
  CommonDiscoveryStatusData data;

  @JsonUnwrapped
  @NotNull
  @NonNull
  @BabelFishModelProperty(required = true)
  public CDRResponse<Links, Meta> container;
}