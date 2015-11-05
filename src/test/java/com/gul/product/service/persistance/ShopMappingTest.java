package com.gul.product.service.persistance;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;

import com.google.inject.ConfigurationException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.ProvisionException;
import com.gul.product.service.representation.Category;
import com.gul.product.service.representation.Product;
import com.gul.product.service.representation.Shop;

public class ShopMappingTest {

	@Test
	public void creatingEmptyShopTest() throws SQLException, ConfigurationException, ProvisionException {
		Injector injector = Guice.createInjector(new DbModule());
		PersistedClassDao persistedClassDao = injector.getInstance(PersistedClassDao.class);

		Shop shop = new Shop("gulgs");
		persistedClassDao.saveInNewTransaction(shop);
		Shop retrievedShop = persistedClassDao.getEntityManager().find(Shop.class, shop.getId());
		
		Assert.assertNotNull(retrievedShop.getId());
		Assert.assertTrue(retrievedShop.getName().equals("gulgs"));
	}	
	
//	@Test
//	public void newShopWithProductsTest() throws SQLException, ConfigurationException, ProvisionException {
//		Injector injector = Guice.createInjector(new DbModule());
//		PersistedClassDao persistedClassDao = injector.getInstance(PersistedClassDao.class);
//
//		// create shop
//		Shop shop = new Shop("gulgs");
//		//persistedClassDao.saveInNewTransaction(shop);
//		
//		Product product = new Product(); 
//		product.setName("Test Women Skirt");
//		product.setSku("SKU101");
//		product.setShortDesc("Short Description Women Skirt");
//		product.setLongDesc("Short Description Women Skirt");
//		product.setImagePath("/winter/2015/women");
//		product.setQuantity(10L);
//		
//		Category category = new Category("1001", "Women");
//		product.setCategory(category);
//		product.setShop(shop);
//
//		Collection<Product> products = new ArrayList<Product>();
//		shop.setProducts(products);
//		
//		persistedClassDao.saveInNewTransaction(category);
//		persistedClassDao.saveInNewTransaction(product);
//		persistedClassDao.saveInNewTransaction(shop);
//
//		Shop retrievedShop = persistedClassDao.getEntityManager().find(Shop.class, shop.getId());
//		
//		Iterator<Product> ite =  retrievedShop.getProducts().iterator();
//		while(ite.hasNext()) {
//			Product shopProduct = ite.next();
//			Assert.assertTrue(shopProduct.getId().equals(product.getId()));
//			Assert.assertTrue(shopProduct.getId().equals(product.getName()));
//		}
//		
//		Assert.assertNotNull(retrievedShop.getId());
//	}
	
}
