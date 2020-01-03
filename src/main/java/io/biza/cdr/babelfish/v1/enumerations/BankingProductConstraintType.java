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
package io.biza.cdr.babelfish.v1.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.LabelValueEnumInterface;

@BabelFishModel(description = "Banking Product Constraint Type")
public enum BankingProductConstraintType implements LabelValueEnumInterface {
    MIN_BALANCE("MIN_BALANCE", "A minimum balance is required for the product"),
    MAX_BALANCE("MAX_BALANCE", "A maximum balance is required for the product"),
    OPENING_BALANCE("OPENING_BALANCE", "An opening balance is required for the product"),
    MAX_LIMIT("MAX_LIMIT", "A maximum credit limit applies for the product"),
    MIN_LIMIT("MIN_LIMIT", "A minimum credit limit applies for the product");
    
	private String value;
	private String label;

	BankingProductConstraintType(String value, String label) {
		this.value = value;
		this.label = label;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static BankingProductConstraintType fromValue(String text) {
		for (BankingProductConstraintType b : BankingProductConstraintType.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

	@Override
	@JsonIgnore
	public String label() {
		return label;
	}
}