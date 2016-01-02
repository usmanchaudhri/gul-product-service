package com.gul.product.service.persistance;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import com.google.inject.ConfigurationException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.ProvisionException;
import com.gul.product.service.representation.Category;
import com.gul.product.service.representation.Designer;
import com.gul.product.service.representation.Product;
import com.gul.product.service.representation.Shop;

public class ShopMappingTest {
	
	@Test
	public void test_creating_new_shop_with_designer() {
		Injector injector = Guice.createInjector(new DbModule());
		PersistedClassDao persistedClassDao = injector.getInstance(PersistedClassDao.class);

		Shop shop = new Shop("gulgs");
		Designer designer = new Designer();
		designer.setImagePath("/winter/2015");
		designer.setName("Nayyar Chaudhri");
		List<Designer> designers = new ArrayList<Designer>();
		designers.add(designer);
		shop.setDesigners(designers);
		
		persistedClassDao.saveInNewTransaction(shop);

		Shop retrievedShop = persistedClassDao.getEntityManager().find(Shop.class, shop.getId());
		Assert.assertNotNull(retrievedShop.getId());
	}
	
	@Ignore
	@Test
	public void test_save_shop_and_designer_profile_when_saving_product() {
		Injector injector = Guice.createInjector(new DbModule());
		PersistedClassDao persistedClassDao = injector.getInstance(PersistedClassDao.class);

		Shop shop = new Shop("gulgs");
		Designer designer = new Designer();
		designer.setImagePath("/winter/2015");
		designer.setName("Nayyar Chaudhri");
		List<Designer> designers = new ArrayList<Designer>();
		designers.add(designer);
		shop.setDesigners(designers);
		
		Product product = new Product(); 
		product.setName("women skirt");
		product.setSku("SKU_SKIRT_101");
		product.setShortDesc("embroided women skirt");
		product.setLongDesc("handmade embroided women skirt");
		product.setQuantity(10L);
		
		Category category = new Category("1001", "Women");
		product.setCategory(category);
		product.setShop(shop);

		persistedClassDao.saveInNewTransaction(category);
		persistedClassDao.saveInNewTransaction(product);
		
		Product retrievedProduct = persistedClassDao.getEntityManager().find(Product.class, product.getId());
		Assert.assertNotNull(retrievedProduct.getId());
		
		Designer retrievedDesigner = retrievedProduct.getShop().getDesigners().get(0);
		Assert.assertNotNull(retrievedDesigner.getId());
	}
	
	
	@Test
	public void test_create_shop_with_product() {
		Injector injector = Guice.createInjector(new DbModule());
		PersistedClassDao persistedClassDao = injector.getInstance(PersistedClassDao.class);

		Shop shop = new Shop("gulgs");
		Product product = new Product(); 
		product.setName("women skirt");
		product.setSku("SKU_SKIRT_101");
		product.setShortDesc("embroided women skirt");
		product.setLongDesc("handmade embroided women skirt");
		product.setQuantity(10L);
		
		Category category = new Category("1001", "Women");
		product.setCategory(category);
		product.setShop(shop);

		persistedClassDao.saveInNewTransaction(category);
		persistedClassDao.saveInNewTransaction(product);
		
		Product retrievedProduct = persistedClassDao.getEntityManager().find(Product.class, product.getId());
		Assert.assertNotNull(retrievedProduct.getId());
		Assert.assertNotNull(retrievedProduct.getShop().getId());
	}
	
	@Test
	public void test_shop_creation() throws SQLException, ConfigurationException, ProvisionException {
		Injector injector = Guice.createInjector(new DbModule());
		PersistedClassDao persistedClassDao = injector.getInstance(PersistedClassDao.class);

		Shop shop = new Shop("gulgs");
		persistedClassDao.saveInNewTransaction(shop);
		Shop retrievedShop = persistedClassDao.getEntityManager().find(Shop.class, shop.getId());
		
		Assert.assertNotNull(retrievedShop.getId());
		Assert.assertTrue(retrievedShop.getName().equals("gulgs"));
	}	
	
	@Test
	public void test_named_query_returns_all_shops() {
		Injector injector = Guice.createInjector(new DbModule());
		PersistedClassDao persistedClassDao = injector.getInstance(PersistedClassDao.class);

		Shop shopGulgs = new Shop("gulgs");
		Shop shopDesignUnlimited = new Shop("designs-unlimited");
		Shop shopMyCollection = new Shop("my collection");

		persistedClassDao.saveInNewTransaction(shopGulgs);
		persistedClassDao.saveInNewTransaction(shopDesignUnlimited);
		persistedClassDao.saveInNewTransaction(shopMyCollection);
		
		Shop retrievedShop = persistedClassDao.getEntityManager().find(Shop.class, shopGulgs.getId());
		Assert.assertNotNull(retrievedShop.getId());

		Query queryShopFindAll = persistedClassDao.getEntityManager().createNamedQuery("com.gul.product.service.representation.Shop.findAll");
		List<Shop> shops = queryShopFindAll.getResultList();
		for(Shop shop : shops) {
			Assert.assertNotNull(shop.getId());
		}
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
