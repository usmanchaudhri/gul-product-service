package com.gul.product.service.representation;

import static io.dropwizard.testing.FixtureHelpers.*;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;

/**
 * Test if Product serializes correctly.
 **/
public class TestProduct {

	private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
	
	@Test
	public void serializesToJson() throws Exception {
		final Product product = new Product("abc131", "test product",
				"this is a test product", "test product long description",
				"/2015/winter/fall/sep/15/scarf");
		final String expected = MAPPER.writeValueAsString(MAPPER.readValue(fixture("fixtures/product.json"), Product.class));
		assertThat(MAPPER.writeValueAsString(product)).isEqualTo(expected);
	}
	
}
