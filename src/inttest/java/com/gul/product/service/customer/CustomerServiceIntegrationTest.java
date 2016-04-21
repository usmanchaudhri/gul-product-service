package com.gul.product.service.customer;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.junit.Ignore;
import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gul.product.service.product.AbstractProductServiceIntegrationTest;
import com.gul.product.service.representation.CChat;
import com.gul.product.service.representation.Customer;
import com.gul.product.service.representation.CustomerShipping;
import com.gul.product.service.representation.Order;

public class CustomerServiceIntegrationTest extends AbstractProductServiceIntegrationTest {

	@Ignore
	@Test
	public void test_delete_customer_shipping_address() throws JsonParseException, JsonMappingException, IOException {
		Client client = JerseyClientBuilder.createClient();
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("usman.chaudhri6@gmail.com", "password");
		client.register(feature);
	
		// CREATE NEW USER
		Customer customer = new Customer();
		customer.setUsername("usman.chaudhri6@gmail.com");
		customer.setPassword("password");
		Customer customerPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/customer").append("/signup").toString())
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(customer), Customer.class);
		assertThat(customerPersisted.getId()).isNotNull();
		
		// ADD CUSTOMERSHIPPING TO USER
		CustomerShipping customerShipping = new CustomerShipping("Usman", "Chaudhri", "2460 Fulton", "San Francisco", "CA", "94118", "USA", "Y");
		CustomerShipping persistedCustomerShipping = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/customershipping").toString())
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(customerShipping), CustomerShipping.class);
		assertThat(persistedCustomerShipping.getId()).isNotNull();
		assertThat(persistedCustomerShipping.getIsActive()).isNotNull();

		// DELETED CUSTOMERSHIPPING ADDRESS
		CustomerShipping deletedCustomerShipping = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/customershipping/").append(persistedCustomerShipping.getId()).toString())
				.request(MediaType.APPLICATION_JSON)
				.delete(CustomerShipping.class);
		assertThat(deletedCustomerShipping.getId()).isNotNull();
	}

	@Test
	public void test_add_customer_active_shipping_address() throws JsonParseException, JsonMappingException, IOException {
		Client client = JerseyClientBuilder.createClient();
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("usman.chaudhri6@gmail.com", "password");
		client.register(feature);
		
		// CREATE NEW USER
		Customer customer = new Customer();
		customer.setUsername("usman.chaudhri6@gmail.com");
		customer.setPassword("password");
		Customer customerPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/customer").append("/signup").toString())
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(customer), Customer.class);
		assertThat(customerPersisted.getId()).isNotNull();

		// ADD CUSTOMERSHIPPING TO USER
		CustomerShipping customerShipping = new CustomerShipping("Usman", "Chaudhri", "2460 Fulton", "San Francisco", "CA", "94118", "USA", "Y");
		CustomerShipping persistedCustomerShipping = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/customershipping").toString())
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(customerShipping), CustomerShipping.class);
		assertThat(persistedCustomerShipping.getId()).isNotNull();
		assertThat(persistedCustomerShipping.getFirstName()).isEqualTo("Usman");
		assertThat(persistedCustomerShipping.getLastName()).isEqualTo("Chaudhri");
		assertThat(persistedCustomerShipping.getIsActive()).isEqualTo("Y");
	}
	
	@Test
	public void test_add_customer_shipping_to_customer() throws JsonParseException, JsonMappingException, IOException {
		Client client = JerseyClientBuilder.createClient();
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("usman.chaudhri@gmail.com", "password");
		client.register(feature);

		// CREATE NEW USER
		Customer customer = new Customer();
		customer.setUsername("usman.chaudhri@gmail.com");
		customer.setPassword("password");
		Customer customerPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/customer").append("/signup").toString())
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(customer), Customer.class);
		assertThat(customerPersisted.getId()).isNotNull();
		
		// ADD CCHAT TO USER
		CChat cchat = new CChat("Usman-Asifa");
		CChat persistedCchat = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/customer/").append(customerPersisted.getId()).append("/cchat").toString())
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(cchat), CChat.class);
		assertThat(persistedCchat.getId()).isNotNull();
		// assertThat(persistedCchat.getUniqueName()).isEqualTo("Usman-Asifa");
		
		// ADD CCHAT1 TO USER
		CChat cchat1 = new CChat("Usman-Gulgs");
		CChat persistedCchat1 = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/customer/").append(customerPersisted.getId()).append("/cchat").toString())
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(cchat1), CChat.class);
		assertThat(persistedCchat1.getId()).isNotNull();
		// assertThat(persistedCchat1.getUniqueName()).isEqualTo("Usman-Gulgs");
		
		// ADD CUSTOMERSHIPPING TO USER
		CustomerShipping customerShipping = new CustomerShipping("Usman", "Chaudhri", "2460 Fulton", "San Francisco", "CA", "94118", "USA", "Y");
		CustomerShipping persistedCustomerShipping = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/customershipping").toString())
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(customerShipping), CustomerShipping.class);
		assertThat(persistedCustomerShipping.getId()).isNotNull();
		assertThat(persistedCustomerShipping.getFirstName()).isEqualTo("Usman");
		assertThat(persistedCustomerShipping.getLastName()).isEqualTo("Chaudhri");
		
		// ADD ORDER TO USER
		Order orderRequest = new Order();
		orderRequest.setProductId("101");
		orderRequest.setProductCategoryId("10");
		orderRequest.setProductImagePath("/image/listing");
		orderRequest.setProductName("shirt");
		orderRequest.setProductPrice("10.99");
		orderRequest.setProductQuantity("5");
		orderRequest.setProductShopId("40");
		orderRequest.setProductSku("SKU_101");
		orderRequest.setStatus("Processed");
		Order persistedOrder = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/orders").toString())
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(orderRequest), Order.class);
		assertThat(persistedOrder.getId()).isNotNull();
		
		// GET PERSISTED CUSTOMER
		Customer getPersistedCustomer = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/customer").append("/login") .toString())
				.request(MediaType.APPLICATION_JSON)
				.get(Customer.class);
		assertThat(getPersistedCustomer.getId()).isNotNull();
		assertThat(getPersistedCustomer.getCustomerShipping().get(0).getId()).isNotNull();
		assertThat(getPersistedCustomer.getCustomerShipping().get(0).getFirstName()).isNotNull();

		// lazy load cchat
		JsonNode getPersistedCustomerCchat = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/customer/").append(getPersistedCustomer.getId()).append("/cchat") .toString())
				.request(MediaType.APPLICATION_JSON)
				.get(JsonNode.class);
		
		ObjectMapper mapper = new ObjectMapper();
		List<CChat> getPersistedCChats = mapper.readValue(mapper.treeAsTokens(getPersistedCustomerCchat) , new TypeReference<List<CChat>>(){});
		assertThat(getPersistedCChats.get(0).getId()).isNotNull();
		assertThat(getPersistedCChats.get(1).getId()).isNotNull();
	}

	@Test
	public void test_customer_update_cchat_list() {
		Client client = JerseyClientBuilder.createClient();
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("usman.chaudhri1@gmail.com", "password");
		client.register(feature);

		CChat cchat1 = new CChat("Usman-Safina-1");
		CChat cchat2 = new CChat("Usman-Amjad-1");
		CChat cchat3 = new CChat("Usman-Talha-1");
		List<CChat> cchats = new ArrayList<CChat>();
		cchats.add(cchat1);
		cchats.add(cchat2);
		cchats.add(cchat3);

		Customer customer = new Customer();
		customer.setUsername("usman.chaudhri1@gmail.com");
		customer.setPassword("password");
		customer.setCchat(cchats);
		Customer customerPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/customer").append("/signup") .toString())
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(customer), Customer.class);
		assertThat(customerPersisted.getId()).isNotNull();
		
		CChat cchat4 = new CChat("Usman-Safina-2");
		CChat cchatPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/customer/").append(customerPersisted.getId()).append("/cchat").toString())
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(cchat4) , CChat.class);

		assertThat(cchatPersisted.getId()).isNotNull();
	}
	
	@Test
	public void test_customer_creating_multiple_chat() {
		Client client = JerseyClientBuilder.createClient();
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("usman.chaudhri2@gmail.com", "password");
		client.register(feature);
		
		CChat cchat1 = new CChat("Talha-Amjad");
		CChat cchat2 = new CChat("Talha-Talha");
		CChat cchat3 = new CChat("Talha-Zunabia");
		
		List<CChat> cchats = new ArrayList<CChat>();
		cchats.add(cchat1);
		cchats.add(cchat2);
		cchats.add(cchat3);
		
		Customer customer = new Customer();
		customer.setUsername("usman.chaudhri2@gmail.com");
		customer.setPassword("password");
		customer.setCchat(cchats);
		
		Customer customerPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/customer").append("/signup").toString())
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
	public void test_creating_cchat_during_customer_signup() {
		Client client = JerseyClientBuilder.createClient();
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("usman.chaudhri3@gmail.com", "password");
		client.register(feature);

		CChat cchat = new CChat("awais-zara");
		List<CChat> cchats = new ArrayList<CChat>();
		cchats.add(cchat);

		Customer customer = new Customer();
		customer.setUsername("usman.chaudhri3@gmail.com");
		customer.setPassword("password");
		customer.setCchat(cchats);
		
		Customer customerPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/customer").append("/signup").toString())
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(customer), Customer.class);

		assertThat(customerPersisted.getId()).isNotNull();
		assertThat(customerPersisted.getCchat().get(0).getId()).isNotNull();
		assertThat(customerPersisted.getCchat().get(0).getUniqueName()).isEqualToIgnoringCase("awais-zara");
	}
	
	@Test
	public void test_find_customer_by_id() {
		Client client = JerseyClientBuilder.createClient();
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("usman.chaudhri4@gmail.com", "password");
		client.register(feature);

		CustomerShipping customerShipping = new CustomerShipping("Usman", "Chaudhri", "2460 Fulton", "San Francisco", "CA", "94118", "USA", "Y");
		List<CustomerShipping> shipping = new ArrayList<CustomerShipping>();
		shipping.add(customerShipping);
		Customer customer = new Customer("usman.chaudhri4@gmail.com", "password");

		Customer customerPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/customer").append("/signup").toString())
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(customer), Customer.class);
		assertThat(customerPersisted.getId()).isNotNull();
	}
	
	@Test
	public void test_edit_customer_shipping() {
		Client client = JerseyClientBuilder.createClient();
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("usman.chaudhri5@gmail.com", "password");
		client.register(feature);
	
		// CREATE NEW USER
		Customer customer = new Customer();
		customer.setUsername("usman.chaudhri5@gmail.com");
		customer.setPassword("password");
		Customer customerPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/customer").append("/signup").toString())
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(customer), Customer.class);
		assertThat(customerPersisted.getId()).isNotNull();
		
		// ADD CUSTOMERSHIPPING TO USER
		CustomerShipping customerShipping = new CustomerShipping("Usman", "Chaudhri", "2460 Fulton", "San Francisco", "CA", "94118", "USA", "Y");
		CustomerShipping persistedCustomerShipping = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/customershipping").toString())
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(customerShipping), CustomerShipping.class);
		assertThat(persistedCustomerShipping.getId()).isNotNull();
		
		// EDIT CUSTOMER SHIPPING
		CustomerShipping customerShippingNew = new CustomerShipping("Zarka", "Ahmed", "92E Street 3", "Lahore", "PJ", "04004", "Pakistan", "Y");
		CustomerShipping customerShippingUpdated = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/customershipping/").append(persistedCustomerShipping.getId()).toString())
				.request(MediaType.APPLICATION_JSON)
				.put(Entity.json(customerShippingNew), CustomerShipping.class);
		
		assertThat(customerShippingUpdated.getId()).isNotNull();
		assertThat(customerShippingUpdated.getFirstName()).isEqualTo("Zarka");
		assertThat(customerShippingUpdated.getLastName()).isEqualTo("Ahmed");
		assertThat(customerShippingUpdated.getAddress()).isEqualTo("92E Street 3");
		assertThat(customerShippingUpdated.getCity()).isEqualTo("Lahore");
	}

	@Test
	public void test_edit_customer_shipping_isActive() {
		Client client = JerseyClientBuilder.createClient();
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("usman.chaudhri8@gmail.com", "password");
		client.register(feature);
		
		// CREATE NEW USER
		Customer customer = new Customer();
		customer.setUsername("usman.chaudhri8@gmail.com");
		customer.setPassword("password");
		Customer customerPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/customer").append("/signup").toString())
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(customer), Customer.class);
		assertThat(customerPersisted.getId()).isNotNull();
		
		// ADD CUSTOMERSHIPPING TO USER
		CustomerShipping customerShipping = new CustomerShipping("Usman", "Chaudhri", "2460 Fulton", "San Francisco", "CA", "94118", "USA", "Y");
		CustomerShipping persistedCustomerShipping = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/customershipping").toString())
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(customerShipping), CustomerShipping.class);
		assertThat(persistedCustomerShipping.getId()).isNotNull();
		assertThat(persistedCustomerShipping.getFirstName()).isEqualTo("Usman");
		assertThat(persistedCustomerShipping.getLastName()).isEqualTo("Chaudhri");
		assertThat(persistedCustomerShipping.getAddress()).isEqualTo("2460 Fulton");
		assertThat(persistedCustomerShipping.getCity()).isEqualTo("San Francisco");
		assertThat(persistedCustomerShipping.getIsActive()).isEqualToIgnoringCase("Y");
		
		
		// EDIT CUSTOMER SHIPPING
		CustomerShipping customerShippingNew = new CustomerShipping();
		customerShippingNew.setIsActive("N");
		CustomerShipping customerShippingUpdated = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/customershipping/").append(persistedCustomerShipping.getId()).toString())
				.request(MediaType.APPLICATION_JSON)
				.put(Entity.json(customerShippingNew), CustomerShipping.class);
		assertThat(customerShippingUpdated.getId()).isNotNull();
		assertThat(customerShippingUpdated.getFirstName()).isEqualTo("Usman");
		assertThat(customerShippingUpdated.getLastName()).isEqualTo("Chaudhri");
		assertThat(customerShippingUpdated.getAddress()).isEqualTo("2460 Fulton");
		assertThat(customerShippingUpdated.getCity()).isEqualTo("San Francisco");
		assertThat(customerShippingUpdated.getIsActive()).isEqualToIgnoringCase("N");
	}
}
