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
package io.biza.babelfish.common.enumerations;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Babelfish Validation Error Type", enumAsRef = true)
public enum BabelValidationErrorType {
  ATTRIBUTE_INVALID, DATABASE_INTEGRITY, PATH_PARAMETER_INVALID
}
