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

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.biza.babelfish.cdr.enumerations.CommonEmailAddressPurpose;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Valid
@ToString
@EqualsAndHashCode
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Email Address Definition", name = "CommonEmailAddressV1")
public class CommonEmailAddressV1 {
  @Schema(description = "The purpose for the email, as specified by the customer (Enumeration)",
      required = true)
  @JsonProperty("purpose")
  @NotNull
  CommonEmailAddressPurpose purpose;

  @Schema(
      description = "A correctly formatted email address, as defined by the addr_spec format in [RFC 5322](https://www.ietf.org/rfc/rfc5322.txt)",
      required = true)
  @Email(message = "Must be a well formatted email address")
  @JsonProperty("address")
  @NotNull
  String address;

  @Schema(
      description = "May be true for one and only one email record in the collection. Denotes the default email address")
  @JsonProperty("isPreferred")
  Boolean isPreferred;

}
