package com.gul.product.service.user;

import javax.ws.rs.client.Client;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.gul.product.service.product.AbstractProductServiceIntegrationTest;

public class UserServiceIntegrationTest extends AbstractProductServiceIntegrationTest {

	private static final String REST_PRODUCT_SERVICE_URL = "http://localhost:%d/gul-product-service";
	
	@Test
	public void test_creating_new_product() throws JsonProcessingException {
		Client client = JerseyClientBuilder.createClient();
		
		
	}
}
