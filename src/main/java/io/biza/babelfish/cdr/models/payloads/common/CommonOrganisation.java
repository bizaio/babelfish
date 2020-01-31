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
package io.biza.babelfish.cdr.models.payloads.common;

import java.util.Arrays;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import io.biza.babelfish.cdr.enumerations.CommonOrganisationType;
import io.biza.babelfish.cdr.support.AssertTrueBabelfish;
import io.biza.babelfish.cdr.support.FormatChecker;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Valid
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)


public class CommonOrganisation extends
    io.biza.babelfish.cdr.abstracts.payloads.common.organisation.CommonOrganisation<CommonOrganisation> {

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
