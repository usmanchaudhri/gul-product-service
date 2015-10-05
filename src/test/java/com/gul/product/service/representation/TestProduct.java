package com.gul.product.service.representation;

import static io.dropwizard.testing.FixtureHelpers.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.dropwizard.jackson.Jackson;

/**
 * Representation classes testing
 * Test if Product serializes correctly.
 **/
public class TestProduct {

	private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
	
	// test creating product
	@Test
	public void serializesToJson() throws JsonParseException, JsonMappingException, JsonProcessingException, IOException {
		final Product product = new Product("abc131", "test product",
				"this is a test product", "test product long description",
				"/2015/winter/fall/sep/15/scarf");
		final String expected = MAPPER.writeValueAsString(MAPPER.readValue(fixture("fixtures/product.json"), Product.class));
		assertThat(MAPPER.writeValueAsString(product)).isEqualTo(expected);
	}
	
	// test creating product with category
	@Test
	public void testProductCategoryAssociation() throws JsonParseException, JsonMappingException, JsonProcessingException, IOException {
		final Product product = new Product("Cloth_1001", "Embroided Skirt",
				"Handmade embroidreded skirt", "Pakistani cultural Skirt, hand embroidery",
				"/winter/2015");
		Category category = new Category(14L, "1001", "Sub Girls Clothing");
		product.setCategory(category);
		final String expected = MAPPER.writeValueAsString(MAPPER.readValue(fixture("fixtures/productCategory.json"), Product.class));
		assertThat(MAPPER.writeValueAsString(product)).isEqualTo(expected);
	}
	
	// test creating product with price
	@Test
	public void testProductPricing() throws JsonParseException, JsonMappingException, JsonProcessingException, IOException {
		final Product product = new Product("Cloth_1001", "Embroided Skirt",
				"Handmade embroidreded skirt", "Pakistani cultural Skirt, hand embroidery",
				"/winter/2015");
		PricingProduct pricingProduct = new PricingProduct(50.98);
		product.setPricingProduct(pricingProduct);
		
		final String expected = MAPPER.writeValueAsString(MAPPER.readValue(fixture("fixtures/productPricing.json"), Product.class));
		assertThat(MAPPER.writeValueAsString(product)).isEqualTo(expected);
		
	}
	
}
