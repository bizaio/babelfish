package io.biza.cdr.babelfish.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.LabelValueEnumInterface;

@BabelFishModel(description = "Banking Product Lending Rate Interest Payment Configuration")
public enum BankingProductLendingRateInterestPaymentType implements LabelValueEnumInterface {
  // @formatter:off  
  IN_ARREARS("IN_ARREARS", "Interest Paid in Arrears"),
  IN_ADVANCE("IN_ADVANCE", "Interest Paid in Advance");
  // @formatter:on  

  private String value;
  private String label;

  BankingProductLendingRateInterestPaymentType(String value, String label) {
    this.value = value;
    this.label = label;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static BankingProductLendingRateInterestPaymentType fromValue(String text) {
    for (BankingProductLendingRateInterestPaymentType b : BankingProductLendingRateInterestPaymentType
        .values()) {
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
