package io.biza.cdr.babelfish.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.LabelValueEnumInterface;

@BabelFishModel(description = "Payload Type: Transaction Extension Type")
public enum PayloadTypeTransactionExtension implements LabelValueEnumInterface {
  // @formatter:off    
  X2P101("x2p101Payload", "NPP X2P1.01 Transaction Type");
  // @formatter:on

  private String value;
  private String label;

  PayloadTypeTransactionExtension(String value, String label) {
    this.value = value;
    this.label = label;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static PayloadTypeTransactionExtension fromValue(String text) {
    for (PayloadTypeTransactionExtension b : PayloadTypeTransactionExtension.values()) {
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
