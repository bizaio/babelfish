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
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonGetter;
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

@BabelFishModel(description = "Person definition in detail", parent = CommonPerson.class)
public interface CommonPersonDetail {

  @BabelFishModelProperty(hidden = true)
  @JsonUnwrapped
  public CommonPerson getCommonPerson();

  public void setCommonPerson(@NotNull CommonPerson commonPerson);

  public default CommonPersonDetail commonPerson(@NotNull CommonPerson commonPerson) {
    setCommonPerson(commonPerson);
    return this;
  }

  @BabelFishModelProperty(
      description = "Array is mandatory but may be empty if no phone numbers are held",
      required = true)
  @JsonGetter("phoneNumbers")
  public List<CommonPhoneNumber> getPhoneNumbers();

  @JsonSetter("phoneNumbers")
  public void setPhoneNumbers(@NotNull List<CommonPhoneNumber> phoneNumbers);

  public default CommonPersonDetail phoneNumbers(@NotNull List<CommonPhoneNumber> phoneNumbers) {
    setPhoneNumbers(phoneNumbers);
    return this;
  }

  @BabelFishModelProperty(description = "May be empty", required = true)
  @JsonGetter("emailAddresses")
  public List<CommonEmailAddress> getEmailAddresses();

  @JsonSetter("emailAddresses")
  public void setEmailAddresses(@NotNull List<CommonEmailAddress> emailAddresses);

  public default CommonPersonDetail emailAdddresses(
      @NotNull List<CommonEmailAddress> emailAddresses) {
    setEmailAddresses(emailAddresses);
    return this;
  }

  @BabelFishModelProperty(
      description = "Must contain at least one address. One and only one address may have the purpose of REGISTERED. Zero or one, and no more than one, record may have the purpose of MAIL. If zero then the REGISTERED address is to be used for mail",
      required = true)
  @JsonGetter("physicalAddresses")
  public List<CommonPhysicalAddressWithPurpose> getPhysicalAddresses();

  @JsonSetter("physicalAddresses")
  public void setPhysicalAddresses(
      @NotNull List<CommonPhysicalAddressWithPurpose> physicalAddresses);

  public default CommonPersonDetail physicalAddresses(
      @NotNull List<CommonPhysicalAddressWithPurpose> physicalAddresses) {
    setPhysicalAddresses(physicalAddresses);
    return this;
  }

}
