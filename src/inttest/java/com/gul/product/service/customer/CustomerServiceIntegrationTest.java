package com.gul.product.service.customer;

import static org.assertj.core.api.Assertions.assertThat;
import io.dropwizard.flyway.FlywayFactory;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.flywaydb.core.Flyway;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;

import com.gul.product.service.app.ProductServiceApplicationTest;
import com.gul.product.service.app.ProductServiceConfigurationTest;
import com.gul.product.service.representation.Customer;
import com.gul.product.service.representation.CustomerShipping;

public class CustomerServiceIntegrationTest {

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

		// migrate customer
		flyway.migrate();
	}

	@Test
	public void create_new_customer_with_empty_shop() {
		Client client = JerseyClientBuilder.createClient();

		CustomerShipping customerShipping = new CustomerShipping("2460 Fulton", "San Francisco", "CA", "94118", "USA");
		List<CustomerShipping> shipping = new ArrayList<CustomerShipping>();
		shipping.add(customerShipping);
		Customer customer = new Customer("Usman", "Chaudhri", "azhar.rao@gmail.com", "310-809-8581", shipping);

		Customer customerPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort())).path("/customer")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(customer), Customer.class);

		Long customerId = customerPersisted.getId();
		String firstName = customerPersisted.getFirstName();
		
		assertThat(customerId).isNotNull();
		assertThat(firstName).isEqualTo("Usman");

	}
	
}
