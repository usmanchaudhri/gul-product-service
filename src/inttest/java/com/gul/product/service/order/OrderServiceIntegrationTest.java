package com.gul.product.service.order;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.Test;

import com.gul.product.service.product.AbstractProductServiceIntegrationTest;
import com.gul.product.service.representation.Customer;
import com.gul.product.service.representation.Order;

public class OrderServiceIntegrationTest extends AbstractProductServiceIntegrationTest {

	@Test
	public void test_place_a_new_order() {
		Client client = JerseyClientBuilder.createClient();

		Customer customerRequest = new Customer();
		customerRequest.setUsername("azhar.rao@gmail.com");
		
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
		customerRequest.setOrder(orders);
		
		Customer customerPersisted = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path("/customer")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(customerRequest), Customer.class);
		assertThat(customerPersisted.getId());

		Long orderPersistedId = customerPersisted.getOrder().get(0).getId();
		Order orderRequestUpdate = new Order();
		orderRequestUpdate.setStatus("Cancelled");

		// updating Order
		Order orderPersistedUpdated = client
				.target(String.format(REST_PRODUCT_SERVICE_URL, RULE.getLocalPort()))
				.path(new StringBuilder("/order/").append(orderPersistedId).toString())
				.request(MediaType.APPLICATION_JSON)
				.put(Entity.json(orderRequestUpdate), Order.class);
		assertThat(orderPersistedUpdated.getId()).isNotNull();
		assertThat(orderPersistedUpdated.getStatus()).isEqualTo("Cancelled");
	}

	
//	@Test
//	public void test_place_a_new_order() {
//		Client client = JerseyClientBuilder.createClient();
//	}
	
}



