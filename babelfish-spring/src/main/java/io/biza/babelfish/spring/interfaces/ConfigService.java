package io.biza.babelfish.spring.interfaces;

import java.util.Map;
import java.util.UUID;
import io.biza.babelfish.spring.exceptions.NotFoundException;
import io.biza.babelfish.spring.exceptions.NotInitialisedException;

public interface ConfigService {

	/**
	 * Given a configuration key and an implied type parameter retrieve a config
	 * value
	 * 
	 * @param <T>    is an implied type based on the context of the call being made
	 * @param object describes the element identifier for which the configuration
	 *               value is being loaded
	 * @param key    contains a string value describing the parameter being loaded
	 * @return
	 * @throws NotFoundException
	 */
	public <T> T getConfig(String object, String key) throws NotFoundException;

	/**
	 * Given a configuration key/value and an owning object string stores a value of
	 * a specified T type
	 * 
	 * @param <T>    the type of configuration object to store
	 * @param object describes the element identifier for which this configuration
	 *               value belongs
	 * @param key    describes the key name to store this configuration key under
	 * @param value  contains the value to store of type T
	 * @throws NotInitialisedException
	 */
	public <T> T setConfig(String object, String key, T value) throws NotInitialisedException;

	/**
	 * Retrieve a config value or set it's value if it doesn't exist
	 * 
	 * @param <T>          implied type of configuration
	 * @param object       the config key belongs to
	 * @param key          describes the config key for an object
	 * @param defaultValue is the default value to set if it is not found
	 * @return value contains the object/key value which has either been retrieved
	 *         or set
	 * @throws NotInitialisedException is thrown if the object/key pair could not be
	 *                                 found and initialisation did not succeed
	 */
	default <T> T getConfigOrDefault(String object, String key, T defaultValue) {
		try {
			return getConfig(object, key);
		} catch (NotFoundException e) {
			try {
				return setConfig(object, key, defaultValue);
			} catch (NotInitialisedException e1) {
				return defaultValue;
			}
		}
	}

	/**
	 * Given an object string deletes all config entries related to it
	 * 
	 * @param object string identifying the configuration group to delete
	 */
	public void deleteObject(String object);

	/**
	 * Given an object produce a list of configuration attributes
	 */
	public Map<String, Object> listConfig(String object);

	default Boolean getConfig(UUID object, String key, boolean defaultValue) {
		return getConfigOrDefault(object.toString(), key, defaultValue);
	}

	default Map<String, Object> setConfig(UUID object, Map<String, Object> configs) {
		if (configs != null) {
			configs.forEach((key, value) -> {
				try {
					setConfig(object.toString(), key, value);
				} catch (NotInitialisedException e) {
					// no-op, there should be an error printed by the setter
				}
			});
		}
		return configs;
	}

}
