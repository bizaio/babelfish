package io.biza.cdr.babelfish.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.LabelValueEnumInterface;

@BabelFishModel(description = "Banking: Transaction Type")
public enum BankingTransactionType implements LabelValueEnumInterface {
  // @formatter:off  
  FEE("FEE", "Prescribed Account Fee"),
  INTEREST_CHARGED("INTEREST_CHARGED", "Interest Charged"),
  INTEREST_PAID("INTEREST_PAID", "Interest Paid"),
  TRANSFER_OUTGOING("TRANSFER_OUTGOING", "Account Transfer (Outgoing)"),
  TRANSFER_INCOMING("TRANSFER_INCOMING", "Account Transfer (Incoming)"),
  PAYMENT("PAYMENT", "External Account Payment"),
  DIRECT_DEBIT("DIRECT_DEBIT", "Direct Debit Charge"),
  OTHER("OTHER", "Other Transaction");
  // @formatter:on

  private String value;
  private String label;

  BankingTransactionType(String value, String label) {
    this.value = value;
    this.label = label;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static BankingTransactionType fromValue(String text) {
    for (BankingTransactionType b : BankingTransactionType.values()) {
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
