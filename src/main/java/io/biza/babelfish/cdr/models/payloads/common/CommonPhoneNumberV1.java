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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.biza.babelfish.cdr.enumerations.CommonPhoneNumberPurpose;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Valid
@ToString
@EqualsAndHashCode(callSuper = false)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Phone Number Detail", name = "CommonPhoneNumberV1")
public class CommonPhoneNumberV1 extends
    io.biza.babelfish.cdr.abstracts.payloads.common.CommonPhoneNumberV1<CommonPhoneNumberV1> {
  @Schema(
      description = "May be true for one and only one entry to indicate the preferred phone number. Assumed to be 'false' if not present")
  @JsonProperty(value = "isPreferred", defaultValue = "false")
  @Builder.Default
  Boolean isPreferred = false;

  @Schema(description = "The purpose of the number as specified by the customer", required = true)
  @JsonProperty("purpose")
  @NotNull
  CommonPhoneNumberPurpose purpose;

  @Schema(description = "If absent, assumed to be Australia (+61). The + should be included")
  @JsonProperty("countryCode")
  @Builder.Default
  String countryCode = "+61";

  @Schema(
      description = "Required for non Mobile Phones, if field is present and refers to Australian code - the leading 0 should be omitted.")
  @JsonProperty("areaCode")
  String areaCode;

  @Schema(description = "The actual phone number, with leading zeros as appropriate",
      required = true)
  @NotEmpty(message = "Phone Number must be populated")
  @JsonProperty("number")
  String number;

  @Schema(description = "An extension number (if applicable)")
  @JsonProperty("extension")
  String extension;

  @Schema(
      description = "Fully formatted phone number with country code, area code, number and extension incorporated. Formatted according to section 5.1.4. of [RFC 3966](https://www.ietf.org/rfc/rfc3966.txt)",
      required = true)
  @JsonProperty("fullNumber")
  @NotEmpty(
      message = "Full Phone Number with Country Code, Area Code, Number and Extension in RFC-3966 format is required")
  String fullNumber;

}
