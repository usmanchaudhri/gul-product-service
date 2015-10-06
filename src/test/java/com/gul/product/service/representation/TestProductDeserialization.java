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

public class TestProductDeserialization {

	private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

	@Test
	public void deserializesFromJson() throws JsonParseException, JsonMappingException, JsonProcessingException, IOException {
		final Product product = new Product("abc131", "test product",
				"this is a test product", "test product long description",
				"/2015/winter/fall/sep/15/scarf");
		assertThat(MAPPER.readValue(fixture("fixtures/product.json"), Product.class)).isEqualToComparingFieldByField(product);
	}
	
	@Test
	public void testProductCategoryAssociation() throws JsonParseException, JsonMappingException, JsonProcessingException, IOException {
		final Product product = new Product("Cloth_1001", "Embroided Skirt",
				"Handmade embroidreded skirt", "Pakistani cultural Skirt, hand embroidery",
				"/winter/2015");
		Category category = new Category(14L, "1001", "Sub Girls Clothing");
		product.setCategory(category);
		assertThat(MAPPER.readValue(fixture("fixtures/productCategory.json"), Product.class)).isEqualTo(product);
	}
	
	// TODO - this test should fail
	@Test
	public void testProductCategoryPricingAssociation() throws JsonParseException, JsonMappingException, JsonProcessingException, IOException {
		final Product product = new Product("Cloth_1001", "Embroided Skirt",
				"Handmade embroidreded skirt", "Pakistani cultural Skirt, hand embroidery",
				"/winter/2015");
		Category category = new Category(14L, "1001", "Sub Girls Clothing");
		product.setCategory(category);

		PricingProduct pricingProduct = new PricingProduct(50.98);
		product.setPricingProduct(pricingProduct);
		
		Product prod = MAPPER.readValue(fixture("fixtures/productCategoryPricing.json"), Product.class);
//		assertThat(MAPPER.readValue(fixture("fixtures/productPricing.json"), Product.class)).isEqualTo(product);
		assertThat(prod).isEqualTo(product);
	}


}
