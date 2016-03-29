package com.gul.product.service.customer;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.junit.Ignore;
import org.junit.Test;

import com.gul.product.service.product.AbstractProductServiceIntegrationTest;
import com.gul.product.service.representation.CChat;
import com.gul.product.service.representation.Customer;
import com.gul.product.service.representation.CustomerShipping;
import com.gul.product.service.representation.Order;

public class CustomerServiceIntegrationTest extends AbstractProductServiceIntegrationTest {

	@Ignore
	@Test
	public void test_add_customer_shipping_to_customer() throws JsonParseException, JsonMappingException, IOException {
		Client client = JerseyClientBuilder.createClient();
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("azhar.rao", "password");
		client.register(feature);

		// CREATE NEW USER
		Customer customer = new Customer();
		customer.setUsername("azhar.rao");
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
				.path(new StringBuilder("/cchat").toString())
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(cchat), CChat.class);
		assertThat(persistedCchat.getId()).isNotNull();
		// assertThat(persistedCchat.getUniqueName()).isEqualTo("Usman-Asifa");
		
		// ADD CCHAT1 TO USER
		CChat cchat1 = new CChat("Usman-Gulgs");
		CChat persistedCchat1 = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/cchat").toString())
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(cchat1), CChat.class);
		assertThat(persistedCchat1.getId()).isNotNull();
		// assertThat(persistedCchat1.getUniqueName()).isEqualTo("Usman-Gulgs");
		
		// ADD CUSTOMERSHIPPING TO USER
		CustomerShipping customerShipping = new CustomerShipping("Usman", "Chaudhri", "2460 Fulton", "San Francisco", "CA", "94118", "USA");
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
//		JsonNode getPersistedCustomerCchat = client
//				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
//				.path(new StringBuilder("/customer/").append(getPersistedCustomer.getId()).append("/cchat") .toString())
//				.request(MediaType.APPLICATION_JSON)
//				.get(JsonNode.class);
//		
//		ObjectMapper mapper = new ObjectMapper();
//		List<CChat> getPersistedCChats = mapper.readValue(mapper.treeAsTokens(getPersistedCustomerCchat) , new TypeReference<List<CChat>>(){});
//		assertThat(getPersistedCChats.get(0).getId()).isNotNull();

		
//		assertThat(getPersistedCustomerCchat.get(0).getId()).isNotNull();
//		assertThat(getPersistedCustomerCchat.get(0).getUniqueName()).containsIgnoringCase("Usman-Asifa");
		
		// lazy load cchat
//		Customer getPersistedCustomerOrders = client
//				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
//				.path(new StringBuilder("/customer/").append(getPersistedCustomer.getId()).append("/orders") .toString())
//				.request(MediaType.APPLICATION_JSON)
//				.get(Customer.class);
//		assertThat(getPersistedCustomerOrders.getOrder().get(0).getId()).isNotNull();
//		assertThat(getPersistedCustomerOrders.getOrder().get(0).getProductId()).isEqualTo("101");

	}
	
	@Test
	public void test_customer_update_cchat_list() {
		Client client = JerseyClientBuilder.createClient();
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("azhar.rao", "password");
		client.register(feature);

		CChat cchat1 = new CChat("Usman-Safina-1");
		CChat cchat2 = new CChat("Usman-Amjad-1");
		CChat cchat3 = new CChat("Usman-Talha-1");
		
		List<CChat> cchats = new ArrayList<CChat>();
		cchats.add(cchat1);
		cchats.add(cchat2);
		cchats.add(cchat3);

		Customer customer = new Customer();
		customer.setUsername("azhar.rao1");
		customer.setPassword("password1");
		customer.setCchat(cchats);
		Customer customerPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/customer").append("/signup") .toString())
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(customer), Customer.class);

		assertThat(customerPersisted.getId()).isNotNull();
		
		CChat cchat4 = new CChat("Usman-Safina-2");
		customerPersisted.getCchat().add(cchat4);
		Customer customerPersistedUpdated = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/customer/"+customerPersisted.getId()).toString())
				.request(MediaType.APPLICATION_JSON)
				.put(Entity.json(customerPersisted), Customer.class);

		assertThat(customerPersistedUpdated.getId()).isNotNull();


		// TODO - lazy load cchat from customers.
		LinkedHashMap<String, CChat> cusCchats = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/customer/").append(customerPersistedUpdated.getId()).append("/cchat") .toString())
				.request(MediaType.APPLICATION_JSON)
				.get(LinkedHashMap.class);

//		List<CChat> cusCchats = client
//				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
//				.path(new StringBuilder("/customer/").append(customerPersistedUpdated.getId()).append("/cchat").toString())
//				.request(MediaType.APPLICATION_JSON)
//				.get(List.class);
		
		for(Map.Entry<String , CChat> entry : cusCchats.entrySet()) {
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
			CChat cchat = entry.getValue();
			assertThat(cchat.getId()).isNotNull();
		}
		
		System.out.println("");		
	}
	
	@Ignore
	@Test
	public void test_customer_creating_multiple_chat() {
		Client client = JerseyClientBuilder.createClient();
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("talha.khan", "password");
		client.register(feature);
		
		CChat cchat1 = new CChat("Talha-Amjad");
		CChat cchat2 = new CChat("Talha-Talha");
		CChat cchat3 = new CChat("Talha-Zunabia");
		
		List<CChat> cchats = new ArrayList<CChat>();
		cchats.add(cchat1);
		cchats.add(cchat2);
		cchats.add(cchat3);
		
		Customer customer = new Customer();
		customer.setUsername("talha.khan");
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

	@Ignore
	@Test
	public void test_customer_creating_single_chat() {
		Client client = JerseyClientBuilder.createClient();
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("awais.khan", "password");
		client.register(feature);

		CChat cchat = new CChat("awais-zara");
		List<CChat> cchats = new ArrayList<CChat>();
		cchats.add(cchat);

		Customer customer = new Customer();
		customer.setUsername("awais.khan");
		customer.setPassword("password");
		customer.setCchat(cchats);
		
		Customer customerPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/customer").append("/signup").toString())
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(customer), Customer.class);

		assertThat(customerPersisted.getId()).isNotNull();
		assertThat(customerPersisted.getCchat().get(0).getId()).isNotNull();
	}
	
	@Ignore
	@Test
	public void test_find_customer_by_id() {
		Client client = JerseyClientBuilder.createClient();
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("asif.nabeel", "password");
		client.register(feature);

		CustomerShipping customerShipping = new CustomerShipping("Usman", "Chaudhri", "2460 Fulton", "San Francisco", "CA", "94118", "USA");
		List<CustomerShipping> shipping = new ArrayList<CustomerShipping>();
		shipping.add(customerShipping);
		Customer customer = new Customer("asif.nabeel", "password");

		Customer customerPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/customer").append("/signup").toString())
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
