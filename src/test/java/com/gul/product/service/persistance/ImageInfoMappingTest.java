package com.gul.product.service.persistance;

import org.junit.Assert;
import org.junit.Test;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.gul.product.service.representation.Category;
import com.gul.product.service.representation.ImageInfo;
import com.gul.product.service.representation.Product;
import com.gul.product.service.representation.Shop;

public class ImageInfoMappingTest {

	@Test
	public void test_creating_image_path_after_product_creation() {
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
		
		persistedClassDao.saveInNewTransaction(category);
		persistedClassDao.saveInNewTransaction(product);
		
		ImageInfo imageInfo = new ImageInfo();
		imageInfo.setImagePath("/listings/20987645");

		product.setImageInfo(imageInfo);
		persistedClassDao.saveInNewTransaction(product);
		
		Product persistedProduct = persistedClassDao.getEntityManager().find(Product.class, product.getId());
		Assert.assertNotNull(persistedProduct.getId());
		Assert.assertNotNull(persistedProduct.getImageInfo().getId());
		Assert.assertNotNull(persistedProduct.getImageInfo().getImagePath().equalsIgnoreCase("/listings/20987645"));
	}
	
	
}
