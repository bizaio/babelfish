package io.biza.cdr.babelfish.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.LabelValueEnumInterface;

@BabelFishModel(description = "Payload Type: Banking Payee Type")
public enum PayloadTypeBankingPayee implements LabelValueEnumInterface {
  // @formatter:off
  DOMESTIC("domestic", "Domestic Payee"),
  BILLER("biller", "BPAY Payee"),
  INTERNATIONAL("international", "International Payee");
  // @formatter:on

  private String value;
  private String label;

  PayloadTypeBankingPayee(String value, String label) {
    this.value = value;
    this.label = label;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static PayloadTypeBankingPayee fromValue(String text) {
    for (PayloadTypeBankingPayee b : PayloadTypeBankingPayee.values()) {
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
