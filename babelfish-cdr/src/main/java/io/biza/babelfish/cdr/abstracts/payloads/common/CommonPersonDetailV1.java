package io.biza.babelfish.cdr.abstracts.payloads.common;

import java.util.List;
import io.biza.babelfish.cdr.enumerations.AddressPurpose;
import io.biza.babelfish.cdr.models.payloads.common.CommonEmailAddressV1;
import io.biza.babelfish.cdr.models.payloads.common.CommonPhysicalAddressWithPurposeV1;
import io.biza.babelfish.cdr.support.AssertTrueBabelfish;

public abstract class CommonPersonDetailV1 {

  public abstract List<CommonPhysicalAddressWithPurposeV1> physicalAddresses();

  public abstract List<CommonEmailAddressV1> emailAddresses();

  @AssertTrueBabelfish(message = "Only one email address can be designated as Preferred",
      fields = {"emailAddress"})
  private boolean isSinglePreferredEmailAddress() {
    if (emailAddresses() == null) {
      return true;
    }

    int preferredCount = 0;
    for (CommonEmailAddressV1 oneAddress : emailAddresses()) {
      if (oneAddress.purpose() == null) {
        continue;
      }
      if (oneAddress.isPreferred() != null && oneAddress.isPreferred()) {
        preferredCount++;
        if (preferredCount > 1) {
          return false;
        }
      }
    }

    return true;
  }

  @AssertTrueBabelfish(
      message = "Physical Addresses must contain one and only one address of REGISTERED purpose and zero or one addresses of MAIL purpose",
      fields = {"physicalAddresses"})
  private boolean isPhysicalAddressesCorrect() {
    if (physicalAddresses() == null) {
      return true;
    }

    int registeredCount = 0;
    int mailCount = 0;
    for (CommonPhysicalAddressWithPurposeV1 oneAddress : physicalAddresses()) {
      if (oneAddress.purpose() == null) {
        continue;
      }
      if (oneAddress.purpose().equals(AddressPurpose.REGISTERED)) {
        registeredCount++;
      }
      if (oneAddress.purpose().equals(AddressPurpose.MAIL)) {
        mailCount++;
      }
    }

    if (registeredCount == 1 && mailCount <= 1) {
      return true;
    }
    return false;
  }
}
