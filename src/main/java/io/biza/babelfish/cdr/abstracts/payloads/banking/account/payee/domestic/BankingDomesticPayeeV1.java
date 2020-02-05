package io.biza.babelfish.cdr.abstracts.payloads.banking.account.payee.domestic;

import javax.validation.constraints.AssertTrue;
import io.biza.babelfish.cdr.enumerations.PayloadTypeBankingDomesticPayee;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.domestic.BankingDomesticPayeeAccountV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.domestic.BankingDomesticPayeeCardV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.payee.domestic.BankingDomesticPayeePayIdV1;

public abstract class BankingDomesticPayeeV1 {

  public abstract PayloadTypeBankingDomesticPayee payeeAccountType();

  public abstract BankingDomesticPayeeAccountV1 account();

  public abstract BankingDomesticPayeeCardV1 card();

  public abstract BankingDomesticPayeePayIdV1 payId();

  @AssertTrue(
      message = "Payee Account Type must supply matching Payee Account Type Specific Information")
  private boolean isAccountTypeCorrectlyPopulated() {
    if (payeeAccountType() == null) {
      return true;
    }

    if (payeeAccountType().equals(PayloadTypeBankingDomesticPayee.ACCOUNT)) {
      return account() != null && card() == null && payId() == null ? true : false;
    } else if (payeeAccountType().equals(PayloadTypeBankingDomesticPayee.CARD)) {
      return card() != null && account() == null && payId() == null ? true : false;
    } else if (payeeAccountType().equals(PayloadTypeBankingDomesticPayee.PAY_ID)) {
      return payId() != null && account() == null && card() == null ? true : false;
    }
    return false;
  }
}
