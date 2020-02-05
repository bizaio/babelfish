package io.biza.babelfish.cdr.abstracts.payloads.banking.account.payee.international;

import io.biza.babelfish.cdr.support.AssertTrueBabelfish;
import io.biza.babelfish.cdr.support.FormatChecker;

public abstract class BankingInternationalPayeeBankDetailsV1 {

  public abstract String fedWireNumber();

  @AssertTrueBabelfish(message = "FedWire Codes are ALWAYS exactly 9 digits",
      fields = {"fedWireNumber"})
  private boolean isFedWireNumberOk() {
    return FormatChecker.isDefined(fedWireNumber()) ? FormatChecker.isFedWireFormat(fedWireNumber())
        : true;
  }

}
