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

import java.time.OffsetDateTime;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Valid
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Person definition in detail", name = "CommonPersonDetailV1")
public class CommonPersonDetailV1
    extends io.biza.babelfish.cdr.abstracts.payloads.common.CommonPersonDetailV1 {

  @Schema(
      description = "The date and time that this record was last updated by the customer.  If no update has occurred then this date should reflect the initial creation date for the data",
      format = "date-time")
  @JsonProperty("lastUpdateTime")
  OffsetDateTime lastUpdateTime;

  @Schema(
      description = "For people with single names this field need not be present.  The single name should be in the lastName field")
  @JsonProperty("firstName")
  String firstName;

  @Schema(description = "For people with single names the single name should be in this field",
      required = true)
  @JsonProperty("lastName")
  @NotEmpty(
      message = "Should be populated with last name or, for single names, this field should be used")
  String lastName;

  @Schema(description = "Field is mandatory but array may be empty", required = true)
  @JsonProperty("middleNames")
  @NotNull
  @Builder.Default
  List<String> middleNames = List.of();

  @Schema(
      description = "Also known as title or salutation.  The prefix to the name (e.g. Mr, Mrs, Ms, Miss, Sir, etc)")
  @JsonProperty("prefix")
  String prefix;

  @Schema(description = "Used for a trailing suffix to the name (e.g. Jr)")
  @JsonProperty("suffix")
  String suffix;

  @Schema(
      description = "Value is a valid [ANZCO v1.2](http://www.abs.gov.au/ANZSCO) Standard Occupation classification.")
  @JsonProperty("occupationCode")
  String occupationCode;

  @Schema(description = "Array is mandatory but may be empty if no phone numbers are held",
      required = true)
  @JsonProperty("phoneNumbers")
  @NotNull
  @Valid
  @Builder.Default
  List<CommonPhoneNumberV1> phoneNumbers = List.of();

  @Schema(description = "May be empty", required = true)
  @JsonProperty("emailAddresses")
  @NotNull
  @Valid
  @Builder.Default
  List<CommonEmailAddressV1> emailAddresses = List.of();

  @Schema(
      description = "Must contain at least one address. One and only one address may have the purpose of REGISTERED. Zero or one, and no more than one, record may have the purpose of MAIL. If zero then the REGISTERED address is to be used for mail",
      required = true)
  @JsonProperty("physicalAddresses")
  @NotNull
  @Valid
  @Builder.Default
  List<CommonPhysicalAddressWithPurposeV1> physicalAddresses = List.of();

}
