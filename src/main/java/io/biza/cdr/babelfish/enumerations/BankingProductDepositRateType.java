package io.biza.cdr.babelfish.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.LabelValueEnumInterface;

@BabelFishModel(description = "Banking Product Deposit Rate Type")
public enum BankingProductDepositRateType implements LabelValueEnumInterface {
    FIXED("FIXED", "Fixed rate for a period of time"),
    BONUS("BONUS", "A bonus rate available by meeting a specific criteria"),
    BUNDLE_BONUS("BUNDLE_BONUS", "A bonus rate obtained by originating a bundle instead of a standalone product"),
    VARIABLE("VARIABLE", "A variable base rate for the product"),
    INTRODUCTORY("INTRODUCTORY", "An introductory bonus that will expire after a set period"),
    FLOATING("FLOATING", "A floating rate is relatively fixed but still adjusts under specific circumstances"),
    MARKET_LINKED("MARKET_LINKED", "A rate that is linked to a specific market, commodity or asset class");
	
    
   	private String value;
   	private String label;

   	BankingProductDepositRateType(String value, String label) {
   		this.value = value;
   		this.label = label;
   	}

   	@Override
   	@JsonValue
   	public String toString() {
   		return String.valueOf(value);
   	}

   	@JsonCreator
   	public static BankingProductDepositRateType fromValue(String text) {
   		for (BankingProductDepositRateType b : BankingProductDepositRateType.values()) {
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