/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU Lesser General Public License as published by the Free Software Foundation, either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *******************************************************************************/
package io.biza.babelfish.cdr.v1.model.banking;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import io.biza.babelfish.enumerations.cdr.PayloadTypeBankingDomesticPayeePayId;
import io.biza.babelfish.support.FormatChecker;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Valid
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BankingDomesticPayeePayId extends
    io.biza.babelfish.cdr.model.banking.BankingDomesticPayeePayId<BankingDomesticPayeePayId> {

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
