package com.gul.product.service.persistance;

import org.junit.Assert;
import org.junit.Test;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.gul.product.service.representation.EmailSubscription;

public class EmailSubscriptionMappingTest {

	@Test
	public void test_subscribing_new_email_address() {
		Injector injector = Guice.createInjector(new DbModule());	// test method will have it's own EntityManager 
		PersistedClassDao persistedClassDao = injector.getInstance(PersistedClassDao.class);
		
		EmailSubscription emailSubscription = new EmailSubscription("azhar.rao@gmail.com");
		persistedClassDao.saveInNewTransaction(emailSubscription);
		
		EmailSubscription persistedEmailSubscription = persistedClassDao.getEntityManager().find(EmailSubscription.class, emailSubscription.getId());
		Assert.assertNotNull(persistedEmailSubscription.getId());
	}
	
	
}
