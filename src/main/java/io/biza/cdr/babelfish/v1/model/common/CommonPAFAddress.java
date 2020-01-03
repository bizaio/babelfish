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
package io.biza.cdr.babelfish.v1.model.common;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.v1.enumerations.AddressPAFFlatUnitType;
import io.biza.cdr.babelfish.v1.enumerations.AddressPAFFloorLevelType;
import io.biza.cdr.babelfish.v1.enumerations.AddressPAFPostalDeliveryType;
import io.biza.cdr.babelfish.v1.enumerations.AddressPAFStateType;
import io.biza.cdr.babelfish.v1.enumerations.AddressPAFStreetSuffix;
import io.biza.cdr.babelfish.v1.enumerations.AddressPAFStreetType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Valid
public class CommonPAFAddress extends io.biza.cdr.babelfish.model.common.CommonPAFAddress {

	public CommonPAFAddress(@NonNull String dpid, @NonNull String localityName,
      @NonNull String postcode, @NonNull AddressPAFStateType state) {
    super(dpid, localityName, postcode, state);
  }

	@AssertTrue(message = "Thoroughfare Suffixes should only be set when Thoroughfare Numbers are set")
	private boolean isInvalidSuffixes() {
		if (getThoroughfareNumber1Suffix() != null && getThoroughfareNumber1() == null) {
			return false;
		}
		if (getThoroughfareNumber2Suffix() != null && getThoroughfareNumber2() == null) {
			return false;
		}
		return true;
	}

	@AssertTrue(message = "Thoroughfare Number 2 must only be set when Thoroughfare Number 1 exists and must be greater than Thoroughfare Number 1")
	private boolean isThoroughFareNumber2LargerThan1() {
		if (getThoroughfareNumber1() == null) {
			if (getThoroughfareNumber2() == null) {
				return true;
			} else {
				return false;
			}
		} else {
			if (getThoroughfareNumber2() == null) {
				return false;
			} else {
				if (getThoroughfareNumber2() > getThoroughfareNumber1()) {
					return true;
				} else {
					return false;
				}
			}

		}
	}
}
