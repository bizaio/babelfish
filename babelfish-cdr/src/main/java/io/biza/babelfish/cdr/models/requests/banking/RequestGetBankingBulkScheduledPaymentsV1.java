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


import javax.validation.Valid;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.biza.babelfish.cdr.RequestParameters;
import io.biza.babelfish.cdr.enumerations.BankingAccountStatusWithAll;
import io.biza.babelfish.cdr.enumerations.BankingProductCategory;
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
@Schema(description = "Get Bulk Direct Debits Request V1", name = "RequestGetBankingBulkDirectDebitsV1")
public class RequestGetBankingBulkScheduledPaymentsV1 {
	
	@JsonProperty(RequestParameters.PRODUCT_CATEGORY)
	BankingProductCategory productCategory;
	
	@JsonProperty(RequestParameters.OPEN_STATUS)
	@Builder.Default
	BankingAccountStatusWithAll accountStatus = BankingAccountStatusWithAll.ALL;
	
	@JsonProperty(RequestParameters.IS_OWNED)
	Boolean isOwned;
	

}
