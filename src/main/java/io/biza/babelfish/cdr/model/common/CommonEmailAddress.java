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
package io.biza.babelfish.cdr.model.common;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.biza.babelfish.cdr.v1.enumerations.CommonEmailAddressPurpose;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Valid
@ToString
@EqualsAndHashCode

@Schema(description = "Email Address Definition")
public abstract class CommonEmailAddress<T> {
  @Schema(description = "The purpose for the email, as specified by the customer (Enumeration)",
      required = true)
  @JsonProperty("purpose")
  @NotNull
  CommonEmailAddressPurpose purpose;

  public CommonEmailAddressPurpose purpose() {
    return getPurpose();
  }

  @SuppressWarnings("unchecked")
  public T purpose(CommonEmailAddressPurpose purpose) {
    setPurpose(purpose);
    return (T) this;
  }

  @Schema(
      description = "A correctly formatted email address, as defined by the addr_spec format in [RFC 5322](https://www.ietf.org/rfc/rfc5322.txt)",
      required = true)
  @Email
  @JsonProperty("address")
  @NotNull
  String address;

  public String address() {
    return getAddress();
  }

  @SuppressWarnings("unchecked")
  public T address(String address) {
    setAddress(address);
    return (T) this;
  }

  @Schema(
      description = "May be true for one and only one email record in the collection. Denotes the default email address")
  @JsonProperty("isPreferred")
  Boolean isPreferred;

  public Boolean isPreferred() {
    return getIsPreferred();
  }

  @SuppressWarnings("unchecked")
  public T isPreferred(Boolean isPreferred) {
    setIsPreferred(isPreferred);
    return (T) this;
  }
}
