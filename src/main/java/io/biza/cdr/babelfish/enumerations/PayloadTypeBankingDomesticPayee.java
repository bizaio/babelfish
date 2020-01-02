package io.biza.cdr.babelfish.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.LabelValueEnumInterface;

@BabelFishModel(description = "Payload Type: Domestic Payee")
public enum PayloadTypeBankingDomesticPayee implements LabelValueEnumInterface {
  // @formatter:off
  ACCOUNT("account", "A Standard Australian account defined by BSB/Account Number"),
  CARD("card", "A Payment using a Credit Card Number"),
  PAY_ID("payId", "A PayID recognised by NPP");
  // @formatter:on

  private String value;
  private String label;

  PayloadTypeBankingDomesticPayee(String value, String label) {
    this.value = value;
    this.label = label;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static PayloadTypeBankingDomesticPayee fromValue(String text) {
    for (PayloadTypeBankingDomesticPayee b : PayloadTypeBankingDomesticPayee.values()) {
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
