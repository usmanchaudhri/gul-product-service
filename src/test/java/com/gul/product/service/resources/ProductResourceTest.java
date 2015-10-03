package com.gul.product.service.resources;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.Test;
import org.junit.ClassRule;
import com.gul.product.service.persistance.ProductDao;
import com.gul.product.service.representation.Product;

/**
 *	these tests are at the REST level 
 **/
public class ProductResourceTest {

	private static ProductDao dao = mock(ProductDao.class);

	@ClassRule
	public static final ResourceTestRule resources = ResourceTestRule.builder().addResource(new ProductResource(dao)).build();
	
	@Test
	public void testGetProduct() {
		Product product = new Product(1L, "abc131", "test product",
				"this is a test product", "test product long description",
				"/2015/winter/fall/sep/15/scarf");
		when(dao.findById(10L)).thenReturn(product);
		assertThat(resources.client().target("/product/10").request().get(Product.class)).isEqualTo(product);
		verify(dao).findById(10L);
	}

}
