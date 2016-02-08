package com.gul.product.service.persistance;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import com.google.inject.ConfigurationException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.ProvisionException;
import com.gul.product.service.representation.Customer;
import com.gul.product.service.representation.CustomerShipping;
import com.gul.product.service.representation.Order;

public class OrderMappingTest {
	
	@Test
	public void test_customer_placing_order() throws SQLException, ConfigurationException, ProvisionException {
		Injector injector = Guice.createInjector(new DbModule());
		PersistedClassDao persistedClassDao = injector.getInstance(PersistedClassDao.class);
		
		CustomerShipping customerShipping = new CustomerShipping("2460 Fulton", "San Francisco", "CA", "94118", "USA");
		List<CustomerShipping> shipping = new ArrayList<CustomerShipping>();
		shipping.add(customerShipping);
		Customer customer = new Customer("Usman", "Chaudhri", "azhar.rao@gmail.com", "310-809-8581", shipping);
		persistedClassDao.saveInNewTransaction(customer);
		Customer retrievedCustomer = persistedClassDao.getEntityManager().find(Customer.class, customer.getId());
		Assert.assertNotNull(retrievedCustomer.getId());
		
		// create an order
		Order order = new Order("1001", "Tunic Top", "SKU_101_TUNIC", "9", "39.99", "/gul/product", "31", "10");
		order.setCustomer(retrievedCustomer);
		
		persistedClassDao.saveInNewTransaction(order);
		Order retrievedOrder = persistedClassDao.getEntityManager().find(Order.class, order.getId());
		
		Assert.assertNotNull(retrievedOrder.getId());
		Assert.assertThat(retrievedOrder.getProductName(), Matchers.is("Tunic Top"));
		Assert.assertThat(retrievedOrder.getProductId(), Matchers.is("1001"));
		Assert.assertThat(retrievedOrder.getProductSku(), Matchers.is("SKU_101_TUNIC"));
		Assert.assertThat(retrievedOrder.getCustomer().getId(), Matchers.is(retrievedCustomer.getId()));
		
	}

}
