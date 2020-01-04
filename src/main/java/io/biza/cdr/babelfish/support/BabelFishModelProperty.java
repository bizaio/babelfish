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
package io.biza.cdr.babelfish.support;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Provides API Model data for Methods and Fields
 */
@Target({
    ElementType.METHOD, ElementType.FIELD
})
@Retention(RetentionPolicy.RUNTIME)
public @interface BabelFishModelProperty {
  /**
   * Description of the property
   */
  String description() default "";

  /**
   * Specifies if the parameter is required or not.
   */
  boolean required() default false;

  /**
   * A specific class name to present this value as
   */
  String dataType() default "";

  /**
   * Should this property be hidden when parsing into api definitions
   */
  boolean hidden() default false;

  /**
   * Attribute name
   */
  String attributeName() default "";
}
