package com.gul.product.service.persistance;

import io.dropwizard.hibernate.AbstractDAO;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import com.gul.product.service.representation.CustomerShipping;

public class CustomerShippingDao extends AbstractDAO<CustomerShipping> {

	public CustomerShippingDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	public CustomerShipping create(CustomerShipping customerShipping) {
		customerShipping.setCreatedOn(new Date());
		return persist(customerShipping);
	}
	
	public CustomerShipping findById(Long id) {
		return get(id);
	}
	
	public List<CustomerShipping> findUserShippingAddresses(Long customerId) {
		return list(namedQuery("com.gul.product.service.representation.CustomerShipping.findUserShippingAddresses"));
	}
	
	public List<CustomerShipping> findAll() {
		return list(namedQuery("com.gul.checkout.service.representation.Customer.findAll"));
	}


}
