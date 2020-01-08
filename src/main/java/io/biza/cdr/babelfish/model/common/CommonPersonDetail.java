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

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.biza.cdr.babelfish.support.BabelFishModel;
import io.biza.cdr.babelfish.support.BabelFishModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Valid
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@BabelFishModel(description = "Person definition in detail", parent = CommonPerson.class)
public abstract class CommonPersonDetail<T> extends CommonPerson<T> {
  @BabelFishModelProperty(
      description = "Array is mandatory but may be empty if no phone numbers are held",
      required = true)
  @JsonProperty("phoneNumbers")
  @NonNull
  @NotNull
  public List<CommonPhoneNumber<?>> phoneNumbers = List.of();

  public List<CommonPhoneNumber<?>> phoneNumbers() {
    return getPhoneNumbers();
  }

  @SuppressWarnings("unchecked")
  public T phoneNumbers(List<CommonPhoneNumber<?>> phoneNumbers) {
    setPhoneNumbers(phoneNumbers);
    return (T) this;
  }

  @BabelFishModelProperty(description = "May be empty", required = true)
  @JsonProperty("emailAddresses")
  @NonNull
  @NotNull
  public List<CommonEmailAddress<?>> emailAddresses = List.of();

  public List<CommonEmailAddress<?>> emailAddresses() {
    return getEmailAddresses();
  }

  @SuppressWarnings("unchecked")
  public T emailAddresses(List<CommonEmailAddress<?>> emailAddresses) {
    setEmailAddresses(emailAddresses);
    return (T) this;
  }


  @BabelFishModelProperty(
      description = "Must contain at least one address. One and only one address may have the purpose of REGISTERED. Zero or one, and no more than one, record may have the purpose of MAIL. If zero then the REGISTERED address is to be used for mail",
      required = true)
  @JsonProperty("physicalAddresses")
  @NotNull
  @NonNull
  public List<CommonPhysicalAddressWithPurpose<?>> physicalAddresses = List.of();

  public List<CommonPhysicalAddressWithPurpose<?>> physicalAddresses() {
    return getPhysicalAddresses();
  }

  @SuppressWarnings("unchecked")
  public T physicalAddresses(List<CommonPhysicalAddressWithPurpose<?>> physicalAddresses) {
    setPhysicalAddresses(physicalAddresses);
    return (T) this;
  }
}
