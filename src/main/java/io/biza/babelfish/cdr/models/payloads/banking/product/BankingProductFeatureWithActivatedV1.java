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
package io.biza.babelfish.cdr.models.payloads.banking.product;

import javax.validation.Valid;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Valid
@ToString
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Schema(name = "BankingAccountProductFeature", description = "Banking Account Product Feature",
    allOf = {BankingProductFeatureV1.class})
public class BankingProductFeatureWithActivatedV1 extends BankingProductFeatureV1 {
  @Schema(
      description = "True if the feature is already activated and false if the feature is available for activation.")
  @JsonProperty("isActivated")
  @Builder.Default
  Boolean isActivated = true;
}
