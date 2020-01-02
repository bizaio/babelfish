package io.biza.cdr.babelfish.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.LabelValueEnumInterface;

@BabelFishModel(description = "Banking: Term Deposit Maturity Instructions")
public enum BankingTermDepositMaturityInstructions implements LabelValueEnumInterface {
  // @formatter:off    
  ROLLED_OVER("ROLLED_OVER", "Rolled Over at Maturity"),
  PAID_OUT_AT_MATURITY("PAID_OUT_AT_MATURITY", "Paid Out at Maturity");
  // @formatter:on

  private String value;
  private String label;

  BankingTermDepositMaturityInstructions(String value, String label) {
    this.value = value;
    this.label = label;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static BankingTermDepositMaturityInstructions fromValue(String text) {
    for (BankingTermDepositMaturityInstructions b : BankingTermDepositMaturityInstructions.values()) {
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
