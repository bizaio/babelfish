package io.biza.babelfish.cdr.abstracts.models.common;

import java.util.Arrays;
import io.biza.babelfish.cdr.enumerations.CommonOrganisationType;
import io.biza.babelfish.cdr.support.AssertTrueBabelfish;
import io.biza.babelfish.cdr.support.FormatChecker;

public abstract class CommonOrganisationV1 {
  
  public abstract CommonOrganisationType organisationType();
  public abstract String abn();
  public abstract String acn();
  
  @AssertTrueBabelfish(message = "ACN must be populated when organisationType is COMPANY",
      fields = {"acn"})
  private boolean isAcnPopulated() {
    return FormatChecker.isDefined(organisationType())
        ? (Arrays.asList(new CommonOrganisationType[] {CommonOrganisationType.COMPANY})
            .contains(organisationType()) ? FormatChecker.isDefined(acn()) : true)
        : true;
  }

  @AssertTrueBabelfish(message = "ACN when defined must pass ASIC checksum checks",
      fields = {"acn"})
  private boolean isAcnValidated() {
    return FormatChecker.isDefined(acn()) ? FormatChecker.isAcn(acn()) : true;
  }

  @AssertTrueBabelfish(message = "ABN when defined must pass ABR checksum checks", fields = {"abn"})
  private boolean isAbnValidated() {
    return FormatChecker.isDefined(abn()) ? FormatChecker.isAbn(abn()) : true;
  }
}
