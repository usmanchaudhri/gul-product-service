package com.gul.product.service.product;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import io.dropwizard.flyway.FlywayFactory;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import org.apache.commons.lang3.StringUtils;
import org.flywaydb.core.Flyway;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.gul.product.service.app.ProductServiceApplicationTest;
import com.gul.product.service.app.ProductServiceConfigurationTest;
import com.gul.product.service.representation.Category;
import com.gul.product.service.representation.Product;
import com.gul.product.service.representation.Shop;

/**
 * End to end testing for product service. 
 * This class uses Flyway (db utility)
 * which loads the DB migrations for initial database setup. Rest resources are called using HTTP client and response are than verified.
 **/
public class ProductServiceIntegrationTest {
	
	private static final String REST_PRODUCT_SERVICE_URL = "http://localhost:%d/gul-product-service";
	private static Flyway flyway;
	
	@ClassRule
    public static final DropwizardAppRule<ProductServiceConfigurationTest> RULE =
            new DropwizardAppRule<ProductServiceConfigurationTest>(ProductServiceApplicationTest.class, ResourceHelpers.resourceFilePath("testProductService.yml"));

	@BeforeClass
	public static void setupClass() {
		FlywayFactory flywayFactory = RULE.getConfiguration().getFlyway();

		String url = RULE.getConfiguration().getDatabase().getUrl();
		String user = RULE.getConfiguration().getDatabase().getUser();
		String password = RULE.getConfiguration().getDatabase().getPassword();
		
		JdbcDataSource ds = new JdbcDataSource();
		ds.setURL(url);
		ds.setUser(user);
		ds.setPassword(password);		

		flyway = flywayFactory.build(ds);

		// migrate category
		flyway.migrate();		
		
		// migrate product
		flyway.migrate();
		
		// migrate shop
		flyway.migrate();
	}
	
	@Test
	public void test_creating_new_product() throws JsonProcessingException {
		Client client = JerseyClientBuilder.createClient();

		Shop shop = new Shop("GULGS");
		Product productRequest = new Product(); 
		productRequest.setName("Test Women Skirt");
		productRequest.setSku("SKU101");
		productRequest.setShortDesc("Short Description Women Skirt");
		productRequest.setLongDesc("Long Description Women Skirt");
		productRequest.setImagePath("/winter/2015/women");
		productRequest.setQuantity(10L);
		
		Category categoryRequest = new Category("1000", "Women");
		productRequest.setShop(shop);
		
		Category categoryPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort())).path("/category")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(categoryRequest), Category.class);

		Long categoryId = categoryPersisted.getId();
		assertThat(categoryId).isNotNull();
		productRequest.setCategory(categoryPersisted);
        
		Product productPersisted = client
			.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort())).path("/product")
			.request(MediaType.APPLICATION_JSON)
			.post(Entity.json(productRequest), Product.class);
		
		assertThat(productPersisted).isNotNull();
		assertThat(productPersisted.getId()).isNotNull();
		assertThat(productPersisted.getSku()).isEqualTo("SKU101");
		assertThat(productPersisted.getName()).isEqualTo("Test Women Skirt");
		assertThat(productPersisted.getShortDesc()).isEqualTo("Short Description Women Skirt");
		assertThat(productPersisted.getLongDesc()).isEqualTo("Long Description Women Skirt");
		assertThat(productPersisted.getImagePath()).isEqualTo("/winter/2015/women");
		
		assertThat(productPersisted.getShop().getId()).isNotNull();
		assertThat(productPersisted.getShop().getName()).isEqualTo("GULGS");
	} 
	

	@AfterClass
	public static void teardownClass() throws IOException {
		String dblocation = StringUtils.substringAfter(RULE.getConfiguration().getDatabase().getUrl(), ".");
		Files.deleteIfExists(Paths.get( new StringBuilder(".").append(dblocation).append(".").append("mv").append(".").append("db").toString() ));
		Files.deleteIfExists(Paths.get( new StringBuilder(".").append(dblocation).append(".").append("trace").append(".").append("db").toString() ));
	}
	
	public void restAssuredTesting() {
//		given()
//		.contentType(MediaType.APPLICATION_JSON)
//		.body(MAPPER.writeValueAsString(productRequest))
//		.expect()
//		.statusCode(Status.CREATED.getStatusCode())
//		.contentType(MediaType.APPLICATION_JSON)
//		.when()
//		.body("sku", Matchers.equalTo("SKU101"), 
//				"name", Matchers.equalTo("Test Women Skirt"), 
//				"shortDesc", Matchers.equalTo("Short Description Women Skirt"), 
//				"longDesc", Matchers.equalTo("Long Description Women Skirt"), 
//				"imagePath", Matchers.equalTo("/winter/2015/women"))
//		.post(String.format("http://localhost:%d/gul-product-service/product", RULE.getLocalPort()));
	}
	
	
}
