package io.biza.babelfish.cdr.abstracts.payloads.banking;

import io.biza.babelfish.cdr.enumerations.PayloadTypeBankingPayee;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.bpay.BankingBillerPayeeV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.domestic.BankingDomesticPayeeV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.international.BankingInternationalPayeeV1;
import io.biza.babelfish.cdr.support.AssertTrueBabelfish;

public abstract class BankingPayeeDetailV1 {

  public abstract PayloadTypeBankingPayee type();

  public abstract BankingDomesticPayeeV1 domestic();

  public abstract BankingBillerPayeeV1 biller();

  public abstract BankingInternationalPayeeV1 international();

  @AssertTrueBabelfish(
      message = "One and only one of payeeUType specified payload of domestic, biller and international should be present",
      fields = {"payeeUType", "domestic", "biller", "international"})
  private boolean isUTypePopulated() {
    if (type() == null) {
      return true;
    }
    if (type().equals(PayloadTypeBankingPayee.DOMESTIC)) {
      return domestic() != null && biller() == null && international() == null;
    } else if (type().equals(PayloadTypeBankingPayee.BILLER)) {
      return biller() != null && domestic() == null && international() == null;
    } else if (type().equals(PayloadTypeBankingPayee.INTERNATIONAL)) {
      return international() != null && biller() == null && domestic() == null;
    }
    return false;
  }

}
