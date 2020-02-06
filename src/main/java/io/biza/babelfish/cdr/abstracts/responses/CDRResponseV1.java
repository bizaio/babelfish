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
package io.biza.babelfish.cdr.abstracts.responses;

import javax.validation.Valid;
import io.biza.babelfish.cdr.models.payloads.LinksV1;
import io.biza.babelfish.cdr.models.payloads.MetaV1;

@Valid
public abstract class CDRResponseV1 {
  public abstract LinksV1 links();
  public abstract MetaV1 meta();
}
