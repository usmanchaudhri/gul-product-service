package com.gul.product.service.persistance;

import java.sql.SQLException;
import org.junit.Assert;
import org.junit.Test;
import com.google.inject.ConfigurationException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.ProvisionException;
import com.gul.product.service.representation.CustomerShipping;

public class CustomerShippingMappingTest {

	@Test
	public void testCustomerShipping() throws SQLException, ConfigurationException, ProvisionException {
		Injector injector = Guice.createInjector(new DbModule());	// test method will have it's own EntityManager 
		PersistedClassDao persistedClassDao = injector.getInstance(PersistedClassDao.class);

		CustomerShipping customerShipping = new CustomerShipping("Usman" , "Chaudhri", "2460 Fulton", "San Francisco", "CA", "94118", "USA");
		persistedClassDao.saveInNewTransaction(customerShipping);

		CustomerShipping retrievedCustomerShipping = persistedClassDao.getEntityManager().find(CustomerShipping.class, customerShipping.getId());
		Assert.assertNotNull(retrievedCustomerShipping.getId());
	}

}
