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
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Provides model data for classes
 */
@Target({
    ElementType.TYPE
})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface BabelFishModel {
  /**
   * Provide a name for the model, if not specified it is assumed this is to match the class name
   */
  String modelName() default "";

  /**
   * Provide a longer description of the class.
   */
  String description() default "";

  /**
   * Provide a super class for this model
   */
  Class<?> parent() default Void.class;
}
