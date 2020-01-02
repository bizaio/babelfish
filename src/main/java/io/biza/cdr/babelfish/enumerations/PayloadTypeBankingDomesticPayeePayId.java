package io.biza.cdr.babelfish.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.LabelValueEnumInterface;

@BabelFishModel(description = "Payload Type: Domestic Payee PayID Identifier Type")
public enum PayloadTypeBankingDomesticPayeePayId implements LabelValueEnumInterface {
  // @formatter:off
  EMAIL("EMAIL", "Email Address"),
  TELEPHONE("TELEPHONE", "Telephone"),
  ABN("ABN", "Australian Business Number"),
  ORG_IDENTIFIER("ORG_IDENTIFIER", "Organisational Identifier");
  // @formatter:on

  private String value;
  private String label;

  PayloadTypeBankingDomesticPayeePayId(String value, String label) {
    this.value = value;
    this.label = label;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static PayloadTypeBankingDomesticPayeePayId fromValue(String text) {
    for (PayloadTypeBankingDomesticPayeePayId b : PayloadTypeBankingDomesticPayeePayId.values()) {
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
