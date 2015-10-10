package com.gul.product.service.persistance;

import java.sql.SQLException;
import org.junit.Assert;
import org.junit.Test;
import com.google.inject.ConfigurationException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.ProvisionException;
import com.gul.product.service.representation.Category;
import com.gul.product.service.representation.PricingProduct;
import com.gul.product.service.representation.Product;

// Entity Product test against H2 DB
public class ProductMappingTest {

	@Test
	public void testProductCategoryMappingAssociationTest() throws SQLException, ConfigurationException, ProvisionException {
		Injector injector = Guice.createInjector(new DbModule());
		PersistedClassDao persistedClassDao = injector.getInstance(PersistedClassDao.class);

		Product product = new Product(); 
		product.setName("Test Women Skirt");
		product.setSku("SKU101");
		product.setShortDesc("Short Description Women Skirt");
		product.setLongDesc("Short Description Women Skirt");
		product.setImagePath("/winter/2015/women");
		product.setQuantity(10L);
		
		Category category = new Category("1001", "Women");
		product.setCategory(category);

		persistedClassDao.saveInNewTransaction(category);					// save category first since it will be needed for the product association
		persistedClassDao.saveInNewTransaction(product);
		
		Product retrievedProduct = persistedClassDao.getEntityManager().find(Product.class, product.getId());
		Assert.assertTrue(retrievedProduct.getName().equals("Test Women Skirt"));
		Assert.assertTrue(retrievedProduct.getImagePath().equals("/winter/2015/women"));
		Assert.assertTrue(retrievedProduct.getSku().equals("SKU101"));
		Assert.assertTrue(retrievedProduct.getQuantity().equals(10L));
	}
	
	@Test
	public void testCreatingProductPricing() throws SQLException, ConfigurationException, ProvisionException {
		Injector injector = Guice.createInjector(new DbModule());
		PersistedClassDao persistedClassDao = injector.getInstance(PersistedClassDao.class);

		Product product = new Product(); 
		product.setName("Test Women Skirt");
		product.setSku("SKU101");
		product.setShortDesc("Short Description Women Skirt");
		product.setLongDesc("Short Description Women Skirt");
		product.setImagePath("/winter/2015/women");
		product.setQuantity(10L);

		Category category = new Category("1001", "Women");
		product.setCategory(category);

		PricingProduct pricingProduct = new PricingProduct(99.95);
		pricingProduct.setProduct(product);
		product.setPricingProduct(pricingProduct);
		
		persistedClassDao.getEntityManager().getTransaction().begin();
		persistedClassDao.save(category);
		persistedClassDao.save(pricingProduct);
		persistedClassDao.save(product);
		persistedClassDao.getEntityManager().getTransaction().commit();

		Product retrievedProduct = persistedClassDao.getEntityManager().find(Product.class, product.getId());
		PricingProduct pricing =  retrievedProduct.getPricingProduct();
		
		Double storedValue = pricing.getStoredValue();
		Long pricingProductId = pricing.getPricingProductId();
		
		Assert.assertNotNull(pricing);
		Assert.assertNotNull(pricingProductId);
		Assert.assertTrue(storedValue.equals(99.95));
		Assert.assertTrue(pricing.getProduct().getSku().equals("SKU101"));
		
	}
	

}
