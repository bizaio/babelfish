package io.biza.cdr.babelfish.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.LabelValueEnumInterface;

@BabelFishModel(description = "Payload Type: Banking Account")
public enum PayloadTypeBankingAccount implements LabelValueEnumInterface {
  // @formatter:off
  TERM_DEPOSIT("termDeposit", "Term Deposit Account Type"),
  CREDIT_CARD("creditCard", "Credit Card Account Type"),
  LOAN("loan", "Loan Account Type");
  // @formatter:on

  private String value;
  private String label;

  PayloadTypeBankingAccount(String value, String label) {
    this.value = value;
    this.label = label;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static PayloadTypeBankingAccount fromValue(String text) {
    for (PayloadTypeBankingAccount b : PayloadTypeBankingAccount.values()) {
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
