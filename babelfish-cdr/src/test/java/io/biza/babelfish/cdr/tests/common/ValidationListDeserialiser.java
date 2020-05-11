package io.biza.babelfish.cdr.tests.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.deser.std.DelegatingDeserializer;
import io.biza.babelfish.common.enumerations.BabelValidationErrorType;
import io.biza.babelfish.common.payloads.BabelValidationError;

public class ValidationListDeserialiser<T> extends DelegatingDeserializer {
	private static final long serialVersionUID = 1L;
	private Class<T> clazz;
	private List<BabelValidationError> validationErrors;

	public ValidationListDeserialiser(JsonDeserializer<?> delegate, Class<T> clazz, List<BabelValidationError> validationErrors) {
		super(delegate);
		this.clazz = clazz;
		this.validationErrors = validationErrors;
	}

	@Override
	protected JsonDeserializer<?> newDelegatingInstance(JsonDeserializer<?> newDelegate) {
		return new ValidationListDeserialiser<T>(newDelegate, clazz, validationErrors);
	}

	@Override
	public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
		try {
			return clazz.cast(super.deserialize(p, ctxt));
		} catch (JsonProcessingException e) {
			logException(e);
			return null;
		}
	}

	private void logException(JsonProcessingException e) {

		BabelValidationError error = BabelValidationError.builder().type(BabelValidationErrorType.ATTRIBUTE_INVALID)
				.message(e.getMessage()).build();		

		if (e instanceof JsonMappingException) {
			JsonMappingException e1 = (JsonMappingException) e;
			List<String> pathList = new ArrayList<String>();
			for (Reference reference : e1.getPath()) {
				pathList.add(reference.getFieldName());
			}
			error.fields(List.of(String.join(".", pathList)));
		}
		
		validationErrors.add(error);
	}
}