package io.biza.babelfish.cdr.abstracts.payloads.common;

import javax.validation.constraints.AssertTrue;
import io.biza.babelfish.cdr.enumerations.PayloadTypeAddress;
import io.biza.babelfish.cdr.models.payloads.common.CommonPAFAddressV1;
import io.biza.babelfish.cdr.models.payloads.common.CommonSimpleAddressV1;

public abstract class CommonPhysicalAddressV1 {

  public abstract PayloadTypeAddress type();

  public abstract CommonSimpleAddressV1 simple();

  public abstract CommonPAFAddressV1 paf();

  @AssertTrue(
      message = "One and only one of simple or paf should be populated based on addressUType")
  public boolean isAddressTypeSpecifiedIsPopulated() {
    if (type() == null) {
      return true;
    }

    if (type().equals(PayloadTypeAddress.SIMPLE)) {
      return simple() != null && paf() == null;
    } else if (type().equals(PayloadTypeAddress.PAF)) {
      return paf() != null && simple() == null;
    }
    return false;
  }
}
