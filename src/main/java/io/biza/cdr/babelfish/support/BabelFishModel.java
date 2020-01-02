package io.biza.cdr.babelfish.support;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Provides model data for classes
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface BabelFishModel {
    /**
     * Provide a name for the model, if not specified
     * it is assumed this is to match the class name
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
