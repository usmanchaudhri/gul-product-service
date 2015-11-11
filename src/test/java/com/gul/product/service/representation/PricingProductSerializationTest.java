package com.gul.product.service.representation;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;
import io.dropwizard.jackson.Jackson;
import java.io.IOException;
import org.junit.Test;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PricingProductSerializationTest {

	private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

	@Test
	public void serializesToJson() throws JsonParseException, JsonMappingException, JsonProcessingException, IOException {
		final PricingProduct pricingProduct = new PricingProduct(50.98);
		final String expected = MAPPER.writeValueAsString(MAPPER.readValue(fixture("fixtures/pricingProduct.json"), PricingProduct.class));
		assertThat(MAPPER.writeValueAsString(pricingProduct)).isEqualTo(expected);
	}
}
