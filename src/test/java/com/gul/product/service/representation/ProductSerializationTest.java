package com.gul.product.service.representation;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;
import io.dropwizard.jackson.Jackson;
import java.io.IOException;

import org.junit.Test;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.SerializerFactory;
import com.gul.product.service.representation.serializer.ProductShopSerializer;

/**
 * Representation classes testing
 * Test if Product serializes correctly.
 **/
public class ProductSerializationTest {

	private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
	
	// test creating product
	@Test
	public void serializesToJson() throws JsonParseException, JsonMappingException, JsonProcessingException, IOException {
		final Product product = new Product("abc131", "test product",
				"this is a test product", "test product long description",
				"/2015/winter/fall/sep/15/scarf");
		Shop shop = new Shop("gulgs");
		product.setShop(shop);
		
		final String expected = MAPPER.writeValueAsString(MAPPER.readValue(fixture("fixtures/product.json"), Product.class));
		assertThat(MAPPER.writeValueAsString(product)).isEqualTo(expected);
	}

	// test creating product with category
	@Test
	public void serialCategoryToJson() throws JsonParseException, JsonMappingException, JsonProcessingException, IOException {
		Category category = new Category(14L, "1001", "Sub Girls Clothing");
		final String expected = MAPPER.writeValueAsString(MAPPER.readValue(fixture("fixtures/productCategory.json"), Category.class));
		assertThat(MAPPER.writeValueAsString(category)).isEqualTo(expected);
	}
	
	// test creating product with price
	@Test
	public void serialPricingProductToJson() throws JsonParseException, JsonMappingException, JsonProcessingException, IOException {
		PricingProduct pricingProduct = new PricingProduct(50.98);
		final String expected = MAPPER.writeValueAsString(MAPPER.readValue(fixture("fixtures/productPricing.json"), PricingProduct.class));
		assertThat(MAPPER.writeValueAsString(pricingProduct)).isEqualTo(expected);
		
	}
	
}
