package com.gul.product.service.persistance;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import com.google.inject.ConfigurationException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.ProvisionException;
import com.gul.product.service.audit.TimeStamped;
import com.gul.product.service.representation.Customer;
import com.gul.product.service.representation.CustomerShipping;
import com.gul.product.service.representation.Shop;

public class CustomerMappingTest {

	@Test
	public void test_password_to_md5_hash() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String password = "password123";
		byte[] hashedPassword = DigestUtils.getSha256Digest().digest(password.getBytes("UTF-8"));		
		System.out.println("Hashed Password: " + hashedPassword.toString());
		// assert something here ???
	}
	
	@Ignore
	@Test
	public void test_create_a_new_customer() throws SQLException, ConfigurationException, ProvisionException {
		Injector injector = Guice.createInjector(new DbModule());	// test method will have it's own EntityManager 
		PersistedClassDao persistedClassDao = injector.getInstance(PersistedClassDao.class);
		
		CustomerShipping customerShipping = new CustomerShipping("Usman", "Chaudhri","2460 Fulton", "San Francisco", "CA", "94118", "USA");
		List<CustomerShipping> shipping = new ArrayList<CustomerShipping>();
		shipping.add(customerShipping);
		Customer customer = new Customer("username", "password");		
		setTimeStamp(customer);
		
		persistedClassDao.saveInNewTransaction(customer);

		Customer retrievedCustomer = persistedClassDao.getEntityManager().find(Customer.class, customer.getId());
		
		Assert.assertNotNull(retrievedCustomer.getId());
		Assert.assertTrue(retrievedCustomer.getUsername().equals("azhar.rao@gmail.com"));
	}
	
	@Ignore
	@Test
	public void test_add_and_empty_shop_to_an_existing_customer() {
		Injector injector = Guice.createInjector(new DbModule());	// test method will have it's own EntityManager 
		PersistedClassDao persistedClassDao = injector.getInstance(PersistedClassDao.class);
				
		CustomerShipping customerShipping = new CustomerShipping("Usman", "Chaudhri","2460 Fulton", "San Francisco", "CA", "94118", "USA");
		List<CustomerShipping> shipping = new ArrayList<CustomerShipping>();
		shipping.add(customerShipping);
		Customer customer = new Customer("username", "password");		
		setTimeStamp(customer);

		persistedClassDao.saveInNewTransaction(customer);
		
		Customer retrievedCustomer = persistedClassDao.getEntityManager().find(Customer.class, customer.getId());
		Assert.assertNotNull(retrievedCustomer.getId());

		Shop shop = new Shop("MyFirstShop");
		retrievedCustomer.setShop(shop);
		setTimeStamp(shop);

		persistedClassDao.saveInNewTransaction(customer);

		Shop retrievedShop = persistedClassDao.getEntityManager().find(Shop.class, shop.getId());

		Assert.assertNotNull(retrievedShop.getId());
		Assert.assertTrue(retrievedShop.getName().equals("MyFirstShop"));
	}
	
	public void setTimeStamp(TimeStamped timeStamped) {
		timeStamped.setCreatedOn(new Date());
	}
	
	
}
