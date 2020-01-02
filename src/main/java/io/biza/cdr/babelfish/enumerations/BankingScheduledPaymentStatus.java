package io.biza.cdr.babelfish.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.LabelValueEnumInterface;

@BabelFishModel(description = "Banking: Scheduled Payment Status")
public enum BankingScheduledPaymentStatus implements LabelValueEnumInterface {
  // @formatter:off
  ACTIVE("ACTIVE", "Active Scheduled Payment"),
  SKIP("SKIP", "Active but Skip Next Scheduled Payment"),
  INACTIVE("INACTIVE", "Inactive Scheduled Payment");
  // @formatter:on

  private String value;
  private String label;

  BankingScheduledPaymentStatus(String value, String label) {
    this.value = value;
    this.label = label;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static BankingScheduledPaymentStatus fromValue(String text) {
    for (BankingScheduledPaymentStatus b : BankingScheduledPaymentStatus.values()) {
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
