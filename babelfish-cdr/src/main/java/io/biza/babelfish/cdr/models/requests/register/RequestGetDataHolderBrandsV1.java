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
package io.biza.babelfish.cdr.models.requests.register;

import java.time.OffsetDateTime;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.biza.babelfish.cdr.enumerations.register.IndustryType;
import lombok.Builder;
import io.biza.babelfish.cdr.RequestParameters;
import lombok.Data;

@Data
@Builder
public class RequestGetDataHolderBrandsV1 {

	@JsonIgnore
	@NotNull
	@Builder.Default
	IndustryType industryType = IndustryType.BANKING;

	@JsonProperty(RequestParameters.UPDATED_SINCE)
	OffsetDateTime updatedSince;
}
