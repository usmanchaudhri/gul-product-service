package com.gul.product.service.persistance;

import io.dropwizard.hibernate.AbstractDAO;
import java.util.List;
import org.hibernate.SessionFactory;
import com.gul.product.service.representation.CustomerShipping;

public class CustomerShippingDao extends AbstractDAO<CustomerShipping> {

	public CustomerShippingDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	public CustomerShipping create(CustomerShipping customerShipping) {
		return persist(customerShipping);
	}
	
	public CustomerShipping findById(Long id) {
		return get(id);
	}
	
	public List<CustomerShipping> findAll() {
		return list(namedQuery("com.gul.checkout.service.representation.Customer.findAll"));
	}


}
