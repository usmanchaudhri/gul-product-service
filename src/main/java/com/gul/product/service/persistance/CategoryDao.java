package com.gul.product.service.persistance;

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
	
	public Category findById(Integer id) {
		return get(id);
	}

}
