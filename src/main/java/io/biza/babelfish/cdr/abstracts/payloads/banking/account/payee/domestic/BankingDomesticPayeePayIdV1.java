package io.biza.babelfish.cdr.abstracts.payloads.banking.account.payee.domestic;

import javax.validation.constraints.AssertTrue;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import io.biza.babelfish.cdr.enumerations.PayloadTypeBankingDomesticPayeePayId;
import io.biza.babelfish.cdr.support.FormatChecker;

public abstract class BankingDomesticPayeePayIdV1 {

  public abstract PayloadTypeBankingDomesticPayeePayId type();

  public abstract String identifier();

  @AssertTrue(message = "Identifier must be a valid telephone number when type is TELEPHONE")
  private boolean isValidTelephoneNumber() {
    if (type() != null && type().equals(PayloadTypeBankingDomesticPayeePayId.TELEPHONE)) {
      if (identifier() == null) {
        return false;
      } else {
        return FormatChecker.isPhoneNumber(identifier(), PhoneNumberUtil.PhoneNumberFormat.NATIONAL,
            false)
            || FormatChecker.isPhoneNumber(identifier(),
                PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL, false)
            || FormatChecker.isPhoneNumber(identifier(), PhoneNumberUtil.PhoneNumberFormat.RFC3966,
                false);
      }
    }
    return true;
  }

  @AssertTrue(message = "Identifier must be a valid Australian Business Number when type is ABN")
  private boolean isValidAustralianBusinessNumber() {
    if (type() != null && type().equals(PayloadTypeBankingDomesticPayeePayId.ABN)) {
      if (identifier() == null) {
        return false;
      } else {
        return FormatChecker.isAbn(identifier());
      }
    }
    return true;
  }

  @AssertTrue(message = "Identifier must be a valid non local email address when type is EMAIL")
  private boolean isValidEmailAddress() {
    if (type() != null && type().equals(PayloadTypeBankingDomesticPayeePayId.EMAIL)) {
      if (identifier() == null) {
        return false;
      } else {
        return FormatChecker.isEmail(identifier());
      }
    }
    return true;
  }
}
