package com.gul.product.service.order;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.junit.Test;

import com.gul.product.service.product.AbstractProductServiceIntegrationTest;
import com.gul.product.service.representation.Customer;
import com.gul.product.service.representation.Order;

public class OrderServiceIntegrationTest extends AbstractProductServiceIntegrationTest {

	@Test
	public void test_place_a_new_order() {
		Client client = JerseyClientBuilder.createClient();

		Customer customerRequest = new Customer();
		customerRequest.setUsername("azhar.rao");
		customerRequest.setPassword("password");
		Customer customerPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/customer").append("/signup") .toString())
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(customerRequest), Customer.class);
		assertThat(customerPersisted.getId());

		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("azhar.rao", "password");
		client.register(feature);

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
		orderRequest.setCustomer(customerRequest);
		
		List<Order> orders = new ArrayList<Order>();
		orders.add(orderRequest);
		customerPersisted.setOrder(orders);
		
		// CREATE ORDER
		Order persistedOrder = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/orders").toString())
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(orderRequest), Order.class);
		assertThat(persistedOrder.getId()).isNotNull();
		assertThat(persistedOrder.getProductId()).isEqualToIgnoringCase("101");
		assertThat(persistedOrder.getProductCategoryId()).isEqualToIgnoringCase("10");
		assertThat(persistedOrder.getCustomer().getId()).isEqualTo(customerPersisted.getId());
		
		// updating Order
//		Order orderPersistedUpdated = client
//				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
//				.path(new StringBuilder("/orders/").append(orderPersistedId).toString())
//				.request(MediaType.APPLICATION_JSON)
//				.put(Entity.json(orderRequestUpdate), Order.class);
//		assertThat(orderPersistedUpdated.getId()).isNotNull();
//		assertThat(orderPersistedUpdated.getStatus()).isEqualTo("Cancelled");
	}

	
	
	
}



