package io.biza.babelfish.cdr.abstracts.payloads.banking.transaction;

import java.math.BigDecimal;
import io.biza.babelfish.cdr.models.payloads.banking.account.transaction.BankingTransactionDetailExtendedDataV1;
import io.biza.babelfish.cdr.support.AssertTrueBabelfish;
import io.biza.babelfish.cdr.support.FormatChecker;

public abstract class BankingTransactionDetailV1 extends BankingTransactionV1 {

  public abstract BigDecimal amount();

  public abstract BankingTransactionDetailExtendedDataV1 extendedData();

  @AssertTrueBabelfish(message = "Payer must be populated for inbound payments",
      fields = {"extendedData.payer"})
  private boolean isPayerPopulated() {
    return FormatChecker.isDefined(amount()) && FormatChecker.isPositive(amount())
        ? extendedData() != null && FormatChecker.isNotEmpty(extendedData().payer())
        : true;
  }

  @AssertTrueBabelfish(message = "Payee must be populated for outbound payments",
      fields = {"extendedData.payee"})
  private boolean isPayeePopulated() {
    return FormatChecker.isDefined(amount()) && FormatChecker.isNegative(amount())
        ? extendedData() != null && FormatChecker.isNotEmpty(extendedData().payee())
        : true;
  }
}
