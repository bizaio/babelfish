package io.biza.cdr.babelfish.support;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Provides API Model data for Methods and Fields
 */
@Target({ElementType.METHOD, ElementType.FIELD})
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

}
