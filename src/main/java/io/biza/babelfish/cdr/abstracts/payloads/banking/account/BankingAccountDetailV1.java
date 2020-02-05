package io.biza.babelfish.cdr.abstracts.payloads.banking.account;

import io.biza.babelfish.cdr.enumerations.PayloadTypeBankingAccount;
import io.biza.babelfish.cdr.models.payloads.banking.account.BankingCreditCardAccountV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.BankingLoanAccountV1;
import io.biza.babelfish.cdr.models.payloads.banking.account.BankingTermDepositAccountV1;
import io.biza.babelfish.cdr.support.AssertTrueBabelfish;
import io.biza.babelfish.cdr.support.FormatChecker;

public abstract class BankingAccountDetailV1 {

  public abstract PayloadTypeBankingAccount specificAccountUType();

  public abstract String accountNumber();

  public abstract BankingTermDepositAccountV1 termDeposit();

  public abstract BankingCreditCardAccountV1 creditCard();

  public abstract BankingLoanAccountV1 loan();

  @AssertTrueBabelfish(message = "Account Number must not be an unmasked PAN",
      fields = {"accountNumber"})
  private boolean isAccountNumberUnmaskedPan() {
    return FormatChecker.isPanNumber(accountNumber()) ? false : true;
  }

  @AssertTrueBabelfish(
      message = "Account Type must supply matching Account Type Specific Information",
      fields = {"specificAccountUType", "creditCard", "termDeposit", "loan"})
  private boolean isAccountTypeCorrect() {
    // Return true if not defined, @NotNull will pick this up later
    if (specificAccountUType() == null) {
      return true;
    }

    if (specificAccountUType().equals(PayloadTypeBankingAccount.TERM_DEPOSIT)) {
      return termDeposit() != null && creditCard() == null && loan() == null ? true : false;
    } else if (specificAccountUType().equals(PayloadTypeBankingAccount.CREDIT_CARD)) {
      return creditCard() != null && termDeposit() == null && loan() == null ? true : false;
    } else if (specificAccountUType().equals(PayloadTypeBankingAccount.LOAN)) {
      return loan() != null && creditCard() == null && termDeposit() == null ? true : false;
    }
    return false;
  }
}
