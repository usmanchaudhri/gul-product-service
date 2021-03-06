package com.gul.product.service.shop;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gul.product.service.product.AbstractProductServiceIntegrationTest;
import com.gul.product.service.representation.Customer;
import com.gul.product.service.representation.CustomerShipping;
import com.gul.product.service.representation.Designer;
import com.gul.product.service.representation.Shop;

public class ShopServiceIntegrationTest extends AbstractProductServiceIntegrationTest {
	
	private static final String REST_PRODUCT_SERVICE_URL = "http://localhost:%d/gul-product-service";
	
	
	@Test
	public void test_get_shop_terms_and_conditions() {
		Client client = JerseyClientBuilder.createClient();
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("usman.chaudhri@gmail.com", "password");
		client.register(feature);
		
		// Create Customer
		CustomerShipping customerShipping = new CustomerShipping("Usman", "Chaudhri", "2460 Fulton", "San Francisco", "CA", "94118", "USA", "Y");
		List<CustomerShipping> shipping = new ArrayList<CustomerShipping>();
		shipping.add(customerShipping);
		Customer customer = new Customer("usman.chaudhri@gmail.com", "password");
		Customer customerPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/customer").append("/signup").toString())
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(customer), Customer.class);
		assertThat(customerPersisted.getId()).isNotNull();

