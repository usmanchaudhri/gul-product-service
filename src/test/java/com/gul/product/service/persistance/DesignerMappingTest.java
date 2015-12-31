package com.gul.product.service.persistance;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.gul.product.service.representation.Designer;
import com.gul.product.service.representation.Shop;

public class DesignerMappingTest {
	
	@Test
	public void test_create_shop_designer() {
		Injector injector = Guice.createInjector(new DbModule());
		PersistedClassDao persistedClassDao = injector.getInstance(PersistedClassDao.class);
		
		Shop shop = new Shop("gulgs");
		Designer designer = new Designer();
		designer.setName("Ayesha Mumtaz");
		designer.setShop(shop);
		List<Designer> designers = new ArrayList<Designer>();
		designers.add(designer);		
		shop.setDesigners(designers);
		
		persistedClassDao.saveInNewTransaction(shop);
		
		Shop retrievedShop = persistedClassDao.getEntityManager().find(Shop.class, shop.getId());
		Assert.assertNotNull(retrievedShop.getId());
		
		Designer retrievedDesigner = retrievedShop.getDesigners().get(0);
		Assert.assertNotNull(retrievedDesigner.getId());
	}

}
