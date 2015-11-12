package com.gul.product.service.persistance;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.gul.product.service.representation.ShipsTo;

public class ShippingMappingTest {
	
	@Test
	public void testShippingHierarchy() {
		Injector injector = Guice.createInjector(new DbModule());
		PersistedClassDao persistedClassDao = injector.getInstance(PersistedClassDao.class);

		ShipsTo shippingFrom = new ShipsTo("USA");
		ShipsTo shippingToPak = new ShipsTo("Pakistan", 5L, 35.00);
		ShipsTo shippingToEngland = new ShipsTo("England", 2L, 20.00);
		ShipsTo shippingToGermany = new ShipsTo("Germany", 1L, 15.00);

		Set<ShipsTo> shippingTo = new HashSet<ShipsTo>();
		shippingTo.add(shippingToPak);
		shippingTo.add(shippingToEngland);
		shippingTo.add(shippingToGermany);
		
		shippingFrom.setShippingTo(shippingTo);
		
		persistedClassDao.saveInNewTransaction(shippingFrom);					

		ShipsTo retrievedShipping = persistedClassDao.getEntityManager().find(ShipsTo.class, shippingFrom.getId());
		Assert.assertNotNull(retrievedShipping.getId());
		
		Set<ShipsTo> shippingsFrom = (Set<ShipsTo>) retrievedShipping.getShippingTo();
		Iterator<ShipsTo> ite = shippingsFrom.iterator();
		while(ite.hasNext()) {
			ShipsTo shipping = ite.next();
			Assert.assertNotNull(shipping.getId());
		}
	}

}
