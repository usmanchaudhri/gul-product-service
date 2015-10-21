package com.gul.product.service.resources;

import static org.mockito.Mockito.mock;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.ClassRule;
import org.junit.Test;
import com.gul.product.service.persistance.CategoryDao;
import com.gul.product.service.representation.Category;

public class CategoryResourceTest {

	private static CategoryDao dao = mock(CategoryDao.class);

	@ClassRule
	public static final ResourceTestRule resources = ResourceTestRule.builder().addResource(new CategoryResource(dao)).build();

	@Test
	public void testCreateCategory() {
		Category category = new Category();
	}
	
}
