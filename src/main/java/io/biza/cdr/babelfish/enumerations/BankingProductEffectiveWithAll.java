package io.biza.cdr.babelfish.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.LabelValueEnumInterface;

@BabelFishModel(description = "Banking Product Effective Filter with All")
public enum BankingProductEffectiveWithAll implements LabelValueEnumInterface {
  // @formatter:off
  CURRENT("CURRENT", "Products Currently Available in Market"), 
  FUTURE("FUTURE", "Products Currently Available in the Future"), 
  ALL("ALL", "All Products available now and in the future");
  // @formatter:on

  private String value;
  private String label;

  BankingProductEffectiveWithAll(String value, String label) {
    this.value = value;
    this.label = label;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static BankingProductEffectiveWithAll fromValue(String text) {
    for (BankingProductEffectiveWithAll b : BankingProductEffectiveWithAll.values()) {
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
