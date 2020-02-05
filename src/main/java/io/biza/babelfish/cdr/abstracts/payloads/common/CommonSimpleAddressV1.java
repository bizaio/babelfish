package io.biza.babelfish.cdr.abstracts.payloads.common;

import java.util.Locale;
import io.biza.babelfish.cdr.support.AssertTrueBabelfish;

public abstract class CommonSimpleAddressV1 {

  public abstract String state();

  public abstract Locale country();

  public abstract String postcode();

  @AssertTrueBabelfish(
      message = "Postcode and State must be correct when Country is defined as Australia (en-AU)",
      fields = {"state", "country", "postcode"})
  private boolean isAustralianFieldChecks() {
    if (country().equals(Locale.forLanguageTag("en-AU"))) {
      return postcode() != null && state() != null ? true : false;
    }
    return true;
  }
}
