package com.gul.product.service.persistance;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.gul.product.service.representation.Category;
import com.gul.product.service.representation.Designer;
import com.gul.product.service.representation.Product;
import com.gul.product.service.representation.Shop;

public class DesignerMappingTest {
	
	@Ignore
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
	
	@Test
	public void test_create_new_shop_designer_when_saving_product() {
		Injector injector = Guice.createInjector(new DbModule());
		PersistedClassDao persistedClassDao = injector.getInstance(PersistedClassDao.class);
		
		Product product = new Product(); 
		product.setName("Women Skirt");
		product.setSku("SKU_WOMEN_SKIRT_101");
		product.setShortDesc("Short Description Women Skirt");
		product.setLongDesc("Long Description Women Skirt");
		product.setQuantity(10L);

		Category category = new Category("1001", "Women");
		product.setCategory(category);

		Shop shop = new Shop("gulgs");
		Designer designer = new Designer();
		designer.setName("Ayesha Mumtaz");
		List<Designer> designers = new ArrayList<Designer>();
		designers.add(designer);		
		shop.setDesigners(designers);
		product.setShop(shop);
		
		persistedClassDao.saveInNewTransaction(category);
		persistedClassDao.saveInNewTransaction(product);
		
		Product retrievedProduct = persistedClassDao.getEntityManager().find(Product.class, product.getId());
		Assert.assertNotNull(retrievedProduct.getId());
		
		List<Designer> retrievedDesigners = retrievedProduct.getShop().getDesigners();
		for(Designer retrievedDesigner : retrievedDesigners) {
			Assert.assertTrue(retrievedDesigner.getName().equalsIgnoreCase("Ayesha Mumtaz"));
			Assert.assertNotNull(retrievedDesigner.getId());
		}
	}
	

}
