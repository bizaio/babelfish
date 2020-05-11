package io.biza.babelfish.cdr.tests.v2.model.response;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.deser.std.FromStringDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;

import io.biza.babelfish.cdr.converters.UriToUriStringConverter;
import io.biza.babelfish.cdr.enumerations.BabelValidationErrorType;
import io.biza.babelfish.cdr.models.payloads.BabelValidationError;
import io.biza.babelfish.cdr.models.responses.ResponseBankingProductByIdV2;
import io.biza.babelfish.cdr.models.responses.ResponseBankingProductListV2;
import io.biza.babelfish.cdr.tests.common.ValidationListDeserialiser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductListV2Sample1Test {

	private Validator validator;

	@BeforeEach
	public void setup() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	@DisplayName("Process broken application uri and bad links payload and expect a JsonProcessingException")
	void parseProductJsonWithInvalidUriAndThrow() throws JsonProcessingException {
		String payload = "{\"data\":{\"products\":[{\"productId\":\"id1\",\"effectiveFrom\":null,\"effectiveTo\":null,\"lastUpdated\":\"2020-03-09T15:05:24.000+11:00\",\"productCategory\":\"CRED_AND_CHRG_CARDS\",\"name\":\"Credit Card\",\"description\":\"Use your credit card for shopping.\",\"brand\":\"Bank\",\"brandName\":\"Bank\",\"applicationUri\":\"https://someBank/credit-card-apply\",\"isTailored\":false,\"additionalInformation\":{\"overviewUri\":\"https:/someBank/credit-card-overview\",\"termsUri\":\"https://someBank/terms-and-conditions\",\"eligibilityUri\":\"https://someBank/eligibility\",\"feesAndPricingUri\":\"https://someBank/savings-fees-and-charges\",\"bundleUri\":null}},{\"productId\":\"id2\",\"effectiveFrom\":null,\"effectiveTo\":null,\"lastUpdated\":\"2020-03-07T15:55:33.000+11:00\",\"productCategory\":\"CRED_AND_CHRG_CARDS\",\"name\":\"Data Conformance Exceptions\",\"description\":\"Test Data Conformance Exceptions\",\"brand\":\"Bank\",\"brandName\":\"Bank\",\"applicationUri\":\"URI field accepting random text\",\"isTailored\":false,\"additionalInformation\":{\"overviewUri\":null,\"termsUri\":null,\"eligibilityUri\":null,\"feesAndPricingUri\":null,\"bundleUri\":null}}]},\"links\":{\"first\":\"https://someBank/cds-au/v1/banking/products/\",\"prev\":null,\"next\":null,\"last\":\"https://someBank/Emu/cds-au/v1/banking/products/\",\"self\":\"https://someBank/Emu/cds-au/v1/banking/products/\"},\"meta\":{\"totalRecords\":2,\"totalPages\":1}}";
		/**
		 * JsonProcessingException is a valid response to this payload because the
		 * payload doesn't contain a URI that is valid
		 */
		assertThrows(JsonProcessingException.class, () -> {
			new ObjectMapper().readValue(payload, ResponseBankingProductListV2.class);
		});
	}

	@Test
	@DisplayName("Process broken application uri and bad links payload but expect a list of errors rather than an exception")
	void parseProductJsonWithInvalidUriAndLogErrors() {
		String payload = "{\"data\":{\"products\":[{\"productId\":\"id1\",\"effectiveFrom\":null,\"effectiveTo\":null,\"lastUpdated\":\"2020-03-09T15:05:24.000+11:00\",\"productCategory\":\"CRED_AND_CHRG_CARDS\",\"name\":\"Credit Card\",\"description\":\"Use your credit card for shopping.\",\"brand\":\"Bank\",\"brandName\":\"Bank\",\"applicationUri\":\"https://someBank/credit-card-apply\",\"isTailored\":false,\"additionalInformation\":{\"overviewUri\":\"https:/someBank/credit-card-overview\",\"termsUri\":\"https://someBank/terms-and-conditions\",\"eligibilityUri\":\"https://someBank/eligibility\",\"feesAndPricingUri\":\"https://someBank/savings-fees-and-charges\",\"bundleUri\":null}},{\"productId\":\"id2\",\"effectiveFrom\":null,\"effectiveTo\":null,\"lastUpdated\":\"2020-03-07T15:55:33.000+11:00\",\"productCategory\":\"CRED_AND_CHRG_CARDS\",\"name\":\"Data Conformance Exceptions\",\"description\":\"Test Data Conformance Exceptions\",\"brand\":\"Bank\",\"brandName\":\"Bank\",\"applicationUri\":\"URI field accepting random text\",\"isTailored\":false,\"additionalInformation\":{\"overviewUri\":null,\"termsUri\":null,\"eligibilityUri\":null,\"feesAndPricingUri\":null,\"bundleUri\":null}}]},\"links\":{\"first\":\"https://someBank/cds-au/v1/banking/products/\",\"prev\":null,\"next\":null,\"last\":\"https://someBank/Emu/cds-au/v1/banking/products/\",\"self\":\"https://someBank/Emu/cds-au/v1/banking/products/\"},\"meta\":{\"totalRecords\":2,\"totalPages\":1}}";
		List<BabelValidationError> validationErrors = List.of();

		try {
			validationErrors = processToErrorList(payload, ResponseBankingProductListV2.class);
		} catch (JsonProcessingException e) {
			fail("Errorless mapper threw JsonProcessException which should not occur");
		}

		LOG.error("parseProductJsonWithInvalidUriAndLogErrors: Validation errors encountered are: {}",
				validationErrors);
		assertTrue(validationErrors.size() > 0);
	}

	private <T> List<BabelValidationError> processToErrorList(String payload, Class<T> targetClass)
			throws JsonMappingException, JsonProcessingException {
		List<BabelValidationError> validationErrors = new ArrayList<BabelValidationError>();
		T result = getErrorlessMapper(validationErrors).readValue(payload, targetClass);
		Set<ConstraintViolation<T>> validationResult = Validation.buildDefaultValidatorFactory().getValidator()
				.validate(result);

		validationResult.forEach(violation -> {
			validationErrors.add(BabelValidationError.builder().fields(List.of(violation.getPropertyPath().toString()))
					.message(StringUtils.capitalize(violation.getMessage()))
					.type(BabelValidationErrorType.ATTRIBUTE_INVALID).build());
		});

		return validationErrors;
	}

	private ObjectMapper getErrorlessMapper(List<BabelValidationError> validationErrors) {
		SimpleModule module = new SimpleModule();
		module.setDeserializerModifier(new BeanDeserializerModifier() {
			@Override
			public JsonDeserializer<?> modifyDeserializer(DeserializationConfig config, BeanDescription beanDesc,
					JsonDeserializer<?> defaultDeserializer) {
				Class<?> beanClass = beanDesc.getBeanClass();
				return new ValidationListDeserialiser(defaultDeserializer, beanClass, validationErrors);
			}
		});

		// Create ObjectMapper and register module
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(module);

		return mapper;
	}

}
