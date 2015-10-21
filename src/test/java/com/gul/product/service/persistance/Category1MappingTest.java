package com.gul.product.service.persistance;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import com.google.inject.ConfigurationException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.ProvisionException;
import com.gul.product.service.representation.Category1;
import com.gul.product.service.representation.Product1;

public class Category1MappingTest {

	@Ignore
	@Test
	public void testCategoryHierarchy() throws SQLException, ConfigurationException, ProvisionException {
		Injector injector = Guice.createInjector(new DbModule());	// test method will have it's own EntityManager 
		PersistedClassDao persistedClassDao = injector.getInstance(PersistedClassDao.class);

		Category1 womenCategory = new Category1("Women");
		persistedClassDao.saveInNewTransaction(womenCategory);

		Category1 retrievedCategory = persistedClassDao.getEntityManager().find(Category1.class, womenCategory.getId());
		Assert.assertNotNull(retrievedCategory.getId());

		Product1 product = new Product1("Top");
		product.setCategory(womenCategory);
		persistedClassDao.saveInNewTransaction(product);
		
		Product1 retrievedProduct = persistedClassDao.getEntityManager().find(Product1.class, product.getId());
		Assert.assertNotNull(retrievedProduct.getId());
	
//		Category retrievedWomenCat = persistedClassDao.getEntityManager().find(Category.class, womenCategory.getId());
//		Category retrievedWomenCatTotes = persistedClassDao.getEntityManager().find(Category.class, womenTotes.getId());
//		Category retrievedWomenCatShirts = persistedClassDao.getEntityManager().find(Category.class, womenShirtsAndDresses.getId());

//		Assert.assertNotNull(retrievedWomenCat.getId());
//		Assert.assertNotNull(retrievedWomenCatTotes.getId());
//		Assert.assertNotNull(retrievedWomenCatShirts.getId());
	}
	
	@Test
	public void testCategoryMultipleHierarchy() {
		Injector injector = Guice.createInjector(new DbModule());	// test method will have it's own EntityManager 
		PersistedClassDao persistedClassDao = injector.getInstance(PersistedClassDao.class);

		Category1 category = new Category1("Women");

		Product1 product1 = new Product1("Top");
		product1.setCategory(category);
		
		Set<Product1> products = new HashSet<Product1>();
		products.add(product1);
		
		category.setProducts(products);
		
		persistedClassDao.saveInNewTransaction(category);
		persistedClassDao.saveInNewTransaction(product1);
		
		// get category
		Category1 retrievedCategory = persistedClassDao.getEntityManager().find(Category1.class, category.getId());
		Assert.assertNotNull(retrievedCategory.getId());
		
		Set<Product1> retrievedProducts = retrievedCategory.getProducts();
		for(Product1 tmpProduct: retrievedProducts) {
			Assert.assertNotNull(tmpProduct.getId());
		}
		
		System.out.println("End");
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
