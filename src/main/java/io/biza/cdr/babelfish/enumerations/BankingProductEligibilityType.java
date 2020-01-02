package io.biza.cdr.babelfish.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.LabelValueEnumInterface;

@BabelFishModel(description = "Banking Product Eligibility Criteria Type")
public enum BankingProductEligibilityType implements LabelValueEnumInterface {
    BUSINESS("BUSINESS", "Only Business may apply to the account"),
    PENSION_RECIPIENT("PENSION_RECIPIENT", "A recipient of a government pension may apply for the product"),
    MIN_AGE("MIN_AGE", "Only customers older than a minimum age may apply"),
    MAX_AGE("MAX_AGE", "Only customers younger than a maximum age may apply"),
    MIN_INCOME("MIN_INCOME", "The customer must have an income greater than a specified threshold to obtain the product"),
    MIN_TURNOVER("MIN_TURNOVER", "Only a business with greater than a minimum turnover may apply"),
    STAFF("STAFF", "Only a staff member of the provider may apply"),
    STUDENT("STUDENT", "Only students may apply for the product"),
    EMPLOYMENT_STATUS("EMPLOYMENT_STATUS", "An eligibility constraint based on employment status applies"),
    RESIDENCY_STATUS("RESIDENCY_STATUS", "An eligibility constraint based on residency status applies"),
    NATURAL_PERSON("NATURAL_PERSON", "The customer must be a natural person rather than another legal entity"),
    OTHER("OTHER", "Another eligibility criteria exists and is described within Additional Information");
	
	private String value;
   	private String label;

   	BankingProductEligibilityType(String value, String label) {
   		this.value = value;
   		this.label = label;
   	}

   	@Override
   	@JsonValue
   	public String toString() {
   		return String.valueOf(value);
   	}

   	@JsonCreator
   	public static BankingProductEligibilityType fromValue(String text) {
   		for (BankingProductEligibilityType b : BankingProductEligibilityType.values()) {
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
