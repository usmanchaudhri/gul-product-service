package com.gul.product.service.client;

import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.ClassRule;
import com.gul.product.service.app.ProductServiceApplication;
import com.gul.product.service.app.ProductServiceConfiguration;
import com.gul.product.service.representation.Product;
import org.junit.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 *	TODO - working on running this with H2
 **/
public class ProductServiceIntegrationTest {

	@ClassRule
    public static final DropwizardAppRule<ProductServiceConfiguration> RULE =
            new DropwizardAppRule<ProductServiceConfiguration>(ProductServiceApplication.class, ResourceHelpers.resourceFilePath("testProductService.yml"));

	@Ignore
	@Test
	public void getProduct() {
		Client client = JerseyClientBuilder.createClient();
//        Product product = client.target(String.format("http://localhost:%d/gul-product-service/product/11", RULE.getLocalPort())).request().get(Product.class); 

        Response response = client.target(String.format("http://localhost:%d/gul-product-service/product/11", RULE.getLocalPort())).request().get(); 
        assertThat(response.getStatus()).isEqualTo(200);
	} 
	
	
}
