package com.gul.product.service.persistance;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.gul.product.service.representation.Shipping;

public class ShippingMappingTest {
	
	@Test
	public void testShippingHierarchy() {
		Injector injector = Guice.createInjector(new DbModule());
		PersistedClassDao persistedClassDao = injector.getInstance(PersistedClassDao.class);

		Shipping shippingFrom = new Shipping("USA");
		Shipping shippingToPak = new Shipping("Pakistan", 5L, 35.00);
		Shipping shippingToEngland = new Shipping("England", 2L, 20.00);
		Shipping shippingToGermany = new Shipping("Germany", 1L, 15.00);

		Set<Shipping> shippingTo = new HashSet<Shipping>();
		shippingTo.add(shippingToPak);
		shippingTo.add(shippingToEngland);
		shippingTo.add(shippingToGermany);
		
		shippingFrom.setShippingTo(shippingTo);
		
		persistedClassDao.saveInNewTransaction(shippingFrom);					

		Shipping retrievedShipping = persistedClassDao.getEntityManager().find(Shipping.class, shippingFrom.getId());
		Assert.assertNotNull(retrievedShipping.getId());
		
		Set<Shipping> shippingsFrom = (Set<Shipping>) retrievedShipping.getShippingTo();
		Iterator<Shipping> ite = shippingsFrom.iterator();
		while(ite.hasNext()) {
			Shipping shipping = ite.next();
			Assert.assertNotNull(shipping.getId());
		}
	}

}
