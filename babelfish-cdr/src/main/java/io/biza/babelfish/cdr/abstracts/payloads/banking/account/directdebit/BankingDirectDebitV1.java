package io.biza.babelfish.cdr.abstracts.payloads.banking.account.directdebit;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import javax.validation.constraints.AssertTrue;

public abstract class BankingDirectDebitV1 {

  public abstract OffsetDateTime lastDebitDateTime();

  public abstract BigDecimal lastDebitAmount();

  @AssertTrue(message = "If lastDebitDateTime set then lastDebitAmount should be PRESENT")
  private boolean isLastDebitAmountPresent() {
    if (lastDebitDateTime() == null) {
      return true;
    } else {
      return lastDebitAmount() != null;
    }
  }

  @AssertTrue(message = "If lastDebitAmount set then lastDebitDateTime should be PRESENT")
  private boolean isLastDebitDateTimePresent() {
    if (lastDebitAmount() == null) {
      return true;
    } else {
      return lastDebitDateTime() != null;
    }
  }
}
