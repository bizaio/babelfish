package io.biza.cdr.babelfish.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.LabelValueEnumInterface;

@BabelFishModel(description = "Banking Payee Type with All")
public enum BankingPayeeTypeWithAll implements LabelValueEnumInterface {
  // @formatter:off
  ALL("ALL", "All Payee Types"),
  DOMESTIC("DOMESTIC", "Domestic Payee Type"),
  INTERNATIONAL("INTERNATIONAL", "International Payee Type"),
  BILLER("BILLER", "BPay Biller Type");
  // @formatter:on

  private String value;
  private String label;

  BankingPayeeTypeWithAll(String value, String label) {
    this.value = value;
    this.label = label;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static BankingPayeeTypeWithAll fromValue(String text) {
    for (BankingPayeeTypeWithAll b : BankingPayeeTypeWithAll.values()) {
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
