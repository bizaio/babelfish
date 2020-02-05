package io.biza.babelfish.cdr.abstracts.payloads.common;

import java.util.List;
import javax.validation.constraints.AssertTrue;
import io.biza.babelfish.cdr.enumerations.AddressPurpose;
import io.biza.babelfish.cdr.models.payloads.common.CommonPhysicalAddressWithPurposeV1;

public abstract class CommonOrganisationDetailV1 extends CommonOrganisationV1 {

  public abstract List<CommonPhysicalAddressWithPurposeV1> physicalAddresses();

  @AssertTrue(
      message = "Physical Addresses must contain one and only one address of REGISTERED purpose and zero or one addresses of MAIL purpose")
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
