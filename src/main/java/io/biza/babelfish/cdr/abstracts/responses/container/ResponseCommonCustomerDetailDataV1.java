package io.biza.babelfish.cdr.abstracts.responses.container;

import javax.validation.constraints.AssertTrue;
import io.biza.babelfish.cdr.abstracts.models.common.CommonOrganisationDetailV1;
import io.biza.babelfish.cdr.enumerations.PayloadTypeCustomer;
import io.biza.babelfish.cdr.models.payloads.common.CommonPersonDetailV1;

public abstract class ResponseCommonCustomerDetailDataV1 {
  public abstract PayloadTypeCustomer type();
  public abstract CommonPersonDetailV1 person();
  public abstract CommonOrganisationDetailV1 organisation();
  
  @AssertTrue(message = "Only person should be populated when customer type is set to PERSON")
  private boolean isPersonPopulated() {
    if (type() != null && type().equals(PayloadTypeCustomer.PERSON)) {
      return person() != null && organisation() == null;
    } else {
      return true;
    }
  }

  @AssertTrue(
      message = "Only organisation should be populated when customer type is set to ORGANISATION")
  private boolean isOrganisationPopulated() {
    if (type() != null && type().equals(PayloadTypeCustomer.ORGANISATION)) {
      return organisation() != null && person() == null;
    } else {
      return true;
    }
  }
  
}
