package com.gul.product.service.resources;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import io.dropwizard.testing.junit.ResourceTestRule;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import com.gul.product.service.persistance.CChatDao;
import com.gul.product.service.persistance.CustomerDao;
import com.gul.product.service.representation.Customer;
import com.gul.product.service.representation.CustomerShipping;
import com.gul.product.service.representation.Order;

public class CustomerEndPointTest {

    private static final CustomerDao customerDao = mock(CustomerDao.class);
    private static final CChatDao cchatDao = mock(CChatDao.class);

    @Rule
	public final ResourceTestRule resources = 
			ResourceTestRule.builder().addResource(new CustomerResource(customerDao, cchatDao)).build();

	@Test
	public void creating_new_customer() {
		Client client = resources.client();
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("azhar.rao", "password");
		client.register(feature);

		CustomerShipping customerShipping = new CustomerShipping("Usman", "Chaudhri","2460 Fulton", "San Francisco", "CA", "94118", "USA", "Y");
		List<CustomerShipping> shipping = new ArrayList<CustomerShipping>();
		shipping.add(customerShipping);
		Customer customer = new Customer("azhar.rao@gmail.com", "password");
		customer.setId(1L);
		
		Mockito.when(customerDao.create(Mockito.any(Customer.class))).thenReturn(customer);

		Customer customerPersisted = resources
				.client()
				.target(new StringBuilder("/customer").append("/signup") .toString())
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(customer), Customer.class);

		assertThat(customerPersisted.getId()).isNotNull();
	}
	
	@Test
	public void create_new_customer_with_single_order_and_shipping() {
		Client client = resources.client();
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("azhar.rao", "password");
		client.register(feature);

		CustomerShipping customerShipping = new CustomerShipping("Usman", "Chaudhri","2460 Fulton", "San Francisco", "CA", "94118", "USA", "Y");
		List<CustomerShipping> shipping = new ArrayList<CustomerShipping>();
		shipping.add(customerShipping);
		Customer customer = new Customer("azhar.rao@gmail.com", "password");
		customer.setId(1L);

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
		orderRequest.setCustomer(customer);
		
		List<Order> orders = new ArrayList<Order>();
		orders.add(orderRequest);
		customer.setOrder(orders);
		
		Mockito.when(customerDao.create(Mockito.any(Customer.class))).thenReturn(customer);
		Customer customerPersisted = resources
				.client()
				.target(new StringBuilder("/customer").append("/signup") .toString())
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(customer), Customer.class);
		
		assertThat(customerPersisted.getId()).isNotNull();
		List<Order> persistedOrders = customerPersisted.getOrder();
		Order persistedOrder = persistedOrders.get(0);
		assertThat(persistedOrder.getStatus()).isEqualTo("Processed");
	}
	
	

}
