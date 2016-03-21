package com.gul.product.service.customer;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.junit.Ignore;
import org.junit.Test;
import com.gul.product.service.product.AbstractProductServiceIntegrationTest;
import com.gul.product.service.representation.Customer;
import com.gul.product.service.representation.CustomerShipping;

public class CustomerServiceIntegrationTest extends AbstractProductServiceIntegrationTest {

	@Test
	public void test_incorrect_username_and_password() {
		
	}
	
	@Test
	public void test_add_customer_shipping_for_an_existing_customer() {
		Client client = JerseyClientBuilder.createClient();

		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("azhar.rao", "password");
		client.register(feature);

		// create customer
		Customer customer = new Customer();
		customer.setUsername("azhar.rao");
		customer.setPassword("password");
		Customer customerPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path("/customer/signup")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(customer), Customer.class);
		assertThat(customerPersisted.getId()).isNotNull();

		CustomerShipping customerShipping = new CustomerShipping("Usman", "Chaudhri", "2460 Fulton", "San Francisco", "CA", "94118", "USA");
		List<CustomerShipping> shipping = new ArrayList<CustomerShipping>();
		shipping.add(customerShipping);
		
		CustomerShipping persistedCustomerShipping = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/customer/").append(customerPersisted.getId()).append("/customershipping").toString())
				.request(MediaType.APPLICATION_JSON)
				.put(Entity.json(customerShipping), CustomerShipping.class);

		assertThat(persistedCustomerShipping.getId()).isNotNull();
		assertThat(persistedCustomerShipping.getFirstName()).isEqualTo("Usman");
		assertThat(persistedCustomerShipping.getAddress()).isEqualTo("2460 Fulton");
	}
	
	@Test
	public void test_customer_signup_and_login() {
		Client client = JerseyClientBuilder.createClient();
		
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("talha.khan", "password");
		client.register(feature);

		Customer customer = new Customer();
		customer.setUsername("talha.khan");
		customer.setPassword("password");
		Customer customerPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path("/customer/signup")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(customer), Customer.class);
		
		assertThat(customerPersisted.getId()).isNotNull();

		Customer findExistingCustomer = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path("/customer/login")
				.request(MediaType.APPLICATION_JSON)
				.get(Customer.class);
		
		assertThat(findExistingCustomer.getId()).isNotNull();
		assertThat(findExistingCustomer.getUsername().equalsIgnoreCase(customerPersisted.getUsername())).isNotNull();
		assertThat(findExistingCustomer.getPassword().equalsIgnoreCase(customerPersisted.getPassword())).isNotNull();
	}
	
	@Test
	public void test_find_customer_by_id() {
		Client client = JerseyClientBuilder.createClient();
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("azhar.rao", "password");
		client.register(feature);

		CustomerShipping customerShipping = new CustomerShipping("Usman", "Chaudhri", "2460 Fulton", "San Francisco", "CA", "94118", "USA");
		List<CustomerShipping> shipping = new ArrayList<CustomerShipping>();
		shipping.add(customerShipping);
		Customer customer = new Customer("asifa.nabeel", "password");

		Customer customerPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path("/customer/signup")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(customer), Customer.class);

		Customer findCustomerPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/customer").append("/").append(customerPersisted.getId()).toString())
				.request(MediaType.APPLICATION_JSON)
				.get(Customer.class);
		
		assertThat(findCustomerPersisted.getId()).isNotNull();
		assertThat(findCustomerPersisted.getId()).isEqualTo(customerPersisted.getId());
	}
	


}
