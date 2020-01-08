/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *******************************************************************************/
package io.biza.cdr.babelfish.v1.model.common;

import java.util.Arrays;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import io.biza.cdr.babelfish.support.FormatChecker;
import io.biza.cdr.babelfish.v1.enumerations.CommonOrganisationType;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Valid
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)


public class CommonOrganisation
    extends io.biza.cdr.babelfish.model.common.CommonOrganisation<CommonOrganisation> {

  // TODO: Verify ACN and ABN are correctly formatted

  @AssertTrue(message = "ACN must be populated when organisationType is COMPANY")
  private boolean isAcnPopulated() {
    return FormatChecker.isDefined(organisationType())
        ? (Arrays.asList(new CommonOrganisationType[] {CommonOrganisationType.COMPANY})
            .contains(organisationType()) ? FormatChecker.isDefined(acn()) : true)
        : true;
  }
}
