package io.biza.babelfish.cdr.abstracts.payloads.banking.transaction;

import java.time.OffsetDateTime;
import io.biza.babelfish.cdr.enumerations.BankingTransactionStatus;
import io.biza.babelfish.cdr.support.AssertTrueBabelfish;

public abstract class BankingTransactionV1 {

  public abstract BankingTransactionStatus status();

  public abstract OffsetDateTime postingDateTime();

  @AssertTrueBabelfish(message = "Posting Date and Time must be set when status is POSTED",
      fields = {"postingDateTime", "status"})
  private boolean isPostedDateDefined() {
    return BankingTransactionStatus.POSTED.equals(status())
        ? (postingDateTime() != null ? true : false)
        : true;
  }
}
