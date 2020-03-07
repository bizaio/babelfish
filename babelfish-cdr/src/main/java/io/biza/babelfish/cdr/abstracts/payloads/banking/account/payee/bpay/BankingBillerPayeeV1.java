package io.biza.babelfish.cdr.abstracts.payloads.banking.account.payee.bpay;

import io.biza.babelfish.cdr.support.AssertTrueBabelfish;

public abstract class BankingBillerPayeeV1 {
  public abstract String billerCode();

  public abstract String billerName();

  public abstract String crn();

  @AssertTrueBabelfish(message = "BPAY CRN of Card Format MUST be Masked", fields = {"crn"})
  private boolean isCrnMasked() {
    if (crn() != null && crn().matches("(\\w{4} ){3}\\w{4}")) {
      if (crn().matches("(x{4} ){3}\\w{4}")) {
        return true;
      } else {
        return false;
      }
    } else {
      return true;
    }
  }

  @AssertTrueBabelfish(message = "BPAY CRN of Card Format MUST be Masked", fields = {"billerCode"})
  private boolean isBillerCodeCompliant() {
    if (billerCode() == null) {
      return true;
    }
    // From BPAY's Developer site:
    // The biller code must be a numeric value with 3 to 10 digits.
    if (billerCode().matches("\\d{3,10}")) {
      return true;
    }
    return false;

  }
}
