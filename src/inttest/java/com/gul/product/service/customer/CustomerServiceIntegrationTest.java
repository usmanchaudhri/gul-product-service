package com.gul.product.service.customer;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.Ignore;
import org.junit.Test;

import com.gul.product.service.product.AbstractProductServiceIntegrationTest;
import com.gul.product.service.representation.CChat;
import com.gul.product.service.representation.Customer;
import com.gul.product.service.representation.CustomerShipping;

public class CustomerServiceIntegrationTest extends AbstractProductServiceIntegrationTest {

	@Ignore
	@Test
	public void test_customer_update_cchat_list() {
		Client client = JerseyClientBuilder.createClient();
		CChat cchat1 = new CChat("Usman-Safina");
		CChat cchat2 = new CChat("Usman-Amjad");
		CChat cchat3 = new CChat("Usman-Talha");
		
		List<CChat> cchats = new ArrayList<CChat>();
		cchats.add(cchat1);
		cchats.add(cchat2);
		cchats.add(cchat3);

		Customer customer = new Customer("Usman", "Chaudhri", "azhar.rao@gmail.com", "310-809-8581", null);		
		customer.setCchat(cchats);
		
		Customer customerPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/customer").toString())
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(customer), Customer.class);

		assertThat(customerPersisted.getId()).isNotNull();
		
		CChat cchat4 = new CChat("Usman-Safina");
		customerPersisted.getCchat().add(cchat4);
		Customer customerPersistedUpdated = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/customer/"+customerPersisted.getId()).toString())
				.request(MediaType.APPLICATION_JSON)
				.put(Entity.json(customerPersisted), Customer.class);

		assertThat(customerPersistedUpdated.getId()).isNotNull();
		assertThat(customerPersistedUpdated.getCchat().contains("Usman-Safina")).isTrue();
	}
	
	@Test
	public void test_customer_creating_multiple_chat() {
		Client client = JerseyClientBuilder.createClient();
		
		CChat cchat1 = new CChat("Usman-Amjad");
		CChat cchat2 = new CChat("Usman-Talha");
		CChat cchat3 = new CChat("Usman-Zunabia");
		
		List<CChat> cchats = new ArrayList<CChat>();
		cchats.add(cchat1);
		cchats.add(cchat2);
		cchats.add(cchat3);
		
		Customer customer = new Customer("Usman", "Chaudhri", "azhar.rao@gmail.com", "310-809-8581", null);		
		customer.setCchat(cchats);
		
		Customer customerPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/customer").toString())
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(customer), Customer.class);
		
		assertThat(customerPersisted.getId()).isNotNull();
		List<CChat> cchatsPersisted = customerPersisted.getCchat();
	
		assertThat(cchatsPersisted.get(0).getId()).isNotNull();
		assertThat(cchatsPersisted.get(1).getId()).isNotNull();
		assertThat(cchatsPersisted.get(2).getId()).isNotNull();
		
		for(CChat cchat: cchatsPersisted) {
			cchats.contains(cchat.getUniqueName());
		}
	}

	@Test
	public void test_customer_creating_single_chat() {
		Client client = JerseyClientBuilder.createClient();
		
		CChat cchat = new CChat("Amjad-Usman");
		List<CChat> cchats = new ArrayList<CChat>();
		cchats.add(cchat);
		Customer customer = new Customer("Usman", "Chaudhri", "azhar.rao@gmail.com", "310-809-8581", null);		
		customer.setCchat(cchats);
		
		Customer customerPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/customer").toString())
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(customer), Customer.class);

		assertThat(customerPersisted.getId()).isNotNull();
		assertThat(customerPersisted.getCchat().get(0).getId()).isNotNull();
	}
	
	@Test
	public void create_new_customer_with_empty_shop() {
		Client client = JerseyClientBuilder.createClient();

		CustomerShipping customerShipping = new CustomerShipping("2460 Fulton", "San Francisco", "CA", "94118", "USA");
		List<CustomerShipping> shipping = new ArrayList<CustomerShipping>();
		shipping.add(customerShipping);
		Customer customer = new Customer("Usman", "Chaudhri", "azhar.rao@gmail.com", "310-809-8581", shipping);
		
		Customer customerPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path("/customer")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(customer), Customer.class);

		Long customerId = customerPersisted.getId();
		String firstName = customerPersisted.getFirstName();
		
		assertThat(customerId).isNotNull();
		assertThat(firstName).isEqualTo("Usman");
	}

	@Test
	public void test_find_customer_by_id() {
		Client client = JerseyClientBuilder.createClient();

		CustomerShipping customerShipping = new CustomerShipping("2460 Fulton", "San Francisco", "CA", "94118", "USA");
		List<CustomerShipping> shipping = new ArrayList<CustomerShipping>();
		shipping.add(customerShipping);
		Customer customer = new Customer("Usman", "Chaudhri", "azhar.rao@gmail.com", "310-809-8581", shipping);

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
