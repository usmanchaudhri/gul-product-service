package com.gul.product.service.persistance;

import io.dropwizard.hibernate.AbstractDAO;
import java.util.List;
import org.hibernate.SessionFactory;
import com.gul.product.service.representation.Product;

// dao class
public class ProductDao extends AbstractDAO<Product> {

	public ProductDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public Product create(Product product) {
		return persist(product);
	}
	
	public Product findById(Long id) {
		return get(id);
	}
	
	public List<Product> findAll() {
		return list(namedQuery("com.gul.product.service.representation.Product.findAll"));
	}

	public List<Product> findProductsByCategory(String categoryId) {
		return list(namedQuery("com.gul.product.service.representation.Product.findProductsByCategory").setParameter("categoryId", Long.valueOf(categoryId)));
	}
	
}
