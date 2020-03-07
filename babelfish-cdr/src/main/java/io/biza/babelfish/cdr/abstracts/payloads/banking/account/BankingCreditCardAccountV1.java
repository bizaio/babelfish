package io.biza.babelfish.cdr.abstracts.payloads.banking.account;

import java.math.BigDecimal;
import io.biza.babelfish.cdr.support.AssertTrueBabelfish;
import io.biza.babelfish.cdr.support.FormatChecker;

public abstract class BankingCreditCardAccountV1 {

  public abstract BigDecimal minPaymentAmount();

  public abstract BigDecimal paymentDueAmount();

  @AssertTrueBabelfish(message = "Minimum Payment Amount should be >= 0",
      fields = {"minPaymentAmount"})
  private boolean isMinimumPaymentAmountZeroOrMore() {
    return FormatChecker.isDefined(minPaymentAmount()) ? FormatChecker.isNatural(minPaymentAmount()) : true;
  }

  @AssertTrueBabelfish(message = "Payment Due Amount should be >= 0", fields = {"paymentDueAmount"})
  private boolean isPaymentDueAmountZeroOrMore() {
    return FormatChecker.isDefined(paymentDueAmount()) ? FormatChecker.isNatural(paymentDueAmount()) : true;
  }

}
