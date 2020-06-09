package io.biza.babelfish.converter.orika;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import ma.glasnost.orika.metadata.Property;
import ma.glasnost.orika.metadata.Type;
import ma.glasnost.orika.property.IntrospectorPropertyResolver;

public class OrikaFluentLombokResolver extends IntrospectorPropertyResolver {

  @Override
  protected void collectProperties(Class<?> type, Type<?> referenceType,
      Map<String, Property> properties) {
    super.collectProperties(type, referenceType, properties);
    Set<String> fieldNames =
        Arrays.stream(type.getDeclaredFields()).map(Field::getName).collect(Collectors.toSet());
    Arrays.stream(type.getDeclaredMethods()).filter(method -> fieldNames.contains(method.getName()))
        .forEach(method -> {
          if (method.getParameterCount() > 0) {
            Property.Builder builder = new Property.Builder();
            builder.expression(method.getName());
            builder.name(method.getName());
            builder.setter(method.getName() + "(%s)");
            builder.getter(method.getName() + "()");
            Class<?> fieldType = method.getParameterTypes()[0];
            builder.type(this.resolvePropertyType(null, fieldType, type, referenceType));
            Property property = builder.build(this);
            properties.put(method.getName(), property);
          }
        });
  }
}
