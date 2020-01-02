package io.biza.cdr.babelfish.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.LabelValueEnumInterface;

@BabelFishModel(description = "Banking Product Categories")
public enum BankingProductCategory implements LabelValueEnumInterface {
	TRANS_AND_SAVINGS_ACCOUNTS("TRANS_AND_SAVINGS_ACCOUNTS", "Transaction & Savings"),
	TERM_DEPOSITS("TERM_DEPOSITS", "Term Deposits"), TRAVEL_CARDS("TRAVEL_CARDS", "Travel Cards"),
	REGULATED_TRUST_ACCOUNTS("REGULATED_TRUST_ACCOUNTS", "Regulated Trusts"),
	RESIDENTIAL_MORTGAGES("RESIDENTIAL_MORTGAGES", "Residential Mortgages"),
	CRED_AND_CHRG_CARDS("CRED_AND_CHRG_CARDS", "Credit and Charge Cards"), PERS_LOANS("PERS_LOANS", "Personal Loans"),
	MARGIN_LOANS("MARGIN_LOANS", "Margin Loans"), LEASES("LEASES", "Leases"),
	TRADE_FINANCE("TRADE_FINANCE", "Trade Finance"), OVERDRAFTS("OVERDRAFTS", "Overdrafts"),
	BUSINESS_LOANS("BUSINESS_LOANS", "Business Loans");

	private String value;
	private String label;

	BankingProductCategory(String value, String label) {
		this.value = value;
		this.label = label;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static BankingProductCategory fromValue(String text) {
		for (BankingProductCategory b : BankingProductCategory.values()) {
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
