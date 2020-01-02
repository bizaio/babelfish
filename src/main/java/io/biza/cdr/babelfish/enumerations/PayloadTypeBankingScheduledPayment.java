package io.biza.cdr.babelfish.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.LabelValueEnumInterface;

@BabelFishModel(description = "Payload Type: Banking Scheduled Payment Target")
public enum PayloadTypeBankingScheduledPayment implements LabelValueEnumInterface {
  // @formatter:off
  ACCOUNT_ID("accountId", "Transfer to a specific Account ID"),
  PAYEE_ID("payeeId", "Payment to a specific Payee ID"),
  DOMESTIC("domestic", "Domestic Payment"),
  BILLER("biller", "BPAY Payment"),
  INTERNATIONAL("international", "International Payment");
  // @formatter:on

  private String value;
  private String label;

  PayloadTypeBankingScheduledPayment(String value, String label) {
    this.value = value;
    this.label = label;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static PayloadTypeBankingScheduledPayment fromValue(String text) {
    for (PayloadTypeBankingScheduledPayment b : PayloadTypeBankingScheduledPayment.values()) {
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
