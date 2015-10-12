package com.gul.product.service.persistance;

import java.util.List;
import org.hibernate.SessionFactory;
import com.gul.product.service.representation.Category;
import io.dropwizard.hibernate.AbstractDAO;

public class CategoryDao extends AbstractDAO<Category> {

	public CategoryDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public Category create(Category category) {
		return persist(category);
	}
	
	public Category findById(Long id) {
		return get(id);
	}
	
	// laods product lazily
	public Category findByIdLoadProducts(Long id) {
		Category category = get(id);
		initialize(category.getProducts());
		return category;
	}
	
	public List<Category> findAll() {
		return list(namedQuery("com.gul.product.service.representation.Category.findAll"));
	}

}
