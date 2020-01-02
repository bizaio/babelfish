package io.biza.cdr.babelfish.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.LabelValueEnumInterface;

@BabelFishModel(description = "Banking Account Loan Repayment Type")
public enum BankingLoanRepaymentType implements LabelValueEnumInterface {
  // @formatter:off
  INTEREST_ONLY("INTEREST_ONLY", "Interest Only Repayment"),
  PRINCIPAL_AND_INTEREST("PRINCIPAL_AND_INTEREST", "Principal and Interest Repayment");
  // @formatter:on

  private String value;
  private String label;

  BankingLoanRepaymentType(String value, String label) {
    this.value = value;
    this.label = label;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static BankingLoanRepaymentType fromValue(String text) {
    for (BankingLoanRepaymentType b : BankingLoanRepaymentType.values()) {
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
