package com.gul.product.service.persistance;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

		List<Shipping> shippingTo = new ArrayList<Shipping>();
		shippingTo.add(shippingToPak);
		shippingTo.add(shippingToEngland);
		shippingTo.add(shippingToGermany);
		
		shippingFrom.setShippingTo(shippingTo);
		

		persistedClassDao.saveInNewTransaction(shippingFrom);					

		Shipping shippingInfo = persistedClassDao.getEntityManager().find(Shipping.class, shippingFrom.getId());
		Assert.assertNotNull(shippingInfo.getId());
		
		Shipping shippingsFrom = shippingInfo.getShippingFrom();
		Collection<Shipping> shippings = shippingInfo.getShippingTo();

		System.out.println("");
	}

}
