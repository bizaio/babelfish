package io.biza.cdr.babelfish.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.LabelValueEnumInterface;

@BabelFishModel(description = "Banking Transaction: NPP Service Overlay")
public enum BankingTransactionService implements LabelValueEnumInterface {
  // @formatter:off    
  X2P101("X2P1.01", "X2P1.01 Overlay");
  // @formatter:on

  private String value;
  private String label;

  BankingTransactionService(String value, String label) {
    this.value = value;
    this.label = label;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static BankingTransactionService fromValue(String text) {
    for (BankingTransactionService b : BankingTransactionService.values()) {
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
