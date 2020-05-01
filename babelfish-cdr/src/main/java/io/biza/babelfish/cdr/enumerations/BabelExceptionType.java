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
package io.biza.babelfish.cdr.enumerations;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Babelfish Exception Type", enumAsRef = true)
public enum BabelExceptionType {
  VALIDATION_ERROR, DATABASE_ERROR, INVALID_JSON
}
