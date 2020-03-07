package io.biza.babelfish.cdr.abstracts.payloads.banking.account.payee.scheduled;

import io.biza.babelfish.cdr.enumerations.PayloadTypeBankingScheduledPaymentTo;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.bpay.BankingBillerPayeeV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.domestic.BankingDomesticPayeeV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.international.BankingInternationalPayeeV1;
import io.biza.babelfish.cdr.support.AssertTrueBabelfish;
import io.biza.babelfish.cdr.support.FormatChecker;

public abstract class BankingScheduledPaymentToV1 {

  public abstract PayloadTypeBankingScheduledPaymentTo type();

  public abstract String accountId();

  public abstract String payeeId();

  public abstract BankingDomesticPayeeV1 domestic();

  public abstract BankingBillerPayeeV1 biller();

  public abstract BankingInternationalPayeeV1 international();

  @AssertTrueBabelfish(message = "Only Account ID must be present when uType is set to ACCOUNT_ID",
      fields = {"type", "accountId"})
  private boolean isAccountIdPopulated() {
    return FormatChecker.isDefined(type())
        && type().equals(PayloadTypeBankingScheduledPaymentTo.ACCOUNT_ID)
            ? FormatChecker.isNotEmpty(accountId()) && !FormatChecker.isDefined(payeeId())
                && domestic() == null && biller() == null && international() == null
            : true;
  }

  @AssertTrueBabelfish(message = "Only Payee ID must be present when uType is set to PAYEE_ID",
      fields = {"type", "payeeId"})
  private boolean isPayeeIdPopulated() {
    return FormatChecker.isDefined(type())
        && type().equals(PayloadTypeBankingScheduledPaymentTo.PAYEE_ID)
            ? FormatChecker.isNotEmpty(payeeId()) && !FormatChecker.isDefined(accountId())
                && domestic() == null && biller() == null && international() == null
            : true;
  }

  @AssertTrueBabelfish(
      message = "Only Domestic details must be present when uType is set to DOMESTIC",
      fields = {"type", "domestic"})
  private boolean isDomesticPopulated() {
    return FormatChecker.isDefined(type())
        && type().equals(PayloadTypeBankingScheduledPaymentTo.DOMESTIC)
            ? domestic() != null && !FormatChecker.isDefined(accountId())
                && !FormatChecker.isDefined(payeeId()) && biller() == null
                && international() == null
            : true;
  }

  @AssertTrueBabelfish(message = "Only Biller details must be present when uType is set to BILLER",
      fields = {"type", "biller"})
  private boolean isBillerPopulated() {
    return FormatChecker.isDefined(type())
        && type().equals(PayloadTypeBankingScheduledPaymentTo.BILLER)
            ? biller() != null && !FormatChecker.isDefined(accountId())
                && !FormatChecker.isDefined(payeeId()) && domestic() == null
                && international() == null
            : true;
  }

  @AssertTrueBabelfish(
      message = "Only International details must be present when uType is set to INTERNATIONAL",
      fields = {"type", "biller"})
  private boolean isInternationalPopulated() {
    return FormatChecker.isDefined(type())
        && type().equals(PayloadTypeBankingScheduledPaymentTo.INTERNATIONAL)
            ? international() != null && !FormatChecker.isDefined(accountId())
                && !FormatChecker.isDefined(payeeId()) && biller() == null && domestic() == null
            : true;
  }

}
