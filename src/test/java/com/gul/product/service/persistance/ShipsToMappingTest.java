package com.gul.product.service.persistance;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.junit.Assert;
import org.junit.Test;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.gul.product.service.representation.ShipsTo;

public class ShipsToMappingTest {
	
	/**
	 * add shipping information.
	 **/
	@Test
	public void test_new_shipping_info() {
		Injector injector = Guice.createInjector(new DbModule());
		PersistedClassDao persistedClassDao = injector.getInstance(PersistedClassDao.class);

		// shipping from
		ShipsTo shipsFrom = new ShipsTo();
		shipsFrom.setCountryName("Pakistan");

		// shipping to
		ShipsTo shipsToUSA = new ShipsTo();
		shipsToUSA.setShippingFrom(shipsFrom);
		shipsToUSA.setCountryName("USA");
		shipsToUSA.setProcessingDays(10L);
		shipsToUSA.setShippingCost(2000.00);
		
		ShipsTo shipsToBRAZIL = new ShipsTo();
		shipsToBRAZIL.setShippingFrom(shipsFrom);
		shipsToBRAZIL.setCountryName("BRAZIL");
		shipsToBRAZIL.setProcessingDays(10L);
		shipsToBRAZIL.setShippingCost(1500.40);
		
		ShipsTo shipsToDUBAI = new ShipsTo();
		shipsToDUBAI.setShippingFrom(shipsFrom);
		shipsToDUBAI.setCountryName("DUBAI");
		shipsToDUBAI.setProcessingDays(5L);
		shipsToDUBAI.setShippingCost(1000.00);
		
		Set<ShipsTo> shippings = new HashSet<ShipsTo>();
		shippings.add(shipsToUSA);
		shippings.add(shipsToBRAZIL);
		shippings.add(shipsToDUBAI);
		shipsFrom.setShippingTo(shippings);

		// save all places to ship to in a single transaction.
		persistedClassDao.getEntityManager().getTransaction().begin();
		persistedClassDao.save(shipsFrom);
		persistedClassDao.save(shipsToUSA);
		persistedClassDao.save(shipsToBRAZIL);
		persistedClassDao.save(shipsToDUBAI);		
		persistedClassDao.getEntityManager().getTransaction().commit();

		
		ShipsTo retrievedShipsTo = persistedClassDao.getEntityManager().find(ShipsTo.class, shipsFrom.getId());		
		Assert.assertNotNull(retrievedShipsTo.getId());		
		
		Collection<ShipsTo> retrievedShippingsTo = retrievedShipsTo.getShippingTo();
		Iterator ite = retrievedShippingsTo.iterator();
		while(ite.hasNext()) {
			ShipsTo shippingTo = (ShipsTo) ite.next();
			Assert.assertNotNull(shippingTo.getId());		
		}
		
		// update shipping 
		ShipsTo shipsToAMSTERDAM = new ShipsTo();
		shipsToAMSTERDAM.setShippingFrom(shipsFrom);
		shipsToAMSTERDAM.setCountryName("AMSTERDAM");
		shipsToAMSTERDAM.setProcessingDays(6L);
		shipsToAMSTERDAM.setShippingCost(1200.00);
		
		shippings.add(shipsToAMSTERDAM);
		
		persistedClassDao.getEntityManager().getTransaction().begin();
		persistedClassDao.save(shipsToAMSTERDAM);
		persistedClassDao.getEntityManager().getTransaction().commit();
		
		ShipsTo retrievedUpdatedShipsTo = persistedClassDao.getEntityManager().find(ShipsTo.class, shipsFrom.getId());		
		Assert.assertNotNull(retrievedUpdatedShipsTo.getId());		
		Collection<ShipsTo> retrievedUpdatedShippingsTo = retrievedShipsTo.getShippingTo();
		Assert.assertTrue(retrievedUpdatedShippingsTo.size() == 4);
		
	}
	
	
}
