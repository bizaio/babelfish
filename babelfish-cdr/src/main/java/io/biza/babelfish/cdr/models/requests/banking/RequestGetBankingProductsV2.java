package io.biza.babelfish.cdr.models.requests.banking;
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


import java.time.OffsetDateTime;

import javax.validation.Valid;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.biza.babelfish.cdr.RequestParameters;
import io.biza.babelfish.cdr.enumerations.BankingProductCategory;
import io.biza.babelfish.cdr.enumerations.BankingProductEffectiveWithAll;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Valid
@ToString
@EqualsAndHashCode
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Get Products Request V2", name = "RequestGetBankingProducts")
public class RequestGetBankingProductsV2 {
    
    @JsonProperty(RequestParameters.EFFECTIVE)
    BankingProductEffectiveWithAll effective;
	
    @JsonProperty(RequestParameters.UPDATED_SINCE)
    OffsetDateTime updatedSince;
    
    @JsonProperty(RequestParameters.BRAND)
    String brand;
    
	@JsonProperty(RequestParameters.PRODUCT_CATEGORY)
	BankingProductCategory productCategory;

}
