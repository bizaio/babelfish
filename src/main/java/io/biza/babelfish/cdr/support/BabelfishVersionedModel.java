package io.biza.babelfish.cdr.support;

import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface BabelfishVersionedModel {
  String schemaName();
  int version();
}
