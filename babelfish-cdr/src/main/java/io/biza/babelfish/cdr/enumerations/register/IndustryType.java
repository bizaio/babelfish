package io.biza.babelfish.cdr.enumerations.register;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import io.biza.babelfish.common.exceptions.LabelValueEnumValueNotSupportedException;
import io.biza.babelfish.common.interfaces.LabelValueEnumInterface;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "CDR Industry Specification", enumAsRef = true)
public enum IndustryType implements LabelValueEnumInterface {
	// @formatter:off
	BANKING("banking", "Banking");
	// @formatter:on

	private String value;

	private String label;

	IndustryType(String value, String label) {
		this.value = value;
		this.label = label;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static IndustryType fromValue(String text) throws LabelValueEnumValueNotSupportedException {
		for (IndustryType b : IndustryType.values()) {
			if (String.valueOf(b.value).equals(text.toLowerCase())) {
				return b;
			}
		}
		throw new LabelValueEnumValueNotSupportedException("Unable to identify value of IndustryType from " + text,
				IndustryType.class.getSimpleName(), IndustryType.values(), text);
	}

	@Override
	@JsonIgnore
	public String label() {
		return label;
	}
}