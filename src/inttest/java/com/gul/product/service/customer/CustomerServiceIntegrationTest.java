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
import com.gul.product.service.representation.User;

public class CustomerServiceIntegrationTest extends AbstractProductServiceIntegrationTest {

	@Ignore
	@Test
	public void test_customer_authenticator() {
		Client client = JerseyClientBuilder.createClient();
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("azhar.rao", "password");
		client.register(feature);

		Customer customerPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path("/customer")
				.request(MediaType.APPLICATION_JSON)
				.get(Customer.class);
		
		assertThat(customerPersisted.getId()).isNotNull();
	}
	
	@Ignore
	@Test
	public void test_customer_login() {
		Client client = JerseyClientBuilder.createClient();
		
		Customer customer = new Customer();
		customer.setEmail("azhar.rao@gmail.com");
		customer.setPassword("password");
		Customer customerPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path("/customer")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(customer), Customer.class);
		
		assertThat(customerPersisted.getId()).isNotNull();

		Customer customerToFind = new Customer();
		customerToFind.setEmail("azhar.rao@gmail.com");
		customerToFind.setPassword("password");

		Customer findExistingCustomer = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path("/customer/login")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(customer), Customer.class);

		assertThat(findExistingCustomer.getId()).isNotNull();
		assertThat(findExistingCustomer.getEmail().equalsIgnoreCase(customerPersisted.getEmail())).isNotNull();
		assertThat(findExistingCustomer.getPassword().equalsIgnoreCase(customerPersisted.getPassword())).isNotNull();
	}
	
	@Ignore
	@Test
	public void test_storing_hashed_passwords() {
		Client client = JerseyClientBuilder.createClient();
		
		Customer customer = new Customer();
		customer.setEmail("azhar.rao@gmail.com");
		customer.setPassword("password");
		
		Customer customerPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path("/customer")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(customer), Customer.class);
		
		assertThat(customerPersisted.getId()).isNotNull();
	}

	@Ignore
	@Test
	public void create_new_customer_with_empty_shop() {
		Client client = JerseyClientBuilder.createClient();

		CustomerShipping customerShipping = new CustomerShipping("2460 Fulton", "San Francisco", "CA", "94118", "USA");
		List<CustomerShipping> shipping = new ArrayList<CustomerShipping>();
		shipping.add(customerShipping);
		Customer customer = new Customer("azhar.rao", "password");
		
		Customer customerPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path("/customer")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(customer), Customer.class);

		Long customerId = customerPersisted.getId();
		String firstName = customerPersisted.getEmail();
		
		assertThat(customerId).isNotNull();
		assertThat(firstName).isEqualTo("azhar.rao");
	}
	
	@Ignore
	@Test
	public void test_find_customer_by_id() {
		Client client = JerseyClientBuilder.createClient();

		CustomerShipping customerShipping = new CustomerShipping("2460 Fulton", "San Francisco", "CA", "94118", "USA");
		List<CustomerShipping> shipping = new ArrayList<CustomerShipping>();
		shipping.add(customerShipping);
		Customer customer = new Customer("azhar.rao", "password");

		Customer customerPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort())).path("/customer")
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
