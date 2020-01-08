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
package io.biza.cdr.babelfish.v1.response.container;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import io.biza.cdr.babelfish.v1.enumerations.PayloadTypeCustomer;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Valid
@ToString
@EqualsAndHashCode(callSuper = true)


public class ResponseCommonCustomerData extends
    io.biza.cdr.babelfish.response.container.ResponseCommonCustomerData<ResponseCommonCustomerData> {

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
