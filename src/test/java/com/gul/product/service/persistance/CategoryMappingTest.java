package com.gul.product.service.persistance;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.internal.util.collections.Sets;

import com.google.inject.ConfigurationException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.ProvisionException;
import com.gul.product.service.representation.Category;
import com.gul.product.service.representation.Product;
import com.gul.product.service.representation.Shop;

public class CategoryMappingTest {

	@Test
	public void testCategoryHierarchy() throws SQLException, ConfigurationException, ProvisionException {
		Injector injector = Guice.createInjector(new DbModule());	// test method will have it's own EntityManager 
		PersistedClassDao persistedClassDao = injector.getInstance(PersistedClassDao.class);

		// creating category hierarchy
		Category womenCategory = new Category("1001", "Women");

		Category womenSkirtCategory = new Category("1002", "Women Skirts");
		Category womenSkirtLongCategory = new Category("1003", "Women Skirts Long");
		
		Category womenBagsCategory = new Category("1004", "Women Bags");
		Category womenBagsToteCategory = new Category("1005", "Women Bags Tote");

		// set Women Skirt parent and child
		womenSkirtLongCategory.setParentCategory(womenSkirtCategory);
		womenSkirtCategory.setSubCategories(Arrays.asList(womenSkirtLongCategory));
		
		// set Women Bags parent and child
		womenBagsToteCategory.setParentCategory(womenBagsCategory);
		womenBagsCategory.setSubCategories(Arrays.asList(womenBagsToteCategory));
		
		// set Women Skirt and Women Bags parent and child
		womenSkirtCategory.setParentCategory(womenCategory);
		womenBagsCategory.setParentCategory(womenCategory);
		womenCategory.setSubCategories(Arrays.asList(womenSkirtCategory, womenBagsCategory));
		
		// save the parent child relationship with-in a transaction
		persistedClassDao.getEntityManager().getTransaction().begin();
		persistedClassDao.save(womenCategory);
		persistedClassDao.save(womenSkirtCategory);
		persistedClassDao.save(womenBagsCategory);
		persistedClassDao.save(womenSkirtLongCategory);
		persistedClassDao.save(womenBagsToteCategory);
		persistedClassDao.getEntityManager().getTransaction().commit();

		Category retrievedCategory = persistedClassDao.getEntityManager().find(Category.class, womenCategory.getId());

		List<Category> subCategories = (List<Category>) retrievedCategory.getSubCategories();
		Long parentIdSkirt = subCategories.get(0).getParentCategory().getId();
		Long parentIdBags = subCategories.get(1).getParentCategory().getId();
		Assert.assertTrue(parentIdSkirt.equals(parentIdBags));
		
		// women sub sub category
		List<Category> subSubCategories = (List<Category>) subCategories.get(0).getSubCategories();
		Assert.assertNotNull(subSubCategories.get(0).getId());
	}
	
	
	// tests category contains the list of products
	@Test
	public void testCategoryProductAssociation() throws SQLException, ConfigurationException, ProvisionException {
		Injector injector = Guice.createInjector(new DbModule());
		PersistedClassDao persistedClassDao = injector.getInstance(PersistedClassDao.class);

		Category category = new Category("1001", "Women");

		Shop shop = new Shop("MyShop");
		
		Product productSkirt = new Product("SKU101", "Embroided Skirt",
				"Embroided Women Skirt",
				"Handmade embroided Women Skirt made from the finest silk",
				10L);
		productSkirt.setQuantity(10L);
		productSkirt.setCategory(category);
		productSkirt.setShop(shop);

		Product productTop = new Product("SKU102", "Wolen Tunic Top",
				"Embroided Wolen Tunic Top",
				"Handmade embroided Women Tunic Top made from the finest silk",
				10L);
		productTop.setQuantity(34L);
		productTop.setCategory(category);
		productTop.setShop(shop);
		
		category.setProducts(Sets.newSet(productSkirt, productTop));
		
		persistedClassDao.saveInNewTransaction(category);					
		persistedClassDao.saveInNewTransaction(productSkirt);					
		persistedClassDao.saveInNewTransaction(productTop);	
		
		Category retrievedCategory = persistedClassDao.getEntityManager().find(Category.class, category.getId());
		Set<Product> products = retrievedCategory.getProducts();

		Iterator<Product> ite = products.iterator();
		while(ite.hasNext()) {
			Product product = ite.next();
			Assert.assertNotNull(product.getId());
		}
	}

	
	
}
