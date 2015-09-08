package com.gul.product.service.persistance;

import io.dropwizard.hibernate.AbstractDAO;
import java.util.List;
import org.hibernate.SessionFactory;
import com.gul.product.service.representation.Product;

public class ProductDao extends AbstractDAO<Product> {

	public ProductDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public long create(Product product) {
		return persist(product).getId();
	}
	
	public Product findById(Integer id) {
		return get(id);
	}
	
	public List<Product> findAll() {
		return list(namedQuery("com.gul.product.service.representation.Product.findAll"));
	}
	
}
