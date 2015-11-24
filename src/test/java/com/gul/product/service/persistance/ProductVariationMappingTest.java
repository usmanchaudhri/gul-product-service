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
import com.gul.product.service.representation.Product;
import com.gul.product.service.representation.ProductVariation;
import com.gul.product.service.representation.Shop;

public class ProductVariationMappingTest {
	
	@Test
	public void test_creating_similar_product_variations() throws SQLException, ConfigurationException, ProvisionException {
		Injector injector = Guice.createInjector(new DbModule());
		PersistedClassDao persistedClassDao = injector.getInstance(PersistedClassDao.class);

		Product product = buildBaseProduct();
		ProductVariation variation1 = new ProductVariation();
		variation1.setColor("blue");
		variation1.setQuantity("5");
		variation1.setSize("x");
		variation1.setProduct(product);
		ProductVariation variation2 = new ProductVariation();
		variation2.setColor("blue");
		variation2.setQuantity("2");
		variation2.setSize("m");
		variation2.setProduct(product);

		List<ProductVariation> variations = new ArrayList<ProductVariation>();
		variations.add(variation1);
		variations.add(variation2);
		
		product.setProductVariation(variations);
		
		persistedClassDao.saveInNewTransaction(product.getCategory());					// save category first since it will be needed for the product association
		persistedClassDao.saveInNewTransaction(product);

		Product retrievedProduct = persistedClassDao.getEntityManager().find(Product.class, product.getId());
		Assert.assertNotNull(retrievedProduct.getId());

		List<ProductVariation> retrievedVariations =  retrievedProduct.getProductVariation();
		for(ProductVariation retrievedVariation : retrievedVariations) {
			Assert.assertNotNull(retrievedVariation.getId());
		}
	}
	
	@Test
	public void test_creating_nonsimilar_product_variations() throws SQLException, ConfigurationException, ProvisionException {
		Injector injector = Guice.createInjector(new DbModule());
		PersistedClassDao persistedClassDao = injector.getInstance(PersistedClassDao.class);

		Product product = buildBaseProduct();
		ProductVariation variation1 = new ProductVariation();
		variation1.setColor("blue");
		variation1.setSize("x");
		variation1.setProduct(product);
		ProductVariation variation2 = new ProductVariation();
		variation2.setColor("red");
		variation2.setQuantity("2");
		variation2.setSize("m");
		variation2.setProduct(product);
		
		List<ProductVariation> variations = new ArrayList<ProductVariation>();
		variations.add(variation1);
		variations.add(variation2);
		product.setProductVariation(variations);
		
		persistedClassDao.saveInNewTransaction(product.getCategory());					// save category first since it will be needed for the product association
		persistedClassDao.saveInNewTransaction(product);

		Product retrievedProduct = persistedClassDao.getEntityManager().find(Product.class, product.getId());
		Assert.assertNotNull(retrievedProduct.getId());

		List<ProductVariation> retrievedVariations =  retrievedProduct.getProductVariation();
		for(ProductVariation retrievedVariation : retrievedVariations) {
			Assert.assertNotNull(retrievedVariation.getId());
			if(retrievedVariation.getSize().equalsIgnoreCase("x")) {
				Assert.assertNull(retrievedVariation.getQuantity());
			}
		}
	}
	
	private Product buildBaseProduct() {
		Shop shop = new Shop("gulgs");
		Product product = new Product(); 
		product.setName("Fancy Skirt");
		product.setSku("SKU_100_SKIRT");
		product.setShortDesc("Short description women skirt");
		product.setLongDesc("Long description women skirt");
		product.setImagePath("/winter/2015/women/Skirt");
		product.setQuantity(10L);
		
		Category category = new Category("1001", "Women");
		product.setCategory(category);
		product.setShop(shop);
		return product;
	}
	
	
}
