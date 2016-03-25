package com.gul.product.service.persistance;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import com.google.inject.ConfigurationException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.ProvisionException;
import com.gul.product.service.audit.TimeStamped;
import com.gul.product.service.representation.CChat;
import com.gul.product.service.representation.Customer;

public class CChatMappingTest {

	@Test
	public void test_create_a_new_customer() throws SQLException, ConfigurationException, ProvisionException {
		Injector injector = Guice.createInjector(new DbModule());	// test method will have it's own EntityManager 
		PersistedClassDao persistedClassDao = injector.getInstance(PersistedClassDao.class);
	
		CChat cchat = new CChat("Amjad-Usman");
		List<CChat> cchats = new ArrayList<CChat>();
		cchats.add(cchat);
		
		Customer customer = new Customer("azhar.rao@gmail.com", "password");
		customer.setCchat(cchats);
		setTimeStamp(customer);
		
		persistedClassDao.saveInNewTransaction(customer);
		Customer retrievedCustomer = persistedClassDao.getEntityManager().find(Customer.class, customer.getId());
		Assert.assertNotNull(retrievedCustomer.getId());
		
		List<CChat> retrievedCChats = retrievedCustomer.getCchat();
		Assert.assertNotNull(retrievedCChats.get(0).getId());
		Assert.assertTrue(retrievedCChats.get(0).getUniqueName().equalsIgnoreCase("Amjad-Usman"));
	}
	
	public void setTimeStamp(TimeStamped timeStamped) {
		timeStamped.setCreatedOn(new Date());
	}
	
}
