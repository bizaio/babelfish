package io.biza.babelfish.cdr.abstracts.payloads.common;

import javax.validation.constraints.AssertTrue;

public abstract class CommonPAFAddressV1 {

  public abstract Integer thoroughfareNumber1();

  public abstract String thoroughfareNumber1Suffix();

  public abstract Integer thoroughfareNumber2();

  public abstract String thoroughfareNumber2Suffix();

  @AssertTrue(
      message = "Thoroughfare Suffixes should only be set when Thoroughfare Numbers are set")
  private boolean isInvalidSuffixes() {
    if (thoroughfareNumber1Suffix() != null && thoroughfareNumber1() == null) {
      return false;
    }
    if (thoroughfareNumber2Suffix() != null && thoroughfareNumber2() == null) {
      return false;
    }
    return true;
  }

  @AssertTrue(
      message = "Thoroughfare Number 2 must only be set when Thoroughfare Number 1 exists and must be greater than Thoroughfare Number 1")
  private boolean isThoroughFareNumber2LargerThan1() {
    if (thoroughfareNumber1() == null) {
      if (thoroughfareNumber2() == null) {
        return true;
      } else {
        return false;
      }
    } else {
      if (thoroughfareNumber2() == null) {
        return false;
      } else {
        if (thoroughfareNumber2() > thoroughfareNumber1()) {
          return true;
        } else {
          return false;
        }
      }
    }
  }
}
