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
package io.biza.babelfish.cdr.models.payloads.banking.account.payee.domestic;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import io.biza.babelfish.cdr.enumerations.PayloadTypeBankingDomesticPayeePayId;
import io.biza.babelfish.cdr.support.FormatChecker;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Valid
@ToString
@EqualsAndHashCode
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Domestic Payee PayID Detail", name = "BankingDomesticPayeePayId")
public class BankingDomesticPayeePayIdV1 {
  @Schema(description = "The name assigned to the PayID by the owner of the PayID")
  @JsonProperty("name")
  String name;

  @Schema(description = "The identifier of the PayID (dependent on type)", required = true)
  @NotNull
  @JsonProperty("identifier")
  String identifier;

  @Schema(description = "The type of the PayID", required = true)
  @NotNull
  @JsonProperty("type")
  @Valid
  PayloadTypeBankingDomesticPayeePayId type;
  
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
