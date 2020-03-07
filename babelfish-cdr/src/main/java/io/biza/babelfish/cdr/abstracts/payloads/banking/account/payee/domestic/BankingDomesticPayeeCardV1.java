package io.biza.babelfish.cdr.abstracts.payloads.banking.account.payee.domestic;

import javax.validation.constraints.AssertTrue;

public abstract class BankingDomesticPayeeCardV1 {

  public abstract String cardNumber();

  @AssertTrue(message = "Card Number MUST be Masked PAN Format")
  private boolean isPanMasked() {
    if (cardNumber() == null) {
      return true;
    }
    // We check masked PAN by checking for explicit types that are unmasked
    if (cardNumber().matches("\\d{4}-\\d{4}-\\d{4}-\\d{4}")
        || cardNumber().matches("\\d{4} \\d{4} \\d{4} \\d{4}") || cardNumber().matches("\\d{16}")) {
      return false;
    } else {
      return true;
    }
  }
}
