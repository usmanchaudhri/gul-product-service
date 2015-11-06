package com.gul.product.service.resources;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import io.dropwizard.testing.junit.ResourceTestRule;
import javax.ws.rs.client.Entity;
import org.junit.After;
import org.junit.ClassRule;
import org.junit.Test;
import com.gul.product.service.persistance.ProductDao;
import com.gul.product.service.representation.Category;
import com.gul.product.service.representation.PricingProduct;
import com.gul.product.service.representation.Product;
import static org.mockito.Mockito.*;

/**
 * these tests are at the REST level
 **/
public class ProductResourceTest {

	private static ProductDao dao = mock(ProductDao.class);

	@ClassRule
	public static final ResourceTestRule resources = ResourceTestRule.builder().addResource(new ProductResource(dao)).build();
	
	@Test
	public void testGetProduct() {
		Product product = new Product(
				"abc131", 
				"test product",
				"this is a test product", 
				"test product long description",
				"/2015/winter/fall/sep/15/scarf");
		
		when(dao.findById(10L)).thenReturn(product);
		assertThat(resources.client().target("/product/10").request().get(Product.class)).isEqualTo(product);
		verify(dao).findById(10L);
	}
	
	@Test
	public void testProductCreation() {
		Product product = new Product(
				"Cloth_1001", 
				"Embroided Skirt",
				"Handmade embroidreded skirt", 
				"Pakistani cultural Skirt, hand embroidery",
				"/winter/2015");
		Category category = new Category("1001", "Sub Girls Clothing");
		product.setCategory(category);
		PricingProduct pricingProduct = new PricingProduct(50.98);
		product.setPricingProduct(pricingProduct);

		when(dao.create(product)).thenReturn(product);		
		assertThat(resources.client().target("/product").request().post(Entity.json(product), Product.class)).isEqualTo(product);
		verify(dao).create(product);
	}
	
	@Test
	public void testGetCategoryWithoutProducts() {
		Product product = new Product(
				"Cloth_1001", 
				"Embroided Skirt",
				"Handmade embroidreded skirt", 
				"Pakistani cultural Skirt, hand embroidery",
				"/winter/2015");
		Category category = new Category("1001", "Sub Girls Clothing");
		product.setCategory(category);
		PricingProduct pricingProduct = new PricingProduct(50.98);
		product.setPricingProduct(pricingProduct);
		
		when(dao.create(product)).thenReturn(product);
		assertThat(resources.client().target("/product").request().post(Entity.json(product), Product.class)).isEqualTo(product);
		verify(dao).create(product);
	}
	
	@After
    public void tearDown() {
        // we have to reset the mock after each test because of the
        // @ClassRule, or use a @Rule as mentioned below.
        reset(dao);
    }

	
}
