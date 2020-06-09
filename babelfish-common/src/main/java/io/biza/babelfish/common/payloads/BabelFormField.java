/*******************************************************************************
 * Copyright (C) 2020 Biza Pty Ltd
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *******************************************************************************/
package io.biza.babelfish.common.payloads;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.biza.babelfish.common.enumerations.BabelFormFieldType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Valid
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "BabelFormField", description = "Babelfish Form Field Container")
public class BabelFormField {

  @JsonProperty("attribute")
  @Schema(description = "Form Field Attribute Name", example = "attribute")
  @NotNull
  String attribute;
  
  @JsonProperty("title")
  @Schema(description = "Form Field Title", example = "Short Title")
  String title;

  @JsonProperty("tooltip")
  @Schema(description = "Form Field Tooltip", example = "A helpful description of the form field")
  @NotNull
  String tooltip;
  
  @JsonProperty("defaultValue")
  @Schema(description = "Form Field Default Value")
  BabelFieldLabelValue defaultValue;
  
  @JsonProperty("fieldType")
  @Schema(description = "Form Field Type", example = "INPUT")
  @NotNull
  BabelFormFieldType fieldType;
  
  @JsonProperty("values")
  @Schema(description = "Form Field Label Values")
  List<BabelFieldLabelValue> values;
      
  @JsonProperty("form")
  @Schema(description = "Sub Form Definition")
  BabelForm form;
  
  @JsonProperty("readOnly")
  @Schema(description = "Define whether field is read only")
  @Builder.Default
  Boolean readOnly = false;
  
  @JsonProperty("mandatory")
  @Schema(description = "Is Field Mandatory?")
  @Builder.Default
  Boolean mandatory = false;
  
  
  
}
