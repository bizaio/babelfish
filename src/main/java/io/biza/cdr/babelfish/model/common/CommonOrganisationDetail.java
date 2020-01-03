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
package io.biza.cdr.babelfish.model.common;

import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import io.biza.cdr.babelfish.support.FormatChecker;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

@BabelFishModel(description = "Organisation Definition in Detail",
    parent = CommonOrganisation.class)
public interface CommonOrganisationDetail {

  @JsonUnwrapped
  @BabelFishModelProperty(hidden = true)
  public CommonOrganisation getOrganisation();

  public void setOrganisation(CommonOrganisation commonOrganisation);

  default public CommonOrganisationDetail organisation(CommonOrganisation commonOrganisation) {
    setOrganisation(commonOrganisation);
    return this;
  }

  @BabelFishModelProperty(
      description = "Must contain at least one address. One and only one address may have the purpose of REGISTERED. Zero or one, and no more than one, record may have the purpose of MAIL. If zero then the REGISTERED address is to be used for mail",
      required = true)
  @JsonGetter("physicalAddresses")
  public List<CommonPhysicalAddressWithPurpose> getPhysicalAddresses();

  @JsonSetter("physicalAddresses")
  public void setPhysicalAddresses(List<CommonPhysicalAddressWithPurpose> physicalAddresses);

  public default CommonOrganisationDetail physicalAddresses(
      List<CommonPhysicalAddressWithPurpose> physicalAddresses) {
    setPhysicalAddresses(physicalAddresses);
    return this;
  }
}
