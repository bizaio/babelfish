package io.biza.babelfish.cdr.abstracts.responses.container;

import io.biza.babelfish.cdr.enumerations.PayloadTypeCustomer;
import io.biza.babelfish.cdr.models.payloads.common.CommonOrganisationV1;
import io.biza.babelfish.cdr.models.payloads.common.CommonPersonV1;
import io.biza.babelfish.cdr.support.AssertTrueBabelfish;

public abstract class ResponseCommonCustomerDataV1 {

  public abstract PayloadTypeCustomer type();

  public abstract CommonPersonV1 person();

  public abstract CommonOrganisationV1 organisation();

  @AssertTrueBabelfish(
      message = "Only person should be populated when customer type is set to PERSON",
      fields = {"type", "person"})
  private boolean isPersonPopulated() {
    if (type() != null && type().equals(PayloadTypeCustomer.PERSON)) {
      return person() != null && organisation() == null;
    } else {
      return true;
    }
  }

  @AssertTrueBabelfish(
      message = "Only organisation should be populated when customer type is set to ORGANISATION",
      fields = {"type", "organisation"})
  private boolean isOrganisationPopulated() {
    if (type() != null && type().equals(PayloadTypeCustomer.ORGANISATION)) {
      return organisation() != null && person() == null;
    } else {
      return true;
    }
  }
}
