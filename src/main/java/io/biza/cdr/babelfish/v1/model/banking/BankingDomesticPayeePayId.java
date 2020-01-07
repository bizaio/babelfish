/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *******************************************************************************/
package io.biza.cdr.babelfish.v1.model.banking;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import io.biza.cdr.babelfish.support.FormatChecker;
import io.biza.cdr.babelfish.v1.enumerations.PayloadTypeBankingDomesticPayeePayId;
import io.biza.cdr.babelfish.v1.enumerations.PayloadTypeBankingPayee;

@Valid
public class BankingDomesticPayeePayId extends
    io.biza.cdr.babelfish.model.banking.BankingDomesticPayeePayId<BankingDomesticPayeePayId> {

  @AssertTrue(message = "Identifier must be a valid telephone number when type is TELEPHONE")
  private boolean isValidTelephoneNumber() {
    if (type() != null && type().equals(PayloadTypeBankingDomesticPayeePayId.TELEPHONE)) {
      if (identifier() == null) {
        return false;
      } else {
        System.out.println("Phone number check against " + identifier() + " results are " + FormatChecker.isPhoneNumber(identifier(), PhoneNumberUtil.PhoneNumberFormat.NATIONAL)
            + FormatChecker.isPhoneNumber(identifier(),
                PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL)
            + FormatChecker.isPhoneNumber(identifier(), PhoneNumberUtil.PhoneNumberFormat.RFC3966));
        
        return FormatChecker.isPhoneNumber(identifier(), PhoneNumberUtil.PhoneNumberFormat.NATIONAL)
            || FormatChecker.isPhoneNumber(identifier(),
                PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL)
            || FormatChecker.isPhoneNumber(identifier(), PhoneNumberUtil.PhoneNumberFormat.RFC3966);
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
