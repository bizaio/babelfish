package io.biza.cdr.babelfish.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.LabelValueEnumInterface;

@BabelFishModel(description = "Banking Product: Rate Tier Application Method")
public enum BankingProductRateTierApplicationMethod implements LabelValueEnumInterface {
  // @formatter:off
  WHOLE_BALANCE("WHOLE_BALANCE", "Rate is applied to the entire balance"),
  PER_TIER("PER_TIER", "Rate is applied on a per tier basis");
  // @formatter:on

  private String value;
  private String label;

  BankingProductRateTierApplicationMethod(String value, String label) {
    this.value = value;
    this.label = label;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static BankingProductRateTierApplicationMethod fromValue(String text) {
    for (BankingProductRateTierApplicationMethod b : BankingProductRateTierApplicationMethod.values()) {
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
