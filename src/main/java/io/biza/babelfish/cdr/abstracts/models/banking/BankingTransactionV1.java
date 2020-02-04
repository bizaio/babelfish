package io.biza.babelfish.cdr.abstracts.models.banking;

import java.time.OffsetDateTime;
import javax.validation.constraints.AssertTrue;
import io.biza.babelfish.cdr.enumerations.BankingTransactionStatus;

public abstract class BankingTransactionV1 {
  
  public abstract BankingTransactionStatus status();
  public abstract OffsetDateTime postingDateTime();

  @AssertTrue(message = "Posting Date and Time must be set when status is POSTED")
  private boolean isPostedDateDefined() {
    return BankingTransactionStatus.POSTED.equals(status())
        ? (postingDateTime() != null ? true : false)
        : true;
  }
}
