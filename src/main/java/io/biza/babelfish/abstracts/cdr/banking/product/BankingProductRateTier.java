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
package io.biza.babelfish.abstracts.cdr.banking.product;

import java.math.BigDecimal;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.biza.babelfish.enumerations.cdr.BankingProductRateTierApplicationMethod;
import io.biza.babelfish.enumerations.cdr.CommonUnitOfMeasureType;
import io.biza.babelfish.interfaces.cdr.banking.product.BankingProductRateTierApplicabilityV1;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
public abstract class BankingProductRateTier {
  String name;
  CommonUnitOfMeasureType unitOfMeasure;
  BigDecimal minimumValue;
  BigDecimal maximumValue;
  BankingProductRateTierApplicationMethod rateApplicationMethod;
  BankingProductRateTierApplicabilityV1 applicabilityConditions;
}
