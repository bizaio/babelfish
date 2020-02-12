package io.biza.babelfish.cdr.support;

import java.util.HashMap;
import java.util.Optional;
import io.biza.babelfish.cdr.Constants;
import io.biza.babelfish.cdr.exceptions.PayloadConversionException;
import io.biza.babelfish.cdr.exceptions.UnsupportedPayloadException;
import io.biza.babelfish.cdr.models.VersionerConstants;
import io.biza.babelfish.cdr.orika.OrikaFactoryConfigurer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BabelfishVersioner {

  public static Class<?> getVersionedClass(Class<?> clazz, Integer version, Optional<Integer> optionalMinimum)
      throws UnsupportedPayloadException {

    if (VersionerConstants.classMap == null) {
      LOG.error("Class mapper is not initialised");
      throw new UnsupportedPayloadException("Class Mapper has not been initialised!");
    }

    HashMap<Integer, Class<?>> versionMap = VersionerConstants.classMap.get(clazz);

    if (version == null) {
      version = getVersion(clazz);
    }
    
    Integer minimumVersion = version;
    
    if (optionalMinimum.isPresent() && optionalMinimum.get() < version) {
      minimumVersion = optionalMinimum.get();
    }

    /**
     * Error if I don't know how to handle this class
     */
    if (versionMap == null) {
      LOG.error("Version map has not initialised!");
      throw new UnsupportedPayloadException(
          String.format("Unable to retrieve version map for class: %s", clazz.getName()));
    }

    /**
     * Iterate down to minimumVersion until we find something
     */
    for (Integer targetVersion = version; targetVersion >= minimumVersion; targetVersion--) {
      if (versionMap.get(targetVersion) != null) {
        LOG.debug("Identified version of {} and target class of {}", targetVersion, versionMap.get(targetVersion));
        return versionMap.get(targetVersion);
      }
    }

    throw new UnsupportedPayloadException(
        String.format("Unable to locate a version of class %s for version %d <= %d",
            clazz.getName(), minimumVersion, version));

  }
  
  public static Boolean isSupported(Class<?> clazz) {
    if (clazz.getPackageName().startsWith(Constants.BASE_MODELS_PACKAGE)) {
      return true;
    }
    
    return false;
  }

  public static Integer getVersion(Class<?> clazz) throws UnsupportedPayloadException {
    if(!isSupported(clazz)) {
      throw new UnsupportedPayloadException(
          "BabelFishVersioner only supports version discovery for classes in "
              + Constants.BASE_MODELS_PACKAGE);
    }

    if (VersionerConstants.versionMap == null) {
      throw new UnsupportedPayloadException("Version mapper has not been initialised!");
    }
    return VersionerConstants.versionMap.getOrDefault(clazz, 1);
  }

  public static <S, D> D convert(S sourceObject, Class<D> destinationClass)
      throws PayloadConversionException {
    try {
      OrikaFactoryConfigurer factory = new OrikaFactoryConfigurer();
      LOG.debug("Converting from {} to {}", sourceObject.getClass(), destinationClass);
      return factory.getFactory().getMapperFacade().map(sourceObject, destinationClass);
    } catch (Exception e) {
      throw new PayloadConversionException(e.getMessage());
    }
  }
}