		Shop shopRequest = new Shop("BridalShop");
		shopRequest.setDetails("This is a Bridal Shop.");
		shopRequest.setImagePath("/gul/images/2016");
		shopRequest.setShopOwner(customerPersisted);
		shopRequest.setPolicy("All sales are final and product could not be sold at our shop.");
		Shop shopPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/shop/").toString())
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(shopRequest), Shop.class);

		assertThat(shopPersisted.getId()).isNotNull();
		assertThat(shopPersisted.getName()).isEqualToIgnoringCase("BridalShop");
		assertThat(shopPersisted.getPolicy()).isEqualToIgnoringCase("All sales are final and product could not be sold at our shop.");
	}
	
	@Test
	public void test_get_shop_details() {
		Client client = JerseyClientBuilder.createClient();
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("usman.chaudhri@gmail.com", "password");
		client.register(feature);
		
		// Create Customer
		CustomerShipping customerShipping = new CustomerShipping("Usman", "Chaudhri", "2460 Fulton", "San Francisco", "CA", "94118", "USA", "Y");
		List<CustomerShipping> shipping = new ArrayList<CustomerShipping>();
		shipping.add(customerShipping);
		Customer customer = new Customer("usman.chaudhri@gmail.com", "password");
		Customer customerPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/customer").append("/signup").toString())
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(customer), Customer.class);
		assertThat(customerPersisted.getId()).isNotNull();

		Shop shopRequest = new Shop("testshop");
		shopRequest.setDetails("This is a test shop created for testing details.");
		shopRequest.setImagePath("/gul/images/2016");
		shopRequest.setShopOwner(customerPersisted);
		Shop shopPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/shop/").toString())
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(shopRequest), Shop.class);
		
		assertThat(shopPersisted.getId()).isNotNull();
		assertThat(shopPersisted.getDetails()).isEqualToIgnoringCase("This is a test shop created for testing details.");
		assertThat(shopPersisted.getImagePath()).isEqualToIgnoringCase("/gul/images/2016");
	}
	
	@Ignore
	@Test
	public void test_get_designers_and_product_from_shop() {
		Client client = JerseyClientBuilder.createClient();
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("usman.chaudhri@gmail.com", "password");
		client.register(feature);
		
		// Create Customer
		CustomerShipping customerShipping = new CustomerShipping("Usman", "Chaudhri", "2460 Fulton", "San Francisco", "CA", "94118", "USA", "Y");
		List<CustomerShipping> shipping = new ArrayList<CustomerShipping>();
		shipping.add(customerShipping);
		Customer customer = new Customer("usman.chaudhri@gmail.com", "password");
		Customer customerPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/customer").append("/signup").toString())
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(customer), Customer.class);
		assertThat(customerPersisted.getId()).isNotNull();

		// Create and link Shop to Customer
		Shop shopRequest = new Shop("testshop");
		Designer designer = new Designer();
		designer.setName("Ayesha Mumtaz");
		designer.setImagePath("/listings/designers/ayeshamumtaz/");
		designer.setShop(shopRequest);
		List<Designer> designers = new ArrayList<Designer>();
		designers.add(designer);
		shopRequest.setDesigners(designers);
		shopRequest.setShopOwner(customerPersisted);
		
		Shop shopPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/shop").toString())
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(shopRequest), Shop.class);
		assertThat(shopPersisted.getId()).isNotNull();
		
		//	Load Designers
		List<Designer> persistedDesigners = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/shop/").append(shopPersisted.getId()).append("/designers").toString())
				.request(MediaType.APPLICATION_JSON)
				.get(List.class);
		
		Designer persistedDesigner = persistedDesigners.get(0);
		assertThat(persistedDesigner.getId()).isNotNull();		
	}
	
	@Test
	public void test_add_new_shop_to_existing_customer() {
		Client client = JerseyClientBuilder.createClient();
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("usman.chaudhri1@gmail.com", "password");
		client.register(feature);


		// Create Customer
		CustomerShipping customerShipping = new CustomerShipping("Usman", "Chaudhri", "2460 Fulton", "San Francisco", "CA", "94118", "USA", "Y");
		List<CustomerShipping> shipping = new ArrayList<CustomerShipping>();
		shipping.add(customerShipping);
		Customer customer = new Customer("usman.chaudhri1@gmail.com", "password");
		
		Customer customerPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/customer").append("/signup").toString())
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(customer), Customer.class);
		
		assertThat(customerPersisted.getId()).isNotNull();
		
		// Create and link Shop to Customer
		Shop shopRequest = new Shop("testshop");
		shopRequest.setShopOwner(customerPersisted);
		Shop shopPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/shop/").toString())
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(shopRequest), Shop.class);

		assertThat(shopPersisted.getId()).isNotNull();
		assertThat(shopPersisted.getName()).isEqualTo("testshop");
		assertThat(shopPersisted.getShopOwner().getId()).isEqualTo(customerPersisted.getId()); 
	}
	
	// Why is this test case failing ??? It passes with a breakpoint installed.
	@Test
	public void test_updating_shop_with_designers() {
		Client client = JerseyClientBuilder.createClient();
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("usman.chaudhri2@gmail.com", "password");
		client.register(feature);
		
		// Create Customer
		CustomerShipping customerShipping = new CustomerShipping("Usman", "Chaudhri", "2460 Fulton", "San Francisco", "CA", "94118", "USA", "Y");
		List<CustomerShipping> shipping = new ArrayList<CustomerShipping>();
		shipping.add(customerShipping);
		Customer customer = new Customer("usman.chaudhri2@gmail.com", "password");
		Customer customerPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/customer").append("/signup").toString())
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(customer), Customer.class);
		assertThat(customerPersisted.getId()).isNotNull();

		// Create Shop
		Shop shopRequest = new Shop("gulgs");
		shopRequest.setShopOwner(customerPersisted);
		Shop shopPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/shop").toString())
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(shopRequest), Shop.class);
		assertThat(shopPersisted.getId()).isNotNull();

		// Updating Shop
		Designer designer = new Designer();
		designer.setName("Ayesha Mumtaz");
		designer.setImagePath("/listings/designers/ayeshamumtaz/");
		designer.setShop(shopPersisted);
		List<Designer> designers = new ArrayList<Designer>();
		designers.add(designer);
		shopPersisted.setDesigners(designers);
		Shop shopUpdated = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/shop/").append(shopPersisted.getId()).toString())
				.request(MediaType.APPLICATION_JSON)
				.put(Entity.json(shopPersisted), Shop.class);
		
		assertThat(shopUpdated.getId()).isNotNull();
		List<Designer> persistedDesigners = shopUpdated.getDesigners();
		assertThat(persistedDesigners.get(0).getId()).isNotNull();
		assertThat(persistedDesigners.get(0).getName()).isEqualTo("Ayesha Mumtaz");
	}
	
	@Test
	public void test_creating_new_shop() throws JsonProcessingException {
		Client client = JerseyClientBuilder.createClient();
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("usman.chaudhri3@gmail.com", "password");
		client.register(feature);

		// Create Customer
		CustomerShipping customerShipping = new CustomerShipping("Usman", "Chaudhri", "2460 Fulton", "San Francisco", "CA", "94118", "USA", "Y");
		List<CustomerShipping> shipping = new ArrayList<CustomerShipping>();
		shipping.add(customerShipping);
		Customer customer = new Customer("usman.chaudhri3@gmail.com", "password");
		
		Customer customerPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/customer").append("/signup").toString())
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(customer), Customer.class);
		assertThat(customerPersisted.getId()).isNotNull();

		// Create Shop
		Shop shopRequest = new Shop("gulgs");
		shopRequest.setShopOwner(customerPersisted);
		Designer designer = new Designer();
		designer.setName("Ayesha Mumtaz");
		designer.setImagePath("/listings/designers/ayeshamumtaz/");
		designer.setShop(shopRequest);
		List<Designer> designers = new ArrayList<Designer>();
		designers.add(designer);
		shopRequest.setDesigners(designers);
		
		Shop shopPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/shop").toString())
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(shopRequest), Shop.class);
		assertThat(shopPersisted.getId()).isNotNull();

		Designer persistedDesigner = shopPersisted.getDesigners().get(0);
		assertThat(persistedDesigner.getId()).isNotNull();
		assertThat(persistedDesigner.getName().equalsIgnoreCase("Ayesha Mumtaz"));
	}
	
}



