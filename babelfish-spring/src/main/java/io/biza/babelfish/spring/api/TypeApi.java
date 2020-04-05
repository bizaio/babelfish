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
package io.biza.babelfish.spring.api;

import io.biza.babelfish.spring.payloads.FormFieldType;
import io.biza.babelfish.spring.payloads.ResponseGetTypes;
import io.biza.babelfish.spring.service.TypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import io.biza.babelfish.spring.Constants;

@Tag(name = Constants.TAG_TYPE_NAME, description = Constants.TAG_TYPE_DESCRIPTION)
@RequestMapping("/v1/type")
public class TypeApi {

  @Autowired
  TypeService typeService;
  
  @Operation(summary = "Get a List of Available Types",
      description = "Returns a list of available types and the type of type they are")
  @ApiResponses(value = {@ApiResponse(responseCode = Constants.RESPONSE_CODE_OK,
      description = "List of possible form fields",
      content = @Content(array = @ArraySchema(schema = @Schema(implementation = FormFieldType.class))))})
  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<List<FormFieldType>> listTypes() {
    return ResponseEntity.ok(typeService.listTypes());
  }

  @Operation(summary = "Get a value/label set for one or more enumeration types",
      description = "Returns a value/label set of FormValueLabel's based on the enumerations requested")
  @ApiResponses(value = {@ApiResponse(responseCode = Constants.RESPONSE_CODE_OK,
      description = "Map of Lists of Value/Label for use in Form selects",
      content = @Content(schema = @Schema(implementation = ResponseGetTypes.class)))})
  @RequestMapping(path = "/enumerations", method = RequestMethod.GET, params = {"labelTypes"})
  public ResponseEntity<ResponseGetTypes> getEnumerationTypes(
      @Parameter(name = "labelTypes", description = "Set of Value Label Type lists to get",
          required = true, in = ParameterIn.QUERY) @RequestParam List<String> labelTypes) {
    return ResponseEntity.ok(ResponseGetTypes.builder().fieldTypes(typeService.getEnumerationTypes(labelTypes))
        .build().populateFieldNames());
  }

}

