package io.biza.cdr.babelfish.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.LabelValueEnumInterface;

@BabelFishModel(description = "Banking Account Status with All Status")
public enum BankingAccountStatusWithAll implements LabelValueEnumInterface {
  // @formatter:off    
  OPEN("OPEN", "Account is Open"),
  CLOSED("CLOSED", "Account is Closed"),
  ALL("CLOSED", "All Accounts");
  // @formatter:on

  private String value;
  private String label;

  BankingAccountStatusWithAll(String value, String label) {
    this.value = value;
    this.label = label;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static BankingAccountStatusWithAll fromValue(String text) {
    for (BankingAccountStatusWithAll b : BankingAccountStatusWithAll.values()) {
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
