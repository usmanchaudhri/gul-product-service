package com.gul.product.service.persistance;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import com.google.inject.ConfigurationException;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.ProvisionException;
import com.gul.product.service.representation.Category;
import com.gul.product.service.representation.PricingProduct;
import com.gul.product.service.representation.Product;
import com.gul.product.service.representation.ProductVariation;
import com.gul.product.service.representation.Shop;

/**
 * Entity Product test against H2 DB
 **/
public class ProductMappingTest {

	@Test
	public void test_updating_an_existing_shop_with_addotional_products() throws SQLException, ConfigurationException, ProvisionException {
		Injector injector = Guice.createInjector(new DbModule());
		PersistedClassDao persistedClassDao = injector.getInstance(PersistedClassDao.class);
		
		Shop shop = new Shop("Usman Chaudhri");
		Product product = new Product(); 
		product.setName("Test Women Skirt");
		product.setSku("SKU101");
		product.setShortDesc("Short Description Women Skirt");
		product.setLongDesc("Short Description Women Skirt");
		product.setQuantity(10L);
		
		Category category = new Category("1001", "Women");
		product.setCategory(category);
		product.setShop(shop);

		persistedClassDao.saveInNewTransaction(category);					// save category first since it will be needed for the product association
		persistedClassDao.saveInNewTransaction(product);

		Product retrievedProduct = persistedClassDao.getEntityManager().find(Product.class, product.getId());
		Shop retrievedShop = persistedClassDao.getEntityManager().find(Shop.class, shop.getId());
		
		Assert.assertNotNull(retrievedShop.getId());
		Assert.assertTrue(retrievedProduct.getSku().equals("SKU101"));
		
		// updating shop with additional products
		Product newProduct = new Product();
		newProduct.setName("Test Laptop Bag");
		newProduct.setSku("SKU102");
		newProduct.setShortDesc("Short Description Laptop Bag");
		newProduct.setLongDesc("Long Description Laptop Bag");
		newProduct.setQuantity(10L);
		newProduct.setShop(retrievedShop);
		newProduct.setCategory(category);
	
		persistedClassDao.saveInNewTransaction(newProduct);
		Shop retrievedShop1 = persistedClassDao.getEntityManager().find(Shop.class, retrievedShop.getId());
		
		Assert.assertTrue(retrievedShop.getId().equals(retrievedShop1.getId()));
	}
	
	@Test
	public void test_product_and_category_association_when_passing_category_name_and_code() throws SQLException, ConfigurationException, ProvisionException {
		Injector injector = Guice.createInjector(new DbModule());
		PersistedClassDao persistedClassDao = injector.getInstance(PersistedClassDao.class);
		
		Shop shop = new Shop("Usman Chaudhri");
		
		Product product = new Product(); 
		product.setName("Test Women Skirt");
		product.setSku("SKU101");
		product.setShortDesc("Short Description Women Skirt");
		product.setLongDesc("Short Description Women Skirt");
		product.setQuantity(10L);
		
		Category category = new Category("1001", "Women");
		product.setCategory(category);
		
		product.setShop(shop);

		persistedClassDao.saveInNewTransaction(category);					// save category first since it will be needed for the product association
		persistedClassDao.saveInNewTransaction(product);
		
		Product retrievedProduct = persistedClassDao.getEntityManager().find(Product.class, product.getId());
		Assert.assertTrue(retrievedProduct.getName().equals("Test Women Skirt"));
		Assert.assertTrue(retrievedProduct.getSku().equals("SKU101"));
		Assert.assertTrue(retrievedProduct.getQuantity().equals(10L));
	}
	
	@Test
	public void test_creating_product_pricing() throws SQLException, ConfigurationException, ProvisionException {
		Injector injector = Guice.createInjector(new DbModule());
		PersistedClassDao persistedClassDao = injector.getInstance(PersistedClassDao.class);

		Shop shop = new Shop("Usman Chaudhri");

		Product product = new Product(); 
		product.setName("Test Women Skirt");
		product.setSku("SKU101");
		product.setShortDesc("Short Description Women Skirt");
		product.setLongDesc("Short Description Women Skirt");
		product.setQuantity(10L);

		PricingProduct pricingProduct = new PricingProduct(99.95);
		pricingProduct.setProduct(product);
		product.setPricingProduct(pricingProduct);

		Category category = new Category("1001", "Women");
		product.setCategory(category);

		product.setShop(shop);
		
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
	
	@Test
	public void test_named_query_fetech_product_by_category() {
		Injector injector = Guice.createInjector(new DbModule());
		PersistedClassDao persistedClassDao = injector.getInstance(PersistedClassDao.class);

		Shop shop = new Shop("Usman Chaudhri");
		
		Category category = new Category("1001", "Women");
		Product product = new Product("SKU101", "Embroided Skirt",
				"Embroided Women Skirt",
				"Handmade embroided Women Skirt made from the finest silk",
				10L);
		product.setQuantity(10L);
		product.setCategory(category);
		
		product.setShop(shop);
		
		persistedClassDao.saveInNewTransaction(category);
		persistedClassDao.saveInNewTransaction(product);
		
		Product productQuery = (Product) persistedClassDao.getEntityManager().
		createNamedQuery("com.gul.product.service.representation.Product.findProductsByCategory").
		setParameter("categoryId", 1L).
		getSingleResult();

		Assert.assertNotNull(productQuery.getId());
		Assert.assertTrue(productQuery.getName().equals("Embroided Skirt"));
		Assert.assertTrue(productQuery.getSku().equals("SKU101"));
		Assert.assertTrue(productQuery.getCategory().getName().equals("Women"));
	}
	
	@Test
	public void test_add_variation_size_to_product() {
		Injector injector = Guice.createInjector(new DbModule());
		PersistedClassDao persistedClassDao = injector.getInstance(PersistedClassDao.class);

		Shop shop = new Shop("Gulgs");
		Category category = new Category("1001", "Women");
		Product product = new Product("SKU101", "Embroided Skirt",
				"Embroided Women Skirt",
				"Handmade embroided Women Skirt made from the finest silk",
				10L);
		product.setQuantity(10L);
		product.setCategory(category);
		product.setShop(shop);
	
		ProductVariation productVariation = new ProductVariation();
		productVariation.setColor("Blue");
		productVariation.setMaterial("Leather");
		productVariation.setPrice("99.99");
		productVariation.setQuantity("5");
		
		List<ProductVariation> productVariations = new ArrayList<ProductVariation>();
		productVariations.add(productVariation);
		product.setProductVariation(productVariations);
		
		persistedClassDao.saveInNewTransaction(category);
		persistedClassDao.saveInNewTransaction(product);
		
		Product persistedProduct = persistedClassDao.getEntityManager().find(Product.class, product.getId());
		Assert.assertNotNull(persistedProduct.getId());
		List<ProductVariation> persistedVariations = persistedProduct.getProductVariation();
		for(ProductVariation persistedVariation : persistedVariations) {
			Assert.assertNotNull(persistedVariation.getId());
		}
	}
	
	
}
