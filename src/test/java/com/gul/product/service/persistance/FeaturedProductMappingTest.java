package com.gul.product.service.persistance;

import org.junit.Assert;
import org.junit.Test;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.gul.product.service.representation.Category;
import com.gul.product.service.representation.FeaturedProduct;
import com.gul.product.service.representation.Product;
import com.gul.product.service.representation.Shop;

public class FeaturedProductMappingTest {

	@Test
	public void test_tag_product_as_featured_product() {
		Injector injector = Guice.createInjector(new DbModule());
		PersistedClassDao persistedClassDao = injector.getInstance(PersistedClassDao.class);

		Shop shop = new Shop("Gulgs");
		Product product = new Product(); 
		product.setName("Women Skirt");
		product.setSku("SKU_SKIRT_101");
		product.setShortDesc("Short Description Women Skirt");
		product.setLongDesc("Long Description Women Skirt");
		product.setQuantity(10L);
		
		Category category = new Category("1001", "Women");
		product.setCategory(category);
		product.setShop(shop);

		FeaturedProduct featuredProduct = new FeaturedProduct();
		product.setFeaturedProduct(featuredProduct);
		
		persistedClassDao.saveInNewTransaction(category);					// save category first since it will be needed for the product association
		persistedClassDao.saveInNewTransaction(product);
		
		Product retrievedProduct = persistedClassDao.getEntityManager().find(Product.class, product.getId());
		Shop retrievedShop = persistedClassDao.getEntityManager().find(Shop.class, shop.getId());
		FeaturedProduct retrievedFeaturedProduct = persistedClassDao.getEntityManager().find(FeaturedProduct.class, featuredProduct.getId());
		
		Assert.assertNotNull(retrievedProduct.getId());
		Assert.assertNotNull(retrievedShop.getId());
		Assert.assertNotNull(retrievedFeaturedProduct.getId());
		
	}
	
	
	
}
