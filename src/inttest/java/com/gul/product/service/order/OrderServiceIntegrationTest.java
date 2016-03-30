package com.gul.product.service.order;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.junit.Ignore;
import org.junit.Test;

import com.gul.product.service.product.AbstractProductServiceIntegrationTest;
import com.gul.product.service.representation.Customer;
import com.gul.product.service.representation.Order;

public class OrderServiceIntegrationTest extends AbstractProductServiceIntegrationTest {

	@Test
	public void test_submit_multiple_orders() {
		Client client = JerseyClientBuilder.createClient();

		Customer customerRequest = new Customer();
		customerRequest.setUsername("talha.khan");
		customerRequest.setPassword("password");
		Customer customerPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/customer").append("/signup") .toString())
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(customerRequest), Customer.class);
		assertThat(customerPersisted.getId());

		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("talha.khan", "password");
		client.register(feature);

		Order order1 = new Order();
		order1.setProductId("101");
		order1.setProductCategoryId("10");
		order1.setProductImagePath("/image/listing");
		order1.setProductName("shirt");
		order1.setProductPrice("10.99");
		order1.setProductQuantity("5");
		order1.setProductShopId("40");
		order1.setProductSku("SKU_101");
		order1.setStatus("Processed");
		order1.setCustomer(customerRequest);

		Order order2 = new Order();
		order2.setProductId("102");
		order2.setProductCategoryId("20");
		order2.setProductImagePath("/image/listing");
		order2.setProductName("sh");
		order2.setProductPrice("10.99");
		order2.setProductQuantity("5");
		order2.setProductShopId("40");
		order2.setProductSku("SKU_101");
		order2.setStatus("Processed");
		order2.setCustomer(customerRequest);
		
		List<Order> orders = new ArrayList<Order>();
		orders.add(order1);
		orders.add(order2);

		List<Order> persistedOrders = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/orders").append("/submit") .toString())
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(orders), List.class);

		// TODO - getting LinkedHashMap instead of Order
//		Order orderTest1 = (Order) persistedOrders.get(0);
//		System.out.println("");
		
//		for(Order persistedOrder : persistedOrders) {
//			assertThat(persistedOrder.getId()).isNotNull();
//			assertThat(persistedOrder.getProductId()).containsIgnoringCase("101");
//			assertThat(persistedOrder.getProductId()).containsIgnoringCase("102");
//		}
	}
	
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



